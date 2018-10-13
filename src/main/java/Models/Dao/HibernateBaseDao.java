/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Models.BaseEntity;
import Util.Data.HibernateUtil;

/**
 *
 * @author german.ramirez
 * @param <T>
 */
public class HibernateBaseDao<T extends BaseEntity> implements IBaseDao<T> {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    private final Class<T> type;
    
    //<editor-fold desc="Constructors" defaultstate="collapse">
    /**
     * Create a new instance
     * @param type The Class type for the object.
     */
    public HibernateBaseDao(Class<T> type)
    {        
        this.type = type;
    }    
    //</editor-fold>
    
    //<editor-fold desc="Methods" defaultstate="collapsed">
    /**
     * The Class type for the object.
     * @return
     */
    public Class<T> getType()
    {
        return this.type;
    } 

    @Override
    /**
     * Gets a collection of elements of the type of the Entity established.
     */
    public List<T> getList() {
        List<T> list = new ArrayList<>();
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            list = session.createCriteria(type).list();                
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

    @Override
    /**
     * Gets an element of the type of the Entity established.
     */    
    public T getById(int id) {
        T obj = null;
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            obj = session.get(type, id); 
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
        
        return obj;    
    }

    @Override
    /**
     *  Save a new element of the type of the Entity established.
     */
    public void save(T obj) {        
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            session.save(obj);            
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
    }

    @Override    
    /**
     * Update an element of the type of the Entity established.
     */
    public void update(T obj) {
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            session.update(obj);            
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
    }

    @Override
    /**
     * Delete an element of the type of the Entity established.
     */
    public void delete(T obj) {        
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            session.delete(obj);            
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
    }
    //</editor-fold>
}