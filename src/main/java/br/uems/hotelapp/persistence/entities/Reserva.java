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
@Table(name = "reserva")
public class Reserva implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_chegada")
    private Date dataHoraChegada;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_saida")
    private Date dataHoraSaida;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_hospede", nullable = false, updatable = false)
    Hospede hospede;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_acomodacao", nullable = false, updatable = true)
    Acomodacao acomodacao;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_cartao")
    DadosCartao cartao;
    
    @Column(name = "valor_diaria", nullable = false)
    private Double valorDiaria;
    
    @Column(name = "taxa_multa")
    private Double taxaMulta;
    
    @Column(name = "desconto")
    private Double desconto;
    
    @Column(name = "qtde_adulto", nullable = false)
    private Integer qtdeAdulto;
    
    @Column(name = "qtde_crianca")
    private Integer qtdeCrianca;
    
    @Override
    public Integer getId() {
        return id;
    }

    public Date getDataHoraChegada() {
        return dataHoraChegada;
    }

    public void setDataHoraChegada(Date dataHoraChegada) {
        this.dataHoraChegada = dataHoraChegada;
    }

    public Date getDataHoraSaida() {
        return dataHoraSaida;
    }

    public void setDataHoraSaida(Date dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public DadosCartao getCartao() {
        return cartao;
    }

    public void setCartao(DadosCartao cartao) {
        this.cartao = cartao;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Double getTaxaMulta() {
        return taxaMulta;
    }

    public void setTaxaMulta(Double taxaMulta) {
        this.taxaMulta = taxaMulta;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
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
    
}
