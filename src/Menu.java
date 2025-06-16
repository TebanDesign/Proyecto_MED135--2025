/**
 *
 * @author river
 */
import javax.swing.JOptionPane;

public class Menu {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();
        int opcion = 0;

        do {
            String menu = """
                --- MENÚ DE LISTA ENLAZADA ---
                1. Agregar nodo al inicio
                2. Agregar nodo al final
                3. Listar elementos
                4. Vaciar lista
                5. Salir
                Ingrese una opción:
                """;

            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida.");
                continue;
            }

            int dato;

            switch (opcion) {
                case 1:
                    dato = pedirNumero("Ingrese un número para agregar al inicio:");
                    lista.agregarAlInicio(dato);
                    break;
                case 2:
                    dato = pedirNumero("Ingrese un número para agregar al final:");
                    lista.agregarAlFinal(dato);
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, lista.listarElementos());
                    break;
                case 4:
                    lista.vaciarLista();
                    JOptionPane.showMessageDialog(null, "La lista ha sido vaciada.");
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Programa finalizado.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }

        } while (opcion != 5);
    }

    private static int pedirNumero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }
        }
    }
}