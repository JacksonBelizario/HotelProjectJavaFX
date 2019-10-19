/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Hospede;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class HospedeDao extends Dao implements AbstractDao<Hospede> {

    @Override
    public void save(Hospede hospede) {
        executeInsideTransaction(entityMan -> entityMan.persist(hospede));
    }

    @Override
    public void delete(Hospede hospede) {
        executeInsideTransaction(entityMan -> entityMan.remove(hospede));
    }

    @Override
    public void update(Hospede hospede) {
        executeInsideTransaction(entityMan -> entityMan.merge(hospede));
    }

    @Override
    public Hospede find(Integer id) {
        return entityManager.find(Hospede.class, id);
    }

    @Override
    public List<Hospede> getAll() {
        TypedQuery<Hospede> query = entityManager.createQuery("SELECT e FROM Hospede e", Hospede.class);
        return query.getResultList();
    }

    @Override
    public List<Hospede> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hospede> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
    private void executeInsideTransaction(Consumer<EntityManager> action) {
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
