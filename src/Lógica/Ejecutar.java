package Lógica;

public class Ejecutar {
    private Lista<Compuertas> listaCompuertas;

    public Ejecutar() {
        this.listaCompuertas = new Lista<>();
    }
    public void añadirCompuerta(Compuertas comp){
        listaCompuertas.add(comp);
    }
    public void conexiones(int a,int b){
        Compuertas B = (Compuertas) listaCompuertas.buscar(b).getDato();
        Compuertas A = (Compuertas) listaCompuertas.buscar(a).getDato();
        B.setEntradas(A.getSalida(0));// arreglar el operar
    }
    public Lista getlista(){
        return listaCompuertas;
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
