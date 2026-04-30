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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_balance")
public class UserBalance {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable=false)
    private User user;

    private BigDecimal balance;    

    @Column(name="created_at", nullable=true)
    public LocalDateTime createdAt;

    @Column(name="updated_at", nullable=true)
    public LocalDateTime updatedAt;
}
