package Interfaz;

import Lógica.*;

public class main {
    public static void main(String[] args) {
        Lista<Compuertas> lista = new Lista<>();
        lista.add(new Compuerta_AND());
        lista.add(new Compuerta_OR());
        lista.add(new Compuerta_AND());
        System.out.println(lista.buscar(0).getDato());

    }
}
