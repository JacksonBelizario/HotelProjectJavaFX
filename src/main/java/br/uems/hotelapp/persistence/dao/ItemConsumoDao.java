/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.ItemConsumo;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class ItemConsumoDao extends Dao<ItemConsumo> {

    @Override
    public void save(ItemConsumo itemConsumo) {
        executeInsideTransaction(entityMan -> entityMan.persist(itemConsumo));
    }

    @Override
    public void delete(ItemConsumo itemConsumo) {
        executeInsideTransaction(entityMan -> entityMan.remove(itemConsumo));
    }

    @Override
    public void update(ItemConsumo itemConsumo) {
        executeInsideTransaction(entityMan -> entityMan.merge(itemConsumo));
    }

    @Override
    public ItemConsumo find(Integer id) {
        return entityManager.find(ItemConsumo.class, id);
    }

    @Override
    public List<ItemConsumo> getAll() {
        TypedQuery<ItemConsumo> query = entityManager.createQuery("SELECT f FROM ItemConsumo f", ItemConsumo.class);
        return query.getResultList();
    }
}
