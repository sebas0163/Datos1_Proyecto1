package Interfaz;

import Lógica.Compuerta_NOT;
import Lógica.Compuertas;
import Lógica.Ejecutar;
import Lógica.Interruptor;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import static Interfaz.controller2.compuerta;

/**
 * Clase encargada de manejar las caraterísticas de la interfaz principal.
 * @author Sebastián Moya
 * @date 06/09/19
 */
public class Controller {
    @FXML
    private Pane pane;
    public static Pane pane1;
    public static Ejecutar ejecución = new Ejecutar();
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    @FXML
    private ImageView comp1;

    EventHandler<MouseEvent> LabelOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY)){
            }else{
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Label)(t.getSource())).getTranslateX();
            orgTranslateY = ((Label)(t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY)){
            }else {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Label) (t.getSource())).setTranslateX(newTranslateX);
                ((Label) (t.getSource())).setTranslateY(newTranslateY);
            }
        }
    };
    /**
     * Método encargado de iniciar las caracteristicas de la interfaz.
     */
    public void initialize(){
        this.pane1 = pane;
    }

    /**
     * Método que detecta si se está haciendo click en el canvas y envia las posiciones iniciales para dibujar una linea.
     * @param event
     */
    @FXML
    private void clickPane(MouseEvent event){
        /*Linea l = new Linea();
        l.setInicioX(event.getX());
        l.setInicioY(event.getY());
        ejecución.insertarLinea(l);*/
    }

    /**
     * Método que detecta si se está liberando el mouse en el canvas y envia las posiciones finales para dibujar una linea.
     * @param event
     */
    @FXML
    public void relasePane(MouseEvent event){
        /*Linea temp = (Linea) ejecución.getLineas().buscar(ejecución.getLineas().getLargo()-1).getDato();
        temp.setFinX(event.getX());
        temp.setFinY(event.getY());*/
    }
    /**
     * Método que detecta si se seleccionó la compuerta And en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Or en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Nand en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Nor en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Xor en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Nxor en la paleta y procede a abrir una ventana de de selección.
     * @param event
     */
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
    /**
     * Método que detecta si se seleccionó la compuerta Not en la paleta y procede a dibujarla en el canvas.
     * @param event
     */
    @FXML
    private void pressNOT(MouseEvent event){
        ejecución.añadirCompuerta(new Compuerta_NOT());
        Compuertas comp = (Compuertas) ejecución.getlista().buscar(ejecución.getNuemeroCompuertas()-1).getDato();
        Image im = (Image)comp.getImagenes().buscar(0).getDato();
        Label label = new Label();
        label.setGraphic(new ImageView(im));
        label.setOnMousePressed(LabelOnMousePressedEventHandler);
        label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
        pane1.getChildren().add(label);
    }
    /**
     * Método que detecta si se seleccionó un interruptor en la paleta y procede a dibujarlo en el canvas.
     * @param event
     */
    @FXML
    private void pressinterruptor(MouseEvent event){
        ejecución.añadirInterruptor();
        Interruptor interr = (Interruptor)ejecución.getInter().buscar(ejecución.getInter().getLargo()-1).getDato();
        Image im = (Image) interr.getImage().buscar(1).getDato();
        Label label = new Label();
        label.setGraphic(new ImageView(im));
        label.setOnMousePressed(LabelOnMousePressedEventHandler);
        label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
        pane1.getChildren().add(label);
    }

    /**
     * Método encargado restablecer la aplicación a los valores de inicio.
     */
    @FXML
    private void reset(){
        ejecución = new Ejecutar();
        pane.getChildren().clear();
    }
}
