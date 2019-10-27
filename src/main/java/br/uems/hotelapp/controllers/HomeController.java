package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.entities.Funcionario;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    
    
    public static HomeController controller;
    
    public static HomeController getController() {
        return controller;
    }

    private static void setController(HomeController controller) {
        HomeController.controller = controller;
    }
    
    @FXML
    private Label label;

    @FXML
    private StackPane stackPane;
    
    @FXML
    private VBox pnItems;
    
    @FXML
    private Button btnOverview, btnOrders, btnCustomers, btnConsumable, btnRooms, btnUsers, btnSignout;

    @FXML
    private Pane pnlOverview, pnlOrders, pnlConsumable, pnlRooms, pnlCustomers, pnlUsers, pnlUserForm;
    
    UserFormController userFormController;
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
        HomeController.setController(this);
        try {
            dummyData(pnItems);
        } catch (IOException e) {
        }

    }


    public void handleClicks(ActionEvent actionEvent) {
        btnOverview.getStyleClass().remove("active");
        btnOrders.getStyleClass().remove("active");
        btnCustomers.getStyleClass().remove("active");
        btnConsumable.getStyleClass().remove("active");
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
        if (actionEvent.getSource() == btnConsumable) {
            btnConsumable.getStyleClass().add("active");
            
            showPlaneItensConsumo();
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
    
    public void showUserForm() {
        if (pnlUserForm == null) {
            try {
                FXMLLoader userFormLoader = new FXMLLoader(getClass().getResource("/fxml/UserForm.fxml"));
                pnlUserForm = userFormLoader.load();
                userFormController = userFormLoader.<UserFormController>getController();
                stackPane.getChildren().add(pnlUserForm);
            } catch (IOException e) {
                return;
            }
        }
        
        pnlUserForm.toFront();
    }
    
    public void showUserForm(Funcionario funcionario) {
        if (pnlUserForm == null) {
            try {
                FXMLLoader userFormLoader = new FXMLLoader(getClass().getResource("/fxml/UserForm.fxml"));
                pnlUserForm = userFormLoader.load();
                userFormController = userFormLoader.<UserFormController>getController();
                stackPane.getChildren().add(pnlUserForm);
            } catch (IOException e) {
                return;
            }
        }
        userFormController.editUser(funcionario);
        pnlUserForm.toFront();
    }
    
    public void showPlaneCustomers() {
        if (pnlCustomers == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Customers.fxml"));
                pnlCustomers = loader.load();
                stackPane.getChildren().add(pnlCustomers);
            } catch (IOException e) {
                return;
            }
        }
        
        pnlCustomers.toFront();
    }
    
    public void showPlaneItensConsumo() {
        if (pnlConsumable == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ItensConsumo.fxml"));
                pnlConsumable = loader.load();
                stackPane.getChildren().add(pnlConsumable);
            } catch (IOException e) {
                e.getStackTrace();
                return;
            }
        }
        
        pnlConsumable.toFront();
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

    public void showMaterialDialog(List<JFXButton> controls, String header, String body) {
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
    }
}
