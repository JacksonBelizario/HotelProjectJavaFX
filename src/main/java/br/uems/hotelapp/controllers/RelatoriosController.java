package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.EstadiaDao;
import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.dao.ReservaDao;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.DateUtils;
import com.jfoenix.controls.JFXButton;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;

public class RelatoriosController {

    @FXML
    private Pane pnlBooking;

    @FXML
    private JFXButton btnHospedesAtuais;

    @FXML
    private JFXButton btnReservasAtuais;

    @FXML
    private TableView<ObservableList<String>> tableReport;
    
    EstadiaDao estadiaDao = new EstadiaDao();
    ReservaDao reservaDao = new ReservaDao();
    HospedeDao hospedeDao = new HospedeDao();

    @FXML
    void showReport(ActionEvent event) {
        tableReport.getItems().clear();
        tableReport.getColumns().clear();
        if (event.getSource() == btnHospedesAtuais) {
            showHospedesAtuais();
        }
        if (event.getSource() == btnReservasAtuais) {
            showReservasAtuais();
        }

    }
    
    private void showHospedesAtuais() {
        
        List<String> columns = Arrays.asList("Hospede", "Data Início", "Data Término", "Quarto");
        addColumns(columns);
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList(); 
        
        List<Estadia> estadias = estadiaDao.getCurrents();

        Iterator<Estadia> estadiasIterator = estadias.iterator();
        while (estadiasIterator.hasNext()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            Estadia estadia = (Estadia) estadiasIterator.next();
            Hospede hospede = estadia.getHospede();
            row.add(hospede.getNome());
            row.add(DateUtils.format(estadia.getDataHoraInicio()));
            row.add(DateUtils.format(estadia.getDataHoraTermino()));
            row.add(estadia.getAcomodacao().toString());
            data.add(row); 
        }
        tableReport.setItems(data);
    }
    
    private void showReservasAtuais() {
        
        List<String> columns = Arrays.asList("Nome", "Telefone", "Data Entrada", "Data Saída", "Quarto");
        addColumns(columns);
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList(); 
        
        List<Reserva> reservas = reservaDao.getBookingsforToday();

        Iterator<Reserva> reservasIterator = reservas.iterator();
        while (reservasIterator.hasNext()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            Reserva reserva = (Reserva) reservasIterator.next();
            Hospede hospede = reserva.getHospede();
            row.add(hospede.getNome());
            row.add(hospede.getTelefone());
            row.add(DateUtils.format(reserva.getDataHoraChegada()));
            row.add(DateUtils.format(reserva.getDataHoraSaida()));
            row.add(reserva.getAcomodacao().toString());
            data.add(row); 
        }
        tableReport.setItems(data);
    }
    
    private void addColumns(List<String> columns) {
        for (int i = 0; i < columns.size(); i++) {
            final int finalIdx = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(columns.get(i));

            column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx)));

            tableReport.getColumns().add(column);
        }
    }

}
