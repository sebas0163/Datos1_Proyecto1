package Lógica;

/**
 * Clase encargada del funcionamiento de la compuerta lógica AND.
 * @author Sebastián Moya.
 * @date 31/08/19
 */
public class Compuerta_AND extends Compuertas{
    private int numeroSalidas;

    /**
     * Método constructor que define los valores de los.
     */
    public Compuerta_AND(){
        this.numeroSalidas = 1;
        this.numeroEntradas = 2;
    }

    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.buscar(0).getDato().equals(true) & entradas.buscar(1).getDato().equals(true)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("no se puede");
        }
    }
}