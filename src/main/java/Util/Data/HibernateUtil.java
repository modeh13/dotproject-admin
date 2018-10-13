/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Data;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author german.ramirez
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            if (sessionFactory == null) {
                // loads configuration and mappings
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(Models.Administrator.class);
                configuration.addAnnotatedClass(Models.Column.class);
                configuration.addAnnotatedClass(Models.Configuration.class);
                configuration.addAnnotatedClass(Models.ConfigurationColumn.class);
                configuration.addAnnotatedClass(Models.User.class);                
                configuration.configure();
                
                ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                // builds a session factory from the service registry
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);       
            }
        
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}