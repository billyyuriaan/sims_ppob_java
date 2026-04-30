/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.math.BigDecimal;

import com.nutech.simsppob.Entitys.UserBalance;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author iolux
 */
@Getter
@ToString
public class UserBalanceResponse {
    private BigDecimal balance;

    public UserBalanceResponse(UserBalance userBalance){
        this.balance = userBalance.getBalance();
    }
}
