package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.NumberUtils;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
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
    
    public void setData(Reserva reserva) {
        labelQuarto.setText(reserva.getAcomodacao().toString());
        labelHospede.setText(reserva.getHospede().toString());
        labelStartDate.setText(DateUtils.format(reserva.getDataHoraChegada()));
        labelEndDate.setText(DateUtils.format(reserva.getDataHoraSaida()));
        
        Period periodo = Period.between(
            DateUtils.toLocalDate(reserva.getDataHoraChegada()), 
            DateUtils.toLocalDate(reserva.getDataHoraSaida())
        );
        labelPreco.setText("R$ " + NumberUtils.formatNumber(periodo.getDays() * reserva.getValorDiaria()));
        
        btnStatus.setText("Confirmar");
    }

}
