package br.uems.hotelapp.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class RelatoriosController {

    @FXML
    private Pane pnlBooking;

    @FXML
    private JFXButton btnHospedesAtuais;

    @FXML
    private JFXButton btnReservasAtuais;

    @FXML
    void showReport(ActionEvent event) {
        if (event.getSource() == btnHospedesAtuais) {
        }
        if (event.getSource() == btnReservasAtuais) {
        }
    }

}
