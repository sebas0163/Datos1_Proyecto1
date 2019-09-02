package Lógica;

/**
 * Clase encargada de dar un valor true o false a una entrada.
 * @author Sebastián Moya
 * @date 1/09/19
 */
public class Interruptor extends Compuertas {

    /**
     * Método constructor.
     */
    public Interruptor(){
        this.salidas = new Lista<>();
        salidas.add(false);
    }
    /**
     * Método encargado de cambiar el valor de salida del interruptor.
     */
    @Override
    public void operar() {
        if (salidas.buscar(0).equals(true)) {
            salidas.eliminar(0);
            salidas.add(false);
        } else {
            salidas.eliminar(0);
            salidas.add(true);
        }
    }
}
