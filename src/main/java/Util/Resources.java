/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author german.ramirez
 */
public class Resources {
 
    private static final ResourceBundle rb = ResourceBundle.getBundle("Location", Locale.getDefault());    
    
    /**
     * Get the resource value by Key.
     * @param key
     * @return
     */
    public static String getValueByKey(String key){
        String value = "";
        
        if(rb.containsKey(key)){
            value = rb.getString(key);
        }
        
        return value;
    }    
}