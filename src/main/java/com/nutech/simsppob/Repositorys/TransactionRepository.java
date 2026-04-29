/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutech.simsppob.Entitys.Transaction;

/**
 *
 * @author iolux
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
}
