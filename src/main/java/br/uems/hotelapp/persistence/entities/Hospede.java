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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jackson
 */

@Entity
@Table(name = "hospede")
public class Hospede implements AbstractEntity, Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;
    
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    
    @Column(name = "endereco", nullable = true, length = 255)
    private String endereco;
    
    @Column(name = "cidade", nullable = true, length = 255)
    private String cidade;
    
    @Column(name = "estado", nullable = true, length = 255)
    private String estado;
    
    @Column(name = "pais", nullable = true, length = 255)
    private String pais;
    
    @Column(name = "telefone", nullable = true, length = 255)
    private String telefone;
    
    @Column(name = "email", nullable = true, length = 255)
    private String email;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = true)
    private Date data_nascimento;
    
    @Column(name = "documento", nullable = true, length = 255)
    private String documento;
    
    @Column(name = "tipo_doc", nullable = true)
    private Integer tipo_doc;


    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setDataNascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }
    
    public void setDataNascimento(String data_nascimento, DateFormat df) {
        try {
            this.data_nascimento = df.parse(data_nascimento);
        } catch (ParseException ex) {
            //
        }
    }
    
    public Date getDataNascimento() {
        return data_nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getTipoDoc() {
        return tipo_doc;
    }

    public void setTipoDoc(Integer tipo_doc) {
        this.tipo_doc = tipo_doc;
    }
    
    @Override
    public String toString() {
        return id + " | " + nome;
    }
    
}