package Lógica;

/**
 * Clase encargada de manejar la logica de las compuertas que son agragadas al area de trabajo.
 * @author Sebastián Moya.
 * @date 02/09/19
 */
public class Ejecutar {
    private Lista<Compuertas> listaCompuertas;
    private Lista<Interruptor> listaInterruptores;

    /**
     * Método constructor de la clase
     */
    public Ejecutar() {
        this.listaCompuertas = new Lista<>();
        this.listaInterruptores = new Lista<>();
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
    }

    /**
     * Método encargado de establecer la conexión entre compuertas e interruptores.
     * @param a dato de tipo entero que es el número de interruptor a utilizar.
     * @param b dato de tipo entero que indica en que posición de la lista se encuentra la compuerta a utilizar.
     */
    public void conectarInterrup(int a, int b){
        Interruptor select = (Interruptor) listaInterruptores.buscar(a).getDato();
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        B.setEntradas(select.isEstado());
    }

    /**
     * Método que establece la conexión entre compuertas
     * @param a dato de tipo entero que representa la posición de la compuerta a la que se le tomará el valor de su salida
     * @param b dato de tipo entero que representa la posición de la compuerta a la que se le asignará el valor de su entrada
     */
    public void conexiones(int a,int b){
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
        B.setEntradas(A.getSalida(0));// arreglar el operar
    }

    /**
     * Método que retorna la lista de compuertas
     * @return atributo listaCompuertas
     */
    public Lista getlista(){
        return listaCompuertas;
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
}
