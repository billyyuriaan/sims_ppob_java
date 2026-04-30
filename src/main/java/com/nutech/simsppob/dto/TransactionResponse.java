/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.Entitys.Service;
import com.nutech.simsppob.Entitys.Transaction;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class TransactionResponse {
    @JsonProperty("invoice_number")
    private String invoiceNumber;

    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("total_amount")
    private BigDecimal amount;

    @JsonProperty("created_on")
    private LocalDateTime createdOn;

    public TransactionResponse(
        Transaction transaction,
        Service service
    )
    {
        this.invoiceNumber = transaction.getInvoiceNumber();
        this.serviceCode = service.getServiceType().getCode();
        this.serviceName = service.getName();
        this.transactionType = transaction.getTransactionType();
        this.amount = transaction.getAmount();
        this.createdOn = LocalDateTime.now();
    }

}
