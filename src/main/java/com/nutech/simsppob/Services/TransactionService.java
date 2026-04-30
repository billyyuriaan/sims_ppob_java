/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutech.simsppob.Entitys.Service;
import com.nutech.simsppob.Entitys.Transaction;
import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.TransactionRepository;

/**
 *
 * @author iolux
 */
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction makeTopUpTransaction(
        User user,
        int amount
    )
    {
            Transaction transaction = new Transaction();

            transaction.setTransactionType("TOPUP");
            transaction.setDescription("Top Up Balance");
            transaction.setUserId(user.getId());
            transaction.setAmount(BigDecimal.valueOf(amount));
            transaction.setUpdatedAt(LocalDateTime.now());
            transaction.setCreatedAt(LocalDateTime.now());

            return this.transactionRepository.save(transaction);
    }

    public Transaction makeUserTransaction(
        User user,
        Service service
    ) throws Exception
    {
        UserBalance balance = new UserBalanceService().getUserBalanceByUserEmailFromAuth(user.getEmail());

        if (balance.getBalance().subtract(service.getTarrif()).compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Saldo user tidak mencukupi untuk transaksi");
        }

        Transaction transaction = new Transaction();

        transaction.setTransactionType("PAYMENT");
        transaction.setDescription(service.getDescription());
        transaction.setUserId(user.getId());
        transaction.setAmount(service.getTarrif());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setCreatedAt(LocalDateTime.now());
        
        transaction = this.transactionRepository.save(transaction);
        new UserBalanceService().TransactionUserCurrentBalance(user.getEmail(), transaction);

        return transaction;
    }
}
