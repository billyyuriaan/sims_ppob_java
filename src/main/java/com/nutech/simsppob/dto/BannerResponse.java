/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.Entitys.Banner;

import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author iolux
 */
@ToString
@Getter
public class BannerResponse {
    @JsonProperty("banner_name")
    private String bannerName;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String description;

    public BannerResponse(Banner banner){
        this.bannerName = banner.getBannerName();
        this.bannerImage = banner.getBannerImage();
        this.description = banner.getDescription();
    }
}
