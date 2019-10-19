package br.uems.hotelapp;

import br.uems.hotelapp.persistence.ConnectionFactory;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ConnectionFactory.getEntityManager();
        
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Home.fxml"));
        
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getStylesheets().add("http://fonts.googleapis.com/css?family=Poppins:400,700");
        scene.setFill(Color.TRANSPARENT);
        
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Sistema Hoteleiro");
        stage.getIcons().add(new Image("/images/reserva.png"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
