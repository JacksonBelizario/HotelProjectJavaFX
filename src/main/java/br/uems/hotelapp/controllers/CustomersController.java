/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.HospedeDao;
import br.uems.hotelapp.persistence.entities.Hospede;
import br.uems.hotelapp.utils.AppUtils;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jackson
 */
public class CustomersController implements Initializable {

    public static CustomersController controller;

    public static CustomersController getController() {
        return controller;
    }

    private static void setController(CustomersController controller) {
        CustomersController.controller = controller;
    }

    private Stage getStage() {
        return (Stage) pnList.getScene().getWindow();
    }

    @FXML
    private VBox pnList;

    HospedeDao hospedeDao = new HospedeDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setController(this);
        loadData();
    }

    public void loadData() {
        pnList.getChildren().clear();

        List<Hospede> hospedes = hospedeDao.getAll();

        Iterator<Hospede> hospedesIterator = hospedes.iterator();
        while (hospedesIterator.hasNext()) {
            addItem((Hospede) hospedesIterator.next());
        }
    }

    private void addItem(Hospede hospede) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CustomerItem.fxml"));
            Node node = loader.load();
            pnList.getChildren().add(node);

            CustomerItemController controller = loader.<CustomerItemController>getController();
            controller.setData(hospede);

            ImageView btnCard = (ImageView) controller.getBtnCard();
            btnCard.setOnMouseClicked((MouseEvent mouseEvent) -> {
                CartaoController cartaoController = (CartaoController) AppUtils.loadPopUp(getClass().getResource("/fxml/Cartao.fxml"), "Adicionar Cartão", getStage());
                cartaoController.setCustomer(hospede);
            });

            ImageView btnEdit = (ImageView) controller.getBtnEdit();
            btnEdit.setOnMouseClicked((MouseEvent mouseEvent) -> {
                HomeController.getController().showCustomerForm(hospede);
            });

            ImageView btnDel = (ImageView) controller.getBtnDel();
            btnDel.setOnMouseClicked((MouseEvent mouseEvent) -> {

                JFXButton noButton = new JFXButton("Não");
                noButton.getStyleClass().add("btn-secondary");

                JFXButton yesButton = new JFXButton("Sim");
                yesButton.getStyleClass().add("btn-danger");
                yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                    hospedeDao.delete(hospede);
                    pnList.getChildren().remove(node);
                });

                HomeController.getController().showMaterialDialog(Arrays.asList(noButton, yesButton), "Remover hóspede?", "Esta ação não pode ser desfeita!");
            });

        } catch (Exception e) {
        }
    }

    @FXML
    void addCustomer(MouseEvent event) {
        HomeController.getController().showCustomerForm();
    }

    @FXML
    void refresh(MouseEvent event) {
        loadData();
    }
}
