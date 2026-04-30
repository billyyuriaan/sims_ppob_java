/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class TransactionHistoryPaginationResponse {
    private Integer offset;

    private Integer limit;

    private List<TranasctionHistoryResponse> records;

    public TransactionHistoryPaginationResponse(
        Integer offset,
        Integer limit,
        List<TranasctionHistoryResponse> records
    )
    {
        this.offset = offset;
        this.limit = limit;
        this.records = records;
    }
}
