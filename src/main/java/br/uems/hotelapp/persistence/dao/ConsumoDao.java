/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Consumo;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class ConsumoDao extends Dao<Consumo> {

    @Override
    public void save(Consumo consumo) {
        executeInsideTransaction(entityMan -> entityMan.persist(consumo));
    }

    @Override
    public void delete(Consumo consumo) {
        executeInsideTransaction(entityMan -> entityMan.remove(consumo));
    }

    @Override
    public void update(Consumo consumo) {
        executeInsideTransaction(entityMan -> entityMan.merge(consumo));
    }

    @Override
    public Consumo find(Integer id) {
        return entityManager.find(Consumo.class, id);
    }

    @Override
    public List<Consumo> getAll() {
        TypedQuery<Consumo> query = entityManager.createQuery("SELECT f FROM Consumo f", Consumo.class);
        return query.getResultList();
    }
}
