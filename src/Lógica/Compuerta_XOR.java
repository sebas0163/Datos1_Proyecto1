package Lógica;

/**
 * Clase encargada del funcionamiento de la compuerta lógica XOR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_XOR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_XOR(int cantidadEntradas, Interruptor inter){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
        this.indice = 0;
        this.interruptor = inter;
        this.interruptor.agregarObservador(this);
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if (indice == numeroEntradas){
            if (entradas.buscar(0).getDato().equals(true)^ entradas.buscar(1).getDato().equals(true)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("Faltan entradas por asignar");
        }
    }
}
