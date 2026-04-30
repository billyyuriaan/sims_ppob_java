/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Getter
@Setter
public class TransactionRequest {
    @JsonProperty("service_code")
    @NotBlank(message="Parameter service_code tidak boleh kosong")
    @NotNull(message="Parameter service_code tidak boleh kosong")
    private String serviceCode;
}
