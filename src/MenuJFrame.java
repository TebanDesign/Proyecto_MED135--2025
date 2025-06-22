
// Librerías para componentes gráficos y manejo de eventos
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MenuJFrame extends JFrame implements ActionListener {
    private ListaEnlazada lista; // Lista enlazada que se manipula
    private JTextArea textArea; // Área de texto para mostrar la lista
    private JLabel estadoLabel; // Label para mostrar el estado de la lista

    // Constructor: configura la ventana y los componentes
    public MenuJFrame() {
        lista = new ListaEnlazada();
        setTitle("Lista Enlazada - Proyecto Final MEDGT01");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con botones
        JPanel panelBotones = new JPanel(new GridLayout(0, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(24, 24, 24), 2),
                "Menú - Lista Enlazada", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16), new Color(24, 24, 24)));
        panelBotones.setBackground(new Color(245, 245, 245));

        // Opciones de menú
        String[] opciones = {
                "Agregar al inicio",
                "Agregar al final",
                "Suprimir nodo",
                "Ordenar lista",
                "Listar elementos",
                "Vaciar lista",
                "Salir"
        };

        // Crear botones para cada opción
        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            boton.setFocusPainted(false);
            boton.setBackground(new Color(24, 24, 24));
            boton.setForeground(Color.WHITE);
            boton.setPreferredSize(new Dimension(140, 40));
            boton.addActionListener(this);
            panelBotones.add(boton);
        }

        // Panel para el área de texto y el estado
        JPanel panelTexto = new JPanel(new BorderLayout(5, 5));
        panelTexto.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(24, 24, 24), 2),
                "Estado de la Lista", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 16), new Color(24, 24, 24)));
        panelTexto.setBackground(Color.WHITE);

        estadoLabel = new JLabel("La lista está vacía. Ingresa números en los nodos para ver la lista aquí.");
        estadoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        estadoLabel.setForeground(Color.GRAY);
        estadoLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setBackground(new Color(244, 244, 244));
        textArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(textArea);

        panelTexto.add(estadoLabel, BorderLayout.NORTH);
        panelTexto.add(scrollPane, BorderLayout.CENTER);

        // Layout principal de la ventana
        setLayout(new BorderLayout(15, 15));
        add(panelBotones, BorderLayout.NORTH);
        add(panelTexto, BorderLayout.CENTER);

        actualizarVistaLista(); // Mostrar estado inicial
    }

    // Manejo de eventos de botones
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Agregar al inicio":
                int datoInicio = pedirNumero("Ingrese un número para agregar al inicio:");
                if (datoInicio == -1)
                    break;
                lista.agregarAlInicio(datoInicio);
                mostrarMensaje("Nodo " + datoInicio + " agregado al inicio.");
                break;

            case "Agregar al final":
                int datoFinal = pedirNumero("Ingrese un número para agregar al final:");
                if (datoFinal == -1)
                    break;
                lista.agregarAlFinal(datoFinal);
                mostrarMensaje("Nodo " + datoFinal + " agregado al final.");
                break;

            case "Suprimir nodo":
                int datoEliminar = pedirNumero("Ingrese el número a suprimir:");
                if (datoEliminar == -1)
                    break;
                boolean eliminado = lista.suprimirNodo(datoEliminar);
                if (eliminado) {
                    mostrarMensaje("Nodo " + datoEliminar + " eliminado.");
                } else {
                    mostrarMensaje("Nodo " + datoEliminar + " no encontrado.");
                }
                break;

            case "Ordenar lista":
                lista.ordenarLista();
                mostrarMensaje("Lista ordenada.");
                break;

            case "Listar elementos":
                mostrarMensaje(lista.listarElementos());
                break;

            case "Vaciar lista":
                lista.vaciarLista();
                mostrarMensaje("La lista ha sido vaciada.");
                break;

            case "Salir":
                int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Confirmación",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
        actualizarVistaLista(); // Actualiza la interfaz
    }

    // Pide un número al usuario (ventana de diálogo)
    private int pedirNumero(String mensaje) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, mensaje);
            if (input == null)
                return -1; // Cancelado
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
            }
        }
    }

    // Muestra mensaje en el área de texto
    private void mostrarMensaje(String mensaje) {
        textArea.append(mensaje + "\n");
    }

    // Actualiza la visualización del estado de la lista
    private void actualizarVistaLista() {
        String listaStr = lista.listarElementos();
        if (listaStr.equals("La lista está vacía.")) {
            estadoLabel.setText("La lista está vacía. Ingresa números en los nodos para ver la lista aquí.");
            textArea.setText("");
        } else {
            estadoLabel.setText("Lista actual:");
            textArea.setText(listaStr);
        }
    }

    // Método main: lanza la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuJFrame frame = new MenuJFrame();
            frame.setVisible(true);
        });
    }
}
