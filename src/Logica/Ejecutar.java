package Logica;

import Interfaz.Linea;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import static Interfaz.Controller.pane1;

/**
 * Clase encargada de manejar la logica de las compuertas que son agragadas al area de trabajo.
 * @author Sebastián Moya.
 */
public class Ejecutar {
    private Lista<Linea> lineasComp;
    private Lista<Linea> lineasInterr;
    private Lista<Compuertas> listaCompuertas;
    private Lista<NuevaCompuerta> compuertasNuevas;
    private Lista<Interruptor> listaInterruptores;
    private Lista<ImageView> listaImageViewComp;
    private Lista<ImageView> listaImageViewInterr;
    private Lista<Lista> tablaVerdad;
    private int numeroCompuertas;
    private int numeroEntradas;
    private int numeroSalidas;

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
        this.numeroEntradas = 0;
        this.compuertasNuevas = new Lista<>();
        this.lineasInterr = new Lista<>();
    }

    /**
     * Método que añade a la lista de interruptores, los interruptores que se usan en el area de trabajo.
     */
    public void annadirInterruptor() {
        listaInterruptores.add(new Interruptor());
    }

    /**
     * Método que añade a la lista de compuertas, las compuertas que se usan en la area de trabajo.
     *
     * @param comp dato de tipo compuerta, es el que se va a añadir.
     */
    public void annadirCompuerta(Compuertas comp) {
        listaCompuertas.add(comp);
        numeroCompuertas++;
    }

    /**
     * Método que inserta a la lista de compuertas la nueva compuerta creada.
     *
     * @param comp compuerta del tipo nueva compuerta.
     */
    public void annadirNuevaCompuerta(NuevaCompuerta comp) {
        compuertasNuevas.add(comp);
    }

    /**
     * Método encargado de establecer la conexión entre compuertas e interruptores, también se encarga de agreagar a a la lista de cbservadores las compuerta dependientes de este interruptor.
     *
     * @param a dato de tipo entero que es el número de interruptor a utilizar.
     * @param b dato de tipo entero que indica en que posición de la lista se encuentra la compuerta a utilizar.
     */
    public void conectarInterrup(int a, int b) {
        Interruptor select = (Interruptor) listaInterruptores.buscar(a).getDato();
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        select.agregarObservador(B);
        B.setEntradas(select.isEstado());
        select.setEntradasDependientes(B.entradas.getLargo() - 1);
        numeroEntradas++;
    }

    /**
     * Método que establece la conexión entre compuertas
     *
     * @param a dato de tipo entero que representa la posición de la compuerta a la que se le tomará el valor de su salida
     * @param b dato de tipo entero que representa la posición de la compuerta a la que se le asignará el valor de su entrada
     */
    public void conexiones(int a, int b) {
        try {
            Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
            Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
            A.agregarObservador(B);
            B.setEntradas(A.getSalida(0));// arreglar el operar
            A.setEntradasDependientes(B.entradas.getLargo() - 1);
        } catch (Exception e) {
            System.out.println("La salida es null");
        }
    }
    /**
     * Método encargado de probar el circuito según las entradas asignadas.
     */
    public void probar() {
        int indice = 0;
        while (listaCompuertas.getLargo() != indice) {
            Compuertas comp = (Compuertas) listaCompuertas.buscar(indice).getDato();
            comp.operar();
            indice++;
        }
        calcularSalidas();
    }

    /**
     * Método que elimina reestablce todas los atributos necesarios a su valor inicial
     */
    public void nuevaHoja(){
        listaCompuertas.reset();
        listaInterruptores.reset();
        listaImageViewInterr.reset();
        listaImageViewComp.reset();
        //tablaVerdad.reset();
        lineasInterr.reset();
        lineasComp.reset();
        numeroCompuertas = 0;
        numeroEntradas =0;
        numeroSalidas = 0;
    }
    /**
     * Método encargado de calcular la canitdad de salidas sin asociar en un circuito.
     */
    private void calcularSalidas() {
        Nodo temp = listaCompuertas.getHead();
        while (temp != null) {
            Compuertas comp = (Compuertas) temp.getDato();
            if (comp.getEntradasDependientes().getLargo() == 0) {
                numeroSalidas++;
                temp = temp.getNext();
            } else {
                temp = temp.getNext();
            }
        }
    }

    /**
     * Método que busca las compuertas que tienen las salidas sin asociar y tomas sus salidas.
     * @param fila recibe la lista que posee los valores de una fila.
     * @return Lista con las salidas del nuevo circuto.
     */
    public Lista salidas(Lista fila) {
        Nodo temp = listaCompuertas.getHead();
        Lista filaAux = fila;
        while (temp != null) {
            Compuertas comp = (Compuertas) temp.getDato();
            if (comp.getEntradasDependientes().getLargo() == 0) {
                filaAux.add(comp.getSalida(0));
                temp = temp.getNext();
            } else {
                temp = temp.getNext();
            }
        }
        return filaAux;
    }
    /**
     * Método encargado de añadir una linea a la lista de lineas.
     * @param identificador  número que permite reconocer la linea.
     * @param l dato de tipo linea, que contiene a la linea dibujada.
     */
    public void insertarLinea(Linea l, int identificador) {
        if (identificador == 1) {
            lineasComp.add(l);
        } else {
            lineasInterr.add(l);
        }
    }

    /**
     * Método encargado de elimnar un interruptor y los datos asociados a este.
     *
     * @param label es el label en donde se encuentra el dato a eliminar.
     */
    public void eliminarInterruptor(Label label) {
        int numeroInterruptor = listaImageViewInterr.getPos(label);
        Interruptor interruptor = (Interruptor) listaInterruptores.buscar(numeroInterruptor).getDato();
        Nodo temporal = interruptor.getObservadores().getHead();
        int indice = 0;
        while (temporal != null) {//Elimina las entradas que están dependientes al interruptor
            Compuertas aux = (Compuertas) temporal.getDato();
            aux.getEntradas().eliminar((Integer) aux.getEntradasDependientes().buscar(indice).getDato());
            aux.setIndice(aux.getIndice() - 1);
            indice++;
            temporal = temporal.getNext();
        }
        temporal = lineasInterr.getHead();
        while (temporal != null) { // elimina todas las lineas que tienen datos del interruptor.
            Linea linea = (Linea) temporal.getDato();
            if (linea.getCompA() == numeroInterruptor) {
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles() - 1);
                lineasInterr.eliminar(lineasInterr.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            } else {
                temporal = temporal.getNext();
            }
        }
        pane1.getChildren().remove(interruptor.getCircle());
        listaImageViewInterr.eliminar(numeroInterruptor);
        listaInterruptores.eliminar(numeroInterruptor);
    }

    /**
     * Método encargado de eliminar una compuerta, también es el encargado de actualizar todas las listas y observadores
     *
     * @param label contenerdor de la compuerta
     */
    public void eliminarCompuerta(Label label) {
        numeroCompuertas--;
        int numeroCompuerta = listaImageViewComp.getPos(label);
        Compuertas comp = (Compuertas) listaCompuertas.buscar(numeroCompuerta).getDato();
        Nodo temporal = listaCompuertas.getHead();
        while (temporal != null) {  // bucle que permite borrar a la compuerta de las listas de observadores de otras compuertas
            Compuertas aux = (Compuertas) temporal.getDato();
            if (aux.getObservadores().getHead() == null) {
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
        while (temporal != null) { //bucle encargado de eliminar las entradas que dependen de la salida de la compuerta.
            Compuertas aux = (Compuertas) temporal.getDato();
            aux.getEntradas().eliminar((Integer) comp.getEntradasDependientes().buscar(indice).getDato());
            aux.setIndice(aux.getIndice() - 1);
            indice++;
            temporal = temporal.getNext();
        }
        temporal = lineasComp.getHead();
        while (temporal != null) { // bucle encargado de eliminar lineas de conexion entre compuertas que tengan datos dependientes a la compuerta a eliminar
            Linea linea = (Linea) temporal.getDato();
            if (linea.getCompA() == numeroCompuerta || linea.getCompB() == numeroCompuerta) {
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles() - 1);
                lineasComp.eliminar(lineasComp.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            } else {
                temporal = temporal.getNext();
            }
        }
        temporal = lineasInterr.getHead();
        while (temporal != null) { //bucle encargado de eliminar las lines de conexiones entre interruptor y compuertas que poseen datos de la compuerta a eliminar.
            Linea linea = (Linea) temporal.getDato();
            if (linea.getCompB() == numeroCompuerta) {
                Compuertas compuertas = (Compuertas) listaCompuertas.buscar(linea.getCompB()).getDato();
                compuertas.setCirculosDisponibles(compuertas.getCirculosDisponibles() - 1);
                lineasInterr.eliminar(lineasInterr.getPos(linea));
                pane1.getChildren().remove(linea.getLineaDibujada());
                temporal = temporal.getNext();
            } else {
                temporal = temporal.getNext();
            }
        }
        temporal = comp.getCirculos().getHead();
        while (temporal != null) { //bucle que elimina los circulos.
            Circle circle = (Circle) temporal.getDato();
            pane1.getChildren().remove(circle);
            temporal = temporal.getNext();
        }
        temporal = comp.getListaEtiquetas().getHead();
        while (temporal != null){
            Label aux = (Label) temporal.getDato();
            pane1.getChildren().remove(aux);
            temporal = temporal.getNext();
        }
        listaCompuertas.eliminar(numeroCompuerta);
        listaImageViewComp.eliminar(numeroCompuerta);

    }
    // Setters y Getters

    public Lista<NuevaCompuerta> getCompuertasNuevas() {
        return compuertasNuevas;
    }
    public Lista getInter() {
        return listaInterruptores;
    }
    public Lista getlista() {
        return listaCompuertas;
    }
    public Lista getListaImageViewComp() {
        return listaImageViewComp;
    }
    public Lista getListaImageViewInterr() {
        return listaImageViewInterr;
    }
    public Lista<Lista> getTablaVerdad() {
        return tablaVerdad;
    }
    public Lista getLineasComp(){
        return lineasComp;
    }
    public Lista getLineasInterr(){
        return lineasInterr;
    }
    public int getNuemeroCompuertas(){
        return numeroCompuertas;
    }
    public int getNumeroSalidas() {
        return numeroSalidas;
    }
    public int getNumeroEntradas() {
        return numeroEntradas;
    }
    public void setTablaVerdad(Lista<Lista> tablaVerdad) {
        this.tablaVerdad = tablaVerdad;
    }
}
