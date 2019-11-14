package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.entities.Hospede;
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
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
        
        List<String> columns = Arrays.asList("Nome", "Cidade", "Documento");
        addColumns(columns);
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList(); 
        
        List<Hospede> hospedes = hospedeDao.getAll();

        Iterator<Hospede> hospedesIterator = hospedes.iterator();
        while (hospedesIterator.hasNext()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            Hospede hospede = (Hospede) hospedesIterator.next();
            row.add(hospede.getNome());
            row.add(hospede.getCidade());
            row.add(hospede.getDocumento());
            data.add(row); 
        }
        tableReport.setItems(data);
    }
    
    private void showReservasAtuais() {
        
        List<String> columns = Arrays.asList("Nome2", "Cidade2", "Documento2");
        addColumns(columns);
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList(); 
        
        List<Hospede> hospedes = hospedeDao.getAll();

        Iterator<Hospede> hospedesIterator = hospedes.iterator();
        while (hospedesIterator.hasNext()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            Hospede hospede = (Hospede) hospedesIterator.next();
            row.add(hospede.getNome());
            row.add(hospede.getCidade());
            row.add(hospede.getDocumento());
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
