package Lógica;

import javafx.scene.image.Image;

/**
 * Clase encargada del funcionamiento de la compuerta lógica NOT.
 * @author Sebastián Moya.
 * @date 01/09/19
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
                salidas.add(false);
            }else{
                salidas.add(true);
            }
        }else{
            System.out.println("faltan entradas por asignar");
        }
        notificar();
    }
}
