/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.nutech.simsppob.Security;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author iolux
 */
public class JwtUtil {
    private static final String SECRET = "programmer-assigment-java";

    public static String generateToken(
        String email
    )
    {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public static String getUserEmail(
        String token
    )
    {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
