/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Hospede;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class HospedeDao extends Dao<Hospede> {

    @Override
    public void delete(Hospede hospede) {
        hospede.setDeleted(1);
        update(hospede);
    }

    @Override
    public Hospede find(Integer id) {
        return entityManager.find(Hospede.class, id);
    }

    @Override
    public List<Hospede> getAll() {
        TypedQuery<Hospede> query = entityManager.createQuery("SELECT h FROM Hospede h WHERE h.deleted = 0", Hospede.class);
        return query.getResultList();
    }
}
