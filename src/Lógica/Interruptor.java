package Lógica;

/**
 * Clase encargada de dar un valor true o false a una entrada.
 * @author Sebastián Moya
 * @date 1/09/19
 */
public class Interruptor {
    private boolean estado;

    /**
     * Método constructor.
     */
    public Interruptor() {
        this.estado = false;
    }
    /**
     * Método encargado de cambiar el valor de salida del interruptor.
     */
    public void cambiarEstado(){
        if(estado){
            this.estado = false;
        }else{
            this.estado = true;
        }
    }

    public boolean isEstado() {
        return estado;
    }
}
