/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Entitys;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Setter
@Getter
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @GeneratedValue(strategy=GenerationType.UUID)
    private String invoiceNumber;

    @Column(name="transaction_type")
    private String transactionType;

    private String description;

    private BigDecimal amount;

    @Column(name="created_at", nullable=true)
    public LocalDateTime createdAt;

    @Column(name="updated_at", nullable=true)
    public LocalDateTime updatedAt;
}
