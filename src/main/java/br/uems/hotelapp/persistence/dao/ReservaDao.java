/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Reserva;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class ReservaDao extends Dao implements AbstractDao<Reserva> {

    @Override
    public void save(Reserva reserva) {
        executeInsideTransaction(entityMan -> entityMan.persist(reserva));
    }

    @Override
    public void delete(Reserva reserva) {
        executeInsideTransaction(entityMan -> entityMan.remove(reserva));
    }

    @Override
    public void update(Reserva reserva) {
        executeInsideTransaction(entityMan -> entityMan.merge(reserva));
    }

    @Override
    public Reserva find(Integer id) {
        return entityManager.find(Reserva.class, id);
    }

    @Override
    public List<Reserva> getAll() {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r ORDER By r.id DESC", Reserva.class);
        return query.getResultList();
    }

    @Override
    public List<Reserva> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reserva> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
