package Interfaz;

import Lógica.Compuerta_AND;
import Lógica.Compuertas;
import Lógica.Ejecutar;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static Interfaz.Controller.*;

public class controller2 {
    @FXML
    private RadioButton seleccion1;
    @FXML
    private RadioButton seleccion2;
    @FXML
    private RadioButton seleccion3;
    @FXML
    private RadioButton seleccion4;
    private GraphicsContext gc;
    private Ejecutar ejecutar;
    private boolean sinSeleccion;


    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        seleccion1.setToggleGroup(group);
        seleccion2.setToggleGroup(group);
        seleccion3.setToggleGroup(group);
        seleccion4.setToggleGroup(group);
        gc = context;
        ejecutar = ejecución;
        sinSeleccion = true;//completar acción
    }
    private void  dibujarImagen(int num){
        Compuertas comp;
        Image im;
        if (num == 2){
            ejecutar.añadirCompuerta(new Compuerta_AND(2));
            comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas()-1).getDato();
            im = (Image) comp.getImagenes().buscar(0).getDato();
            gc.drawImage(im,0,0);
            System.out.println(im);
        }else if(num == 3){
            ejecutar.añadirCompuerta(new Compuerta_AND(3));
            comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas()-1).getDato();
            im = (Image) comp.getImagenes().buscar(1).getDato();
            gc.drawImage(im,300,200);
        }else{
            ejecutar.añadirCompuerta(new Compuerta_AND(4));
            comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas()-1).getDato();
            im = (Image) comp.getImagenes().buscar(2).getDato();
            gc.drawImage(im,500,300);
        }
    }
}
