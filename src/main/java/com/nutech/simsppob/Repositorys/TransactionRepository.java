/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.Transaction;

/**
 *
 * @author iolux
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    @Query(value="SELECT * FROM transactions WHERE user_id = :userId LIMIT :limit OFFSET :offset", nativeQuery=true)
    List<Transaction> getAllHistoryUserByUserId(
        @Param("userId") Long userId,
        @Param("limit") int limit,
        @Param("offset") int offset
    );
    @Query(value="SELECT * FROM transactions WHERE user_id = :userId", nativeQuery=true)
    List<Transaction> getAllHistoryUserByUserId(
        @Param("userId") Long userId
    );
}
