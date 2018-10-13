/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import Models.Administrator;
import Models.Configuration;
import Util.Data.HibernateUtil;

/**
 *
 * @author german.ramirez
 */
public class ConfigurationDao extends HibernateBaseDao<Configuration> {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;
    
    //Constructor
    public ConfigurationDao() {
        super(Configuration.class);
    }
    
    /**
     * Get Configurations list by Administrator ID.
     * @param adminId
     * @return 
     */
    private List<Configuration> getListByAdmin(int adminId)
    {
        List<Configuration> list = new ArrayList<>();
        transaction = null;
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
        
        try{
            transaction = session.beginTransaction();
            Criteria crt = session.createCriteria(super.getType());            
            crt.add(Restrictions.eq("CreatorAdmin", adminId));
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
     * Get Configurations List by Administrator.
     * @param admin
     * @return 
     */
    public List<Configuration> getListByAdmin(Administrator admin)
    {
        if(admin.getSuperAdmin() == 1)
        {
            return super.getList();
        }
        else{
            return getListByAdmin(admin.getId());
        }
    }
}