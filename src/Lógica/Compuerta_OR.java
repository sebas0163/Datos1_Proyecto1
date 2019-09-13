package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica OR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_OR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     */
    public Compuerta_OR(int cantidadEntradas){
        this.numeroSalidas = 1;
        this.indice =0;
        this.numeroEntradas =cantidadEntradas;
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta or.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta or2.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta or3.png"));
        }catch (Exception e) {
            System.out.println("error al cargar imagenes");
        }
    }
    /**
     * Calcula el valor de la salida de la compuerta según sus entradas y tabla de verdad.
     */
    @Override
    public void operar() {
        if(indice == numeroEntradas){
            if (entradas.verificar(true)){
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
