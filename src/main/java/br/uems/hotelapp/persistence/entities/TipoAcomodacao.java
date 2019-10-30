/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jackson
 */
@Entity
@Table(name = "tipo_acomodacao")
public class TipoAcomodacao implements AbstractEntity, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    
    @Column(name = "qtde_adulto", unique = true, nullable = false)
    private Integer qtdeAdulto;
    
    @Column(name = "qtde_crianca", unique = true, nullable = false)
    private Integer qtdeCrianca;
    
    @Column(name = "valor_diaria", unique = true, nullable = false)
    private Double valorDiaria;
    
    @Override
    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdeAdulto() {
        return qtdeAdulto;
    }

    public void setQtdeAdulto(Integer qtdeAdulto) {
        this.qtdeAdulto = qtdeAdulto;
    }

    public Integer getQtdeCrianca() {
        return qtdeCrianca;
    }

    public void setQtdeCrianca(Integer qtdeCrianca) {
        this.qtdeCrianca = qtdeCrianca;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
