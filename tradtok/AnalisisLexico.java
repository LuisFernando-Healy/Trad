import java.util.ArrayList;

public class AnalisisLexico {
    // Se agregaron á, é, í, ó, ú
    private final String abecedario = "abcdefghijlmnñopqrstuvyz áéíóú"; 

    public ArrayList<Token> analizar(String palabra) throws Exception {
        ArrayList<Token> tokens = new ArrayList<>();
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            char validar = Character.toLowerCase(letra);
            if(!abecedario.contains(Character.toString(validar))){
                throw new Exception("Carácter inválido detectado: '" + letra + "'");
            }
            tokens.add(new Token(validar));
        }
        return tokens;
    }
}
