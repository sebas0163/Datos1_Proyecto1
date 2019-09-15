package Lógica;

import Interfaz.Linea;

import javax.swing.text.html.ImageView;

/**
 * Clase encargada de manejar la logica de las compuertas que son agragadas al area de trabajo.
 * @author Sebastián Moya.
 * @date 02/09/19
 */
public class Ejecutar {
    private Lista<Linea> lineas;
    private Lista<Compuertas> listaCompuertas;
    private Lista<Interruptor> listaInterruptores;
    private Lista<ImageView> listaImageView;
    private int numeroCompuertas;

    /**
     * Método constructor de la clase
     */
    public Ejecutar() {
        this.listaCompuertas = new Lista<>();
        this.listaInterruptores = new Lista<>();
        this.lineas = new Lista<>();
        this.listaImageView = new Lista<>();
        this.numeroCompuertas = 0;
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
    public void conectarInterrup(int a, int b){
        Interruptor select = (Interruptor) listaInterruptores.buscar(a).getDato();
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        select.agregarObservador(B);
        B.setEntradas(select.isEstado());
        select.setEntradasDependientes(B.entradas.getLargo() -1);
    }

    /**
     * Método que establece la conexión entre compuertas
     * @param a dato de tipo entero que representa la posición de la compuerta a la que se le tomará el valor de su salida
     * @param b dato de tipo entero que representa la posición de la compuerta a la que se le asignará el valor de su entrada
     */
    public void conexiones(int a,int b){
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
        A.agregarObservador(B);
        B.setEntradas(A.getSalida(0));// arreglar el operar
        A.setEntradasDependientes(B.entradas.getLargo()-1);
    }

    /**
     * Método que retorna la lista de compuertas
     * @return atributo listaCompuertas
     */
    public Lista getlista(){
        return listaCompuertas;
    }
    public Lista getListaImageView(){
        return listaImageView;
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
    public void insertarLinea(Linea l){
        lineas.add(l);
    }

    /**
     * Método que devuleve la lista con las lineas dibujadas.
     * @return lineas dibujadas.
     */
    public Lista getLineas(){
        return lineas;
    }

    /**
     * Método que devuelve la cantidad de compuertas que hay en uso.
     * @return dato de tipo entero hace referencia al atributo numeroCompuertas.
     */
    public int getNuemeroCompuertas(){
        return numeroCompuertas;
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


    }
}
