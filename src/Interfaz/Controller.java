package Interfaz;

import Lógica.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

    EventHandler<MouseEvent> interruptorOnMousePressed = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY)){
                int numInterruptor = ejecución.getListaImageViewInterr().getPos(t.getSource());
                Interruptor interruptor = (Interruptor) ejecución.getInter().buscar(numInterruptor).getDato();
                double X = interruptor.getPosX();
                double Y = interruptor.getPosY();
                Linea linea = new Linea();
                linea.setInicioX(X+134);
                linea.setInicioY(Y+85);
                linea.setCompA(numInterruptor);
                ejecución.insertarLinea(linea,2);
            }else{
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Label)(t.getSource())).getTranslateX();
                orgTranslateY = ((Label)(t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> interruptorOnMouseReleased = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)) {
                int numInterruptor = ejecución.getListaImageViewInterr().getPos(t.getSource());
                Interruptor interruptor = (Interruptor) ejecución.getInter().buscar(numInterruptor).getDato();
                Linea temp = (Linea) ejecución.getLineasInterr().buscar(ejecución.getLineasInterr().getLargo() - 1).getDato();
                Lista listaCompuertas = ejecución.getlista();
                Nodo aux = listaCompuertas.getHead();
                while (aux != null){
                    Compuertas comp = (Compuertas) aux.getDato();
                    double X = interruptor.getPosX();
                    double Y = interruptor.getPosY();
                    if ((t.getX()+X >= comp.getPosX() & t.getX()+X <= comp.getPosX()+167)& t.getY()+Y >= comp.getPosY() & t.getY()+Y <= comp.getPosY()+92){
                        temp.setFinX(t.getX() + X);
                        temp.setFinY(t.getY() + Y);
                        System.out.println(ejecución.getlista().getPos(comp));
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
    EventHandler<MouseEvent> interruptorOnMouseDragged = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY)){
                return;
            }else{
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Label) (t.getSource())).setTranslateX(newTranslateX);
                ((Label) (t.getSource())).setTranslateY(newTranslateY);
                int numInterr = ejecución.getListaImageViewInterr().getPos(t.getSource());
                Interruptor interruptor= (Interruptor) ejecución.getInter().buscar(numInterr).getDato();
                interruptor.setPosX(newTranslateX);
                interruptor.setPosY(newTranslateY);
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numComp = ejecución.getListaImageViewComp().getPos(t.getSource());
                Compuertas compuerta = (Compuertas) ejecución.getlista().buscar(numComp).getDato();
                double X = compuerta.getPosX();
                double Y = compuerta.getPosY();
                Linea linea = new Linea();
                linea.setInicioX(X + 167);
                linea.setInicioY(Y + 45);
                linea.setCompA(numComp);
                ejecución.insertarLinea(linea, 1);
            }else if(t.isAltDown() & t.getButton().equals(MouseButton.SECONDARY)){ //Con este evento se elimina la compuerta seleccionada y con ella todos los datos dependientes .
                Label label = (Label) t.getSource(); //Se toma la compuerta sleccionada
                ejecución.eliminarCompuerta(label);
                pane.getChildren().remove(label); //Se elimina la imagen de la interfaz
            }else{
            orgSceneX = t.getSceneX();
            orgSceneY = t.getSceneY();
            orgTranslateX = ((Label)(t.getSource())).getTranslateX();
            orgTranslateY = ((Label)(t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMouseReleased = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numComp = ejecución.getListaImageViewComp().getPos(t.getSource());
                Compuertas compuerta = (Compuertas) ejecución.getlista().buscar(numComp).getDato();
                Linea temp = (Linea) ejecución.getLineasComp().buscar(ejecución.getLineasComp().getLargo() - 1).getDato();
                Lista listaCompuertas = ejecución.getlista();
                Nodo aux = listaCompuertas.getHead();
                while (aux != null){
                    Compuertas comp = (Compuertas) aux.getDato();
                    double X = compuerta.getPosX();
                    double Y = compuerta.getPosY();
                    if ((t.getX()+X >= comp.getPosX() & t.getX()+X <= comp.getPosX()+167)& t.getY()+Y >= comp.getPosY() & t.getY()+Y <= comp.getPosY()+92){
                        temp.setFinX(t.getX() + X);
                        temp.setFinY(t.getY() + Y);
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
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY)){
                return;
            }else {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Label) (t.getSource())).setTranslateX(newTranslateX);
                ((Label) (t.getSource())).setTranslateY(newTranslateY);
                int numComp = ejecución.getListaImageViewComp().getPos(t.getSource());
                Compuertas comp = (Compuertas) ejecución.getlista().buscar(numComp).getDato();
                comp.setPosX(newTranslateX);
                comp.setPosY(newTranslateY);
            }
        }
    };
    /**
     * Método encargado de iniciar las caracteristicas de la interfaz.
     */
    public void initialize(){
        this.pane1 = pane;
    }

    @FXML
    private void probar(){
        Lista lineasInterruptores = ejecución.getLineasInterr();
        Lista lineasCompuertas = ejecución.getLineasComp();
        Nodo temp1 = lineasInterruptores.getHead();
        Nodo temp2 = lineasCompuertas.getHead();
        while (temp1 != null){
            Linea linea = (Linea) temp1.getDato();
            ejecución.conectarInterrup(linea.getCompA(),linea.getCompB());
            temp1 = temp1.getNext();
        }
        while(temp2 != null){
            Linea linea = (Linea) temp2.getDato();
            ejecución.conexiones(linea.getCompA(),linea.getCompB());
            temp2 = temp2.getNext();
        }
        ejecución.probar();
        Compuertas comp = (Compuertas) ejecución.getlista().buscar(ejecución.getlista().getLargo()-1).getDato();
        comp.mostrar();
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
        label.setOnMouseReleased(LabelOnMouseReleased);
        ejecución.getListaImageViewComp().add(label);
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
        label.setOnMousePressed(interruptorOnMousePressed);
        label.setOnMouseDragged(interruptorOnMouseDragged);
        label.setOnMouseReleased(interruptorOnMouseReleased);
        ejecución.getListaImageViewInterr().add(label);
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
