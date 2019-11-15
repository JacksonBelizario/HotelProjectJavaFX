/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.enums.TipoDocumento;
import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.utils.DateUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jackson
 */
public class CustomerFormController implements Initializable {

    @FXML
    private Pane pnlCustomerForm;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXTextField inputName, inputTel, inputEndereco, inputCidade, inputEstado, inputPais, inputEmail, inputDoc;

    @FXML
    private JFXDatePicker inputDataNasc;

    @FXML
    private JFXComboBox<TipoDocumento> cbTipoDocumento;

    private TipoDocumento tipoDocumento;

    private Label label = new Label();

    HospedeDao hospedeDao = new HospedeDao();
    Hospede hospede;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reset();
        MasksUtils.foneField(inputTel);
        ValidatorUtils.setValidator(inputName, "Informe o nome");

        cbTipoDocumento.getItems().setAll(Arrays.asList(TipoDocumento.values()));
    }

    @FXML
    void setTipoDocumento(ActionEvent event) {
        tipoDocumento = cbTipoDocumento.getSelectionModel().getSelectedItem();
    }

    public void edit(Hospede hospede) {
        this.hospede = hospede;
        inputName.setText(hospede.getNome());
        inputTel.setText(hospede.getTelefone());
        inputEndereco.setText(hospede.getEndereco());
        inputCidade.setText(hospede.getCidade());
        inputEstado.setText(hospede.getEstado());
        inputPais.setText(hospede.getPais());
        inputEmail.setText(hospede.getEmail());
        inputDoc.setText(hospede.getDocumento());
        inputDataNasc.setValue(DateUtils.toLocalDate(hospede.getDataNascimento()));
        tipoDocumento = TipoDocumento.values()[hospede.getTipoDoc()];
        cbTipoDocumento.getSelectionModel().select(tipoDocumento);
    }

    @FXML
    void save(MouseEvent event) {

        Boolean edit = hospede != null;

        if (inputName.getText().isEmpty()) {
            inputName.validate();
            return;
        }

        if (!edit) {
            hospede = new Hospede();
        }

        hospede.setNome(inputName.getText());
        hospede.setTelefone(inputTel.getText());
        hospede.setEndereco(inputEndereco.getText());
        hospede.setCidade(inputCidade.getText());
        hospede.setEstado(inputEstado.getText());
        hospede.setPais(inputPais.getText());
        hospede.setEmail(inputEmail.getText());
        hospede.setDocumento(inputDoc.getText());
        hospede.setDataNascimento(DateUtils.toDate(inputDataNasc.getValue()));
        hospede.setTipoDoc(tipoDocumento.ordinal());

        if (!edit) {
            hospedeDao.save(hospede);
            HomeController.getController().showSnackBar("Hospede inserido");
        } else {
            hospedeDao.update(hospede);
            HomeController.getController().showSnackBar("Hospede atualizado");
        }
        reset();
        CustomersController.getController().loadData();
        HomeController.getController().showPlaneCustomers();
    }

    private void reset() {
        inputName.clear();
        inputTel.clear();
        inputEndereco.clear();
        inputCidade.clear();
        inputEstado.clear();
        inputPais.clear();
        inputEmail.clear();
        inputDoc.clear();
        hospede = null;
        tipoDocumento = TipoDocumento.RG;
        cbTipoDocumento.getSelectionModel().select(TipoDocumento.RG);
    }

    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showPlaneCustomers();
    }
}
