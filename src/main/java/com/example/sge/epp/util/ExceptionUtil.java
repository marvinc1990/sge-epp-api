/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sge.epp.util;

import com.example.sge.epp.response.SgeApiResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MARVIN
 */
public class ExceptionUtil {

    public static void writeException(SgeApiResponse apiResponse, HttpServletResponse response) {
        try {
            response.setStatus(apiResponse.getCode());
            response.setContentType("application/json");
            if (!response.isCommitted()) {
                response.getWriter().write(StrUtil.toJson(apiResponse));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
