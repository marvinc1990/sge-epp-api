/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author MARVIN
 */
public class JwtUtil {

    private static final String SECRET_KEY = "W6SPxGs468BL";

    /* Create Token */
    private static String createAccessToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static String generateToken(String username) {
        return createAccessToken(new HashMap<>(), username);
    }

    /* Get Token data */
    private static Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaims(token));
    }

    public static String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public static Boolean validateToken(String token, String username) {
        return getUsername(token).equals(username);
    }

}
