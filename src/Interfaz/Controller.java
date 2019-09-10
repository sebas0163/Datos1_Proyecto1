package Interfaz;

import Lógica.Ejecutar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private Canvas canv;
    private Ejecutar ejecución = new Ejecutar();
    private int numeroLineas =0;


    @FXML
    private ImageView comp1;
    @FXML
    private void prueba(MouseEvent event){
        GraphicsContext gc = canv.getGraphicsContext2D();
        Linea linea = new Linea(gc);
        linea.setInicioX(event.getX());
        linea.setInicioY(event.getY());
        ejecución.insertarLinea(linea);
    }
    @FXML
    public void prueba2(MouseEvent event){
        Linea temp = (Linea) ejecución.getLineas().buscar(numeroLineas).getDato();
        temp.setFinX(event.getX());
        temp.setFinY(event.getY());
        numeroLineas ++;
    }

    @FXML
    private void pressAND(MouseEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
