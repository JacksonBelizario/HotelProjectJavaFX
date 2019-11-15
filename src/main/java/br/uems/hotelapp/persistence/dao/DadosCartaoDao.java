/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.DadosCartao;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class DadosCartaoDao extends Dao<DadosCartao> {

    @Override
    public void save(DadosCartao dadosCartao) {
        executeInsideTransaction(entityMan -> entityMan.persist(dadosCartao));
    }

    @Override
    public void delete(DadosCartao dadosCartao) {
        executeInsideTransaction(entityMan -> entityMan.remove(dadosCartao));
    }

    @Override
    public void update(DadosCartao dadosCartao) {
        executeInsideTransaction(entityMan -> entityMan.merge(dadosCartao));
    }

    @Override
    public DadosCartao find(Integer id) {
        return entityManager.find(DadosCartao.class, id);
    }

    public DadosCartao findByHospede(Integer idHospede) {
        List<DadosCartao> query = entityManager.createQuery("SELECT t FROM DadosCartao t where t.codigo_hospede = :codigo_hospede")
                .setParameter("codigo_hospede", idHospede).getResultList();

        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    @Override
    public List<DadosCartao> getAll() {
        TypedQuery<DadosCartao> query = entityManager.createQuery("SELECT e FROM DadosCartao e", DadosCartao.class);
        return query.getResultList();
    }
}
