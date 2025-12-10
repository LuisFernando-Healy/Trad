
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PalabrasApp extends JFrame {

    private JTextField campoTexto;
    private JButton botonAnalizar;
    private JButton botonLimpiar;
    private JTextArea areaLista;

    private AnalisisLexico Analizador;

    public PalabrasApp() {

        Analizador = new AnalisisLexico();

        setTitle("Traductor");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // panel y botones
        JPanel panelSuperior = new JPanel();
        campoTexto = new JTextField(20);
        botonAnalizar = new JButton("Analizar Frase");
        botonLimpiar = new JButton("Limpiar");

        panelSuperior.add(new JLabel("Ingrese una frase:"));
        panelSuperior.add(campoTexto);
        panelSuperior.add(botonAnalizar);
        panelSuperior.add(botonLimpiar);

        areaLista = new JTextArea();
        areaLista.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaLista);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // boton limpiar
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoTexto.setText("");
                areaLista.setText("");
            }
        });

        // boton analizar
        botonAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // 1. Obtenemos la frase del campo de texto
                String fraseCompleta = campoTexto.getText();

                if (fraseCompleta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo no debe estar vacio");
                    return;
                }

                try {

                    // analisis lexico
                    ArrayList<Token> tokens = Analizador.analizar(fraseCompleta.trim());

                    // arboles - analisis sintactico
                    boolean esSintaxisValida = Arboles.validarFrase(fraseCompleta.trim().toLowerCase());

                    // analisis semantico
                    boolean SemanticaValdia =false;

                    if (esSintaxisValida) {
                        SemanticaValdia = Semantica.AnalisarSemantica(fraseCompleta.trim().toLowerCase());
                    }

                  
    
   

                    mostrarResultados(fraseCompleta.trim(), tokens, esSintaxisValida, SemanticaValdia);

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(PalabrasApp.this, "Error Léxico: " + ex.getMessage());

                    areaLista.append("--- ANÁLISIS FALLIDO ---\n");
                    areaLista.append("FRASE: " + fraseCompleta + "\n");
                    areaLista.append("ERROR LÉXICO: " + ex.getMessage() + "\n\n");
                }
            }
        });
    }

    private void mostrarResultados(String frase, ArrayList<Token> tokens, boolean esSintaxisValida, boolean esSemanticaValida) {

    
        StringBuilder tokenSalida = new StringBuilder();
        for (Token token : tokens) {
            tokenSalida.append(token.toString()).append("");
        }

        areaLista.append("1. ANÁLISIS LÉXICO  " + tokenSalida.toString() + "\n");

        // Resultado del Análisis Sintáctico
        if (esSintaxisValida) {
            areaLista.append("2.Analisis Sintactico FRASE VÁLIDA\n");
            areaLista.append(" ");
        } else {
            areaLista.append("2.Analisis Sintactico  FRASE NO VÁLIDA\n\n");
            areaLista.append(" ");
        }
        

         

        if(esSintaxisValida){
            if(esSemanticaValida){
                areaLista.append("3.Analisis semantico: correcto \n\n");
            } else 
                {
                    areaLista.append("3.Analisis semantico: frase incoherente \n\n");
            }  
            
            
        }
        
        

        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PalabrasApp().setVisible(true);
        });
    }

}