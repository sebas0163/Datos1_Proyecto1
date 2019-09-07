package Lógica;

/**
 * Superclase encargada de definir los métodos y atributos que heredarán las clases hijas.
 * @author Sebastián Moya
 */
public abstract class Compuertas {
    protected Interruptor interruptor;
    protected Lista<Boolean> salidas;
    protected Lista<Boolean> entradas;
    protected Lista<Compuertas> observadores;
    protected Lista<Integer> entradasDependientes;
    protected int indice;
    protected int numeroEntradas;

    /**
     * Método constructor, que define el valor de los atributos.
     */
    public Compuertas(){
        this.salidas = new Lista<>();
        this.entradas = new Lista<>();
        this.indice = 0;
        this.observadores = new Lista<>();
        this.entradasDependientes = new Lista<>();
    }
    public void agregarObservador(Compuertas comp){
        observadores.add(comp);
    }
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
        salidas.reset();
        operar();
    }

    /**
     * Método encargado de calcular la salida de una compuerta.
     */
    public abstract void operar();
    public void mostrar(){
        System.out.println(salidas.buscar(0).getDato());
    }

    /**
     * Método encargado de eliminar una entrda en específico.
     * @param numeroEntrada entrada que se desea eliminar
     */
    public void eliminarEntrada(int numeroEntrada){
        entradas.eliminar(numeroEntrada);
        indice --;
        salidas = new Lista<>();
    }

    /**
     * Método que retorna una de las salidas de la compuerta
     * @param pos numero de la salida de la que se desea obtener el valor
     * @return valor de la salida.
     */
    public boolean getSalida(int pos){
        boolean salida = (boolean)salidas.buscar(pos).getDato();
        return salida;
    }

}
