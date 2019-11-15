/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Acomodacao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jackson
 */
public class AcomodacaoItemController {

    @FXML
    private Label labelDesc;

    @FXML
    private Label labelTipo;

    @FXML
    private Label labelAndar;

    @FXML
    private ImageView btnEdit;

    @FXML
    private ImageView btnDel;

    public void setData(Acomodacao acomodacao) {
        try {
            labelDesc.setText("Quarto " + acomodacao.getId());
            labelTipo.setText(acomodacao.getTipoAcomodacao().toString());
            labelAndar.setText("Andar " + acomodacao.getAndar());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
