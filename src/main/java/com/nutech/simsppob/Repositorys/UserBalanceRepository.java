/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.UserBalance;

/**
 *
 * @author iolux
 */
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long>{
    @Query(value="SELECT * FROM user_balance WHERE user_id = :user_id", nativeQuery=true)
    Optional<UserBalance> getUserBalanceByUserId(@Param("user_id") Long userId);

    @Query(value="UPDATE user_balance u SET u.balance = :balance WHERE u.user_id = :user_id", nativeQuery=true)
    void updateUserBalanceByUserId(
        @Param("balance") BigDecimal balance,
        @Param("user_id") Long userId
    );
}
