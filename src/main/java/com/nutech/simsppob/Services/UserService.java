/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutech.simsppob.Entitys.User;
import com.nutech.simsppob.Repositorys.UserRepository;

/**
 *
 * @author iolux
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserProfileByEmail(
        String email
    ) throws Exception
    {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new Exception("Token tidak valid atau kadarluasa");
        }

        return user;
    }
}
