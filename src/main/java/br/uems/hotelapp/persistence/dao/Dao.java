/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.ConnectionFactory;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Jackson
 */
class Dao {
    
    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();
    
     
    protected void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
