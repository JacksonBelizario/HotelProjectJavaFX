/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jackson
 */
@Entity
@Table(name = "pagamento")
public class Pagamento implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_estadia", nullable = false, updatable = false)
    Estadia estadia;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora", nullable = false)
    private Date dataHora;
    
    @Column(name = "forma_pagamento", nullable = false, length = 255)
    private String formaPagamento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento", nullable = true)
    private Date dataVencimento;
    
    @Column(name = "valor_total_diaria", nullable = false)
    private Double valorTotalDiaria;
    
    @Column(name = "valor_total_telefonema", nullable = true)
    private Double valorTotalTelefonema;
    
    @Column(name = "valor_total_consumo", nullable = true)
    private Double valorTotalConsumo;
    
    @Column(name = "valor", nullable = false)
    private Double valor;
    
    @Override
    public Integer getId() {
        return id;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValorTotalDiaria() {
        return valorTotalDiaria;
    }

    public void setValorTotalDiaria(Double valorTotalDiaria) {
        this.valorTotalDiaria = valorTotalDiaria;
    }

    public Double getValorTotalTelefonema() {
        return valorTotalTelefonema;
    }

    public void setValorTotalTelefonema(Double valorTotalTelefonema) {
        this.valorTotalTelefonema = valorTotalTelefonema;
    }

    public Double getValorTotalConsumo() {
        return valorTotalConsumo;
    }

    public void setValorTotalConsumo(Double valorTotalConsumo) {
        this.valorTotalConsumo = valorTotalConsumo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
