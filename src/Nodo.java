/**
 * Clase Nodo: representa un nodo de una lista enlazada simple.
 * Contiene un valor entero y una referencia al siguiente nodo.
 * 
 * @author river
 */
public class Nodo {
    // Valor almacenado en el nodo
    private int dato;

    // Referencia al siguiente nodo en la lista
    private Nodo siguiente;

    // Constructor: inicializa el nodo con un dato y sin siguiente
    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getter del dato
    public int getDato() {
        return dato;
    }

    // Setter del dato
    public void setDato(int dato) {
        this.dato = dato;
    }

    // Getter de la referencia al siguiente nodo
    public Nodo getSiguiente() {
        return siguiente;
    }

    // Setter de la referencia al siguiente nodo
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
