/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Hospede;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jackson
 */
public class CustomerItemController {

    @FXML
    private HBox box;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelEnd;

    @FXML
    private Label labelCidade;

    @FXML
    private Label labelEstado;

    @FXML
    private Label labelTel;

    @FXML
    private Label labelEmail;

    @FXML
    private ImageView btnCard;

    @FXML
    private ImageView btnEdit;

    @FXML
    private ImageView btnDel;


    @FXML
    void onCard(MouseEvent event) {

    }

    @FXML
    void onEdit(MouseEvent event) {

    }

    @FXML
    void onRemove(MouseEvent event) {

    }
    
    public void setData(Hospede hospede) {
        try {
            labelNome.setText(hospede.getNome());
            labelEnd.setText(hospede.getEndereco());
            labelCidade.setText(hospede.getCidade());
            labelEstado.setText(hospede.getEstado());
            labelTel.setText(hospede.getTelefone());
            labelEmail.setText(hospede.getEmail());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public ImageView getBtnEdit() {
        return btnEdit;
    }
    
    public ImageView getBtnDel() {
        return btnDel;
    }
}
