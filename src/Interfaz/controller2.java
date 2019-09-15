package Interfaz;

import Lógica.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Interfaz.Controller.*;

/**
 * Clase encargada de controlar los eventos de la ventana emergente
 * @author Sebastián Moya
 * @date 10/09/19
 */
public class controller2 {
    @FXML
    private RadioButton seleccion1;
    @FXML
    private RadioButton seleccion2;
    @FXML
    private RadioButton seleccion3;
    @FXML
    private Button bton;
    private Ejecutar ejecutar;
    public static int compuerta = 0;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;

    /**
     * Método encargado de inicializar las caracteristicas de la ventana
     */
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        seleccion1.setToggleGroup(group);
        seleccion2.setToggleGroup(group);
        seleccion3.setToggleGroup(group);
        ejecutar = ejecución;
    }
    EventHandler<MouseEvent> LabelOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)){
                int numComp = ejecutar.getListaImageView().getPos(t.getSource());
                Label label =(Label) ejecutar.getListaImageView().buscar(0).getDato();
                Compuertas compuerta = (Compuertas)ejecutar.getlista().buscar(numComp).getDato();
                double X = compuerta.getPosX();
                double Y = compuerta.getPosY();
                Linea linea = new Linea();
                linea.setInicioX(X+167);
                linea.setInicioY(Y+45);
                ejecutar.insertarLinea(linea);


            }else {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Label) (t.getSource())).getTranslateX();
                orgTranslateY = ((Label) (t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseReleased = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)) {
                int numComp = ejecutar.getListaImageView().getPos(t.getSource());
                Compuertas compuerta = (Compuertas) ejecutar.getlista().buscar(numComp).getDato();
                Linea temp = (Linea) ejecución.getLineas().buscar(ejecución.getLineas().getLargo() - 1).getDato();
                double X = compuerta.getPosX();
                double Y = compuerta.getPosY();
                temp.setFinX(t.getX() + X );
                temp.setFinY(t.getY() + Y );
                temp.dibujar();
            }else{
                return;
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)){
                return;
            }else{
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Label) (t.getSource())).setTranslateX(newTranslateX);
                ((Label) (t.getSource())).setTranslateY(newTranslateY);
                int numComp = ejecutar.getListaImageView().getPos(t.getSource());
                Compuertas comp = (Compuertas) ejecutar.getlista().buscar(numComp).getDato();
                comp.setPosX(newTranslateX);
                comp.setPosY(newTranslateY);
            }
        }
    };

    /**
     * Método que permite seleccionar el numero de entradas de la compuerta a elegir.
     */
    @FXML
    private void accionar(){
        if (seleccion1.isSelected()){
            dibujarImagen(2);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }else if (seleccion2.isSelected()){
            dibujarImagen(3);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }else {
            dibujarImagen(4);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Método encargado de dibujar la compuerta solicitada con su respectivo número de entradas
     * @param num dato de tipo entero que referencia al número de entradas solicitadas.
     */
    private void  dibujarImagen(int num){
        Compuertas comp;
        Image im;
        switch (compuerta) {
            case 1:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_AND(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_AND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_AND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
            case 2:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_OR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
            case 3:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
            case 4:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
            case 5:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
            case 6:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageView().add(label);
                    pane1.getChildren().add(label);
                }
                break;
        }

    }
}
