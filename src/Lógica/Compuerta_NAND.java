package Lógica;

public class Compuerta_NAND extends Compuertas {
    private int numeroSalidas;

    public Compuerta_NAND(){
        this.numeroEntradas = 2;
        this.numeroSalidas = 1;
    }
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.buscar(0).getDato().equals(false) || entradas.buscar(1).getDato().equals(false)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
    }
}
