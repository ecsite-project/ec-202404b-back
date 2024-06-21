package com.example.security;

import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.security.JWTAuthenticationToken.AuthenticationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.val;

/**
 * JsonWetTokenでアクセス認証処理群をまとめたクラス.<br>
 * 
 * @char5742
 */
@Component
public class JsonWebTokenUtil {

  private final Logger logger = LoggerFactory.getLogger(JsonWebTokenUtil.class);

  @Value("${jwt.expirationDays}")
  private Integer expirationDays;

  @Value("${jwt.secretKey}")
  private String secretKey;

  /**
   * 認証トークン=JWT（JSON Web Token）の生成.
   *
   * @param id ID
   * @return 認証トークン=JWT（JSON Web Token）
   */
  public String generateToken(String id, String username) {
    val expirationDate = toDate(LocalDateTime.now().plusDays(expirationDays)); // 有効期限

    return Jwts.builder().claims().subject(id).expiration(expirationDate).add(
        "username", username).and()
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
        .compact();
  }

  /**
   * 認証トークン=JWT（JSON Web Token）の解析しIDを返します.<br>
   *
   * @param token 認証トークン=JWT（JSON Web Token）
   * @return ID ID
   * @throws SignatureException ログイン時に発行されたトークン(署名)と違う場合に発生(非検査例外)
   */
  public String getIdFromToken(String token, String key) {
    val jwt = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8)))
        .build().parse(token);
    val payload = (Claims) jwt.getPayload();
    return payload.getSubject();

  }

  /**
   * クライアントからのリクエストの情報から、認証トークンを取得して認可処理を行います.
   *
   * @param request リクエスト情報
   * @return 認可OK:true / 認可NG:false
   */
  public boolean authorize(HttpServletRequest request) {
    // Authorizationの値を取得
    val authorization = request.getHeader("Authorization");
    if (authorization == null || authorization.isEmpty()) {
      return false;
    }
    // Bearer tokenの形式であることをチェック
    if (authorization.indexOf("Bearer ") != 0) {
      return false;
    }

    // Authorizationの最初に付加されている「Bearer 」を除去し、アクセストークンのみ取り出し
    val accessToken = authorization.substring(7);
    logger.debug("accessToken : " + accessToken);
    // トークンからユーザーid(ログインした人のID)を取得
    try {
      val userId = getIdFromToken(accessToken, secretKey);
      logger.debug("userId : " + userId);
      // SecurityContextにログインユーザー情報をセット
      val context = SecurityContextHolder.createEmptyContext();
      val principal = new AuthenticationUser(userId);
      context.setAuthentication(new JWTAuthenticationToken(
          Collections.emptyList(),
          principal));
      SecurityContextHolder.setContext(context);

    } catch (Exception e) {
      // 有効期限切れや適当なトークンだった場合はRuntimeExceptionが発生するため、認可NGにする
      logger.debug("認証エラー", e);
      return false;
    }
    return true;
  }

  /*
   * LocalDateTimeからDateに変換.
   */
  private static Date toDate(LocalDateTime localDateTime) {
    val zone = ZoneId.systemDefault();
    val zonedDateTime = ZonedDateTime.of(localDateTime, zone);
    val instant = zonedDateTime.toInstant();
    return Date.from(instant);
  }

}