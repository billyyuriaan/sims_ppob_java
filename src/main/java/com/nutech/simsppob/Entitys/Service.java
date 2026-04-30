/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Entitys;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Setter
@Getter
@Component
@Entity
@ToString
@Table(name="services")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Service {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_type_id")
    private ServiceType serviceType;

    private String name;

    private String icon;

    private BigDecimal tarrif;

    private String description;

    @Column(name="created_at", nullable=true)
    public LocalDateTime createdAt;

    @Column(name="updated_at", nullable=true)
    public LocalDateTime updatedAt;
}
