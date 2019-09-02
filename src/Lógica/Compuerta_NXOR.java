package Lógica;

public class Compuerta_NXOR extends Compuertas {
    private int numeroSalidas;
    public Compuerta_NXOR(){
        this.numeroEntradas = 2;
        this.numeroSalidas = 1;
        this.indice = 0;
    }
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
