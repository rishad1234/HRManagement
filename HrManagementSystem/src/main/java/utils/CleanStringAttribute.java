/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Rishad
 */
public class CleanStringAttribute {
    
    public static String clean(String attribute){
        return attribute.trim();
    }
    
    public static boolean stringValidate(String attribute){
        return attribute.isEmpty() && attribute.equals("");
    }
}
