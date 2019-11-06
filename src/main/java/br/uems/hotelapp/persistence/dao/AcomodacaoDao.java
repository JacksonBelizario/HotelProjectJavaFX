/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import java.util.Date;
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
    
    
    
    public List<Acomodacao> findFreeRooms(Integer qtdeAdulto, Integer qtdeCrianca, Date dataHoraChegada, Date dataHoraSaida) {
        return entityManager.createQuery(
            "SELECT a from Acomodacao a JOIN TipoAcomodacao t ON t.id = a.tipoAcomodacao.id"
                + " WHERE t.qtdeAdulto >= :qtde_adulto AND t.qtdeCrianca >= :qtde_crianca AND a.id NOT IN"
                + " (SELECT a.id FROM Acomodacao a JOIN Reserva r on a.id = r.acomodacao.id WHERE"
                + "  (r.dataHoraSaida > :data_hora_chegada and r.dataHoraChegada <= :data_hora_chegada) OR"
                + "  (r.dataHoraSaida >= :data_hora_saida and r.dataHoraChegada < :data_hora_saida)"
                + "  GROUP BY a.id"
                + " )")
                .setParameter("qtde_adulto", qtdeAdulto)
                .setParameter("qtde_crianca", qtdeCrianca)
                .setParameter("data_hora_chegada", dataHoraChegada)
                .setParameter("data_hora_saida", dataHoraSaida)
                .getResultList();
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
