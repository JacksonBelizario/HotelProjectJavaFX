/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.AcomodacaoDao;
import br.uems.hotelapp.persistence.dao.TipoAcomodacaoDao;
import br.uems.hotelapp.persistence.entities.Acomodacao;
import br.uems.hotelapp.persistence.entities.TipoAcomodacao;
import br.uems.hotelapp.utils.AlertMaker;
import br.uems.hotelapp.utils.MasksUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Jackson
 */
public class AcomodacoesController implements Initializable {

    @FXML
    private Pane pnlRooms;

    @FXML
    private FlowPane fpAcomodacoes;

    @FXML
    private JFXTextField inputAndar;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXComboBox<TipoAcomodacao> cbTipos;

    @FXML
    private JFXButton btnReset;

    private TipoAcomodacaoDao tipoAcomodacaoDao = new TipoAcomodacaoDao();

    private AcomodacaoDao acomodacaoDao = new AcomodacaoDao();

    private ObservableList<TipoAcomodacao> obTipos;

    private TipoAcomodacao tipoAcomodacao;

    private Acomodacao acomodacao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadValidators();
        loadTipos();
        loadItens();
    }

    private void loadValidators() {
        MasksUtils.onlyDigitsValue(inputAndar);

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo obrigatório");
        inputAndar.getValidators().add(validator);
        inputAndar.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                inputAndar.validate();
            } else {
                inputAndar.resetValidation();
            }
        });
        cbTipos.getValidators().add(validator);
        cbTipos.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                cbTipos.validate();
            } else {
                cbTipos.resetValidation();
            }
        });
    }

    private void loadTipos() {
        obTipos = FXCollections.observableArrayList(tipoAcomodacaoDao.getAll());
        cbTipos.setItems(obTipos);
    }

    @FXML
    void reset(MouseEvent event) {
        reset();
    }

    @FXML
    void save(MouseEvent event) {

        Boolean edit = acomodacao != null;

        if (inputAndar.getText().isEmpty()) {
            return;
        }

        if (tipoAcomodacao == null) {
            return;
        }

        if (!edit) {
            acomodacao = new Acomodacao();
        }

        acomodacao.setAndar(Integer.valueOf(inputAndar.getText()));
        acomodacao.setTipoAcomodacao(tipoAcomodacao);

        if (!edit) {
            acomodacaoDao.save(acomodacao);
            addItem(acomodacao);
        } else {
            acomodacaoDao.update(acomodacao);
            loadItens();
        }
        reset();
    }

    @FXML
    void setAndar(ActionEvent event) {
        tipoAcomodacao = cbTipos.getSelectionModel().getSelectedItem();
    }

    private void loadItens() {
        fpAcomodacoes.getChildren().clear();

        List<Acomodacao> acomodacoes = acomodacaoDao.getAll();

        Iterator<Acomodacao> acomodacoesIterator = acomodacoes.iterator();
        while (acomodacoesIterator.hasNext()) {
            addItem((Acomodacao) acomodacoesIterator.next());
        }
    }

    private void addItem(Acomodacao acomodacao) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcomodacaoItem.fxml"));
            Node node = loader.load();
            fpAcomodacoes.getChildren().add(node);

            AcomodacaoItemController controller = loader.<AcomodacaoItemController>getController();
            controller.setData(acomodacao);

            ImageView btnEdit = (ImageView) node.lookup("#btnEdit");
            btnEdit.setOnMouseClicked((MouseEvent mouseEvent) -> {
                editItem(acomodacao);
            });

            ImageView btnDel = (ImageView) node.lookup("#btnDel");
            btnDel.setOnMouseClicked((MouseEvent mouseEvent) -> {

                JFXButton noButton = new JFXButton("Não");
                noButton.getStyleClass().add("btn-secondary");

                JFXButton yesButton = new JFXButton("Sim");
                yesButton.getStyleClass().add("btn-danger");
                yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                    acomodacaoDao.delete(acomodacao);
                    fpAcomodacoes.getChildren().remove(node);
                });

                HomeController.getController().showMaterialDialog(Arrays.asList(noButton, yesButton), "Remover quarto?", "Esta ação não pode ser desfeita!");
            });

        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }

    }

    private void editItem(Acomodacao acomodacao) {
        reset();
        this.acomodacao = acomodacao;
        tipoAcomodacao = acomodacao.getTipoAcomodacao();
        cbTipos.getSelectionModel().select(tipoAcomodacao);
        inputAndar.setText(acomodacao.getAndar().toString());
    }

    private void reset() {
        acomodacao = null;
        tipoAcomodacao = null;
        cbTipos.getSelectionModel().select(tipoAcomodacao);
        inputAndar.resetValidation();
        inputAndar.clear();
    }

}
