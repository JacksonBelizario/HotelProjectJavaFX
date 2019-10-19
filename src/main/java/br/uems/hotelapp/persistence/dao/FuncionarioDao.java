/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.entities.Funcionario;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class FuncionarioDao extends Dao implements AbstractDao<Funcionario> {

    @Override
    public void save(Funcionario funcionario) {
        executeInsideTransaction(entityManager -> entityManager.persist(funcionario));
    }

    @Override
    public void delete(Funcionario funcionario) {
        executeInsideTransaction(entityManager -> entityManager.remove(funcionario));
    }

    @Override
    public void update(Funcionario funcionario) {
        executeInsideTransaction(entityManager -> entityManager.merge(funcionario));
    }

    @Override
    public Funcionario find(Integer id) {
        return entityManager.find(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> getAll() {
        TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f", Funcionario.class);
        return query.getResultList();
    }

    @Override
    public List<Funcionario> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> getList(String qlString, Object[] params, int[] range) {
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
