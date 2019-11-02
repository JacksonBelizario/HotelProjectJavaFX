/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ReservasController implements Initializable {
    
    
    @FXML
    private Pane pnlBooking;

    @FXML
    private VBox pnReservas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            dummyData();
        } catch (IOException ex) {
            Logger.getLogger(ReservasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void novaReserva(MouseEvent event) {
        HomeController.getController().showReservaForm();
    }
    
    public void dummyData() throws IOException {
        
        final Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));

            pnReservas.getChildren().add(nodes[i]);
        }

    }
    
}
