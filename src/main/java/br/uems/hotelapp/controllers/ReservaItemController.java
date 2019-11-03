/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Acomodacao;
import br.uems.hotelapp.utils.NumberUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jackson
 */
public class ReservaItemController {

    @FXML
    private HBox itemReserva;

    @FXML
    private Label labelQuarto;

    @FXML
    private Label labelAndar;

    @FXML
    private Label labelTipo;

    @FXML
    private Label labelQtdeAdultos;

    @FXML
    private Label labelQtdeCriancas;

    @FXML
    private Label labelPreco;

    @FXML
    private Button btnBooking;
    
    
    public void setData(Acomodacao acomodacao) {
        try {
            labelQuarto.setText("Quarto " + acomodacao.getId());
            labelAndar.setText("Andar "+ acomodacao.getAndar());
            labelTipo.setText(acomodacao.getTipoAcomodacao().toString());
            labelPreco.setText("R$ " + NumberUtils.formatNumber(acomodacao.getTipoAcomodacao().getValorDiaria()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
