package com.example.enrollment.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private HashUtil() {}

    public static String md5(String data) {
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        md5.update(data.getBytes());
        byte[] digest = md5.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
