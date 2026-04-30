/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutech.simsppob.Entitys.Transaction;
import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.UserBalanceRepository;
import com.nutech.simsppob.Repositorys.UserRepository;

import jakarta.transaction.Transactional;

/**
 *
 * @author iolux
 */
public class UserBalanceService {
    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Autowired
    private UserRepository userRepository;

    public UserBalance getUserBalanceByUserEmailFromAuth(
        String email
    ) throws Exception
    {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new Exception("Token tidak valid atau kadarluasa");
        }
        
        UserBalance balance = this.userBalanceRepository
            .getUserBalanceByUserId(user.getId())
            .orElse(null);

        if (balance == null) {
            throw new Exception("saldo user tidak ditemukan");
        }

        return balance;
    }

    @Transactional
    public void TopUpUserCurrentBalance(
        String email,
        Transaction transaction
    ) throws Exception
    {
        UserBalance balance = this.getUserBalanceByUserEmailFromAuth(email);

        this.userBalanceRepository.updateUserBalanceByUserId(
            balance.getBalance().add(transaction.getAmount()),
            balance.getUserId()
        );
    }

    @Transactional
    public void TransactionUserCurrentBalance(
        String email,
        Transaction transaction
    ) throws Exception
    {
        UserBalance balance = this.getUserBalanceByUserEmailFromAuth(email);

        this.userBalanceRepository.updateUserBalanceByUserId(
            balance.getBalance().subtract(transaction.getAmount()),
            balance.getUserId()
        );
    }
}
