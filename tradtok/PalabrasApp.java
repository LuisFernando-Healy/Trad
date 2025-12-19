import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PalabrasApp extends JFrame {

    private JTextField campoTexto;
    private JButton botonAnalizar, botonLimpiar;
    private JPanel panelPasos;
    private JLabel lblLexico, lblSintactico, lblSemantico, lblTraduccion;
    private AnalisisLexico analizadorLexico;

    // Paleta de Colores UI Moderna
    Color bgFondo = new Color(245, 247, 250);
    Color azulAccent = new Color(52, 152, 219);
    Color verdeExito = new Color(46, 204, 113);
    Color rojoError = new Color(231, 76, 60);
    Color textoOscuro = new Color(44, 62, 80);

    public PalabrasApp() {
        analizadorLexico = new AnalisisLexico();
        configurarVentana();
        initUI();
    }

    private void configurarVentana() {
        setTitle("Traductor : Español ➔ Italiano");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(bgFondo);
        setLayout(new BorderLayout(20, 20));
    }

    private void initUI() {
        // --- HEADER ---
        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(azulAccent);
        header.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("COMPILADOR SEMÁNTICO", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        
        JLabel sub = new JLabel("Estructura Requerida: [Pronombre] + [Verbo] + [Adjetivo]", JLabel.CENTER);
        sub.setForeground(new Color(220, 240, 255));
        
        header.add(titulo);
        header.add(sub);
        add(header, BorderLayout.NORTH);

        // --- CUERPO PRINCIPAL (UX) ---
        JPanel cuerpo = new JPanel(new BorderLayout(10, 20));
        cuerpo.setOpaque(false);
        cuerpo.setBorder(new EmptyBorder(20, 40, 20, 40));

        // Input Box
        JPanel panelInput = new JPanel(new BorderLayout(15, 0));
        panelInput.setOpaque(false);
        
        campoTexto = new JTextField();
        campoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        campoTexto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        botonAnalizar = new JButton("ANALIZAR Y TRADUCIR");
        estilizarBoton(botonAnalizar, azulAccent);
        
        panelInput.add(campoTexto, BorderLayout.CENTER);
        panelInput.add(botonAnalizar, BorderLayout.EAST);
        cuerpo.add(panelInput, BorderLayout.NORTH);

        // Paneles de Estados (Cards)
        panelPasos = new JPanel(new GridLayout(4, 1, 0, 15));
        panelPasos.setOpaque(false);

        lblLexico = crearCardEstado("1. ANÁLISIS LÉXICO", "");
        lblSintactico = crearCardEstado("2. ANÁLISIS SINTÁCTICO", "");
        lblSemantico = crearCardEstado("3. ANÁLISIS SEMÁNTICO", "");
        lblTraduccion = crearCardEstado("RESULTADO DE TRADUCCIÓN", "");
        lblTraduccion.setFont(new Font("Segoe UI", Font.BOLD, 18));

        panelPasos.add(lblLexico);
        panelPasos.add(lblSintactico);
        panelPasos.add(lblSemantico);
        panelPasos.add(lblTraduccion);

        cuerpo.add(panelPasos, BorderLayout.CENTER);
        add(cuerpo, BorderLayout.CENTER);

        // Eventos
        botonAnalizar.addActionListener(e -> procesarFrase());
    }

    private JLabel crearCardEstado(String titulo, String desc) {
        JLabel label = new JLabel("<html><div style='padding:10px;'><b style='font-size:12px; color:#7f8c8d;'>" + titulo + "</b><br><span style='font-size:14px; color:#2c3e50;'>" + desc + "</span></div></html>");
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, new Color(189, 195, 199)));
        return label;
    }

    private void actualizarCard(JLabel card, String titulo, String desc, Color colorSide, boolean exito) {
        String icon = exito ? "✔ " : "✘ ";
        card.setText("<html><div style='padding:10px;'><b style='color:#7f8c8d;'>" + titulo + "</b><br><span style='font-size:15px;'>" + icon + desc + "</span></div></html>");
        card.setBorder(BorderFactory.createMatteBorder(0, 8, 0, 0, colorSide));
        if (exito) card.setBackground(new Color(240, 255, 240));
        else card.setBackground(new Color(255, 240, 240));
    }

    private void estilizarBoton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 25, 10, 25));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void procesarFrase() {
        String frase = campoTexto.getText().trim();
        resetCards();

        if (frase.isEmpty()) return;

        try {
            // 1. LÉXICO
            analizadorLexico.analizar(frase);
            actualizarCard(lblLexico, "1. ANÁLISIS LÉXICO", "Letras válidas detectadas.", verdeExito, true);

            // 2. SINTÁCTICO
            if (Arboles.validarFrase(frase)) {
                actualizarCard(lblSintactico, "2. ANÁLISIS SINTÁCTICO", "Estructura reconocida por los grafos.", verdeExito, true);

                // 3. SEMÁNTICO
                String res = AnalisisSemantico.validarSemantica(frase);
                if (res.startsWith("CORRECTO")) {
                    actualizarCard(lblSemantico, "3. ANÁLISIS SEMÁNTICO", "Concordancia de género y número perfecta.", verdeExito, true);
                    
                    String trad = res.substring(res.indexOf(":") + 1).trim();
                    actualizarCard(lblTraduccion, "TRADUCCIÓN AL ITALIANO", trad.toUpperCase(), azulAccent, true);
                } else {
                    // ERROR SEMÁNTICO (Concordancia o Tipo de palabra)
                    actualizarCard(lblSemantico, "3. ANÁLISIS SEMÁNTICO", res + " | Ej: 'Ella es alta'", rojoError, false);
                }
            } else {
                // ERROR SINTÁCTICO (Orden o palabras no existentes)
                String ordenCorrecto = "Ejemplo: 'Yo soy feliz'";
                actualizarCard(lblSintactico, "2. ANÁLISIS SINTÁCTICO", "Error en el orden o palabra desconocida. | Sugerencia: " + ordenCorrecto, rojoError, false);
            }
        } catch (Exception ex) {
            actualizarCard(lblLexico, "1. ANÁLISIS LÉXICO", "Carácter no permitido. | Ej: Evita usar números o signos.", rojoError, false);
        }
    }

    private void resetCards() {
        actualizarCard(lblLexico, "1. ANÁLISIS LÉXICO", "Pendiente...", new Color(189, 195, 199), true);
        lblLexico.setBackground(Color.WHITE);
        actualizarCard(lblSintactico, "2. ANÁLISIS SINTÁCTICO", "Pendiente...", new Color(189, 195, 199), true);
        lblSintactico.setBackground(Color.WHITE);
        actualizarCard(lblSemantico, "3. ANÁLISIS SEMÁNTICO", "Pendiente...", new Color(189, 195, 199), true);
        lblSemantico.setBackground(Color.WHITE);
        actualizarCard(lblTraduccion, "RESULTADO DE TRADUCCIÓN", "---", new Color(189, 195, 199), true);
        lblTraduccion.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PalabrasApp().setVisible(true));
    }
}