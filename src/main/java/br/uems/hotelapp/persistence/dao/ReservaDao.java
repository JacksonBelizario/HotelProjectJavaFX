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
public class ReservaDao extends Dao<Reserva> {

    @Override
    public List<Reserva> getAll() {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r ORDER By r.id DESC", Reserva.class);
        return query.getResultList();
    }
}
