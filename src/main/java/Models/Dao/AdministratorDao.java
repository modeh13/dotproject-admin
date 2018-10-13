/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Models.Administrator;
import Util.Data.HibernateUtil;

/**
 *
 * @author german.ramirez
 */
public class AdministratorDao extends HibernateBaseDao<Administrator> {
    
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    //Constructors
    public AdministratorDao() {
        super(Administrator.class);
    }
    
    /**
     * Get Administrator object by IP address.
     * @param ipAddress
     * @return 
     */
    public Administrator getByIpAddress(String ipAddress)
    {
        Administrator admin = null;
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            Criteria crt = session.createCriteria(super.getType());
            crt.add(Restrictions.eq("IpAddress", ipAddress));
            crt.add(Restrictions.eq("State", 1));
            admin = (Administrator) crt.uniqueResult();
            transaction.commit();
            
        }catch (HibernateException e) 
        {            
            if (transaction != null) {
                transaction.rollback();
            }            
            throw e;
        } 
        finally {
            session.close();
        }
        
        return admin;
    }
}