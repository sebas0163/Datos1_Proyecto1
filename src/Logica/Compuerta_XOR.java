package Logica;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica XOR.
 * @author Sebastián Moya.
 */
public class Compuerta_XOR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     * @param cantidadEntradas numero de entradas que tendra la compuerta
     */
    public Compuerta_XOR(int cantidadEntradas){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
        this.indice = 0;
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta XOr.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta XOr2.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta XOr3.png"));
        }catch (Exception e) {
            System.out.println("error al cargar imagenes");
        }
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if (indice == numeroEntradas){
            if (entradas.buscar(0).getDato().equals(true)^ entradas.buscar(1).getDato().equals(true)){
                Label label = (Label) listaEtiquetas.buscar(listaEtiquetas.getLargo()-1).getDato();
                label.setText("o<true>");
                salidas.add(true);
            }else{
                Label label = (Label) listaEtiquetas.buscar(listaEtiquetas.getLargo()-1).getDato();
                label.setText("o<false>");
                salidas.add(false);
            }
        }else{
            System.out.println("Faltan entradas por asignar");
        }
        notificar();
    }
}
