/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author iolux
 */
@Setter
@Getter
public class LoginRequest {
    @Email(message="Parameter email tidak sesuai format")
    @NotBlank(message="Parameter email tidak boleh kosong")
    public String email;

    @NotBlank(message="Parameter password tidak boleh kosong")
    @Size(min=8, message="Parameter password minimal 8 karakter")
    public String password;
}
