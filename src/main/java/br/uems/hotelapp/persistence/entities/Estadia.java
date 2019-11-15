/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.persistence.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jackson
 */
@Entity
@Table(name = "estadia")
public class Estadia implements AbstractEntity, Serializable {

    public static int STATUS_OCUPADO = 0;
    public static int STATUS_PAGO = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo", unique = true, nullable = false)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_inicio", nullable = false)
    private Date dataHoraInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_termino", nullable = true)
    private Date dataHoraTermino;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_reserva", nullable = false, updatable = false)
    Reserva reserva;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_hospede", nullable = false, updatable = false)
    Hospede hospede;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_funcionario", updatable = false)
    Funcionario funcionario;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo_acomodacao", nullable = false, updatable = false)
    Acomodacao acomodacao;

    @OneToMany(mappedBy = "estadia")
    private List<Consumo> consumos;

    @OneToOne(mappedBy = "estadia")
    private Pagamento pagamento;

    @Override
    public Integer getId() {
        return id;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(Date dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

}
