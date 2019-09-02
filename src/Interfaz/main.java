package Interfaz;

import Lógica.*;

public class main {
    public static void main(String[] args) {
        Ejecutar e = new Ejecutar();
        e.añadirCompuerta(new Interruptor());
        e.añadirCompuerta(new Compuerta_AND());
        e.añadirCompuerta(new Compuerta_OR());
        e.conexiones(0,1);
        e.conexiones(0,1);
        e.conexiones(1,2);
        e.conexiones(1,2);
        e.probar();
        Lista p = e.getlista();
        Compuertas r = (Compuertas)p.buscar(2).getDato();
        r.mostrar();
    }
}
