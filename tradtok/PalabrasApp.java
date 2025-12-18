import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PalabrasApp extends JFrame {

    private JTextField campoTexto;
    private JButton botonAnalizar;
    private JButton botonLimpiar;
    private JTextArea areaLista;
    
    private AnalisisLexico analizadorLexico;

    public PalabrasApp() {
        // Inicializamos el analizador léxico
        analizadorLexico = new AnalisisLexico();

        setTitle("Analizador de Lenguaje - Árboles Semánticos");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para entrada de datos
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(240, 240, 240));
        campoTexto = new JTextField(20);
        botonAnalizar = new JButton("Analizar Frase");
        botonLimpiar = new JButton("Limpiar");

        panelSuperior.add(new JLabel("Ingrese una frase (3 palabras):"));
        panelSuperior.add(campoTexto);
        panelSuperior.add(botonAnalizar);
        panelSuperior.add(botonLimpiar);

        // Área de resultados con Scroll
        areaLista = new JTextArea();
        areaLista.setEditable(false);
        areaLista.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaLista.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(areaLista);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // --- EVENTO BOTÓN LIMPIAR ---
        botonLimpiar.addActionListener(e -> {
            campoTexto.setText("");
            areaLista.setText("");
        });

        // --- EVENTO BOTÓN ANALIZAR ---
        botonAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fraseCompleta = campoTexto.getText().trim();

                if (fraseCompleta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo no debe estar vacío");
                    return;
                }

                try {
                    areaLista.append("==========================================\n");
                    areaLista.append("ANALIZANDO: \"" + fraseCompleta + "\"\n");
                    areaLista.append("==========================================\n");

                    // 1. ANÁLISIS LÉXICO (Caracteres válidos)
                    ArrayList<Token> tokens = analizadorLexico.analizar(fraseCompleta);
                    
                    // Prepara la salida de tokens visualmente
                    StringBuilder tokenSalida = new StringBuilder();
                    for (Token t : tokens) {
                        tokenSalida.append("[").append(t.getCaracter()).append("] ");
                    }
                    areaLista.append("1. LÉXICO: Caracteres válidos.\n   Tokens: " + tokenSalida.toString() + "\n");

                    // 2. ANÁLISIS SINTÁCTICO (Grafos AFD en Arboles.java)
                    // Verifica que las palabras existan y sean 3.
                    boolean esSintaxisValida = Arboles.validarFrase(fraseCompleta);
                    
                    if (esSintaxisValida) {
                        areaLista.append("2. SINTÁCTICO: Estructura de grafos correcta.\n");
                        
                        // 3. ANÁLISIS SEMÁNTICO (Árbol de Concordancia)
                        // Aquí es donde se aplican las reglas de género y número.
                        String resultadoSemantico = AnalisisSemantico.validarSemantica(fraseCompleta);
                        areaLista.append("3. SEMÁNTICO (ÁRBOL): " + resultadoSemantico + "\n");
                        
                    } else {
                        areaLista.append("2. SINTÁCTICO: ERROR. Frase no reconocida por los grafos.\n");
                        areaLista.append("3. SEMÁNTICO: No se puede analizar sin sintaxis válida.\n");
                    }

                    areaLista.append("\n");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(PalabrasApp.this, "Error Léxico: " + ex.getMessage());
                    areaLista.append("--- ERROR EN EL PROCESO ---\n");
                    areaLista.append("MOTIVO: " + ex.getMessage() + "\n\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        // Ejecución de la interfaz
        SwingUtilities.invokeLater(() -> {
            new PalabrasApp().setVisible(true);
        });
    }
}