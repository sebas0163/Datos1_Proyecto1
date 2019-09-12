package Lógica;

import javafx.scene.image.Image;

import java.awt.*;
import java.sql.Array;

/**
 * Clase encargada de dar un valor true o false a una entrada.
 * @author Sebastián Moya
 * @date 1/09/19
 */
public class Interruptor {
    private Lista<Compuertas> observadores;
    private Lista<Integer> entradasDependientes;//Guarda la posción de la entrada que depende de la salida del interruptor
    private boolean estado;
    private Lista<Image> imagenes;

    /**
     * Método constructor.
     */
    public Interruptor() {
        this.estado = false;
        this.observadores = new Lista<>();
        this.entradasDependientes = new Lista<>();
        this.imagenes = new Lista<>();
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Interruptor1.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Interruptor2.png"));
    }
    public void setEntradasDependientes(int pos){
        entradasDependientes.add(pos);
    }
    public void agregarObservador(Compuertas comp){
        observadores.add(comp);
    }
    public void notificar(){
        Nodo temp = observadores.getHead();
        int index = 0;
        while(temp != null){
            Compuertas comp = (Compuertas) temp.getDato();
            int pos = (int) entradasDependientes.buscar(index).getDato();
            comp.actualizar(pos,estado);
            temp = temp.getNext();
            index ++;
        }
    }
    /**
     * Método encargado de cambiar el valor de salida del interruptor.
     */
    public void cambiarEstado(){
        if(estado){
            this.estado = false;
            notificar();
        }else{
            this.estado = true;
            notificar();
        }
    }

    public boolean isEstado() {
        return estado;
    }
    public Lista<Image> getImage(){
        return imagenes;
    }
}
