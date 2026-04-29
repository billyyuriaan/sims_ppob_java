/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.Entitys.Service;

import lombok.Getter;

/**
 *
 * @author iolux
 */
@Getter
public class ServiceResponse {
    @JsonProperty("service_code")
    private String serviceCode;

    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("service_icon")
    private String serviceIcon;

    @JsonProperty("service_tariff")
    private int serviceTarrif;

    public ServiceResponse(Service service)
    {
        this.serviceCode = service.getServiceType().getCode();
        this.serviceName = service.getName();
        this.serviceIcon = service.getIcon();
        this.serviceTarrif = service.getTarrif();
    }
}
