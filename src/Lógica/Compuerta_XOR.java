package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica XOR.
 * @author Sebastián Moya.
 * @date 01/09/19
 */
public class Compuerta_XOR extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
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
