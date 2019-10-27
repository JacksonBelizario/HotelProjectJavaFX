/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Pagamento;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class PagamentoDao extends Dao implements AbstractDao<Pagamento> {

    @Override
    public void save(Pagamento pagamento) {
        executeInsideTransaction(entityMan -> entityMan.persist(pagamento));
    }

    @Override
    public void delete(Pagamento pagamento) {
        executeInsideTransaction(entityMan -> entityMan.remove(pagamento));
    }

    @Override
    public void update(Pagamento pagamento) {
        executeInsideTransaction(entityMan -> entityMan.merge(pagamento));
    }

    @Override
    public Pagamento find(Integer id) {
        return entityManager.find(Pagamento.class, id);
    }

    @Override
    public List<Pagamento> getAll() {
        TypedQuery<Pagamento> query = entityManager.createQuery("SELECT f FROM Pagamento f", Pagamento.class);
        return query.getResultList();
    }

    @Override
    public List<Pagamento> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pagamento> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
