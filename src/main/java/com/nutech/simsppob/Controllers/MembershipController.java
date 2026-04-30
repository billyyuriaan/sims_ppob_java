/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Controllers;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Repositorys.UserRepository;
import com.nutech.simsppob.Security.JwtUtil;
import com.nutech.simsppob.Services.FileService;
import com.nutech.simsppob.Services.UserBalanceService;
import com.nutech.simsppob.dto.LoginRequest;
import com.nutech.simsppob.dto.ProfileResponse;
import com.nutech.simsppob.dto.ProfileUpdateRequest;
import com.nutech.simsppob.dto.RegisterRequest;
import com.nutech.simsppob.dto.ResponseJsonFormat;

import jakarta.validation.Valid;

/**
 *
 * @author billyyuriaan
 */
@RestController
public class MembershipController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserBalanceService userBalanceService;

  @Autowired
  private FileService fileService;

  @GetMapping("/profile")
  public ResponseEntity<ResponseJsonFormat> getUserProfile(
      Authentication auth) {
    ResponseJsonFormat res = new ResponseJsonFormat();

    try {
      User user = (User) auth.getPrincipal();

      res.put("status", 0);
      res.put("message", "Sukses");
      res.put("data", new ProfileResponse(user));

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      res.put("status", 108);
      res.put("message", e.getMessage());
      res.put("data", null);

      return ResponseEntity.badRequest().body(res);
    }
  }

  @PutMapping("/profile/update")
  public ResponseEntity<ResponseJsonFormat> updateUserProfile(
      @Valid @RequestBody ProfileUpdateRequest request,
      Authentication auth) {
    ResponseJsonFormat res = new ResponseJsonFormat();

    try {
      User user = (User) auth.getPrincipal();

      this.userRepository.updateUserProfileData(
          user.getId(),
          request.getFirstName(),
          request.getLastName());

      user.setFirstName(request.getFirstName());
      user.setLastName(request.getLastName());

      res.put("status", 0);
      res.put("message", "Update Profile berhasil");
      res.put("data", new ProfileResponse(user));

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      res.put("status", 102);
      res.put("message", e.getMessage());
      res.put("data", null);

      return ResponseEntity.badRequest().body(res);
    }
  }

  @PutMapping("/profile/image")
  public ResponseEntity<ResponseJsonFormat> uploadUserProfileImage(
      @RequestParam("file") MultipartFile file,
      Authentication auth) {
    ResponseJsonFormat res = new ResponseJsonFormat();

    try {
      User user = (User) auth.getPrincipal();
      String contentType = file.getContentType();

      if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
        throw new Exception("Format Image tidak sesuai");
      }

      String url = this.fileService.upload(file);

      this.userRepository.updateUserProfileImage(user.getId(), url);

      user.setProfileImage(url);

      res.put("status", 0);
      res.put("message", "Update Profile Image berhasil");
      res.put("data", new ProfileResponse(user));

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      res.put("status", 102);
      res.put("message", e.getMessage());
      res.put("data", null);

      return ResponseEntity.badRequest().body(res);
    }
  }

  @PostMapping("/registration")
  public ResponseEntity<ResponseJsonFormat> postUserRegistration(
      @Valid @RequestBody RegisterRequest request) {
    ResponseJsonFormat res = new ResponseJsonFormat();

    try {
      if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new Exception("Akun telah ada, silahkan login");
      }

      User user = new User();

      user.setEmail(request.getEmail());
      user.setFirstName(request.getFirstName());
      user.setLastName(request.getLastName());
      user.setCreatedAt(LocalDateTime.now());
      user.setUpdatedAt(LocalDateTime.now());
      user.setPassword(passwordEncoder.encode(request.getPassword()));

      user = this.userRepository.save(user);

      this.userBalanceService.initUserBalance(user);

      res.put("status", 0);
      res.put("message", "registrasi berhasil silahkan login");
      res.put("data", null);

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      res.put("status", 102);
      res.put("message", e.getMessage());
      res.put("data", null);

      return ResponseEntity.badRequest().body(res);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseJsonFormat> postUserLogin(
      @Valid @RequestBody LoginRequest request) {
    ResponseJsonFormat res = new ResponseJsonFormat();

    try {
      User user = userRepository.findByEmail(request.getEmail())
          .orElseThrow(() -> new Exception("username atau password salah"));

      if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Wrong password");
      }

      res.put("status", 0);
      res.put("message", "Login Sukses");
      res.put("data", new HashMap<>() {
        {
          put("token", JwtUtil.generateToken(user.getEmail()));
        }
      });

      return ResponseEntity.ok(res);
    } catch (Exception e) {
      res.put("status", 102);
      res.put("message", e.getMessage());
      res.put("data", null);

      return ResponseEntity.badRequest().body(res);
    }
  }
}
