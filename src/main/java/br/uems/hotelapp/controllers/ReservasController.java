/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.ReservaDao;
import br.uems.hotelapp.persistence.dao.EstadiaDao;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.Reserva;
import br.uems.hotelapp.utils.AlertMaker;
import br.uems.hotelapp.utils.DateUtils;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.Dimension;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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

    @FXML
    private JFXToggleButton toggleButton;
    
    private ReservaDao reservaDao = new ReservaDao();
    
    private EstadiaDao estadiaDao = new EstadiaDao();

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
        pnReservas.getChildren().clear();
        try {
            List<Reserva> reservas = null;
            if (toggleButton.isSelected()) {
                reservas = reservaDao.getCurrents();
            } else {
                reservas = reservaDao.getAll();
            }

            Iterator<Reserva> reservasIterator = reservas.iterator();
            while (reservasIterator.hasNext()) {
                addItem((Reserva) reservasIterator.next());
            }
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }
    }

    private void addItem(Reserva reserva) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListaReservaItem.fxml"));
            Node node = loader.load();
            pnReservas.getChildren().add(node);

            ListaReservaItemController controller = loader.<ListaReservaItemController>getController();
            controller.setData(reserva);
            
            Estadia estadia = reserva.getEstadia();

            Button btnStatus = controller.getBtnStatus();
            Button btnCancel = controller.getBtnCancel();
            
            if (estadia != null) {
                btnStatus.setText("Confirmado");
                btnStatus.getStyleClass().add("btn-round-secondary");
                btnStatus.setPrefWidth(180);
                btnCancel.setVisible(false);
                btnCancel.setManaged(false);
            }
            else {
                if(reserva.getDataHoraChegada().after(Calendar.getInstance().getTime())) {
                    btnStatus.setText("Reservado");
                    btnStatus.getStyleClass().add("btn-round-disabled");
                } else {
                    btnStatus.setOnMouseClicked((MouseEvent mouseEvent) -> {
                        confirmarEstadia(reserva);
                    });
                }
                btnCancel.setOnMouseClicked((MouseEvent mouseEvent) -> {
                    cancelarReserva(reserva);
                });
            }
    }
    
    private void confirmarEstadia(Reserva reserva) {
        Estadia estadia = new Estadia();
        estadia.setReserva(reserva);
        estadia.setAcomodacao(reserva.getAcomodacao());
        estadia.setDataHoraInicio(reserva.getDataHoraChegada());
        estadia.setDataHoraTermino(reserva.getDataHoraSaida());
        estadia.setHospede(reserva.getHospede());
        
        estadiaDao.save(estadia);
        
        reserva.setEstadia(estadia);
        AlertMaker.snackBar(pnlBooking, "Estadia confirmada.");
        loadItens();
    }

    private void cancelarReserva(Reserva reserva) {
        reservaDao.delete(reserva);
        AlertMaker.snackBar(pnlBooking, "Reserva cancelada.");
        loadItens();
    }

    @FXML
    void refresh(MouseEvent event) {
        loadItens();
    }
    

    @FXML
    void onToggleButton(ActionEvent event) {
        loadItens();
    }
}
