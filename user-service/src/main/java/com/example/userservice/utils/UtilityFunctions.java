package com.example.userservice.utils;

import com.example.userservice.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UtilityFunctions {
    UtilityFunctions() {
    }

    public static Map<String, String> generateJWTToken(User user) {
        /*
         * Generates JWT Token
         * */
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("role", user.getRole()).compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

}
