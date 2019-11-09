/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Acompanhante;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class AcompanhanteDao extends Dao<Acompanhante> {

    @Override
    public void save(Acompanhante acompanhante) {
        executeInsideTransaction(entityMan -> entityMan.persist(acompanhante));
    }

    @Override
    public void delete(Acompanhante acompanhante) {
        executeInsideTransaction(entityMan -> entityMan.remove(acompanhante));
    }

    @Override
    public void update(Acompanhante acompanhante) {
        executeInsideTransaction(entityMan -> entityMan.merge(acompanhante));
    }

    @Override
    public Acompanhante find(Integer id) {
        return entityManager.find(Acompanhante.class, id);
    }

    @Override
    public List<Acompanhante> getAll() {
        TypedQuery<Acompanhante> query = entityManager.createQuery("SELECT f FROM Acompanhante f", Acompanhante.class);
        return query.getResultList();
    }

}
