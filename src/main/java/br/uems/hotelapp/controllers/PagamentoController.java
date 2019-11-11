/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;


import br.uems.hotelapp.enums.FormaPagamento;
import br.uems.hotelapp.enums.TipoDocumento;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PagamentoController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private Label labelPrecoEstadia, labelPrecoConsumo, labelPrecoTotal;

    @FXML
    private JFXComboBox<FormaPagamento> cbFormaPagamento;

    @FXML
    private JFXDatePicker inputDataVenc;

    @FXML
    private JFXTextField inputDesconto, inputMulta;

    @FXML
    private JFXButton btnCancel, btnSave, btnPay;
    
    FormaPagamento formaPagamento;
    Hospede hospede;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ValidatorUtils.setValidator(cbFormaPagamento, "Informe a forma de pagamento");
        ValidatorUtils.setValidator(inputDataVenc, "Informe a data de Vencimento");
        
        cbFormaPagamento.getItems().setAll(Arrays.asList(FormaPagamento.values()));
    }

    @FXML
    void setFormaPagamento(ActionEvent event) {
        formaPagamento = cbFormaPagamento.getSelectionModel().getSelectedItem();
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void save(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void pay(ActionEvent event) {
        closeWindow();
    }
    
    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
