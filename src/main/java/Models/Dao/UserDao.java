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
import java.util.ArrayList;
import java.util.List;

import Models.User;
import Util.Data.HibernateUtil;

/**
 *
 * @author german.ramirez
 */
public class UserDao extends HibernateBaseDao<User> {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    //Constructor
    public UserDao() {
        super(User.class);
    }
    
    /**
     * Get the User list by Configuration ID.
     * @param configurationId
     * @return 
     */
    public List<User> getListByConfigurationId(int configurationId)
    {
        List<User> list = new ArrayList<>();
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            Criteria crt = session.createCriteria(super.getType());
            crt.add(Restrictions.eq("ConfigurationId", configurationId));            
            list = crt.list();
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
        
        return list;
    }    
    
    /**
     * Get the active User list by Configuration ID.
     * @param configurationId
     * @return
     * @throws HibernateException 
     */
    public List<User> getListActiveUsers(int configurationId) throws HibernateException
    {
        List<User> list = new ArrayList<>();
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            Criteria crt = session.createCriteria(super.getType());
            crt.add(Restrictions.eq("State", 1));
            crt.add(Restrictions.eq("ConfigurationId", configurationId));
            list = crt.list();
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
        
        return list;
    }
}