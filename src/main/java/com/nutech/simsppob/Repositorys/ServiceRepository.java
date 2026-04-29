/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nutech.simsppob.Entitys.Service;

/**
 *
 * @author iolux
 */
public interface ServiceRepository extends JpaRepository<Object, Object>{
    @Query(value="""
            SELECT s.*
            FROM services s
            JOIN service_types as st ON st.id = s.service_type_id
            """, nativeQuery=true)
    Optional<Service> getAllServices();
}
