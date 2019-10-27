/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.TipoAcomodacao;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class TipoAcomodacaoDao extends Dao implements AbstractDao<TipoAcomodacao> {

    @Override
    public void save(TipoAcomodacao tipoAcomodacao) {
        executeInsideTransaction(entityMan -> entityMan.persist(tipoAcomodacao));
    }

    @Override
    public void delete(TipoAcomodacao tipoAcomodacao) {
        executeInsideTransaction(entityMan -> entityMan.remove(tipoAcomodacao));
    }

    @Override
    public void update(TipoAcomodacao tipoAcomodacao) {
        executeInsideTransaction(entityMan -> entityMan.merge(tipoAcomodacao));
    }

    @Override
    public TipoAcomodacao find(Integer id) {
        return entityManager.find(TipoAcomodacao.class, id);
    }

    @Override
    public List<TipoAcomodacao> getAll() {
        TypedQuery<TipoAcomodacao> query = entityManager.createQuery("SELECT f FROM TipoAcomodacao f", TipoAcomodacao.class);
        return query.getResultList();
    }

    @Override
    public List<TipoAcomodacao> getList(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoAcomodacao> getList(String qlString, Object[] params, int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long getCount(String qlString, Object[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
