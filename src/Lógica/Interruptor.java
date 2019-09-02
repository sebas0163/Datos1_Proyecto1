package Lógica;

public class Interruptor {
    private boolean estado;
    public Interruptor(){
        this.estado = false;
    }
    public void cambiarEstado(){
        if(estado){
            estado = false;
        }else{
            estado = true;
        }
    }
    public void mostrar(){
        System.out.println(estado);
    }
}
