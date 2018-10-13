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

import Models.ConfigurationColumn;
import Util.Data.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author german.ramirez
 */
public class ConfigurationColumnDao extends HibernateBaseDao<ConfigurationColumn> {
    
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    //Constructor
    public ConfigurationColumnDao() {
        super(ConfigurationColumn.class);
    }
    
    /**
     * Get Column configurations List by Configuration ID.
     * @param configurationId
     * @return 
     */
    public List<ConfigurationColumn> getListByConfigurationId(int configurationId)
    {
        List<ConfigurationColumn> list = new ArrayList<>();
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
}