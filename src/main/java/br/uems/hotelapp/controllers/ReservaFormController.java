/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.AcomodacaoDao;
import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.dao.ReservaDao;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.AlertMaker;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.apache.commons.lang3.StringUtils;

public class ReservaFormController implements Initializable {

    @FXML
    private Pane pnlReservaForm;

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXComboBox<Hospede> cbCustomers;

    @FXML
    private JFXTextField inputQtdeAdultos, inputQtdeCriancas;

    @FXML
    private JFXDatePicker inputStartDate, inputEndDate;

    @FXML
    private JFXButton btnSearch, btnReset;

    @FXML
    private VBox pnReservas;
    
    private ObservableList<Hospede> obCustomers;
    
    private ReservaDao reservaDao = new ReservaDao();
    
    private HospedeDao hospedeDao = new HospedeDao();
    
    private Hospede hospede = null;
        
    private AcomodacaoDao acomodacaoDao = new AcomodacaoDao();

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadValidators();
        getCustomers();
        reset();
    }

    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showPlaneBookings();
    }
    
    @FXML
    void onEndDate(ActionEvent event) {
        LocalDate maxDate = inputEndDate.getValue().minusDays(1);
        DateUtils.setDatePickerLimit(inputStartDate, LocalDate.now(), maxDate);
    }

    @FXML
    void onStartDate(ActionEvent event) {
        LocalDate minDate = inputStartDate.getValue().plusDays(1);
        DateUtils.setDatePickerLimit(inputEndDate, minDate, null);
    }

    @FXML
    void search(MouseEvent event) {
        
        cbCustomers.validate();
        inputStartDate.validate();
        inputEndDate.validate();
        
        if (StringUtils.strip(inputQtdeAdultos.getText(), "0").isEmpty()) {
            inputQtdeAdultos.clear();
            inputQtdeAdultos.validate();
            return;
        }
        
        if (StringUtils.strip(inputQtdeCriancas.getText(), "0").isEmpty()) {
            inputQtdeCriancas.setText("0");
        }
        
        if (hospede == null || inputStartDate.getValue() == null || inputEndDate.getValue() == null) {
            return;
        }
        findFreeRooms();
    }
    
    void reservar(Acomodacao acomodacao) {
        Reserva reserva = new Reserva();
        
        reserva.setAcomodacao(acomodacao);
        reserva.setValorDiaria(acomodacao.getTipoAcomodacao().getValorDiaria());
        reserva.setDataHoraChegada(DateUtils.toDate(inputStartDate.getValue()));
        reserva.setDataHoraSaida(DateUtils.toDate(inputEndDate.getValue()));
        reserva.setHospede(hospede);
        reserva.setQtdeAdulto(Integer.parseInt(inputQtdeAdultos.getText()));
        reserva.setQtdeCrianca(Integer.parseInt(inputQtdeCriancas.getText()));
        
        reservaDao.save(reserva);
        reset();
        HomeController.getController().showPlaneBookings();
        HomeController.getController().showSnackBar("Reserva efetuada");
        ReservasController.getController().loadItens();
    }

    @FXML
    void setHospede(ActionEvent event) {
        hospede = cbCustomers.getSelectionModel().getSelectedItem();
    }
    
    private void findFreeRooms() {
        pnReservas.getChildren().clear();
        
        List<Acomodacao> acomodacoes = acomodacaoDao.findFreeRooms(
            Integer.parseInt(inputQtdeAdultos.getText()),
            Integer.parseInt(inputQtdeCriancas.getText()),
            DateUtils.toDate(inputStartDate.getValue()),
            DateUtils.toDate(inputEndDate.getValue())
        );
        
        if (acomodacoes.isEmpty()) {
            AlertMaker.showSimpleAlert("Não há quartos disponíveis", "Altere os filtros e tente novamente.");
            return;
        }
        
        cbCustomers.setDisable(true);
        inputStartDate.setDisable(true);
        inputEndDate.setDisable(true);
        inputQtdeAdultos.setDisable(true);
        inputQtdeCriancas.setDisable(true);

        Iterator<Acomodacao> acomodacoesIterator = acomodacoes.iterator();
        while (acomodacoesIterator.hasNext()){
            addItem((Acomodacao) acomodacoesIterator.next());
        }
    }

    private void addItem(Acomodacao acomodacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ReservaItem.fxml"));
            Node node = loader.load();
            pnReservas.getChildren().add(node);

            ReservaItemController controller = loader.<ReservaItemController>getController();
            controller.setData(acomodacao);

            Button btnEdit = (Button) node.lookup("#btnBooking");
            btnEdit.setOnMouseClicked((MouseEvent mouseEvent) -> {
                reservar(acomodacao);
            });
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }
    }
    
    private void reset() {
        hospede = null;
        cbCustomers.getSelectionModel().select(hospede);
        inputStartDate.setValue(null);
        inputEndDate.setValue(null);
        inputQtdeAdultos.clear();
        inputQtdeCriancas.clear();
        cbCustomers.setDisable(false);
        inputStartDate.setDisable(false);
        inputEndDate.setDisable(false);
        inputQtdeAdultos.setDisable(false);
        inputQtdeCriancas.setDisable(false);
        
        DateUtils.setDatePickerLimit(inputStartDate, LocalDate.now(), null);
        DateUtils.setDatePickerLimit(inputEndDate, LocalDate.now().plusDays(1), null);
        pnReservas.getChildren().clear();
    }
    
    
    private void getCustomers() {
        obCustomers = FXCollections.observableArrayList(hospedeDao.getAll());
        cbCustomers.setItems(obCustomers);
    }
    
    private void loadValidators() {
        MasksUtils.onlyDigitsValue(inputQtdeAdultos);
        MasksUtils.onlyDigitsValue(inputQtdeCriancas);
        
        ValidatorUtils.setValidator(inputQtdeAdultos, "Informe o número de adultos");
        ValidatorUtils.setValidator(cbCustomers, "Informe o hóspede");
        ValidatorUtils.setValidator(inputStartDate, "Informe a data de chegada");
        ValidatorUtils.setValidator(inputEndDate, "Informe a data de saída");
    }
    
    

    @FXML
    void reset(MouseEvent event) {
        reset();
    }

}
