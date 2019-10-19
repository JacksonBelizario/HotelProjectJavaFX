/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.dao;

import br.uems.hotelapp.persistence.ConnectionFactory;
import javax.persistence.EntityManager;

/**
 *
 * @author Jackson
 */
public class Dao {
    
    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();
}
