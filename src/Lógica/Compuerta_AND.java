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
    public Compuerta_AND(int cantidadEntradas){
        this.numeroSalidas = 1;
        this.numeroEntradas = cantidadEntradas;
    }

    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.verificar(false)){
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("no se puede");
        }
        notificar();
    }
}