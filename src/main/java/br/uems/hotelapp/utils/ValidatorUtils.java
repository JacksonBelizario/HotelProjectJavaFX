/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.utils;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Jackson
 */
public abstract class ValidatorUtils {

    public static void setValidator(JFXTextField node) {
        setValidator(node, "Campo obrigatório");
    }

    public static void setValidator(JFXTextField node, String message) {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        node.getValidators().add(validator);
        node.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                node.validate();
            } else {
                node.resetValidation();
            }
        });
    }

    public static void setValidator(JFXComboBox node) {
        setValidator(node, "Campo obrigatório");
    }

    public static void setValidator(JFXComboBox node, String message) {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        node.getValidators().add(validator);
        node.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                node.validate();
            } else {
                node.resetValidation();
            }
        });
    }

    public static void setValidator(JFXDatePicker node) {
        setValidator(node, "Campo obrigatório");
    }

    public static void setValidator(JFXDatePicker node, String message) {

        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage(message);
        node.getValidators().add(validator);
        node.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                node.validate();
            } else {
                node.resetValidation();
            }
        });
    }

}
