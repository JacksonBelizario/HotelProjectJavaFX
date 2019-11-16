/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uems.hotelapp.utils;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppUtils {
    
    public static final String ICON_IMAGE_LOC = "/images/reserva.png";
    
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }
    
    public static void setSceneStyle(Scene scene) {
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Poppins:400,700");
        scene.setFill(Color.TRANSPARENT);
    }

    public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Scene scene = new Scene(parent);
            setSceneStyle(scene);
            Stage stage;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            setStageIcon(stage);
        } catch (IOException ex) {
        }
        return controller;
    }

    public static Object loadPopUp(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Scene scene = new Scene(parent);
            setSceneStyle(scene);
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(parentStage);
            stage.show();
            setStageIcon(stage);
        } catch (IOException ex) {
        }
        return controller;
    }

}
