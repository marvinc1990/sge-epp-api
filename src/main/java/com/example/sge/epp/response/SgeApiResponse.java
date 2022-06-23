/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author MARVIN
 */
@Data
public class SgeApiResponse {
    
    private boolean error;
    private int code;
    private String title;
    private String message;
    private Object data;
    
    public static SgeApiResponse success(String title, String message, Object data) {
        SgeApiResponse apiResponse = new SgeApiResponse();
        apiResponse.setError(false);
        apiResponse.setCode(HttpStatus.OK.value());
        apiResponse.setTitle(title);
        apiResponse.setMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static SgeApiResponse success(String message, Object data) {
        return success("", message, data);
    }

    public static SgeApiResponse failed(String title, String message, int code) {
        SgeApiResponse apiResponse = new SgeApiResponse();
        apiResponse.setError(true);
        apiResponse.setCode(code);
        apiResponse.setTitle(title);
        apiResponse.setMessage(message);
        return apiResponse;
    }

    public static SgeApiResponse failed(String message, int code) {
        return SgeApiResponse.failed("", message, code);
    }

    public static SgeApiResponse failed(String message) {
        return SgeApiResponse.failed(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    
}
