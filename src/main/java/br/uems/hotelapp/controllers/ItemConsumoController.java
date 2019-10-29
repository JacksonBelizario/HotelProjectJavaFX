/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.controllers;


import br.uems.hotelapp.persistence.entities.ItemConsumo;
import br.uems.hotelapp.utils.NumberUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ItemConsumoController {

    @FXML
    private Label labelNome;

    @FXML
    private Label labelDesc;

    @FXML
    private Label labelPreco;

    @FXML
    private ImageView btnEdit;

    @FXML
    private ImageView btnDel;
    
    public void setData(ItemConsumo itemConsumo) {
        try {
            labelNome.setText(itemConsumo.getItem());
            labelDesc.setText(itemConsumo.getDescricao());
            labelPreco.setText("R$ " + NumberUtils.formatNumber(itemConsumo.getPreco()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}