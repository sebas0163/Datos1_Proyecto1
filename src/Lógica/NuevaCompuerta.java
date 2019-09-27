package Lógica;


import javafx.scene.image.Image;

public class NuevaCompuerta extends Compuertas {
    private Lista<Lista> tablaVerdad;
    private int numeroSalidas;
    private boolean encontrado;

    /**
     * Método constructor.
     * @param tablaVerdad lista con los distintos valores de verdad que puede tener el nuevo circuito.
     * @param numeroEntradas cantidad de entradas del nuevo circuito.
     * @param numeroSalidas cantidad de salidas del nuevo circuito.
     */
    public NuevaCompuerta(Lista tablaVerdad, int numeroEntradas,int numeroSalidas){
        this.tablaVerdad = tablaVerdad;
        this.numeroEntradas = numeroEntradas;
        this.numeroSalidas = numeroSalidas;
        this.encontrado = false;
        this.circulosDisponibles = 1;
        try {
            this.imagenes.add(new Image("file:C:\\Users\\sebas\\Desktop\\git\\Datos1_Proyecto1\\src\\Interfaz\\Imagenes\\nueva.png"));
        }catch (Exception e){
            System.out.println("No se puede cargar la imagen");
        }
    }

    /**
     * Método encargado de operar el circuito
     */
    @Override
    public void operar() {
        if (numeroEntradas == indice) {
            Nodo temp = tablaVerdad.getHead();
            while (temp != null) {
                int pos = 0;
                Lista sublista = (Lista) temp.getDato();
                Nodo temp2 = sublista.getHead();
                while (pos != sublista.getLargo() - numeroSalidas) {
                    Boolean dato = (boolean) temp2.getDato();
                    if ((boolean) entradas.buscar(pos).getDato() != dato) {
                        temp = temp.getNext();
                        break;
                    } else if (pos == (sublista.getLargo() - numeroSalidas) - 1) {
                        pos++;
                        setSalidas(sublista, pos);
                        encontrado = true;
                    } else {
                        temp2 = temp2.getNext();
                        pos++;
                    }
                }
                if (encontrado) {
                    break;
                }
            }
            for (int i = 0; i <= numeroSalidas;i++){
                System.out.println("Salida"+(i+1)+": "+ salidas.buscar(i).getDato());
            }
        }else{
            System.out.println("no se puede");
        }
    }

    /**
     * Método encargado de agragr las salidas correspondientes a dicha lista.
     * @param sublista lista donde se encunetran los datos a buscar.
     * @param pos posición en donde partir para encontrar las entradas.
     */
    private void setSalidas(Lista sublista, int pos){
        Lista lista = sublista;
        int n = pos;
        while (n != lista.getLargo()){
            salidas.add((boolean)sublista.buscar(n).getDato());
            n ++;
        }
    }
    @Override
    public void setCirculosDisponibles(int CirculosDisponibles){
        return;
    }

}
