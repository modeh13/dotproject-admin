/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.util.List;
import Models.BaseEntity;

/**
 * Interface base to manage the methods for the objects DAO.
 * @author german.ramirez
 * @param <T>
 */
public interface IBaseDao<T extends BaseEntity> {
    public List<T> getList();
    public T getById(int id);
    public void save(T obj);
    public void update(T obj);
    public void delete(T obj);    
}