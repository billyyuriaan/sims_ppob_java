/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.User;

/**
 *
 * @author billyyuriaan
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value="UPDATE users u SET u.first_name = :first_name, u.last_name = :last_name WHERE u.id = :id")
    void updateUserProfileData(
        @Param("id") Long id,
        @Param("first_name") String firstName,
        @Param("last_name") String last_name
    );

    @Query(value="UPDATE users u SET u.profile_image = :profile_image WHERE u.id = :id", nativeQuery=true)
    void updateUserProfileImage(
        @Param("id") Long id,
        @Param("profile_image") String profileImage
    );
}
