package Lógica;

/**
 * Clase encargada del funcionamiento de la compuerta lógica OR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_OR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_OR(){
        this.numeroSalidas = 1;
        this.indice =0;
        this.numeroEntradas =2;
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if(indice == numeroEntradas){
            if (entradas.buscar(0).getDato().equals(true) || entradas.buscar(1).getDato().equals(true)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
    }
}
