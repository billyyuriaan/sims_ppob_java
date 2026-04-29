/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nutech.simsppob.Entitys.Banner;

/**
 *
 * @author billyyuriaan
 */
public interface BannerRepository extends JpaRepository<Banner, Long>  {
    @Query(value="SELECT * FROM banners", nativeQuery=true)
    Optional<Banner> getAllBanner();
}
