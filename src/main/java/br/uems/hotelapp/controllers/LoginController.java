/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.utils.AppUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane stackPane;
    
    @FXML
    private JFXTextField inputUsername;
    
    @FXML
    private JFXPasswordField inputPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ValidatorUtils.setValidator(inputUsername, "Usuário inválido");
        ValidatorUtils.setValidator(inputPassword, "Senha incorreta");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String uname = inputUsername.getText();
        String pword = DigestUtils.sha1Hex(inputPassword.getText());
        
        String username = "admin";
        String password = DigestUtils.sha1Hex("12345");

        if (uname.equals(username) && pword.equals(password)) {
            loadMain();
            closeStage();
        }
        else {
            inputUsername.clear();
            inputPassword.clear();
            inputUsername.validate();
            inputPassword.validate();
        }
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) stackPane.getScene().getWindow()).close();
    }

    void loadMain() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Scene scene = new Scene(parent);
            AppUtils.setSceneStyle(scene);
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setTitle("Sistema Hoteleiro");
            stage.setScene(scene);
            stage.show();
            AppUtils.setStageIcon(stage);
        }
        catch (IOException ex) {
        }
    }

}