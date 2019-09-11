package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NOR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_NOR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_NOR(int cantidadEntradas){
        this.numeroSalidas = 1;
        this.indice =0;
        this.numeroEntradas =cantidadEntradas;
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nor.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nor2.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nor3.png"));
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if (indice == numeroEntradas){
            if (entradas.verificar(true)){
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("Faltan entradas por asignar");
        }
        notificar();
    }
}
