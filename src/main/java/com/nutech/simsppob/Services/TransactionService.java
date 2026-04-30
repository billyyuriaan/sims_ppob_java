/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutech.simsppob.Entitys.Service;
import com.nutech.simsppob.Entitys.Transaction;
import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Entitys.UserBalance;
import com.nutech.simsppob.Repositorys.TransactionRepository;
import com.nutech.simsppob.dto.TranasctionHistoryResponse;

import jakarta.transaction.Transactional;

/**
 *
 * @author iolux
 */
@org.springframework.stereotype.Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserBalanceService userBalanceService;

    @Transactional
    public Transaction makeTopUpTransaction(
        User user,
        Integer amount
    )
    {
            Transaction transaction = new Transaction();

            transaction.setInvoiceNumber("INV-" + UUID.randomUUID());
            transaction.setTransactionType("TOPUP");
            transaction.setDescription("Top Up Balance");
            transaction.setUser(user);
            transaction.setAmount(BigDecimal.valueOf(amount));
            transaction.setUpdatedAt(LocalDateTime.now());
            transaction.setCreatedAt(LocalDateTime.now());

            return this.transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction makeUserTransaction(
        User user,
        Service service
    ) throws Exception
    {
        UserBalance balance = this.userBalanceService.getUserBalanceByUserEmailFromAuth(user);

        if (balance.getBalance().subtract(service.getTarrif()).compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("Saldo user tidak mencukupi untuk transaksi");
        }

        Transaction transaction = new Transaction();

        transaction.setInvoiceNumber("INV-" + UUID.randomUUID());
        transaction.setTransactionType("PAYMENT");
        transaction.setDescription(service.getDescription());
        transaction.setUser(user);
        transaction.setAmount(service.getTarrif());
        transaction.setUpdatedAt(LocalDateTime.now());
        transaction.setCreatedAt(LocalDateTime.now());
        
        transaction = this.transactionRepository.save(transaction);
        this.userBalanceService.TransactionUserCurrentBalance(user, transaction);

        return transaction;
    }

    public List<TranasctionHistoryResponse> getHistory(User user, Integer limit, Integer offset) {
        List<Transaction> transactions;

        if (limit == null && offset == null) {
            transactions = transactionRepository.getAllHistoryUserByUserId(user.getId());
        } else {
            transactions = transactionRepository.getAllHistoryUserByUserId(user.getId(), limit, offset);
        }

        return transactions.stream()
            .map(t -> new TranasctionHistoryResponse(
                t.getInvoiceNumber(),
                t.getTransactionType(),
                t.getDescription(),
                t.getAmount(),
                t.getCreatedAt()
            ))
            .toList();
    }
}
