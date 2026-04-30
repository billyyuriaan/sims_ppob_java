/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Repositorys.TransactionRepository;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class TransactionHistoryPaginationResponse {
    @Autowired
    private TransactionRepository transactionRepository;
    
    private Integer offset;

    private Integer limit;

    private List<TranasctionHistoryResponse> records;

    public TransactionHistoryPaginationResponse(
        Integer offset,
        Integer limit,
        User user
    )
    {
        this.offset = offset;
        this.limit = limit;

        if (limit == null && offset == null) {
            this.records = this.transactionRepository
                .getAllHistoryUserByUserId(
                    user.getId()
                )
                .stream()
                .map(transaction -> new TranasctionHistoryResponse(
                    transaction.getInvoiceNumber(),
                    transaction.getTransactionType(),
                    transaction.getDescription(),
                    transaction.getAmount(),
                    transaction.getCreatedAt()
                ))
                .toList();            
        }else{
            this.records = this.transactionRepository
                .getAllHistoryUserByUserId(
                    user.getId(),
                    limit,
                    offset
                )
                .stream()
                .map(transaction -> new TranasctionHistoryResponse(
                    transaction.getInvoiceNumber(),
                    transaction.getTransactionType(),
                    transaction.getDescription(),
                    transaction.getAmount(),
                    transaction.getCreatedAt()
                ))
                .toList();
        }

    }
}
