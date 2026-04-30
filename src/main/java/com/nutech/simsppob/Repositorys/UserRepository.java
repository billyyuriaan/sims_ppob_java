/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.User;

import jakarta.transaction.Transactional;

/**
 *
 * @author billyyuriaan
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value="UPDATE users SET first_name = :firstName, last_name = :lastName WHERE id = :id", nativeQuery=true)
    void updateUserProfileData(
        @Param("id") Long id,
        @Param("firstName") String firstName,
        @Param("lastName") String lastName
    );

    @Modifying
    @Transactional
    @Query(
        value = "UPDATE users SET profile_image = :profileImage WHERE id = :id",
        nativeQuery = true
    )
    void updateUserProfileImage(
        @Param("id") Long id,
        @Param("profileImage") String profileImage
    );
}
