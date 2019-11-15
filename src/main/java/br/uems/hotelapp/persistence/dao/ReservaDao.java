/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.DateUtils;
import java.util.Date;
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

    public List<Reserva> getCurrents() {
        return entityManager.createQuery(
                "SELECT r FROM Reserva r"
                + " WHERE r.dataHoraSaida >= :today and r.dataHoraChegada <= :today")
                .setParameter("today", new Date())
                .getResultList();
    }

    public List<Reserva> getBookingsforToday() {
        return entityManager.createQuery(
                "SELECT r FROM Reserva r"
                + " WHERE cast(r.dataHoraChegada as date) = :today")
                .setParameter("today", DateUtils.getToday())
                .getResultList();
    }
}
