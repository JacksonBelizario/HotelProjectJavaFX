/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
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
@Table(name = "dados_cartao")
public class DadosCartao implements AbstractEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;

    @Column(name = "codigo_hospede", unique = true, nullable = false)
    private Integer codigo_hospede;

    @Column(name = "numero", nullable = false, length = 255)
    private String numero;

    @Column(name = "nome_cartao", nullable = false, length = 255)
    private String nome_cartao;

    @Column(name = "codigo_seguranca", nullable = false, length = 3)
    private String codigo_seguranca;

    @Column(name = "validade_mes", nullable = false, length = 2)
    private String validade_mes;

    @Column(name = "validade_ano", nullable = false, length = 2)
    private String validade_ano;

    public Integer getId() {
        return id;
    }

    public Integer getCodigoHospede() {
        return codigo_hospede;
    }

    public void setCodigoHospede(Integer codigo_hospede) {
        this.codigo_hospede = codigo_hospede;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeCartao() {
        return nome_cartao;
    }

    public void setNomeCartao(String nome_cartao) {
        this.nome_cartao = nome_cartao;
    }

    public String getCodigoSeguranca() {
        return codigo_seguranca;
    }

    public void setCodigoSeguranca(String codigo_seguranca) {
        this.codigo_seguranca = codigo_seguranca;
    }

    public String getValidadeMes() {
        return validade_mes;
    }

    public void setValidadeMes(String validade_mes) {
        this.validade_mes = validade_mes;
    }

    public String getValidadeAno() {
        return validade_ano;
    }

    public void setValidadeAno(String validade_ano) {
        this.validade_ano = validade_ano;
    }

}
