package com.example.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.val;

/**
 * イメージをローカルストレージで保存するサービス
 *
 * @author char5742
 */
@Service
public class ImageService {

  /**
   * イメージをローカルストレージに保存する
   * 
   * <pre/>
   * '/images'ディレクトリにイメージを保存すると/img/からアクセスできます
   * 
   * @param uploadDirectory 保存先ディレクトリ
   * @param imageFile       保存するイメージ
   * @return 保存したイメージのファイル名
   * @throws IOException
   */
  public String saveImageToStorage(String uploadDirectory, MultipartFile imageFile)
      throws IOException {
    val split = Arrays.stream(imageFile.getOriginalFilename().split("\\.")).toList();
    val extension = split.get(split.size() - 1);
    val uniqueFileName = UUID.randomUUID().toString() + "." + extension;

    val uploadPath = Path.of(uploadDirectory);
    val filePath = uploadPath.resolve(uniqueFileName);
    if (!Files.exists(uploadPath)) {
      Files.createDirectories(uploadPath);
    }
    Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

    return uniqueFileName;
  }

  /**
   * イメージをローカルストレージから取得する
   * 
   * @param imageDirectory 保存先ディレクトリ
   * @param imageName      取得するイメージのファイル名
   * @return イメージのバイト配列
   */
  public byte[] getImage(String imageDirectory, String imageName) throws IOException {
    Path imagePath = Path.of(imageDirectory, imageName);
    if (Files.exists(imagePath)) {
      byte[] imageBytes = Files.readAllBytes(imagePath);
      return imageBytes;
    } else {
      return null;
    }
  }

}