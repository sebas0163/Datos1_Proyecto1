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
    public Compuerta_OR(int cantidadEntradas){
        this.numeroSalidas = 1;
        this.indice =0;
        this.numeroEntradas =cantidadEntradas;
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if(indice == numeroEntradas){
            if (entradas.verificar(true)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
        notificar();
    }
}
