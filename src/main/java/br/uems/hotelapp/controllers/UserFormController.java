/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.DateUtils;
import br.uems.hotelapp.utils.MascarasFX;
import br.uems.hotelapp.utils.MasksUtils;
import br.uems.hotelapp.utils.NumberUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
    
    private Label label = new Label();
    
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    Funcionario funcionario;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        MascarasFX.mascaraTelefone(inputTel);
        MasksUtils.foneField(inputTel);
        NumberUtils.mascaraDinheiro(inputSalario);
    }
    
    public void editUser(Funcionario funcionario) {
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

        if (!edit) {
            funcionario = new Funcionario();
        }

        if (inputName.getText().isEmpty()) {
            snackBar("Informe o nome", 5);
            return;
        }

        if (inputSalario.getText().isEmpty()) {
            snackBar("Informe o salário", 5);
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
        UsersController.getController().fillData();
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
    
    @FXML
    void back(MouseEvent event) {
        reset();
        HomeController.getController().showPlaneUsers();
    }
    
    public void snackBar(String message, long duration) {
        JFXSnackbar bar = new JFXSnackbar(pnlUserForm);
        label.setText(message);
        label.setTextFill(Color.web("#FFFFFF"));
        bar.enqueue(new SnackbarEvent(label, Duration.seconds(duration), null));
    }
}
