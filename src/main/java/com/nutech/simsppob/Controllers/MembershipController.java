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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.UserRepository;
import com.nutech.simsppob.Security.JwtUtil;
import com.nutech.simsppob.dto.ProfileResponse;
import com.nutech.simsppob.dto.RegisterRequest;
import com.nutech.simsppob.dto.ResponseJsonFormat;

import jakarta.validation.Valid;

/**
 *
 * @author billyyuriaan
 */
@RestController
@Controller
public class MembershipController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("profile")
    public ResponseEntity<ResponseJsonFormat> getUserProfile(
        Authentication auth
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            User user = userRepository.findByEmail(auth.getName()).orElse(null);

            if (user == null) {
                throw new Exception("Token tidak valid atau kadarluasa");
            }

            res.put("status", 0);
            res.put("message", "Sukses");
            res.put("data", new ProfileResponse(user));

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 108);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.status(401).body(res);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<ResponseJsonFormat> postUserRegistration(
        @Valid @RequestBody RegisterRequest request
    ) {
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

            User saveUser = userRepository.save(user);
            UserBalance userBalance = new UserBalance();

            userBalance.setUserId(saveUser.getId());
            userBalance.setCreatedAt(LocalDateTime.now());
            userBalance.setUpdatedAt(LocalDateTime.now());

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
        @Valid @RequestBody RegisterRequest request
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new Exception("username atau password salah"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new RuntimeException("Wrong password");
            }
            
            res.put("status", 0);
            res.put("message", "Login Sukses");
            res.put("data", new HashMap<>(){{
                put("token", JwtUtil.generateToken(user.getEmail()));
            }});

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.badRequest().body(res);
        }
    }
}
