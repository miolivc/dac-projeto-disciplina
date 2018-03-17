
package com.ifpb.dac.resouces.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BasicAuth {
    
    public static String encode(String email, String password) throws UnsupportedEncodingException {
        byte[] encodeParam = (email + ":" + password).getBytes("UTF-8");
        String encode = Base64.getEncoder().encodeToString(encodeParam);
        return "Basic " + encode;
    }
    
    public static Map<String, String> decode(String encode) {
        byte[] decodeParams = Base64.getDecoder().decode(encode.replaceAll("Basic ", ""));

        String emailWithPassword = new String(decodeParams);
        StringTokenizer string = new StringTokenizer(emailWithPassword, ":");

        String email = string.nextToken();
        String password = string.nextToken();
        
        Map<String, String>  decode = new HashMap<>();
        decode.put("email", email);
        decode.put("password", password);
        return decode;
    }
    
}
