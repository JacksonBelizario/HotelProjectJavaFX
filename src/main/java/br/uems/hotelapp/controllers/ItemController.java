package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.NumberUtils;
import javafx.event.ActionEvent;
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
    
    public void setData(Estadia estadia) {
        labelQuarto.setText(estadia.getAcomodacao().toString());
        labelHospede.setText(estadia.getHospede().toString());
        labelStartDate.setText(DateUtils.format(estadia.getDataHoraInicio()));
        labelEndDate.setText(DateUtils.format(estadia.getDataHoraTermino()));
        
        Integer dias = DateUtils.diffInDays(estadia.getDataHoraInicio(), estadia.getDataHoraTermino());
        
        labelPreco.setText(NumberUtils.formatCurrency(dias * estadia.getReserva().getValorDiaria()));
    }

    @FXML
    void onAction(ActionEvent event) {

    }

}
