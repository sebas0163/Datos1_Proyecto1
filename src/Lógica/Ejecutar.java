package Lógica;

public class Ejecutar {
    private Lista<Compuertas> listaCompuertas;
    private Lista<Interruptor> listaInterruptores;

    public Ejecutar() {
        this.listaCompuertas = new Lista<>();
        this.listaInterruptores = new Lista<>();
    }
    public void añadirInterruptor(){
        listaInterruptores.add(new Interruptor());
    }
    public void añadirCompuerta(Compuertas comp){
        listaCompuertas.add(comp);
    }
    public void conectarInterrup(int a, int b){
        Interruptor select = (Interruptor) listaInterruptores.buscar(a).getDato();
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        B.setEntradas(select.isEstado());
    }
    public void conexiones(int a,int b){
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
        B.setEntradas(A.getSalida(0));// arreglar el operar
    }
    public Lista getlista(){
        return listaCompuertas;
    }
    public Lista getInter(){
        return listaInterruptores;
    }
    public void probar(){
        int indice = 0;
        while (listaCompuertas.getLargo() != indice){
            Compuertas comp = (Compuertas)listaCompuertas.buscar(indice).getDato();
            comp.operar();
            indice ++;
        }
    }
}
