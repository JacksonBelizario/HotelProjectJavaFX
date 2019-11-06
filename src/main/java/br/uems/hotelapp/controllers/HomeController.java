package br.uems.hotelapp.controllers;

import br.uems.hotelapp.persistence.dao.EstadiaDao;
import br.uems.hotelapp.persistence.entities.Estadia;
import br.uems.hotelapp.persistence.entities.Funcionario;
import br.uems.hotelapp.utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    private Button btnOverview, btnBooking, btnCustomers, btnConsumable, btnRooms, btnUsers, btnSignout;

    @FXML
    private Pane pnlOverview, pnlBooking, pnlConsumable, pnlRooms, pnlCustomers, pnlUsers, pnlUserForm, pnlReservaForm;
    
    UserFormController userFormController;
    
    private double x, y;
    
    private EstadiaDao estadiaDao = new EstadiaDao();

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
        loadEstadias();
    }
    
    public void loadEstadias() {
        pnItems.getChildren().clear();
        try {
            List<Estadia> estadias = estadiaDao.getAll();

            Iterator<Estadia> estadiasIterator = estadias.iterator();
            while (estadiasIterator.hasNext()) {
                addItem((Estadia) estadiasIterator.next());
            }
        } catch (Exception ex) {
            AlertMaker.showErrorMessage(ex);
        }
    }


    public void handleClicks(ActionEvent actionEvent) {
        btnOverview.getStyleClass().remove("active");
        btnBooking.getStyleClass().remove("active");
        btnCustomers.getStyleClass().remove("active");
        btnConsumable.getStyleClass().remove("active");
        btnUsers.getStyleClass().remove("active");
        btnRooms.getStyleClass().remove("active");
        
        if (actionEvent.getSource() == btnOverview) {
            btnOverview.getStyleClass().add("active");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnBooking) {
            btnBooking.getStyleClass().add("active");
            showPlaneBookings();
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
            showPlaneRooms();
        }
    }
    
    public void showPlaneUsers() {
        if (pnlUsers == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Users.fxml"));
                pnlUsers = loader.load();
                stackPane.getChildren().add(pnlUsers);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
                return;
            }
        }
        
        pnlUsers.toFront();
    }
    
    public void showPlaneBookings() {
        if (pnlBooking == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Reservas.fxml"));
                pnlBooking = loader.load();
                stackPane.getChildren().add(pnlBooking);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
                return;
            }
        }
        
        pnlBooking.toFront();
    }
    
    public void showUserForm() {
        if (pnlUserForm == null) {
            try {
                FXMLLoader userFormLoader = new FXMLLoader(getClass().getResource("/fxml/UserForm.fxml"));
                pnlUserForm = userFormLoader.load();
                userFormController = userFormLoader.<UserFormController>getController();
                stackPane.getChildren().add(pnlUserForm);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
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
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
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
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
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
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
                return;
            }
        }
        
        pnlConsumable.toFront();
    }
    
    public void showPlaneRooms() {
        if (pnlRooms == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Acomodacoes.fxml"));
                pnlRooms = loader.load();
                stackPane.getChildren().add(pnlRooms);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
                return;
            }
        }
        
        pnlRooms.toFront();
    }
    
    public void showReservaForm() {
        if (pnlReservaForm == null) {
            try {
                FXMLLoader userFormLoader = new FXMLLoader(getClass().getResource("/fxml/ReservaForm.fxml"));
                pnlReservaForm = userFormLoader.load();
                stackPane.getChildren().add(pnlReservaForm);
            } catch (IOException ex) {
                AlertMaker.showErrorMessage(ex);
                return;
            }
        }
        
        pnlReservaForm.toFront();
    }

    @FXML
    void refresh(MouseEvent event) {
        loadEstadias();
    }

    private void addItem(Estadia estadia) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Item.fxml"));
            Node node = loader.load();
            pnItems.getChildren().add(node);

            ItemController controller = loader.<ItemController>getController();
            controller.setData(estadia);
    }

    public void showMaterialDialog(List<JFXButton> controls, String header, String body) {
        AlertMaker.showMaterialDialog(stackPane, controls, header, body);
    }
    
    public void showSnackBar(String message) {
        AlertMaker.snackBar(stackPane, message);
    }
}
