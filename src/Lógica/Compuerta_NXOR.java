package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NXOR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_NXOR extends Compuertas {
    private int numeroSalidas;
    /**
     * Método constructor
     */
    public Compuerta_NXOR(int cantidadEntradas){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
        this.indice = 0;
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta NXOR.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta NXOR2.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta NXOR3.png"));
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
            if (entradas.verificar(false) ^ entradas.verificar(true)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("Faltan entradas por asignar");
        }
        notificar();
    }
}
