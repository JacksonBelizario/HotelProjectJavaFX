/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jackson
 */
public class UserFormController {

    @FXML
    private Pane pnlUserForm;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField inputName;

    @FXML
    private JFXTextField inputTel;

    @FXML
    private JFXTextField inputEndereco;

    @FXML
    private JFXTextField inputCidade;

    @FXML
    private JFXTextField inputEstado;

    @FXML
    private JFXDatePicker inputDataNasc;

    @FXML
    private JFXTextField inputSalario;
    

    @FXML
    void save(MouseEvent event) {
        HomeController.getController().showPlaneUsers();
    }
}
