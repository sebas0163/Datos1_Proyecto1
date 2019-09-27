package Interfaz;

import Logica.*;
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
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static Interfaz.controller2.compuerta;

/**
 * Clase encargada de manejar las caraterísticas de la interfaz principal.
 * @author Sebastián Moya
 */
public class Controller {
    @FXML
    private Pane pane;
    @FXML
    private VBox vBox;
    public static Pane pane1;
    public static Ejecutar ejecucion = new Ejecutar();
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    @FXML
    private ImageView comp1;

    EventHandler<MouseEvent> interruptorOnMousePressed = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los eventos relacionados al presionar un Label que posee un interruptor. Entre estos eventos se encuentran elde dibujar una linea, comenzar el drag and drop y elimnar la compuerta.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numInterruptor = ejecucion.getListaImageViewInterr().getPos(t.getSource());
                Linea linea = new Linea();
                linea.setCompA(numInterruptor);
                ejecucion.insertarLinea(linea, 2);
            }else if(t.isAltDown() & t.getButton().equals(MouseButton.SECONDARY)) { //Con este evento se elimina la compuerta seleccionada y con ella todos los datos dependientes .
                Label label = (Label) t.getSource(); //Se toma la compuerta seleccionada
                ejecucion.eliminarInterruptor(label);
                pane.getChildren().remove(label); //Se elimina la imagen de la interfaz
            }else if(t.isAltDown() & t.isPrimaryButtonDown()){//cambiar el estado del interruptor.
                int numInterruptor = ejecucion.getListaImageViewInterr().getPos(t.getSource());
                Label label = (Label) ejecucion.getListaImageViewInterr().buscar(numInterruptor).getDato();
                Interruptor inter = (Interruptor) ejecucion.getInter().buscar(numInterruptor).getDato();
                if (inter.isEstado()){
                    Image im = (Image) inter.getImage().buscar(1).getDato();
                    label.setGraphic(new ImageView(im));
                    inter.cambiarEstado();
                }else{
                    Image im = (Image) inter.getImage().buscar(0).getDato();
                    label.setGraphic(new ImageView(im));
                    inter.cambiarEstado();
                }
            }else if (!(t.isAltDown()) & t.isPrimaryButtonDown()){
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((Label)(t.getSource())).getTranslateX();
                orgTranslateY = ((Label)(t.getSource())).getTranslateY();
            }
        }
    };
    EventHandler<MouseEvent> interruptorOnMouseReleased = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los elementos relacionados al liberar el mouse de un label que posee un interruptor. Entre ellos están hacia donde se extiende la linea.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numInterruptor = ejecucion.getListaImageViewInterr().getPos(t.getSource());
                Interruptor interruptor = (Interruptor) ejecucion.getInter().buscar(numInterruptor).getDato();
                Linea temp = (Linea) ejecucion.getLineasInterr().buscar(ejecucion.getLineasInterr().getLargo() - 1).getDato();
                Lista listaCompuertas = ejecucion.getlista();
                Nodo aux = listaCompuertas.getHead();
                while (aux != null){
                    Compuertas comp = (Compuertas) aux.getDato();
                    double X = interruptor.getPosX();
                    double Y = interruptor.getPosY();
                    if ((t.getX()+X >= comp.getPosX() & t.getX()+X <= comp.getPosX()+167)& t.getY()+Y >= comp.getPosY() & t.getY()+Y <= comp.getPosY()+92){
                        temp.setCompB(ejecucion.getlista().getPos(comp));
                        temp.dibujarLineaInterruptor();
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
        /**
         * Método encargado de atender todos los eventos relacionados al arrastre del label que posee un interruptor. Entre sus funciones están el fin del grag and drop.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())){
                return;
            }else{
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                int numInter = ejecucion.getListaImageViewInterr().getPos(t.getSource());
                ((Label) ejecucion.getListaImageViewInterr().buscar(numInter).getDato()).setTranslateX(newTranslateX);
                ((Label) ejecucion.getListaImageViewInterr().buscar(numInter).getDato()).setTranslateY(newTranslateY);
                Interruptor interruptor= (Interruptor) ejecucion.getInter().buscar(numInter).getDato();
                interruptor.getCircle().setCenterX(newTranslateX + 16);
                interruptor.getCircle().setCenterY(newTranslateY+87);
                interruptor.setPosX(newTranslateX);
                interruptor.setPosY(newTranslateY);
            }
        }
    };
    EventHandler<MouseEvent> LabelOnMousePressedEventHandler = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los eventos relacionados al presionar un Label. Entre estos eventos se encuentran elde dibujar una linea, comenzar el drag and drop y elimnar la compuerta.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if(t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numComp = ejecucion.getListaImageViewComp().getPos(t.getSource());
                Linea linea = new Linea();
                linea.setCompA(numComp);
                ejecucion.insertarLinea(linea, 1);
            }else if(t.isAltDown() & t.getButton().equals(MouseButton.SECONDARY)){ //Con este evento se elimina la compuerta seleccionada y con ella todos los datos dependientes .
                Label label = (Label) t.getSource(); //Se toma la compuerta sleccionada
                ejecucion.eliminarCompuerta(label);
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
        /**
         * Método encargado de atender todos los elementos relacionados al liberar el mouse de un label. Entre ellos están hacia donde se extiende la linea.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY) & !(t.isAltDown())) {
                int numComp = ejecucion.getListaImageViewComp().getPos(t.getSource());
                Compuertas compuerta = (Compuertas) ejecucion.getlista().buscar(numComp).getDato();
                Linea temp = (Linea) ejecucion.getLineasComp().buscar(ejecucion.getLineasComp().getLargo() - 1).getDato();
                Lista listaCompuertas = ejecucion.getlista();
                Nodo aux = listaCompuertas.getHead();
                while (aux != null){
                    Compuertas comp = (Compuertas) aux.getDato();
                    double X = compuerta.getPosX();
                    double Y = compuerta.getPosY();
                    if ((t.getX()+X >= comp.getPosX() & t.getX()+X <= comp.getPosX()+167)& t.getY()+Y >= comp.getPosY() & t.getY()+Y <= comp.getPosY()+92){
                        temp.setCompB(ejecucion.getlista().getPos(comp));
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
    EventHandler<MouseEvent> clickNuevaCompuerta = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de crear una nueva instancia de la nueva compuerta y todos los elementos dependientes de esta.
         * @param event instancia derivada de la clase Mouse Event.
         */
        @Override
        public void handle(MouseEvent event) {
            ejecucion.annadirCompuerta((NuevaCompuerta)ejecucion.getCompuertasNuevas().buscar(0).getDato());
            NuevaCompuerta comp = (NuevaCompuerta) ejecucion.getlista().buscar(ejecucion.getNuemeroCompuertas()-1).getDato();
            Image im = (Image)comp.getImagenes().buscar(0).getDato();
            Circle circle = new Circle(160,45,5);
            Circle circle1 = new Circle(0,45,5);
            comp.getCirculos().add(circle);
            comp.getCirculos().add(circle1);
            Label label = new Label();
            label.setGraphic(new ImageView(im));
            label.setOnMousePressed(LabelOnMousePressedEventHandler);
            label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
            label.setOnMouseReleased(LabelOnMouseReleased);
            ejecucion.getListaImageViewComp().add(label);
            pane1.getChildren().addAll(label,circle,circle1);
        }
    };
    EventHandler<MouseEvent> LabelOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
        /**
         * Método encargado de atender todos los eventos relacionados al arrastre del label. Entre sus funciones están el fin del grag and drop.
         * @param t instancia derivada de la clase MouseEvent.
         */
        @Override
        public void handle(MouseEvent t) {
            if (t.getButton().equals(MouseButton.SECONDARY)) {
                return;
            } else {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                ((Label) (t.getSource())).setTranslateX(newTranslateX);
                ((Label) (t.getSource())).setTranslateY(newTranslateY);
                int numComp = ejecucion.getListaImageViewComp().getPos(t.getSource());
                Compuertas comp = (Compuertas) ejecucion.getlista().buscar(numComp).getDato();
                Nodo temp = comp.getCirculos().getHead();
                int indice = 0;
                while (temp != null) {
                    Circle circle = (Circle) temp.getDato();
                    if (indice == 0) {
                        circle.setCenterX(newTranslateX + 160);
                        circle.setCenterY(newTranslateY + 45);
                    } else {
                        circle.setCenterX(newTranslateX);
                        circle.setCenterY(newTranslateY + 45);
                    }
                    temp = temp.getNext();
                    indice++;
                }
                indice = 0;
                Nodo temp2 = comp.getListaEtiquetas().getHead();
                while (temp2 != null) {
                    Label label = (Label) temp2.getDato();
                    if (indice == 0) {
                        label.setLayoutY(newTranslateY + 32);
                        label.setLayoutX(newTranslateX + 4);
                    } else {
                        label.setLayoutX(newTranslateX + 126);
                        label.setLayoutY(newTranslateY + 32);
                    }
                    temp2 = temp2.getNext();
                    indice++;
                }
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
    private void Tabla(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Tabla.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Tabla de verdad");
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Método encargado de modular el circuito.
     */
    @FXML
    private void guardar(){
        try {
            ejecucion.annadirNuevaCompuerta(new NuevaCompuerta(ejecucion.getTablaVerdad(),ejecucion.getNumeroEntradas(),ejecucion.getNumeroSalidas()));
            pane.getChildren().clear();
            ejecucion.nuevaHoja();
            Image im = new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\nueva.png");
            ImageView view = new ImageView(im);
            view.setOnMouseClicked(clickNuevaCompuerta);
            view.setFitHeight(67);
            view.setFitWidth(137);
            vBox.getChildren().add(view);

        }catch (Exception e){
            System.out.println("No se ha calculado la tabla de verdad");
        }
    }

    /**
     * Método encargado de probar y establecer todas las conexiones del circuito.
     */
    @FXML
    private void probar(){
        try {
            Lista lineasInterruptores = ejecucion.getLineasInterr();
            Lista lineasCompuertas = ejecucion.getLineasComp();
            Nodo temp1 = lineasInterruptores.getHead();
            Nodo temp2 = lineasCompuertas.getHead();
            while (temp1 != null) {
                Linea linea = (Linea) temp1.getDato();
                ejecucion.conectarInterrup(linea.getCompA(), linea.getCompB());
                temp1 = temp1.getNext();
            }
            while (temp2 != null) {
                Linea linea = (Linea) temp2.getDato();
                ejecucion.conexiones(linea.getCompA(), linea.getCompB());
                temp2 = temp2.getNext();
            }
            ejecucion.probar();
        }catch (Exception e){
            System.out.println("Alguna compuerta no se encuetra con todas las entradas definidas");
        }
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
        ejecucion.annadirCompuerta(new Compuerta_NOT());
        Compuertas comp = (Compuertas) ejecucion.getlista().buscar(ejecucion.getNuemeroCompuertas()-1).getDato();
        Image im = (Image)comp.getImagenes().buscar(0).getDato();
        Label label2 = new Label("i<null>");
        Label label3 = new Label("o<null>");
        label2.setFont(new Font("Arial",12));
        label3.setFont(new Font("Arial",12));
        label2.setLayoutX(4);
        label2.setLayoutY(32);
        label3.setLayoutX(126);
        label3.setLayoutY(32);
        comp.getListaEtiquetas().add(label2);
        comp.getListaEtiquetas().add(label3);
        Circle circle = new Circle(0,45,5);
        Circle circle1 = new Circle(160,45,5);
        comp.getCirculos().add(circle1);
        comp.getCirculos().add(circle);
        Label label = new Label();
        label.setGraphic(new ImageView(im));
        label.setOnMousePressed(LabelOnMousePressedEventHandler);
        label.setOnMouseDragged(LabelOnMouseDraggedEventHandler);
        label.setOnMouseReleased(LabelOnMouseReleased);
        ejecucion.getListaImageViewComp().add(label);
        pane1.getChildren().addAll(label,circle,circle1,label2,label3);
    }
    /**
     * Método que detecta si se seleccionó un interruptor en la paleta y procede a dibujarlo en el canvas.
     * @param event
     */
    @FXML
    private void pressinterruptor(MouseEvent event){
        ejecucion.annadirInterruptor();
        Interruptor interr = (Interruptor)ejecucion.getInter().buscar(ejecucion.getInter().getLargo()-1).getDato();
        Image im = (Image) interr.getImage().buscar(1).getDato();
        Label label = new Label();
        Circle circle = new Circle(16,87,5);
        interr.setCircle(circle);
        label.setGraphic(new ImageView(im));
        label.setOnMousePressed(interruptorOnMousePressed);
        label.setOnMouseDragged(interruptorOnMouseDragged);
        label.setOnMouseReleased(interruptorOnMouseReleased);
        ejecucion.getListaImageViewInterr().add(label);
        pane1.getChildren().addAll(label,circle);
    }

    /**
     * Método encargado restablecer la aplicación a los valores de inicio.
     */
    @FXML
    private void reset(){
        ejecucion = new Ejecutar();
        pane.getChildren().clear();

    }
}
