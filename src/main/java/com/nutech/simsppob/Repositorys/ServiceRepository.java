/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.nutech.simsppob.Repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nutech.simsppob.Entitys.Service;

/**
 *
 * @author iolux
 */
public interface ServiceRepository extends JpaRepository<Service, Long>{
    @Query(value="""
            SELECT s.*
            FROM services s
            JOIN service_types as st ON st.id = s.service_type_id
            """, nativeQuery=true)
    List<Service> getAllServices();

    @Query(value="""
            SELECT s.*
            FROM services s
            JOIN service_types as st ON st.id = s.service_type_id
            WHERE st.code = :code
            """, nativeQuery=true)
    Optional<Service> getServiceDataByCode(@Param("code") String code);
}
