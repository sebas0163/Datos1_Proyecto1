package Interfaz;

import Lógica.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.script.SimpleBindings;

import static Interfaz.Controller.ejecución;

public class Tabla {
    @FXML
    private TableView<Lista> tabla;
    private Lista<Boolean> listaValores;
    private Lista<TableColumn> listaColumnas;
    private Ejecutar ejecutar;
    private double iteraciones;
    private Lista<Compuertas> listaCompuertas;
    private ObservableList<Lista> tablaVerdad;
    private Lista<Lista> calculoVerdad;

    /**
     * Método que inicializa los atributos de la interfaz.
     */
    public void initialize() {
        this.ejecutar = ejecución;
        this.tablaVerdad = FXCollections.observableArrayList();
        this.listaValores = new Lista<>();
        this.listaColumnas = new Lista<>();
        this.listaCompuertas = new Lista<>();
        this.calculoVerdad = new Lista<>();
        this.iteraciones = Math.pow(2, ejecutar.getNumeroEntradas());
        setValores();
        añadirColumna();
        tomarCompuertas();
        calcularTabla();
    }

    /**
     * Método que calcula la tabla de verdad y los añade a las columnas.
     */
    private void calcularTabla() {
        Nodo temp = listaCompuertas.getHead();
        int indice =0;
        while (indice != iteraciones){
            Lista<Boolean> fila = new Lista<>();
            cambiarElementos(indice);
            int pos = 0;
            Compuertas comp = (Compuertas) temp.getDato();
            comp.getEntradas().reset();
            comp.setIndice(0);
            comp.getSalidas().reset();
            while (temp != null){
                comp = (Compuertas) temp.getDato();
                comp.setEntradas((boolean)listaValores.buscar(pos).getDato());
                fila.add((boolean)listaValores.buscar(pos).getDato());
                pos ++;
                temp = temp.getNext();
            }
            temp = listaCompuertas.getHead();
            operarEntradas();
            fila = ejecutar.salidas(fila);
            indice++;
            Nodo temp2 = fila.getHead();
            while (temp2 != null){
                System.out.println((boolean)temp2.getDato());
                temp2 = temp2.getNext();
            }
            System.out.println("__________________________");
            calculoVerdad.add(fila);
            tablaVerdad.add(fila);
        }
        ejecutar.setTablaVerdad(calculoVerdad);
        añadirTabla();
    }
    private void añadirTabla(){
        Nodo temp = listaColumnas.getHead();
        int a = 0;
        while (temp != null){
            int contador = a;
            TableColumn<Lista,Boolean> column = (TableColumn) temp.getDato();
            column.setCellValueFactory(valores -> new SimpleBooleanProperty((boolean)valores.getValue().buscar(contador).getDato()));
            temp = temp.getNext();
            a++;
        }
        tabla.setItems(tablaVerdad);
    }

    /**
     * Método que añade valores true a la lista de valores, según la cantidad de entradas de la compuerta
     */
    private void setValores(){
        while(listaValores.getLargo() - 1 != ejecutar.getNumeroEntradas()){
            listaValores.add(true);
        }
    }

    /**
     * Método que va cambiando los elemntos de la lista de valores cada tanto de iteraciones, según la tabla de verdad.
     * @param indice
     */
     private void cambiarElementos(int indice){
         double exponente = iteraciones /2 ;
         int n = 1; // posición de la lista.
         if (indice == 0){
             return;
         }else {
             if (indice == exponente) {
                 listaValores.modificarNodo(0, false);
             }
             while (n != listaValores.getLargo()) {
                 exponente = exponente / 2;
                 if (indice % exponente == 0) {
                     if ((boolean) listaValores.buscar(n).getDato()) {
                         listaValores.modificarNodo(n, false);
                     } else {
                         listaValores.modificarNodo(n, true);
                     }
                 }
                 n++;
             }
         }
     }

    /**
     * Método que permite operar el circuito de nuevo sin tomar en cuenta los interruptores.
     */
    private void operarEntradas(){
        Nodo temp = listaCompuertas.getHead();
        while (temp != null){
            Compuertas comp = (Compuertas) temp.getDato();
            comp.operar();
            temp = temp.getNext();
        }
     }

    /**
     * Método que añade columnas a la tabla según la cantidad de ntradas y salidas.
     */
    private void añadirColumna(){
        int indice = 0;
        while (indice != ejecutar.getNumeroEntradas()){
            TableColumn column = new TableColumn<Lista,Boolean>("entrada");
            listaColumnas.add(column);
            tabla.getColumns().add(column);
            indice ++;
        }
        indice = 0;
        while(indice != ejecutar.getNumeroSalidas()){
            TableColumn column = new TableColumn<Lista,Boolean>("Salida");
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
