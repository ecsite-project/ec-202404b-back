package com.example.contoroller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.ImageService;

import lombok.val;

/**
 * ストレージを扱うコントローラクラス.
 * 
 * @author char5742
 */
@CrossOrigin
@RestController
public class SourceController {

  @Autowired
  private ImageService imageService;

  /**
   * 画像を取得する
   * 
   * @param name 画像名
   * @return 画像のバイト配列
   * @throws IOException
   */
  @GetMapping(value = "/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
  public byte[] img(@PathVariable(name = "name") String name) throws IOException {
    val imageDirectory = "images/";
    return imageService.getImage(imageDirectory, name);

  }

}