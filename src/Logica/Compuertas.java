package Logica;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * Superclase encargada de definir los métodos y atributos que heredarán las clases hijas.
 * @author Sebastián Moya
 */
public abstract class Compuertas {
    protected Lista<Boolean> salidas;
    protected Lista<Boolean> entradas;
    protected Lista<Compuertas> observadores;
    protected Lista<Integer> entradasDependientes;
    protected int indice;
    protected Lista<Image> imagenes;
    protected int numeroEntradas;
    protected double posX;
    protected double posY;
    protected Lista<Label> listaEtiquetas;
    protected Lista<Circle> circulos;
    protected int circulosDisponibles; // circulo a donde va a terminar a la linea dibujada

    /**
     * Método constructor, que define el valor de los atributos.
     */
    public Compuertas(){
        this.salidas = new Lista<>();
        this.entradas = new Lista<>();
        this.indice = 0;
        this.observadores = new Lista<>();
        this.entradasDependientes = new Lista<>();
        this.imagenes = new Lista<>();
        this.circulos= new Lista<>();
        this.circulosDisponibles = 1;
        this.listaEtiquetas = new Lista<>();
    }

    /**
     * Método encargado de añadir a la lista de observadores los obajetos que necesitan ser actualizados cuando la compuerta cambie.
     * @param comp compuerta que está atenta de algún cambio en la salida del sujeto.
     */
    public void agregarObservador(Compuertas comp){
        observadores.add(comp);
    }

    /**
     * Método encargado de notificar a los observadores del cambio en la compuerta.
     */
    protected void notificar(){
        Nodo temp = observadores.getHead();
        int index = 0;
        while(temp != null){
            Compuertas comp = (Compuertas) temp.getDato();
            int pos = (int) entradasDependientes.buscar(index).getDato();
            comp.actualizar(pos,(boolean)salidas.buscar(0).getDato());
            temp = temp.getNext();
            index ++;
        }
    }
    /**
     * Método que incerta la cantidad de entradas dependientes de una salida.
     * @param pos numero de entradas dependientes.
     */
    public void setEntradasDependientes(int pos){
        entradasDependientes.add(pos);
    }

    /**
     * Método encargado de asignar valores a las entradas de la compuerta
     * @param entrada valor booleano
     */
    public void setEntradas(boolean entrada){
        if(indice == numeroEntradas){
            System.out.println("entradas completas");
        }else{
            entradas.add(entrada);
            try {
                Label label = (Label) listaEtiquetas.buscar(entradas.getLargo() - 1).getDato();
                label.setText("i<" + entrada + ">");
            }catch (Exception e){
                System.out.println("No hay Etiquetas");
            }
            indice ++;
            if(indice == numeroEntradas){ operar();}
        }
    }

    /**
     * Método encargado de actualizar las entradas y salidas después de un cambio.
     * @param x es el numero de entrada que se quiere actualizar.
     * @param salida valor booleano a modificar en la posición deseada.
     */
    public void actualizar(int x,boolean salida){
        entradas.modificarNodo(x,salida);
        Label label = (Label) listaEtiquetas.buscar(x).getDato();
        label.setText("i<"+salida+">");
        salidas.reset();
        operar();
    }

    /**
     * Método que me elimina todos los elementos de entradas.
     */
    public void resetEntradas(){
        entradas = new Lista<>();
        indice = 0;
    }

    /**
     * Método encargado de calcular la salida de una compuerta.
     */
    public abstract void operar();

    /**
     * Método que retorna una de las salidas de la compuerta
     * @param pos numero de la salida de la que se desea obtener el valor
     * @return valor de la salida.
     */
    public boolean getSalida(int pos){
        boolean salida = (boolean)salidas.buscar(pos).getDato();
        return salida;
    }
    // Setters y getters
    public Lista<Boolean> getEntradas() {
        return entradas;
    }

    public Lista<Integer> getEntradasDependientes() {
        return entradasDependientes;
    }

    public Lista<Compuertas> getObservadores() {
        return observadores;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
    public int getIndice(){
        return indice;
    }
    public void setIndice(int indice){
        this.indice = indice;
    }
    public Lista getCirculos(){
        return circulos;
    }

    public int getCirculosDisponibles() {
        return circulosDisponibles;
    }

    public Lista<Boolean> getSalidas() {
        return salidas;
    }

    public void setCirculosDisponibles(int circulosDisponibles) {
        this.circulosDisponibles = circulosDisponibles;
    }
    public Lista getImagenes(){
        return this.imagenes;
    }

    public Lista getListaEtiquetas() {
        return listaEtiquetas;
    }
}