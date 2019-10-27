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
@Table(name = "consumo")
public class Consumo implements AbstractEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;
    
    @Override
    public Integer getId() {
        return id;
    }
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_item", nullable = false, updatable = false)
    ItemConsumo itemConsumo;
    
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_estadia", nullable = false, updatable = false)
    Estadia estadia;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora", nullable = false)
    private Date dataHora;
    
    @Column(name = "valor", nullable = false)
    private Double valor;

    public ItemConsumo getItemConsumo() {
        return itemConsumo;
    }

    public void setItemConsumo(ItemConsumo itemConsumo) {
        this.itemConsumo = itemConsumo;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
