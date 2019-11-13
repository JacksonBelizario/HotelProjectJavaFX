/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import static br.uems.hotelapp.persistence.dao.Dao.entityManager;
import br.uems.hotelapp.persistence.entities.DadosCartao;
import br.uems.hotelapp.persistence.entities.Pagamento;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class PagamentoDao extends Dao<Pagamento> {

    @Override
    public List<Pagamento> getAll() {
        TypedQuery<Pagamento> query = entityManager.createQuery("SELECT f FROM Pagamento f", Pagamento.class);
        return query.getResultList();
    }
    
    public Pagamento findByEstadia(Integer idEstadia) {
        List<Pagamento> query = entityManager.createQuery("SELECT p FROM Pagamento p where p.estadia.id = :codigo_estadia")
                        .setParameter("codigo_estadia", idEstadia).getResultList();
        
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }
}
