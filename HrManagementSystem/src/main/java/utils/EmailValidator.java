/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rishad
 */
public class EmailValidator {
    private static final String EMAIL_REGEX =
				"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +  // local-part
				"@" +
				"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; // domain part
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    public static boolean emailValidate(String email){
        
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
