/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.ReservaDao;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.AlertMaker;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class ReservasController implements Initializable {
    
    public static ReservasController controller;
    
    public static ReservasController getController() {
        return controller;
    }

    private static void setController(ReservasController controller) {
        ReservasController.controller = controller;
    }
    
    @FXML
    private Pane pnlBooking;

    @FXML
    private VBox pnReservas;
    
    private ReservaDao reservaDao = new ReservaDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setController(this);
        loadItens();
    }

    @FXML
    void novaReserva(MouseEvent event) {
        HomeController.getController().showReservaForm();
    }
    
    
    public void loadItens() {
        try {
            pnReservas.getChildren().clear();

            List<Reserva> reservas = reservaDao.getAll();

            Iterator<Reserva> reservasIterator = reservas.iterator();
            while (reservasIterator.hasNext()) {
                addItem((Reserva) reservasIterator.next());
            }
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }
    }

    private void addItem(Reserva reserva) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));
            Node node = loader.load();
            pnReservas.getChildren().add(node);

            ItemController controller = loader.<ItemController>getController();
            controller.setData(reserva);
    }
    
    public void dummyData() throws IOException {
        
        final Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));

            pnReservas.getChildren().add(nodes[i]);
        }

    }
    
}
