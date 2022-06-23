package com.example.sge.epp.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class Base64Util {

    public static File parseBase64toFile(String encodedFile, String filename) {
        File file = new File(filename);
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedFile);
            FileUtils.writeByteArrayToFile(file, decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return file;
    }

}
