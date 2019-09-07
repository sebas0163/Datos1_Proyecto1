package Lógica;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NAND.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_NAND extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_NAND(int cantidadEntradas){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
    }

    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.verificar(false)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
    }
}
