/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author MARVIN
 */
public class StrUtil {
    
    public static String toString(Object object) {
        try {
            if (object != null) {
                return String.valueOf(object);
            }
        } catch (Exception e) {
        }
        return "";
    }
    
    public static String toJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String lpad(String value, int count, char character) {
        int size = count - value.length();
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            strBuilder.append(character);
        }
        strBuilder.append(value);
        return strBuilder.toString();
    }
    
}
