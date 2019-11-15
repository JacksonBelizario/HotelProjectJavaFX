/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.utils.DateUtils;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Jackson
 */
public class EstadiaDao extends Dao<Estadia> {

    public List<Estadia> getCurrents() {
        return entityManager.createQuery(
                "SELECT e FROM Estadia e JOIN Hospede h ON e.hospede.id = h.id"
                + " WHERE cast(e.dataHoraInicio as date) <= current_date and cast(e.dataHoraTermino as date) >= current_date")
                .getResultList();
    }

    public int getCurrentsCount() {
        Query query = entityManager.createQuery("SELECT COUNT(e) FROM Estadia e JOIN Hospede h ON e.hospede.id = h.id"
        + " WHERE cast(e.dataHoraInicio as date) <= current_date and cast(e.dataHoraTermino as date) >= current_date");
        
        return ((Number) query.getSingleResult()).intValue();
    }

}
