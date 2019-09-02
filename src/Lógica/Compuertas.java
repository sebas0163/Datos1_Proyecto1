package Lógica;

/**
 * Superclase encargada de definir los métodos y atributos que heredarán las clases hijas.
 * @author Sebastián Moya
 */
public abstract class Compuertas {
    protected Lista<Boolean> salidas;
    protected Lista<Boolean> entradas;
    protected int indice;
    protected int numeroEntradas;

    /**
     * Método constructor, que define el valor de los atributos.
     */
    public Compuertas(){
        this.salidas = new Lista<>();
        this.entradas = new Lista<>();
        this.indice = 0;
    }
    public void setEntradas(boolean entrada){
        if(indice == numeroEntradas){
            System.out.println("entradas completas");
        }else{
            entradas.add(entrada);
            indice ++;
        }
    }
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
    public void conexionCompuertas(boolean salida){//Cuando yo selecciono la salida de la compuerta voy a tomar el valor de la salida  lista.buscar(x).getvalor().getsalidas(x)

    }
    public boolean getSalida(int pos){
        boolean salida = (boolean)salidas.buscar(pos).getDato();
        return salida;
    }

}
