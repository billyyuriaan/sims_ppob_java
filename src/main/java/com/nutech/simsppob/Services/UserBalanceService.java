/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.simsppob.Entitys.Transaction;
import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.UserBalanceRepository;

import jakarta.transaction.Transactional;

/**
 *
 * @author iolux
 */
@Service
public class UserBalanceService {
    @Autowired
    private UserBalanceRepository userBalanceRepository;

    @Transactional
    public void initUserBalance(User user) throws Exception
    {
        UserBalance userBalance = new UserBalance();

        // everytime new user is created, the balance is set to zero
        userBalance.setUser(user);
        userBalance.setBalance(BigDecimal.ZERO);
        userBalance.setCreatedAt(LocalDateTime.now());
        userBalance.setUpdatedAt(LocalDateTime.now());

        this.userBalanceRepository.save(userBalance);
    }

    public UserBalance getUserBalanceByUserEmailFromAuth(
        User user
    ) throws Exception
    {   
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
        User user,
        Transaction transaction
    ) throws Exception
    {
        UserBalance balance = this.getUserBalanceByUserEmailFromAuth(user);

        this.userBalanceRepository.updateUserBalanceByUserId(
            balance.getBalance().add(transaction.getAmount()),
            balance.getUser().getId()
        );
    }

    @Transactional
    public void TransactionUserCurrentBalance(
        User user,
        Transaction transaction
    ) throws Exception
    {
        UserBalance balance = this.getUserBalanceByUserEmailFromAuth(user);

        this.userBalanceRepository.updateUserBalanceByUserId(
            balance.getBalance().subtract(transaction.getAmount()),
            balance.getUser().getId()
        );
    }
}
