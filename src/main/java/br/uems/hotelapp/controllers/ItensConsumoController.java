/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class ItensConsumoController implements Initializable {
    

    @FXML
    private Pane pnlConsumable;

    @FXML
    private VBox pnListItens;

    @FXML
    private JFXTextField inputNome;

    @FXML
    private JFXTextField inputDescricao;

    @FXML
    private JFXTextField inputPreco;

    @FXML
    private JFXButton btnSave;

    @FXML
    void save(MouseEvent event) {

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    
        dummyData();
    }
    
    public void dummyData() {
        try {
            final Node[] nodes = new Node[10];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/ItemConsumo.fxml"));

                pnListItens.getChildren().add(nodes[i]);
            }
            
        }catch(Exception ex) {
        }

    }
    
}
