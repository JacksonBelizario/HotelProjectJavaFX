/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.CategoriaItemDao;
import br.uems.hotelapp.persistence.dao.ItemConsumoDao;
import br.uems.hotelapp.persistence.entities.CategoriaItem;
import br.uems.hotelapp.persistence.entities.ItemConsumo;
import br.uems.hotelapp.utils.AlertMaker;
import br.uems.hotelapp.utils.NumberUtils;
import br.uems.hotelapp.utils.ValidatorUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
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
    private JFXComboBox<CategoriaItem> cbCategorias;

    CategoriaItemDao categoriaItemDao = new CategoriaItemDao();

    ItemConsumoDao itemConsumoDao = new ItemConsumoDao();

    ObservableList<CategoriaItem> obCategorias;

    CategoriaItem categoriaItem;

    ItemConsumo itemConsumo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadValidators();
        loadCategories();
        loadItens();
        reset();
    }

    private void loadValidators() {
        NumberUtils.mascaraDinheiro(inputPreco);
        ValidatorUtils.setValidator(inputNome, "Informe o nome");
        ValidatorUtils.setValidator(inputDescricao, "Informe a descrição");
    }

    private void loadCategories() {
        obCategorias = FXCollections.observableArrayList(categoriaItemDao.getAll());
        cbCategorias.setItems(obCategorias);
    }

    @FXML
    void setCategoria(ActionEvent event) {

        categoriaItem = cbCategorias.getSelectionModel().getSelectedItem();

    }

    private void loadItens() {
        pnListItens.getChildren().clear();

        List<ItemConsumo> itensConsumo = itemConsumoDao.getAll();

        Iterator<ItemConsumo> itensConsumoIterator = itensConsumo.iterator();
        while (itensConsumoIterator.hasNext()) {
            addItem((ItemConsumo) itensConsumoIterator.next());
        }
    }

    private void addItem(ItemConsumo itemConsumo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItemConsumo.fxml"));
            Node node = loader.load();
            pnListItens.getChildren().add(node);

            ItemConsumoController controller = loader.<ItemConsumoController>getController();
            controller.setData(itemConsumo);

            ImageView btnEdit = (ImageView) node.lookup("#btnEdit");
            btnEdit.setOnMouseClicked((MouseEvent mouseEvent) -> {
                editItem(itemConsumo);
            });

            ImageView btnDel = (ImageView) node.lookup("#btnDel");
            btnDel.setOnMouseClicked((MouseEvent mouseEvent) -> {

                JFXButton noButton = new JFXButton("Não");
                noButton.getStyleClass().add("btn-secondary");

                JFXButton yesButton = new JFXButton("Sim");
                yesButton.getStyleClass().add("btn-danger");
                yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev) -> {
                    itemConsumoDao.delete(itemConsumo);
                    pnListItens.getChildren().remove(node);
                });

                HomeController.getController().showMaterialDialog(Arrays.asList(noButton, yesButton), "Remover item?", "Esta ação não pode ser desfeita!");
            });
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }

    }

    @FXML
    void save(MouseEvent event) {

        Boolean edit = itemConsumo != null;

        if (inputNome.getText().isEmpty()) {
            return;
        }

        if (inputDescricao.getText().isEmpty()) {
            return;
        }

        if (inputPreco.getText().isEmpty()) {
            return;
        }

        if (!edit) {
            itemConsumo = new ItemConsumo();
        }

        itemConsumo.setItem(inputNome.getText());
        itemConsumo.setDescricao(inputDescricao.getText());
        itemConsumo.setPreco(NumberUtils.getNumericValue(inputPreco.getText()));
        itemConsumo.setCategoria(categoriaItem);

        if (!edit) {
            itemConsumoDao.save(itemConsumo);
            addItem(itemConsumo);
        } else {
            itemConsumoDao.update(itemConsumo);
            loadItens();
        }
        reset();
    }

    private void editItem(ItemConsumo itemConsumo) {
        reset();
        this.itemConsumo = itemConsumo;
        categoriaItem = itemConsumo.getCategoria();
        cbCategorias.getSelectionModel().select(categoriaItem);
        inputNome.setText(itemConsumo.getItem());
        inputDescricao.setText(itemConsumo.getDescricao());
        inputPreco.setText(NumberUtils.formatNumber(itemConsumo.getPreco()));
    }

    @FXML
    void reset(MouseEvent event) {
        reset();
    }

    private void reset() {
        itemConsumo = null;
        categoriaItem = null;
        cbCategorias.getSelectionModel().select(categoriaItem);
        inputNome.resetValidation();
        inputNome.clear();
        inputDescricao.resetValidation();
        inputDescricao.clear();
        inputPreco.resetValidation();
        inputPreco.clear();
    }

}
