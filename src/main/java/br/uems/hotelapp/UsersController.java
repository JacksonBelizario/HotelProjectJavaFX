/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp;

import java.io.IOException;
import java.util.Iterator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class UsersController {
    
    @FXML
    final private VBox pnListUsers = null;
    
    public void dummyData() {
        try {
        
            final Node[] nodes = new Node[3];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));

                pnListUsers.getChildren().add(nodes[i]);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
