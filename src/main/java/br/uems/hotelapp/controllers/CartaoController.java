/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.DadosCartaoDao;
import br.uems.hotelapp.persistence.entities.DadosCartao;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CartaoController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXTextField inputTitular, inputNumCartao, inputMesVal, inputAnoVal, inputCVV;

    @FXML
    private JFXButton btnCancel, btnSave;

    DadosCartaoDao dadosCartaoDao = new DadosCartaoDao();
    DadosCartao dadosCartao;
    Boolean edit = true;
    Hospede hospede;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MasksUtils.maxField(inputMesVal, 2);
        MasksUtils.maxField(inputAnoVal, 2);
        MasksUtils.maxField(inputCVV, 3);
        MasksUtils.onlyDigitsValue(inputMesVal);
        MasksUtils.onlyDigitsValue(inputAnoVal);
        MasksUtils.onlyDigitsValue(inputCVV);
        ValidatorUtils.setValidator(inputTitular, "Informe o nome");
        ValidatorUtils.setValidator(inputNumCartao, "Informe o número do cartão");
        ValidatorUtils.setValidator(inputMesVal, "Informe o mês");
        ValidatorUtils.setValidator(inputAnoVal, "Informe o ano");
        ValidatorUtils.setValidator(inputCVV, "Informe o código");
    }

    @FXML
    void cancel(ActionEvent event) {
        closeWindow();
    }

    @FXML
    void save(ActionEvent event) {
        if (inputNumCartao.getText().trim().isEmpty()
                || inputTitular.getText().trim().isEmpty()
                || inputCVV.getText().trim().isEmpty()
                || inputMesVal.getText().trim().isEmpty()
                || inputAnoVal.getText().trim().isEmpty()) {
            inputNumCartao.validate();
            inputTitular.validate();
            inputCVV.validate();
            inputMesVal.validate();
            inputAnoVal.validate();
            return;
        }
        dadosCartao.setNumero(inputNumCartao.getText());
        dadosCartao.setNomeCartao(inputTitular.getText());
        dadosCartao.setCodigoSeguranca(inputCVV.getText());
        dadosCartao.setValidadeMes(inputMesVal.getText());
        dadosCartao.setValidadeAno(inputAnoVal.getText());

        if (!edit) {
            dadosCartaoDao.save(dadosCartao);
        } else {
            dadosCartaoDao.update(dadosCartao);
        }
        closeWindow();
    }

    public void setCustomer(Hospede hospede) {
        this.hospede = hospede;
        dadosCartao = dadosCartaoDao.findByHospede(hospede.getId());
        edit = dadosCartao != null;

        if (edit) {
            inputNumCartao.setText(dadosCartao.getNumero());
            inputTitular.setText(dadosCartao.getNomeCartao());
            inputMesVal.setText(dadosCartao.getValidadeMes());
            inputAnoVal.setText(dadosCartao.getValidadeAno());
            inputCVV.setText(dadosCartao.getCodigoSeguranca());
        } else {
            dadosCartao = new DadosCartao();
            dadosCartao.setCodigoHospede(hospede.getId());
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

}
