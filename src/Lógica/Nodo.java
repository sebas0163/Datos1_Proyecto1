package Lógica;

/**
 * Clase encargarda de alamcenar los datos asignados y asociarlos o apuntarlos otro obejto de la misma clase.
 * @author sebas
 * @param <T> parámetro que representa el tipo de dato que se va a almacenar.
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> next;

    public Nodo(T dato) {
        this.dato = dato;
        this.next = null;
    }

    /**
     * Método que toma ek nodo que está siendo apuntado por el nodo actual
     * @return el siguiente nodo
     */
    public Nodo<T> getNext() {
        return next;
    }

    /**
     * Método que retorna el dato guardado en el nodo
     * @return retorna el atributo dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * Método que define a cual nodo va a apuntar el nodo actual
     * @param next Es un objeto de tipo nodo, que tiene un valor asignada para guardar
     */
    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}
