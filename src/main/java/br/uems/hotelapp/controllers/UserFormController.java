/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.MascarasFX;
import br.uems.hotelapp.utils.NumberUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private JFXTextField inputName;

    @FXML
    private JFXTextField inputTel;

    @FXML
    private JFXTextField inputEndereco;

    @FXML
    private JFXTextField inputCidade;

    @FXML
    private JFXTextField inputEstado;

    @FXML
    private JFXDatePicker inputDataNasc;

    @FXML
    private JFXTextField inputSalario;
    
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    Funcionario funcionario;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MascarasFX.mascaraTelefone(inputTel);
        NumberUtils.mascaraDinheiro(inputSalario);
    }
    
    public void editUser(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    

    @FXML
    void save(MouseEvent event) {
        
        Boolean edit = funcionario != null;

        if (!edit) {
            funcionario = new Funcionario();
        }

        if (inputName.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Informe o nome", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (inputSalario.getText().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Informe o salário", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
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
        }
        else {
            funcionarioDao.update(funcionario);
        }
        reset();
        HomeController.getController().showPlaneUsers();
    }
    
    private void reset() {
        inputName.setText("");
        inputTel.setText("");
        inputEndereco.setText("");
        inputCidade.setText("");
        inputEstado.setText("");
        inputSalario.setText("");
        funcionario = null;
    }
}
