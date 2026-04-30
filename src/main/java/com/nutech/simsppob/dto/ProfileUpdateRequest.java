/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class ProfileUpdateRequest {
    @JsonProperty("first_name")
    @NotBlank(message="Parameter first_name tidak boleh kosong")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message="Parameter last_name tidak boleh kosong")
    private String lastName;
}
