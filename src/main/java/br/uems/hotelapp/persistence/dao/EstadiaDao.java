/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Estadia;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class EstadiaDao extends Dao implements AbstractDao<Estadia> {

    @Override
    public void save(Estadia estadia) {
        executeInsideTransaction(entityMan -> entityMan.persist(estadia));
    }

    @Override
    public void delete(Estadia estadia) {
        executeInsideTransaction(entityMan -> entityMan.remove(estadia));
    }

    @Override
    public void update(Estadia estadia) {
        executeInsideTransaction(entityMan -> entityMan.merge(estadia));
    }

    @Override
    public Estadia find(Integer id) {
        return entityManager.find(Estadia.class, id);
    }

    @Override
    public List<Estadia> getAll() {
        TypedQuery<Estadia> query = entityManager.createQuery("SELECT e FROM Estadia e ORDER By e.id DESC", Estadia.class);
        return query.getResultList();
    }

    @Override
    public List<Estadia> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Estadia> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
