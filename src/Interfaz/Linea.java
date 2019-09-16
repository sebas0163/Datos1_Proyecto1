package Interfaz;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


import static Interfaz.Controller.pane1;

/**
 * Clase encargada de crear las lineas que se dibujan  en el canvas.
 * @author Sebastián Moya Monge
 * @date 11/09/19
 */
public class Linea {
    private double inicioX;
    private double inicioY;
    private double finX;
    private double finY;
    private int compA;
    private int compB;
    private Label label;

    /**
     * Método constructor de la clase.
     */
    public Linea(){
        this.inicioX =0;
        this.inicioY=0;
        this.finX = 0;
        this.finY =0;
    }

    /**
     * Método encargado de dibujar la linea en la posición deseada.
     */
    public void dibujar(){
        Line line = new Line(inicioX,inicioY,finX,finY);
        line.setStrokeWidth(3.87);
        line.setStroke(new Color(Math.random(),Math.random(),Math.random(),1));
        pane1.getChildren().add(line);

    }

    /**
     * Método encargado de establecer el valor en x de donde inicia la linea.
     * @param inicioX punto donde se presiona el mouse en x.
     */
    public void setInicioX(double inicioX) {
        this.inicioX = inicioX;
    }

    /**
     * Método encargado de establecer el valor en y de donde inicia la linea.
     * @param inicioY punto donde se presiona el mouse en y.
     */
    public void setInicioY(double inicioY) {
        this.inicioY = inicioY;
    }

    /**
     * Método encargado de establecer el valor en x en donde finaliza la linea.
     * @param finX punto donde se libera el mouse en x
     */
    public void setFinX(double finX) {
        this.finX = finX;
    }

    /**
     * Método encargado de establecer el valor en y en donde finaliza la linea.
     * @param finY punto donde se libera el mouse en y.
     */
    public void setFinY(double finY) {
        this.finY = finY;
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
}

