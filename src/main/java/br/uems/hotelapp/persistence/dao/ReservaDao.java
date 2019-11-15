/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.entities.Reserva;
import java.util.List;

import javax.persistence.Query;
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
                + " WHERE cast(r.dataHoraSaida as date) >= current_date and cast(r.dataHoraChegada as date) <= current_date")
                .getResultList();
    }

    public List<Reserva> getBookingsforToday() {
        return entityManager.createQuery(
                "SELECT r FROM Reserva r"
                + " WHERE cast(r.dataHoraChegada as date) = current_date")
                .getResultList();
    }

    public int getCurrentsCount() {
        Query query = entityManager.createQuery("SELECT COUNT(r) FROM Reserva r"
        + " WHERE cast(r.dataHoraSaida as date) >= current_date and cast(r.dataHoraChegada as date) <= current_date");
        
        return ((Number) query.getSingleResult()).intValue();
    }
}
