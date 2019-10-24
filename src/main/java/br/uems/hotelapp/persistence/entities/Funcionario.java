/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import br.uems.hotelapp.utils.DateUtils;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
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
@Table(name = "funcionario")
public class Funcionario implements AbstractEntity, Serializable {
    
    /**
     * 
     */
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
    
    @Column(name = "telefone", nullable = true, length = 255)
    private String telefone;
    
    @Column(name = "data_nascimento", nullable = true, length = 255)
    private Date data_nascimento;
    
    @Column(name = "salario", nullable = true, length = 255)
    private Double salario;


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
    
    public void setDataNascimento(LocalDate data_nascimento) {
        this.data_nascimento = DateUtils.toDate(data_nascimento);
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
    
    public void setSalario(Double salario) {
        this.salario = salario;
    }
    
    public void setSalario(String salario) {
        try {
            this.salario = Double.parseDouble(salario);
        } catch (NumberFormatException ex) {
            //
        }
    }
    
    public Double getSalario() {
        return salario;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}