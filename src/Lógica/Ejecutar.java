package Lógica;

import Interfaz.Linea;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import static Interfaz.Controller.pane1;

/**
 * Clase encargada de manejar la logica de las compuertas que son agragadas al area de trabajo.
 * @author Sebastián Moya.
 * @date 02/09/19
 */
public class Ejecutar {
    private Lista<Linea> lineasComp;
    private Lista<Linea> lineasInterr;
    private Lista<Compuertas> listaCompuertas;
    private Lista<Interruptor> listaInterruptores;
    private Lista<ImageView> listaImageViewComp;
    private Lista<ImageView> listaImageViewInterr;
    private int numeroCompuertas;


    /**
     * Método constructor de la clase
     */
    public Ejecutar() {
        this.listaCompuertas = new Lista<>();
        this.listaInterruptores = new Lista<>();
        this.lineasComp = new Lista<>();
        this.listaImageViewComp = new Lista<>();
        this.listaImageViewInterr = new Lista<>();
        this.numeroCompuertas = 0;
        this.lineasInterr = new Lista<>();
    }

    /**
     * Método que añade a la lista de interruptores, los interruptores que se usan en el area de trabajo.
     */
    public void añadirInterruptor(){
        listaInterruptores.add(new Interruptor());
    }

    /**
     * Método que añade a la lista de compuertas, las compuertas que se usan en la area de trabajo.
     * @param comp dato de tipo compuerta, es el que se va a añadir.
     */
    public void añadirCompuerta(Compuertas comp){
        listaCompuertas.add(comp);
        numeroCompuertas ++;
    }

    /**
     * Método encargado de establecer la conexión entre compuertas e interruptores, también se encarga de agreagar a a la lista de cbservadores las compuerta dependientes de este interruptor.
     * @param a dato de tipo entero que es el número de interruptor a utilizar.
     * @param b dato de tipo entero que indica en que posición de la lista se encuentra la compuerta a utilizar.
     */
    public void conectarInterrup(int a, int b) {
        Interruptor select = (Interruptor) listaInterruptores.buscar(a).getDato();
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        select.agregarObservador(B);
        System.out.println(B.getIndice());
        B.setEntradas(select.isEstado());
        select.setEntradasDependientes(B.entradas.getLargo() -1);
    }

    /**
     * Método que establece la conexión entre compuertas
     * @param a dato de tipo entero que representa la posición de la compuerta a la que se le tomará el valor de su salida
     * @param b dato de tipo entero que representa la posición de la compuerta a la que se le asignará el valor de su entrada
     */
    public void conexiones(int a,int b){
        try {
            Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
            Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
            A.agregarObservador(B);
            System.out.println(B.getIndice());
            B.setEntradas(A.getSalida(0));// arreglar el operar
            A.setEntradasDependientes(B.entradas.getLargo() - 1);
        }catch (Exception e){
            System.out.println("La salida es null");
        }
    }

    /**
     * Método que retorna la lista de compuertas
     * @return atributo listaCompuertas
     */
    public Lista getlista(){
        return listaCompuertas;
    }
    public Lista getListaImageViewComp(){
        return listaImageViewComp;
    }
    public Lista getListaImageViewInterr(){
        return listaImageViewInterr;
    }

    /**
     * Método encargado de devolver la lista de interruptores.
     * @return atributo lista de interruptores.
     */
    public Lista getInter(){
        return listaInterruptores;
    }
    public void probar(){
        int indice = 0;
        while (listaCompuertas.getLargo() != indice){
            Compuertas comp = (Compuertas)listaCompuertas.buscar(indice).getDato();
            comp.operar();
            indice ++;
        }
    }

    /**
     * Método encargado de añadir una linea a la lista de lineas.
     * @param l dato de tipo linea, que contiene a la linea dibujada.
     */
    public void insertarLinea(Linea l,int identificador) {
        if (identificador == 1) {
            lineasComp.add(l);
        }else{
            lineasInterr.add(l);
        }
    }

    /**
     * Método que devuleve la lista con las lineas dibujadas.
     * @return lineas dibujadas.
     */
    public Lista getLineasComp(){
        return lineasComp;
    }

    /**
     * Método encargado de devolver las lista de lineas concetadas al interruptor.
     * @return
     */
    public Lista getLineasInterr(){
        return lineasInterr;
    }

    /**
     * Método que devuelve la cantidad de compuertas que hay en uso.
     * @return dato de tipo entero hace referencia al atributo numeroCompuertas.
     */
    public int getNuemeroCompuertas(){
        return numeroCompuertas;
    }

    /**
     * Método encargado de elimnar un interruptor y los datos asociados a este.
     * @param label es el label en donde se encuentra el dato a eliminar.
     */
    public void eliminarInterruptor(Label label){
        int numeroInterruptor = listaImageViewInterr.getPos(label);
        Interruptor interruptor = (Interruptor) listaInterruptores.buscar(numeroInterruptor).getDato();
        Nodo temporal = interruptor.getObservadores().getHead();
        int indice = 0;
        while (temporal != null){//Elimina las entradas que están dependientes al interruptor
            Compuertas aux = (Compuertas) temporal.getDato();
            aux.getEntradas().eliminar((Integer) aux.getEntradasDependientes().buscar(indice).getDato());
            aux.setIndice(aux.getIndice()-1);
            indice ++;
            temporal = temporal.getNext();
        }
        temporal = lineasInterr.getHead();
        while (temporal != null){ // elimina todas las lineas que tienen datos del interruptor.
            Linea linea = (Linea) temporal.getDato();
            if(linea.getCompA() == numeroInterruptor){
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles()-1);
                lineasInterr.eliminar(lineasInterr.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            }else{
                temporal = temporal.getNext();
            }
        }
        pane1.getChildren().remove(interruptor.getCircle());
        listaImageViewInterr.eliminar(numeroInterruptor);
        listaInterruptores.eliminar(numeroInterruptor);
    }

    /**
     * Método encargado de eliminar una compuerta, también es el encargado de actualizar todas las listas y observadores
     * @param label contenerdor de la compuerta
     */
    public void eliminarCompuerta(Label label){
        numeroCompuertas --;
        int numeroCompuerta = listaImageViewComp.getPos(label);
        Compuertas comp = (Compuertas) listaCompuertas.buscar(numeroCompuerta).getDato();
        Nodo temporal = listaCompuertas.getHead();
        while (temporal != null) {  // bucle que permite borrar a la compuerta de las listas de observadores de otras compuertas
            Compuertas aux = (Compuertas) temporal.getDato();
            if (aux.getObservadores().getHead()== null) {
                System.out.println("No hay observadores");
            } else {
                while (aux.getObservadores().verificar(comp)) {
                    aux.getEntradasDependientes().eliminar(aux.getObservadores().getPos(comp));// Arreglar error
                    aux.getObservadores().eliminar(aux.getObservadores().getPos(comp));
                }
            }
            temporal = temporal.getNext();
        }
        temporal = comp.getObservadores().getHead();
        int indice = 0;
        while (temporal != null){ //bucle encargado de eliminar las entradas que dependen de la salida de la compuerta.
            Compuertas aux = (Compuertas) temporal.getDato();
            aux.getEntradas().eliminar((Integer) comp.getEntradasDependientes().buscar(indice).getDato());
            aux.setIndice(aux.getIndice()-1);
            indice ++;
            temporal = temporal.getNext();
        }
        temporal = lineasComp.getHead();
        while(temporal != null){ // bucle encargado de eliminar lineas de conexion entre compuertas que tengan datos dependientes a la compuerta a eliminar
            Linea linea = (Linea) temporal.getDato();
            if(linea.getCompA() == numeroCompuerta || linea.getCompB() == numeroCompuerta){
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles()-1);
                lineasComp.eliminar(lineasComp.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            }else{
                temporal = temporal.getNext();
            }
        }
        temporal = lineasInterr.getHead();
        while (temporal != null){ //bucle encargado de eliminar las lines de conexiones entre interruptor y compuertas que poseen datos de la compuerta a eliminar.
            Linea linea = (Linea) temporal.getDato();
            if(linea.getCompB() == numeroCompuerta){
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles()-1);
                lineasInterr.eliminar(lineasInterr.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            }else{
                temporal = temporal.getNext();
            }
        }
        temporal = comp.getCirculos().getHead();
        while (temporal != null){
            Circle circle = (Circle) temporal.getDato();
            pane1.getChildren().remove(circle);
            temporal = temporal.getNext();
        }
        listaCompuertas.eliminar(numeroCompuerta);
        listaImageViewComp.eliminar(numeroCompuerta);

    }

    public static void main(String[] args) {
        Ejecutar e = new Ejecutar();
        e.añadirInterruptor();// seguir pruebas
        e.añadirInterruptor();
        Interruptor k = (Interruptor) e.listaInterruptores.buscar(1).getDato();
        k.cambiarEstado();
        e.añadirCompuerta(new Compuerta_AND(2));
        e.añadirCompuerta(new Compuerta_AND(2));
        e.añadirCompuerta(new Compuerta_AND(2));
        e.conectarInterrup(0,0);
        e.conectarInterrup(0,0);
        e.conexiones(0,1);
        e.conectarInterrup(1,1);
        e.conexiones(1,2);
        e.conectarInterrup(1,2);
        e.probar();
        Compuertas r = (Compuertas)e.listaCompuertas.buscar(2).getDato();
        r.mostrar();
        Interruptor j = (Interruptor) e.listaInterruptores.buscar(0).getDato();
        j.cambiarEstado();
        r.mostrar();
        j.cambiarEstado();
        r.mostrar();
        /*e.añadirCompuerta(new Compuerta_AND(2));
        e.añadirCompuerta(new Compuerta_AND(2));
        e.conexiones(0,1);
        e.conexiones(0,1);
        e.añadirInterruptor();
        e.conectarInterrup(0,0);
        e.conectarInterrup(0,0);*/
        //e.probar();
        //Compuertas r = (Compuertas)e.listaCompuertas.buscar(1).getDato();


    }
}
