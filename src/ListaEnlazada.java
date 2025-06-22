// Clase que implementa una lista enlazada simple
public class ListaEnlazada {
    // Referencia al primer nodo de la lista
    private Nodo cabeza;

    // Constructor: lista vacía
    public ListaEnlazada() {
        cabeza = null;
    }

    // Agrega un nuevo nodo al inicio de la lista
    public void agregarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    // Agrega un nuevo nodo al final de la lista
    public void agregarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    // Elimina el primer nodo que contenga el dato especificado
    public boolean suprimirNodo(int dato) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.getDato() == dato) {
            cabeza = cabeza.getSiguiente();
            return true;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null && actual.getSiguiente().getDato() != dato) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());
            return true;
        } else {
            return false;
        }
    }

    // Ordena la lista en orden ascendente (método burbuja)
    public boolean ordenarLista() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return false;
        }

        boolean swapped;
        do {
            swapped = false;
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                if (actual.getDato() > actual.getSiguiente().getDato()) {
                    // Intercambia los datos de los nodos
                    int temp = actual.getDato();
                    actual.setDato(actual.getSiguiente().getDato());
                    actual.getSiguiente().setDato(temp);
                    swapped = true;
                }
                actual = actual.getSiguiente();
            }
        } while (swapped);
        return true;
    }

    // Devuelve una representación en texto de los elementos de la lista
    public String listarElementos() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        int contador = 1;

        if (actual == null) {
            return "La lista está vacía.";
        }

        while (actual != null) {
            sb.append("Nodo ").append(contador).append(": ").append(actual.getDato()).append("\n");
            actual = actual.getSiguiente();
            contador++;
        }

        return sb.toString();
    }

    // Vacía la lista (elimina todos los nodos)
    public boolean vaciarLista() {
        if (cabeza == null) {
            return false;
        }
        cabeza = null;
        return true;
    }

    // Verifica si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }
}
