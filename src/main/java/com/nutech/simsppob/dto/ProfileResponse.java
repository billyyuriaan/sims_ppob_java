/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.Entitys.User;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author iolux
 */
@Getter
@ToString
public class ProfileResponse {
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("profile_image")
    private String profileImage;

    public ProfileResponse(User user)
    {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.profileImage = user.getProfileImage();
    }
}
