package Logica;

import javafx.scene.control.Label;
import javafx.scene.image.Image;


/**
 * Clase encargada del funcionamiento de la compuerta lógica NOT.
 * @author Sebastián Moya.
 */
public class Compuerta_NOT extends Compuertas {
    private final int numeroSalidas = 1;

    /**
     * Método constructor.
     */
    public Compuerta_NOT(){
        this.indice =0;
        this.numeroEntradas =1;
        try{
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\Compuerta not.png"));
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
            if(entradas.verificar(true)){
                Label label = (Label) listaEtiquetas.buscar(1).getDato();
                label.setText("o<false>");
                salidas.add(false);
            }else{
                Label label = (Label)listaEtiquetas.buscar(1).getDato();
                label.setText("o<true>");
                salidas.add(true);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
        notificar();
    }
}
