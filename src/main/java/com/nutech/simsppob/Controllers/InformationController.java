/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Controllers;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.simsppob.Repositorys.BannerRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nutech.simsppob.Repositorys.ServiceRepository;
import com.nutech.simsppob.dto.BannerResponse;
import com.nutech.simsppob.dto.ResponseJsonFormat;
import com.nutech.simsppob.dto.ServiceResponse;


/**
 *
 * @author iolux
 */
@RestController
@Controller
public class InformationController {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @GetMapping("/banner")
    public ResponseEntity<ResponseJsonFormat> getAllBanners(@RequestParam String param) {
        ResponseJsonFormat res = new ResponseJsonFormat();
    
        try {
            List<BannerResponse> banner = this.bannerRepository
                .getAllBanner()
                .stream()
                .map(BannerResponse::new)
                .toList();

            res.put("status", 0);
            res.put("message", "Sukses");
            res.put("data", banner);
            
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.badRequest().body(res);
        }
    }

    @GetMapping("/services")
    public ResponseEntity<ResponseJsonFormat> getAllServices(@RequestParam String param) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            List<ServiceResponse> services = this.serviceRepository
                .getAllServices()
                .stream()
                .map(ServiceResponse::new)
                .toList();

            res.put("status", 0);
            res.put("message", "Sukses");
            res.put("data", services);

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.badRequest().body(res);
        }
    }
    
    
}
