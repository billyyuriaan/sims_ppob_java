/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.UserBalance;

import jakarta.transaction.Transactional;

/**
 *
 * @author iolux
 */
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long>{
    @Query(value="SELECT * FROM user_balance WHERE user_id = :userId", nativeQuery=true)
    Optional<UserBalance> getUserBalanceByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value="UPDATE user_balance SET balance = :balance WHERE user_id = :userId", nativeQuery=true)
    void updateUserBalanceByUserId(
        @Param("balance") BigDecimal balance,
        @Param("userId") Long userId
    );
}
