package br.uems.hotelapp;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private StackPane stackPane;
    
    @FXML
    private VBox pnItems;
    
    @FXML
    private Button btnOverview, btnOrders, btnCustomers, btnMenus, btnRooms, btnUsers, btnSignout;

    @FXML
    private Pane pnlOverview, pnlOrders, pnlMenus, pnlRooms, pnlCustomers, pnlUsers;
    
    double x, y;

    @FXML
    void dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    

    @FXML
    void minimizeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void closeWindow(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
   
    
    public void onMouseEntered(MouseEvent event) {
        
    }
    
    public void onMouseExited(MouseEvent event) {
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        try {
            dummyData(pnItems);
        } catch (IOException e) {
        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        btnOverview.getStyleClass().remove("active");
        btnOrders.getStyleClass().remove("active");
        btnCustomers.getStyleClass().remove("active");
        btnMenus.getStyleClass().remove("active");
        btnUsers.getStyleClass().remove("active");
        btnRooms.getStyleClass().remove("active");
        
        if (actionEvent.getSource() == btnOverview) {
            btnOverview.getStyleClass().add("active");
            
            pnlOverview.setStyle("-fx-background-color : #FFFFFF");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            btnOrders.getStyleClass().add("active");
            
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnCustomers) {
            btnCustomers.getStyleClass().add("active");
            showPlaneCustomers();
        }
        if (actionEvent.getSource() == btnMenus) {
            btnMenus.getStyleClass().add("active");
            
            pnlMenus.setStyle("-fx-background-color : #464F67");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnUsers) {
            btnUsers.getStyleClass().add("active");
            showPlaneUsers();
        }
        if (actionEvent.getSource() == btnRooms) {
            btnRooms.getStyleClass().add("active");
            
            pnlRooms.setStyle("-fx-background-color : #FFFFFF");
            pnlRooms.toFront();
        }
    }
    
    public void showPlaneUsers() {
        if (pnlUsers == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Users.fxml"));
                pnlUsers = loader.load();
                stackPane.getChildren().add(pnlUsers);
            } catch (IOException e) {
            }
        }
        
        pnlUsers.toFront();
    }
    
    public void showPlaneCustomers() {
        if (pnlCustomers == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Customers.fxml"));
                pnlCustomers = loader.load();
                stackPane.getChildren().add(pnlCustomers);
            } catch (IOException e) {
            }
        }
        
        pnlCustomers.toFront();
    }
    
    public void dummyData(Pane pane) throws IOException {
        
        final Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = FXMLLoader.load(getClass().getResource("/fxml/Item.fxml"));

                //give the items some effect

//                nodes[i].setOnMouseEntered(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        nodes[j].setStyle("-fx-background-color : #DFE4E8");
//                    }
//                });
//                nodes[i].setOnMouseExited(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent mouseEvent) {
//                        nodes[j].setStyle("-fx-background-color : #FFFFFF");
//                        
//                    }
//                });

            pane.getChildren().add(nodes[i]);
        }

    }
}
