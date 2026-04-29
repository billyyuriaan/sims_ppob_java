/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author iolux
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@ToString
@MappedSuperclass
public class CommonModelAttribute {
    @Column(name="created_at", nullable=true)
    public LocalDateTime createdAt;

    @Column(name="updated_at", nullable=true)
    public LocalDateTime updatedAt;
}
