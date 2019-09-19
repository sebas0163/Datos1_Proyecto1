package Interfaz;

import Lógica.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
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
        /**
         * Método encargado de atender todos los eventos relacionados al presionar un Label, entre estos eventos se encuentran elde dibujar una linea, comenzar el drag and drop y elimnar la compuerta.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) { // Toma de referencia de donde dibujar la linea.
                int numComp = ejecutar.getListaImageViewComp().getPos(t.getSource());
                Linea linea = new Linea();
                linea.setCompA(numComp);
                ejecutar.insertarLinea(linea, 1);
            }else if(t.isAltDown() & t.getButton().equals(MouseButton.SECONDARY)){ //Con este evento se elimina la compuerta seleccionada y con ella todos los datos dependientes .
                Label label = (Label) t.getSource(); //Se toma la compuerta sleccionada
                ejecutar.eliminarCompuerta(label);
                pane1.getChildren().remove(label); //Se elimina la imagen de la interfaz
            }else { // permite comenzar el draga and drop.
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Label) (t.getSource())).getTranslateX();
                orgTranslateY = ((Label) (t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseReleased = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los elementos relacionados al liberar el mouse de un label. Entre ellos están hacia donde se extiende la linea.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numComp = ejecutar.getListaImageViewComp().getPos(t.getSource());
                Compuertas compuerta = (Compuertas) ejecutar.getlista().buscar(numComp).getDato();
                Linea temp = (Linea) ejecución.getLineasComp().buscar(ejecución.getLineasComp().getLargo() - 1).getDato();
                Lista listaCompuertas = ejecución.getlista();
                Nodo aux = listaCompuertas.getHead();
                while (aux != null){
                    Compuertas comp = (Compuertas) aux.getDato();
                    double X = compuerta.getPosX();
                    double Y = compuerta.getPosY();
                    if ((t.getX()+X >= comp.getPosX() & t.getX()+X <= comp.getPosX()+167)& t.getY()+Y >= comp.getPosY() & t.getY()+Y <= comp.getPosY()+92){
                        temp.setCompB(ejecución.getlista().getPos(comp));
                        temp.dibujar();
                        break;
                    }else{
                        aux = aux.getNext();
                    }
                }
            }else{
                return;
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los eventos relacionados al arrastre del label. Entre sus funciones están el fin del grag and drop.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)){
                return;
            }else{
                double offsetX = t.getSceneX() ;
                double offsetY = t.getSceneY() ;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                int numComp = ejecutar.getListaImageViewComp().getPos(t.getSource());
                ((Label)ejecutar.getListaImageViewComp().buscar(numComp).getDato()).setLayoutX(newTranslateX);
                ((Label)ejecutar.getListaImageViewComp().buscar(numComp).getDato()).setLayoutY(newTranslateY);
                Compuertas comp = (Compuertas) ejecutar.getlista().buscar(numComp).getDato();
                Nodo temp = comp.getCirculos().getHead();
                int indice = 0;
                while(temp != null){
                    Circle circle = (Circle) temp.getDato();
                    if (comp.getCirculos().getLargo() == 3) {
                        if (indice == 0) {
                            circle.setCenterX(newTranslateX + 160);
                            circle.setCenterY(newTranslateY + 45);
                        } else if (indice == 1) {
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 27);
                        } else {
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 65);
                        }
                    }else if (comp.getCirculos().getLargo() == 4){
                        if (indice == 0) {
                            circle.setCenterX(newTranslateX + 160);
                            circle.setCenterY(newTranslateY + 45);
                        } else if (indice == 1) {
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 28);
                        } else if (indice == 2){
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 47);
                        }else{
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 65);
                        }
                    }else{
                        if (indice == 0) {
                            circle.setCenterX(newTranslateX + 160);
                            circle.setCenterY(newTranslateY + 45);
                        } else if (indice == 1) {
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 18);
                        } else if (indice == 2) {
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 33);
                        }else  if (indice == 3){
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 53);
                        }else{
                            circle.setCenterX(newTranslateX + 10);
                            circle.setCenterY(newTranslateY + 67);
                        }
                    }
                    temp = temp.getNext();
                    indice++;
                }
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
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_AND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_AND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
            case 2:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_OR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
            case 3:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
            case 4:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
            case 5:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
            case 6:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    Circle circle = new Circle(10,27,5);
                    Circle circle1 = new Circle(10,65,5);
                    Circle circle2 = new Circle(160,45,5);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    Circle circle = new Circle(10,28,5);
                    Circle circle1 = new Circle(10,47,5);
                    Circle circle2 = new Circle(10,65,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle3);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3);
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    Circle circle = new Circle(10,18,5);
                    Circle circle1 = new Circle(10,33,5);
                    Circle circle2 = new Circle(10,53,5);
                    Circle circle4 = new Circle(10,67,5);
                    Circle circle3 = new Circle(160,45,5);
                    comp.getCirculos().add(circle4);
                    comp.getCirculos().add(circle);
                    comp.getCirculos().add(circle1);
                    comp.getCirculos().add(circle2);
                    comp.getCirculos().add(circle3);
                    Nodo temp = comp.getCirculos().getHead();
                    while (temp != null){
                        Circle circulo = (Circle) temp.getDato();
                        circulo.setOnMousePressed(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseDragged(LabelOnMousePressedEventHandler);
                        circulo.setOnMouseReleased(LabelOnMouseReleased);
                        temp = temp.getNext();
                    }
                    Label label = new Label();
                    label.setGraphic(new ImageView(im));
                    label.setOnMousePressed(LabelOnMousePressedEventHandler);
                    label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
                    label.setOnMouseReleased(LabelOnMouseReleased);
                    ejecutar.getListaImageViewComp().add(label);
                    pane1.getChildren().addAll(label,circle,circle1,circle2,circle3,circle4);
                }
                break;
        }

    }
}
