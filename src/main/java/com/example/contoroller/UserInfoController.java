package com.example.contoroller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dtos.UserInfo;
import com.example.security.JWTAuthenticationToken;
import com.example.service.UserInfoService;

@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/api/getUser")
    public UserInfo getUserInfo(@AuthenticationPrincipal JWTAuthenticationToken.AuthenticationUser token) {

        if (token == null) {
            return null;
        }
        return userInfoService.getUserInfo(UUID.fromString(token.id()));
    }

}
