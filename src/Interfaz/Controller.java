package Interfaz;

import Lógica.Compuerta_NOT;
import Lógica.Compuertas;
import Lógica.Ejecutar;
import Lógica.Interruptor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Interfaz.controller2.compuerta;


public class Controller {
    @FXML
    private Canvas canv;
    public static GraphicsContext context;
    public static Ejecutar ejecución = new Ejecutar();
    private int numeroLineas =0;
    @FXML
    private ImageView comp1;
    @FXML
    private void clickCanvas(MouseEvent event){
        GraphicsContext gc = canv.getGraphicsContext2D();
        Linea linea = new Linea(gc);
        linea.setInicioX(event.getX());
        linea.setInicioY(event.getY());
        ejecución.insertarLinea(linea);
    }
    public void initialize(){
        context = canv.getGraphicsContext2D();
    }

    @FXML
    public void clickCanvas2(MouseEvent event){
        Linea temp = (Linea) ejecución.getLineas().buscar(numeroLineas).getDato();
        temp.setFinX(event.getX());
        temp.setFinY(event.getY());
        numeroLineas ++;
    }

    @FXML
    private void pressAND(MouseEvent event) {
        compuerta = 1;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressOR(MouseEvent event) {
        compuerta = 2;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressNAND(MouseEvent event) {
        compuerta = 3;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressNOR(MouseEvent event) {
        compuerta = 4;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressXOR(MouseEvent event) {
        compuerta = 5;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressNXOR(MouseEvent event) {
        compuerta = 6;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("emergente.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    private void pressNOT(MouseEvent event){
        ejecución.añadirCompuerta(new Compuerta_NOT());
        Compuertas comp = (Compuertas) ejecución.getlista().buscar(ejecución.getNuemeroCompuertas()-1).getDato();
        context.drawImage((Image) comp.getImagenes().buscar(0).getDato(),400,300);
    }
    @FXML
    private void pressinterruptor(MouseEvent event){
        ejecución.añadirInterruptor();
        Interruptor interr = (Interruptor)ejecución.getInter().buscar(ejecución.getInter().getLargo()-1).getDato();
        context.drawImage((Image) interr.getImage().buscar(1).getDato(),(int) (Math.random()*300), (int)(Math.random()*600),90,70);
    }
    @FXML
    private void reset(){
        context.clearRect(0,0,1500,990);
        ejecución = new Ejecutar();
        numeroLineas = 0;
    }
}
