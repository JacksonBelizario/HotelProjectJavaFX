/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.FuncionarioDao;
import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class UsersController implements Initializable {
    
    
    public static UsersController controller;
    
    public static UsersController getController() {
        return controller;
    }

    private static void setController(UsersController controller) {
        UsersController.controller = controller;
    }
    
    @FXML
    private Pane pnlUsers;

    @FXML
    private VBox pnListUsers;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setController(this);
        fillData();
    }
    
    
    public void fillData() {
        pnListUsers.getChildren().clear();
        
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        
        List<Funcionario> funcionarios = funcionarioDao.getAll();

        Iterator<Funcionario> funcionariosIterator = funcionarios.iterator();
        while (funcionariosIterator.hasNext()){
            Funcionario funcionario = (Funcionario) funcionariosIterator.next();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserItem.fxml"));
                Node node = loader.load();
                pnListUsers.getChildren().add(node);

                UserItemController controller = loader.<UserItemController>getController();
                controller.setUser(funcionario);
                
                ImageView btnEdit = (ImageView) node.lookup("#btnEdit");
                btnEdit.setOnMouseClicked((MouseEvent mouseEvent) -> {
                    HomeController.getController().showUserForm(funcionario);
                });
                
                
                ImageView btnDel = (ImageView) node.lookup("#btnDel");
                btnDel.setOnMouseClicked((MouseEvent mouseEvent) -> {
                    
                    JFXButton noButton = new JFXButton("Não");
                    noButton.getStyleClass().add("btn-secondary");
                    
                    JFXButton yesButton = new JFXButton("Sim");
                    yesButton.getStyleClass().add("btn-danger");
                    yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                        funcionarioDao.delete(funcionario);
                        pnListUsers.getChildren().remove(node);
                    });
                    
                    HomeController.getController().showMaterialDialog(Arrays.asList(noButton, yesButton), "Remover funcionário?", "Esta ação não pode ser desfeita!");
                });
                

            } catch (Exception e) {
            }

        }
    }

    @FXML
    void addUser(MouseEvent event) {
        HomeController.getController().showUserForm();
    }
}
