package Logica;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NAND.
 * @author Sebastián Moya.
 */
public class Compuerta_NAND extends Compuertas {
    private int numeroSalidas;

    /**
     * Método constructor
     * @param cantidadEntradas numero de entradas que tendrá la compuerta
     */
    public Compuerta_NAND(int cantidadEntradas){
        this.numeroEntradas = cantidadEntradas;
        this.numeroSalidas = 1;
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand2.png"));
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta Nand3.png"));
        }catch (Exception e) {
            System.out.println("error al cargar imagenes");
        }
    }

    /**
     * Calcula el valor de la salida de la compuerta según sus entradas
     */
    @Override
    public void operar(){
        if(indice == numeroEntradas){
            if(entradas.verificar(false)){
                Label label = (Label) listaEtiquetas.buscar(listaEtiquetas.getLargo()-1).getDato();
                label.setText("o<true>");
                salidas.add(true);
            }else{
                Label label = (Label) listaEtiquetas.buscar(listaEtiquetas.getLargo()-1).getDato();
                label.setText("o<false>");
                salidas.add(false);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
        notificar();
    }
}
