/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.entities.Hospede;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jackson
 */
public class CustomersController implements Initializable {
    
    @FXML
    private VBox pnList;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomers();
    }
    
    
    public void loadCustomers() {
        HospedeDao hospedeDao = new HospedeDao();
        
        List<Hospede> hospedes = hospedeDao.getAll();

        Iterator<Hospede> hospedesIterator = hospedes.iterator();
        while (hospedesIterator.hasNext()){
            Hospede hospede = (Hospede) hospedesIterator.next();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CustomerItem.fxml"));
                pnList.getChildren().add(loader.load());

                CustomerItemController controller = loader.<CustomerItemController>getController();
                controller.setData(hospede);

            } catch (Exception e) {
            }

        }
    }

    @FXML
    void refresh(MouseEvent event) {
        loadCustomers();
    }
}
