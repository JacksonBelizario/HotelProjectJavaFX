package br.uems.hotelapp;

import br.uems.hotelapp.persistence.ConnectionFactory;
import br.uems.hotelapp.utils.AppUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

    public static MainApp mainApp;

    public static MainApp getAppController() {
        return mainApp;
    }

    private static void setAppController(MainApp mainApp) {
        MainApp.mainApp = mainApp;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainApp.setAppController(this);

        ConnectionFactory.getEntityManager();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        AppUtils.setSceneStyle(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("Sistema Hoteleiro");
        stage.setScene(scene);
        stage.show();
        AppUtils.setStageIcon(stage);
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
