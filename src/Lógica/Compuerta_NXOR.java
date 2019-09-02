package Lógica;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NXOR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_NXOR extends Compuertas {
    private int numeroSalidas;
    /**
     * Método constructor
     */
    public Compuerta_NXOR(){
        this.numeroEntradas = 2;
        this.numeroSalidas = 1;
        this.indice = 0;
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if (indice == numeroEntradas){
            if (entradas.buscar(0).getDato().equals(true) ^ entradas.buscar(1).getDato().equals(true)){
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("Faltan entradas por asignar");
        }
    }
}
