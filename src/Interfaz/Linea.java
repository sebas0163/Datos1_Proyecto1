package Interfaz;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    private boolean valor;
    private GraphicsContext gc;

    public Linea(GraphicsContext context){
        this.inicioX =0;
        this.inicioY=0;
        this.finX = 0;
        this.finY =0;
        this.gc = context;
    }
    private void dibujar(){
        gc.setStroke(new Color(Math.random(),Math.random(),Math.random(),1));
        gc.setLineWidth(3.0);
        gc.strokeLine(inicioX,inicioY,finX,finY);
    }
    public void setInicioX(double inicioX) {
        this.inicioX = inicioX;
    }

    public void setInicioY(double inicioY) {
        this.inicioY = inicioY;
    }

    public void setFinX(double finX) {
        this.finX = finX;
    }

    public void setFinY(double finY) {
        this.finY = finY;
        dibujar();
    }
    public void setValor(boolean valor){
        this.valor = valor;
    }
}
