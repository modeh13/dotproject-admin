/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Data;

import Util.Resources;

/**
 * Class to manage the database connection with the DotProject.
 * @author german.ramirez
 */
public class DotConnection extends ConnectionDB {
    public DotConnection(){       
        super.setUser(Resources.getValueByKey("userDotProject"));
        super.setPassword(Resources.getValueByKey("passDotProject"));
        super.setUrl(Resources.getValueByKey("urlDorProject"));
        super.setDriverClass(Resources.getValueByKey("mySqlDriverClass"));        
    }
}