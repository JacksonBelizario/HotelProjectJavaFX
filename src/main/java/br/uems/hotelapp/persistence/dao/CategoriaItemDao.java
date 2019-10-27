/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.CategoriaItem;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class CategoriaItemDao extends Dao implements AbstractDao<CategoriaItem> {

    @Override
    public void save(CategoriaItem categoriaItem) {
        executeInsideTransaction(entityMan -> entityMan.persist(categoriaItem));
    }

    @Override
    public void delete(CategoriaItem categoriaItem) {
        executeInsideTransaction(entityMan -> entityMan.remove(categoriaItem));
    }

    @Override
    public void update(CategoriaItem categoriaItem) {
        executeInsideTransaction(entityMan -> entityMan.merge(categoriaItem));
    }

    @Override
    public CategoriaItem find(Integer id) {
        return entityManager.find(CategoriaItem.class, id);
    }

    @Override
    public List<CategoriaItem> getAll() {
        TypedQuery<CategoriaItem> query = entityManager.createQuery("SELECT f FROM CategoriaItem f", CategoriaItem.class);
        return query.getResultList();
    }

    @Override
    public List<CategoriaItem> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategoriaItem> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
