/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.entities.Funcionario;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jackson
 */
public class FuncionarioDao extends Dao<Funcionario> {

    @Override
    public void delete(Funcionario funcionario) {
        funcionario.setDeleted(1);
        update(funcionario);
    }

    @Override
    public Funcionario find(Integer id) {
        return entityManager.find(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> getAll() {
        TypedQuery<Funcionario> query = entityManager.createQuery("SELECT f FROM Funcionario f WHERE f.deleted = 0", Funcionario.class);
        return query.getResultList();
    }
}
