/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.NumberUtils;
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_item", nullable = false, updatable = false)
    ItemConsumo itemConsumo;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_estadia", nullable = false, updatable = false)
    Estadia estadia;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora", nullable = false)
    private Date dataHora;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Override
    public Integer getId() {
        return id;
    }

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

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ItemConsumoTable getRowTable() {
        return new ItemConsumoTable(
                itemConsumo.getItem(),
                itemConsumo.getCategoria().toString(),
                quantidade,
                DateUtils.format(dataHora),
                NumberUtils.formatCurrency(quantidade * valor)
        );
    }

    public class ItemConsumoTable {

        private SimpleStringProperty nome;
        private SimpleStringProperty tipo;
        private SimpleIntegerProperty quantidade;
        private SimpleStringProperty data;
        private SimpleStringProperty valor;

        public ItemConsumoTable(String nome, String tipo, Integer quantidade, String data, String valor) {
            this.nome = new SimpleStringProperty(nome);
            this.tipo = new SimpleStringProperty(tipo);
            this.quantidade = new SimpleIntegerProperty(quantidade);
            this.data = new SimpleStringProperty(data);
            this.valor = new SimpleStringProperty(valor);
        }

        public String getNome() {
            return nome.get();
        }

        public void setNome(String nome) {
            this.nome = new SimpleStringProperty(nome);
        }

        public String getTipo() {
            return tipo.get();
        }

        public void setTipo(String tipo) {
            this.tipo = new SimpleStringProperty(tipo);
        }

        public int getQuantidade() {
            return quantidade.get();
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = new SimpleIntegerProperty(quantidade);
        }

        public String getData() {
            return data.get();
        }

        public void setData(String data) {
            this.data = new SimpleStringProperty(data);
        }

        public String getValor() {
            return valor.get();
        }

        public void setValor(String valor) {
            this.valor = new SimpleStringProperty(valor);
        }

    }

}
