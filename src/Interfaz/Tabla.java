package Interfaz;

import Lógica.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static Interfaz.Controller.ejecución;

public class Tabla {
    @FXML
    private TableView tabla;
    private Lista<Boolean> listaValores;
    private Lista<TableColumn> listaColumnas;
    private Ejecutar ejecutar;
    private double iteraciones;
    private Lista<Compuertas> listaCompuertas;

    /**
     * Método que inicializa los atributos de la interfaz.
     */
    public void initialize() {
        this.ejecutar = ejecución;
        this.listaValores = new Lista<>();
        this.listaColumnas = new Lista<>();
        this.listaCompuertas = new Lista<>();
        this.iteraciones = Math.pow(2, ejecutar.getNumeroEntradas());
        setValores();
        añadirColumna();
        tomarCompuertas();
        //calcularTabla();
    }

    /**
     * Método que calcula la tabla de verdad y los añade a las columnas.
     */
    /*private void calcularTabla() {
        Nodo temp = listaCompuertas.getHead();
        int indice =1;
        while (indice != iteraciones){
            cambiarElementos(indice);
            int pos = 0;
            while (temp != null){
                Compuertas comp = (Compuertas) temp.getDato();
                comp.setEntradas((boolean)listaValores.buscar(pos).getDato());
                pos ++;
                temp = temp.getNext();
            }
            indice++;
        }

    }*/

    /**
     * Método que añade valores true a la lista de valores, según la cantidad de entradas de la compuerta
     */
    private void setValores(){
        while(listaValores.getLargo() - 1 != ejecutar.getNumeroEntradas()){
            listaValores.add(true);
        }
    }
     private void cambiarElementos(int indice){
         double exponente = iteraciones /2 ;
         int n = 0; // posición de la lista.
         while(n != listaValores.getLargo()){
             if (indice == exponente){
                 listaValores.modificarNodo(0,false);
             }
             exponente = exponente / 2;
             if (indice % exponente == 0){
                 if ((boolean)listaValores.buscar(n).getDato()){
                     listaValores.modificarNodo(n,false);
                 }else{
                     listaValores.modificarNodo(n,true);
                 }
             }
             n ++;
         }
     }

    /**
     * Método que añade columnas a la tabla según la cantidad de ntradas y salidas.
     */
    private void añadirColumna(){
        int indice = 0;
        while (indice != ejecutar.getNumeroEntradas()){
            TableColumn column = new TableColumn("entrada");
            column.setCellValueFactory();
            listaColumnas.add(column);
            tabla.getColumns().add(column);
            indice ++;
        }
        indice = 0;
        while(indice != ejecutar.getNumeroSalidas()){
            TableColumn column = new TableColumn("Salida");
            listaColumnas.add(column);
            tabla.getColumns().add(column);
            indice ++;
        }
    }

    /**
     * Método que toma las compuertas que poseen las entradas del nuevo circuito
     */
    private void tomarCompuertas(){
        Nodo temp = ejecutar.getLineasInterr().getHead();
        while (temp != null){
            Linea line = (Linea) temp.getDato();
            Compuertas comp = (Compuertas)ejecutar.getlista().buscar(line.getCompB()).getDato();
            comp.resetEntradas();
            listaCompuertas.add(comp);
            temp = temp.getNext();
        }
    }
}
