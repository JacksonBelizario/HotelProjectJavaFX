package br.uems.hotelapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ItemController {

    @FXML
    private HBox item;

    @FXML
    private Label labelQuarto;

    @FXML
    private Label labelHospede;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelEndDate;

    @FXML
    private Label labelPreco;

    @FXML
    private Button btnStatus;
    
    public Button getBtnStatus() {
        return btnStatus;
    }

}
