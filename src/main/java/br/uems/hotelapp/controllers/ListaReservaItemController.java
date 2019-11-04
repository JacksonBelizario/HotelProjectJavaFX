package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.NumberUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ListaReservaItemController {

    @FXML
    private HBox item;

    @FXML
    private Label labelQuarto, labelHospede, labelStartDate, labelEndDate, labelPreco;

    @FXML
    private Button btnStatus, btnCancel;
    
    public void setData(Reserva reserva) {
        labelQuarto.setText(reserva.getAcomodacao().toString());
        labelHospede.setText(reserva.getHospede().toString());
        labelStartDate.setText(DateUtils.format(reserva.getDataHoraChegada()));
        labelEndDate.setText(DateUtils.format(reserva.getDataHoraSaida()));
        
        Integer dias = DateUtils.diffInDays(reserva.getDataHoraChegada(), reserva.getDataHoraSaida());
        
        labelPreco.setText("R$ " + NumberUtils.formatNumber(dias * reserva.getValorDiaria()));
    }
    
    public Button getBtnStatus() {
        return btnStatus;
    }
    
    public Button getBtnCancel() {
        return btnCancel;
    }

}
