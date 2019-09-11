package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NAND.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_NAND extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_NAND(int cantidadEntradas){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand2.png"));
        this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand3.png"));
    }

    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.verificar(false)){
                salidas.add(true);
            }else{
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
        notificar();
    }
}
