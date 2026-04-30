/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.Entitys.Transaction;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class TranasctionHistoryResponse {
    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("transaction_type")
    private String transactionType;

    private String description;

    @JsonProperty("total_amount")
    private BigDecimal amount;

    @JsonProperty("created_on")
    private LocalDateTime createdOn;

    public TranasctionHistoryResponse(
        String invoiceNumber,
        String transactionType,
        String description,
        BigDecimal amount,
        LocalDateTime createdOn
    )
    {
        this.invoiceNumber = invoiceNumber;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.createdOn = createdOn;
    }
}
