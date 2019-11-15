/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.utils.DateUtils;
import java.util.List;

/**
 *
 * @author Jackson
 */
public class EstadiaDao extends Dao<Estadia> {
    
    public List<Estadia> getCurrents() {
        return entityManager.createQuery(
            "SELECT e FROM Estadia e JOIN Hospede h ON h.id = e.hospede.id"
                + " WHERE cast(e.dataHoraInicio as date) >= :today and cast(e.dataHoraTermino as date) <= :today")
                .setParameter("today", DateUtils.getToday())
                .getResultList();
    }

}
