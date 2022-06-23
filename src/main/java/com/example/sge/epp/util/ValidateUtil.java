/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.util;

/**
 *
 * @author MARVIN
 */
public class ValidateUtil {

    public static void isRequired(String value, String field) {
        if (value == null || value.isEmpty()) {
            throw new RuntimeException(new StringBuilder("¡").append(field).append(" es un campo requerido!").toString());
        }
    }

    public static void isRequired(Object value, String field) {
        if (value == null) {
            throw new RuntimeException(new StringBuilder("¡").append(field).append(" es un campo requerido!").toString());
        }
    }

    public static void minLength(String value, int length, String field) {
        if (!(value.length() >= length)) {
            throw new RuntimeException(new StringBuilder("¡").append(field).append(" debe tener más de ").append(length).append(" caracteres!").toString());
        }
    }

    public static void maxLength(String value, int length, String field) {
        if (!(value.length() <= length)) {
            throw new RuntimeException(new StringBuilder("¡").append(field).append(" debe tener menos de ").append(length).append(" caracteres!").toString());
        }
    }

    public static void equalLength(String value, int length, String field) {
        if (value.length() != length) {
            throw new RuntimeException(new StringBuilder("¡").append(field).append(" debe tener ").append(length).append(" caracteres!").toString());
        }
    }

    public static void comply(boolean value, String message) {
        if (!value) {
            throw new RuntimeException(message);
        }
    }

    public static void complyRegex(String value, String regex, String message) {
        if (!value.matches(regex)) {
            throw new RuntimeException(message);
        }
    }

    public static void isNumeric(String valor) {
        try {
            Long.parseLong(valor);
        } catch (NumberFormatException nfe) {
            throw new RuntimeException(new StringBuilder("¡").append(valor).append(" no es un valor numérico!").toString());
        }
    }

}
