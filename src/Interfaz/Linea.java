package Interfaz;

import javafx.scene.canvas.GraphicsContext;

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
