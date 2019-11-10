/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.NumberUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
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
public class UserFormController implements Initializable {

    @FXML
    private Pane pnlUserForm;

    @FXML
    private JFXButton btnSave;

    @FXML
    private ImageView btnBack;

    @FXML
    private JFXTextField inputName, inputTel, inputEndereco, inputCidade, inputEstado, inputSalario;

    @FXML
    private JFXDatePicker inputDataNasc;

    private Label label = new Label();

    FuncionarioDao funcionarioDao = new FuncionarioDao();
    Funcionario funcionario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MasksUtils.foneField(inputTel);
        NumberUtils.mascaraDinheiro(inputSalario);
        ValidatorUtils.setValidator(inputName, "Informe o nome");
        ValidatorUtils.setValidator(inputSalario, "Informe o salário");
    }

    public void edit(Funcionario funcionario) {
        this.funcionario = funcionario;
        inputName.setText(funcionario.getNome());
        inputTel.setText(funcionario.getTelefone());
        inputEndereco.setText(funcionario.getEndereco());
        inputCidade.setText(funcionario.getCidade());
        inputEstado.setText(funcionario.getEstado());
        inputSalario.setText(NumberUtils.formatNumber(funcionario.getSalario()));
        inputDataNasc.setValue(DateUtils.toLocalDate(funcionario.getDataNascimento()));
    }

    @FXML
    void save(MouseEvent event) {

        Boolean edit = funcionario != null;

        if (inputName.getText().isEmpty()) {
            inputName.validate();
            return;
        }

        if (inputSalario.getText().isEmpty()) {
            inputSalario.validate();
            return;
        }

        if (!edit) {
            funcionario = new Funcionario();
        }

        funcionario.setNome(inputName.getText());
        funcionario.setTelefone(inputTel.getText());
        funcionario.setEndereco(inputEndereco.getText());
        funcionario.setCidade(inputCidade.getText());
        funcionario.setEstado(inputEstado.getText());
        funcionario.setDataNascimento(inputDataNasc.getValue());
        funcionario.setSalario(NumberUtils.getNumericValue(inputSalario.getText()));

        if (!edit) {
            funcionarioDao.save(funcionario);
            HomeController.getController().showSnackBar("Funcionário inserido");
        } else {
            funcionarioDao.update(funcionario);
            HomeController.getController().showSnackBar("Funcionário atualizado");
        }
        reset();
        UsersController.getController().loadData();
        HomeController.getController().showPlaneUsers();
    }

    private void reset() {
        inputName.clear();
        inputTel.clear();
        inputEndereco.clear();
        inputCidade.clear();
        inputEstado.clear();
        inputSalario.clear();
        funcionario = null;
    }

    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showPlaneUsers();
    }
}
