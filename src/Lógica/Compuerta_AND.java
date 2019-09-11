package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica AND.
 * @author Sebastián Moya.
 * @date 31/08/19
 */
public class Compuerta_AND extends Compuertas{
    private int numeroSalidas;

    /**
     * Método constructor que define los valores de los.
     */
    public Compuerta_AND(int cantidadEntradas){
        this.numeroSalidas = 1;
        this.numeroEntradas = cantidadEntradas;
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta And.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta And2.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta And3.png"));
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.verificar(false)){
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("no se puede");
        }
        notificar();
    }
}