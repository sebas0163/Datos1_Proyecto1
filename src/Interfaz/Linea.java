package Interfaz;

import Lógica.Compuertas;
import Lógica.Interruptor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


import static Interfaz.Controller.ejecución;
import static Interfaz.Controller.pane1;

/**
 * Clase encargada de crear las lineas que se dibujan  en el canvas.
 * @author Sebastián Moya Monge
 * @date 11/09/19
 */
public class Linea {
    private int compA;
    private int compB;
    private Line lineaDibujada;
    /**
     * Método que permite dibujar las lineas que salen del interruptor y llegan a otra compuerta.
     */
    public void dibujarLineaInterruptor(){
        Line line = new Line();
        Interruptor inter = (Interruptor) ejecución.getInter().buscar(compA).getDato();
        Compuertas compuertas = (Compuertas) ejecución.getlista().buscar(compB).getDato();
        line.startYProperty().bind(inter.getCircle().centerYProperty());
        line.startXProperty().bind(inter.getCircle().centerXProperty());
        line.endXProperty().bind(((Circle)compuertas.getCirculos().buscar(compuertas.getCirculosDisponibles()).getDato()).centerXProperty());
        line.endYProperty().bind(((Circle)compuertas.getCirculos().buscar(compuertas.getCirculosDisponibles()).getDato()).centerYProperty());
        line.setStrokeWidth(3.87);
        line.setStroke(new Color(Math.random(),Math.random(),Math.random(),1));
        compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles()+1);
        this.lineaDibujada = line;
        pane1.getChildren().add(line);
    }

    /**
     * Método encargado de dibujar la linea en la posición deseada.
     */
    public void dibujar(){
        Line line = new Line();
        Compuertas comp1 = (Compuertas) ejecución.getlista().buscar(compA).getDato();
        Compuertas comp2 = (Compuertas) ejecución.getlista().buscar(compB).getDato();
        line.startXProperty().bind(((Circle)comp1.getCirculos().buscar(0).getDato()).centerXProperty());
        line.startYProperty().bind(((Circle)comp1.getCirculos().buscar(0).getDato()).centerYProperty());
        line.endXProperty().bind(((Circle)comp2.getCirculos().buscar(comp2.getCirculosDisponibles()).getDato()).centerXProperty());
        line.endYProperty().bind(((Circle)comp2.getCirculos().buscar(comp2.getCirculosDisponibles()).getDato()).centerYProperty());
        line.setStrokeWidth(3.87);
        line.setStroke(new Color(Math.random(),Math.random(),Math.random(),1));
        comp2.setCirculosDisponibles(comp2.getCirculosDisponibles() + 1);
        this.lineaDibujada = line;
        pane1.getChildren().add(line);

    }
    /**
     *  Posiciona la compuerta a la que se le tomara su salida.
     * @param A entrada de tipo entero que representa la posición de la compuerta a la que se le toma´ra su entrada.
     */
    public void setCompA(int A){
        this.compA = A;
    }
    public void setCompB(int B){
        this.compB = B;
    }

    public int getCompA() {
        return compA;
    }

    public int getCompB() {
        return compB;
    }

    public Line getLineaDibujada() {
        return lineaDibujada;
    }
}

