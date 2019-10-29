/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.NumberUtils;
import java.text.NumberFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jackson
 */
public class UserItemController {

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
    private Label labelSalario;

    @FXML
    private ImageView btnEdit;

    @FXML
    private ImageView btnDel;


    @FXML
    void onEdit(MouseEvent event) {

    }

    @FXML
    void onRemove(MouseEvent event) {

    }
    
    public void setUser(Funcionario funcionario) {
        try {
            labelNome.setText(funcionario.getNome());
            labelEnd.setText(funcionario.getEndereco());
            labelCidade.setText(funcionario.getCidade());
            labelEstado.setText(funcionario.getEstado());
            labelTel.setText(funcionario.getTelefone());
            labelSalario.setText("R$ " + NumberUtils.formatNumber(funcionario.getSalario()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
