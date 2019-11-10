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
    public void delete(ItemConsumo itemConsumo) {
        itemConsumo.setDeleted(1);
        update(itemConsumo);
    }

    @Override
    public ItemConsumo find(Integer id) {
        return entityManager.find(ItemConsumo.class, id);
    }

    @Override
    public List<ItemConsumo> getAll() {
        TypedQuery<ItemConsumo> query = entityManager.createQuery("SELECT i FROM ItemConsumo i WHERE i.deleted = 0", ItemConsumo.class);
        return query.getResultList();
    }
}
