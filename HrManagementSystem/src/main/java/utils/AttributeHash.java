/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Rishad
 */
public class AttributeHash {
    
    public static String hash(String attribute){
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] hashed = digest.digest(attribute.getBytes("UTF-8"));
            return new String(hashed, Charset.defaultCharset());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
