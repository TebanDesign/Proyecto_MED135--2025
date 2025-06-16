/**
 *
 * @author river
 */
public class ListaEnlazada {
    private Nodo cabeza;

    public ListaEnlazada() {
        cabeza = null;
    }

    // Agrega un nodo al inicio
    public void agregarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    // Agrega un nodo al final
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

    // Lista todos los elementos
    public String listarElementos() {
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;

        if (actual == null) {
            return "La lista está vacía.";
        }

        while (actual != null) {
            sb.append(actual.getDato()).append(" -> ");
            actual = actual.getSiguiente();
        }

        sb.append("null");
        return sb.toString();
    }

    // Vacía la lista
    public void vaciarLista() {
        cabeza = null;
    }
}