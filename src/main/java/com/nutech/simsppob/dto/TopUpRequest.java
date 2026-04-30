/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class TopUpRequest {
    @JsonProperty("top_up_amount")
    @NotBlank(message="Parameter top_up_amount tidak boleh kosong")
    @NotNull(message="Parameter top_up_amount tidak boleh kosong")
    @Positive
    private int topUpAmount;
}
