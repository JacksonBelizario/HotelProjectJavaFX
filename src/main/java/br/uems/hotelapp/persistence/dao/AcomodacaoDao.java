/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class AcomodacaoDao extends Dao implements AbstractDao<Acomodacao> {

    @Override
    public void save(Acomodacao acomodacao) {
        executeInsideTransaction(entityMan -> entityMan.persist(acomodacao));
    }

    @Override
    public void delete(Acomodacao acomodacao) {
        executeInsideTransaction(entityMan -> entityMan.remove(acomodacao));
    }

    @Override
    public void update(Acomodacao acomodacao) {
        executeInsideTransaction(entityMan -> entityMan.merge(acomodacao));
    }

    @Override
    public Acomodacao find(Integer id) {
        return entityManager.find(Acomodacao.class, id);
    }

    @Override
    public List<Acomodacao> getAll() {
        TypedQuery<Acomodacao> query = entityManager.createQuery("SELECT f FROM Acomodacao f", Acomodacao.class);
        return query.getResultList();
    }

    @Override
    public List<Acomodacao> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Acomodacao> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
