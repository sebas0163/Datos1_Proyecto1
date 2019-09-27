package Logica;

import javafx.scene.image.Image;
import javafx.scene.shape.Circle;


/**
 * Clase encargada de dar un valor true o false a una entrada.
 * @author Sebastián Moya
 */
public class Interruptor {
    private Lista<Compuertas> observadores;
    private Lista<Integer> entradasDependientes;//Guarda la posción de la entrada que depende de la salida del interruptor
    private boolean estado;
    private Lista<Image> imagenes;
    private double posX;
    private double posY;
    private Circle circle;

    /**
     * Método constructor.
     */
    public Interruptor() {
        this.estado = false;
        this.observadores = new Lista<>();
        this.entradasDependientes = new Lista<>();
        this.imagenes = new Lista<>();
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Interruptor1.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Interruptor2.png"));
        }catch (Exception e) {
            System.out.println("error al cargar imagenes");
        }
    }

    public void setEntradasDependientes(int pos){
        entradasDependientes.add(pos);
    }

    public void agregarObservador(Compuertas comp){
        observadores.add(comp);
    }

    /**
     * Método encargado de enviar a cada observador la notificación de que el estado del interruptor a cambiado.
     */
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
    //Setters y getters
    public boolean isEstado() {
        return estado;
    }
    public Lista<Image> getImage(){
        return imagenes;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Lista<Compuertas> getObservadores() {
        return observadores;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
