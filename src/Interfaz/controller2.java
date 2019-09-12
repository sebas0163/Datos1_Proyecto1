package Interfaz;

import Lógica.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static Interfaz.Controller.*;

public class controller2 {
    @FXML
    private RadioButton seleccion1;
    @FXML
    private RadioButton seleccion2;
    @FXML
    private RadioButton seleccion3;
    @FXML
    private Button bton;
    private GraphicsContext gc;
    private Ejecutar ejecutar;
    public static int compuerta = 0;


    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        seleccion1.setToggleGroup(group);
        seleccion2.setToggleGroup(group);
        seleccion3.setToggleGroup(group);
        gc = context;
        ejecutar = ejecución;
    }
    @FXML
    private void accionar(){
        if (seleccion1.isSelected()){
            dibujarImagen(2);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }else if (seleccion2.isSelected()){
            dibujarImagen(3);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }else {
            dibujarImagen(4);
            Stage stage = (Stage)seleccion1.getScene().getWindow();
            stage.close();
        }
    }
    private void  dibujarImagen(int num){
        Compuertas comp;
        Image im;
        switch (compuerta) {
            case 1:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_AND(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_AND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_AND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im, (int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
            case 2:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_OR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_OR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
            case 3:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NAND(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
            case 4:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
            case 5:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_XOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
            case 6:
                if (num == 2) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(2));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(0).getDato();
                    gc.drawImage(im,(int) (Math.random())*1200, (int)(Math.random()*600));
                    System.out.println(im);
                } else if (num == 3) {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(3));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(1).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                } else {
                    ejecutar.añadirCompuerta(new Compuerta_NXOR(4));
                    comp = (Compuertas) ejecutar.getlista().buscar(ejecutar.getNuemeroCompuertas() - 1).getDato();
                    im = (Image) comp.getImagenes().buscar(2).getDato();
                    gc.drawImage(im,(int) (Math.random()*1200), (int)(Math.random()*600));
                }
                break;
        }

    }
}
