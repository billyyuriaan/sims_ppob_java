/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Entitys;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nutech.simsppob.dto.CommonModelAttribute;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author iolux
 */
@Getter
@Setter
@ToString
@Entity
@Component
@Table(name="banners")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Banner{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @JsonProperty("banner_name")
    private String bannerName;

    @JsonProperty("banner_image")
    private String bannerImage;

    private String description;

    @Column(name="created_at", nullable=true)
    public LocalDateTime createdAt;

    @Column(name="updated_at", nullable=true)
    public LocalDateTime updatedAt;
}
