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

    /**
     * Método constructor de la clase.
     * @param context recibe el contexto gráfico del canvas.
     */
    public Linea(GraphicsContext context){
        this.inicioX =0;
        this.inicioY=0;
        this.finX = 0;
        this.finY =0;
        this.gc = context;
    }

    /**
     * Método encargado de dibujar la linea en la posición deseada.
     */
    private void dibujar(){
        gc.setStroke(new Color(Math.random(),Math.random(),Math.random(),1));// encargado de dar un color aleatorio.
        gc.setLineWidth(3.0);
        gc.strokeLine(inicioX,inicioY,finX,finY);
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
        dibujar();
    }

    /**
     * Establece el valor guarda la linea y pertenece a la salida de un interruptor o una entrada.
     * @param valor dato de tipo booleano.
     */
    public void setValor(boolean valor){
        this.valor = valor;
    }
}

