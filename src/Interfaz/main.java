package Interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Cláse encargada de ejecutar el programa
 * @author Sebastián Moya
 * @date 31/08/19
 */
public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("interfaz.fxml"));
        primaryStage.setTitle("Compuertas Lógicas");
        primaryStage.setScene(new Scene(root, 1500, 990));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
