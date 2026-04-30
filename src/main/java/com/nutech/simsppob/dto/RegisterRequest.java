/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class RegisterRequest {
  @JsonProperty("email")
  @Email(message = "Parameter email tidak sesuai format")
  @NotBlank(message = "Parameter email tidak boleh kosong")
  private String email;

  @JsonProperty("password")
  @NotBlank(message = "Parameter password tidak boleh kosong")
  @Size(min = 8, message = "Parameter password minimal 8 karakter")
  private String password;

  @JsonProperty("first_name")
  @NotBlank(message = "Parameter first_name tidak boleh kosong")
  private String firstName;

  @JsonProperty("last_name")
  @NotBlank(message = "Parameter last_name tidak boleh kosong")
  private String lastName;
}
