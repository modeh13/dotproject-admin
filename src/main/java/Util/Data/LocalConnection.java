/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Data;

import Util.Resources;

/**
 * Class to manage the database connection with the ADMIN DB.
 * @author german.ramirez
 */
public class LocalConnection extends ConnectionDB {
    
    public LocalConnection(){       
        super.setUser(Resources.getValueByKey("userLocal"));
        super.setPassword(Resources.getValueByKey("passLocal"));
        super.setUrl(Resources.getValueByKey("urlLocal"));
        super.setDriverClass(Resources.getValueByKey("mySqlDriverClass"));        
    }
}