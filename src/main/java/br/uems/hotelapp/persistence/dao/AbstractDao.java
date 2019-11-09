/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import java.util.List;

/**
 *
 * @author Jackson
 * @param <T>
 */
interface AbstractDao<T> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T find(Integer id);
    
    List<T> getAll();

    List<T> getList(String qlString, Object[] params);

    List<T> getList(String qlString, Object[] params, int[] range);

    long getCount(String qlString, Object[] params);
    
    long getCount();

}