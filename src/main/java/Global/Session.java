/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Global;

import Models.*;
import java.util.List;

/**
 *
 * @author german.ramirez
 * Class to storage the user logged and your configurations.
 */
public class Session {
 
    //Properties
    private static Configuration configuration;
    private static Administrator administrator;
    private static List<Configuration> configurations;

    //Getters and Setters
    public static Administrator getAdministrator() {
        return administrator;
    }

    public static void setAdministrator(Administrator admin) {
        administrator = admin;
    }

    public static List<Configuration> getConfigurations() {
        return configurations;
    }

    public static void setConfigurations(List<Configuration> configList) {
        configurations = configList;
    }

    public static Configuration getConfifuration() {
        return configuration;
    }

    public static void setConfifuration(Configuration config) {
        configuration = config;
    }
}