package Lógica;

public class Compuerta_NOT extends Compuertas {
    private final int numeroSalidas = 1;

    public Compuerta_NOT(){
        this.indice =0;
        this.numeroEntradas =1;
    }
    @Override
    public void operar() {
        if (indice == numeroEntradas){
            if(entradas.buscar(0).getDato().equals(true)){
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
    }
}
