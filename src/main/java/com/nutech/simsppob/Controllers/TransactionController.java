/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Controllers;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nutech.simsppob.Entitys.Service;
import com.nutech.simsppob.Entitys.Transaction;
import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.ServiceRepository;
import com.nutech.simsppob.Services.TransactionService;
import com.nutech.simsppob.Services.UserBalanceService;
import com.nutech.simsppob.Services.UserService;
import com.nutech.simsppob.dto.ResponseJsonFormat;
import com.nutech.simsppob.dto.TopUpRequest;
import com.nutech.simsppob.dto.TransactionHistoryPaginationResponse;
import com.nutech.simsppob.dto.TransactionRequest;
import com.nutech.simsppob.dto.TransactionResponse;
import com.nutech.simsppob.dto.UserBalanceResponse;

import jakarta.validation.Valid;



/**
 *
 * @author iolux
 */
@RestController
@Controller
public class TransactionController {
    @Autowired
    private ServiceRepository serviceRepository;
    
    @GetMapping("/balance")
    public ResponseEntity<ResponseJsonFormat> getUserBalance(
        Authentication auth
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            UserBalance balance = new UserBalanceService()
                .getUserBalanceByUserEmailFromAuth(auth.getName());

            res.put("status", 0);
            res.put("message", "Get Balance Berhasil");
            res.put("data", new UserBalanceResponse(balance));
            
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("/topup")
    public ResponseEntity<ResponseJsonFormat> postTopUpUserBalance(
        @Valid @RequestBody TopUpRequest request,
        Authentication auth
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            User user = new UserService().getUserProfileByEmail(auth.getName());

            Transaction transaction = new TransactionService().makeTopUpTransaction(user, request.getTopUpAmount());
            new UserBalanceService().TopUpUserCurrentBalance(user.getEmail(), transaction);

            UserBalance userBalance = new UserBalanceService().getUserBalanceByUserEmailFromAuth(user.getEmail());

            res.put("status", 0);
            res.put("message", "Top Up Balance Berhasil");
            res.put("data", new UserBalanceResponse(userBalance));

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);

            return ResponseEntity.badRequest().body(res);
        }
    }
    
    @PostMapping("/transaction")
    public ResponseEntity<ResponseJsonFormat> postMethodName(
        @Valid @RequestBody TransactionRequest request,
        Authentication auth
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();

        try {
            Service service = this.serviceRepository
                .getServiceDataByCode(request.getServiceCode())
                .orElse(null);

            if (service == null) {
                throw new Exception("Service atau layanan tidak ditemukan");
            }

            User user = new UserService().getUserProfileByEmail(auth.getName());
            Transaction transaction = new TransactionService().makeUserTransaction(user, service);

            res.put("status", 0);
            res.put("message", "Get Balance Berhasil");
            res.put("data", new TransactionResponse(transaction, service));
            

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", "Transaksi Berhasil");
            res.put("data", null);
            
            return ResponseEntity.badRequest().body(res);
        }
    }

    @PostMapping("/transaction/history")
    public ResponseEntity<ResponseJsonFormat> postMethodName(
        @RequestParam(required=false) Integer limit,
        @RequestParam(required=false) Integer offset,
        Authentication auth
    ) {
        ResponseJsonFormat res = new ResponseJsonFormat();
        TransactionHistoryPaginationResponse data;

        try {
            User user = new UserService().getUserProfileByEmail(auth.getName());
            data = new TransactionHistoryPaginationResponse(offset, limit, user);

            res.put("status", 0);
            res.put("message", "Get History Berhasil");
            res.put("data", data);

            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("status", 102);
            res.put("message", e.getMessage());
            res.put("data", null);
            
            return ResponseEntity.badRequest().body(res);
        }
    }
    
    
}
