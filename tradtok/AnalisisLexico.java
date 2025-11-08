import java.util.ArrayList;

public class AnalisisLexico {
    
private final String abecedario = "abcdefghijlmnñopqrstuvyz ";

    public ArrayList<Token> analizar(String palabra) throws Exception{

       ArrayList<Token> tokens = new ArrayList<>();

        for (int i = 0; i < palabra.length(); i++) {
             char letra = palabra.charAt(i);
            System.out.println("act");
            // 2 Verificamos si el carácter está en nuestro abecedario

            char validar = Character.toLowerCase(letra);
            if(!abecedario.contains(Character.toString(validar))){
                throw new Exception("caracter invalido");
           
            }

            // agregar tokens ..
            tokens.add(new Token(validar));
        }
        
        return tokens;

    }

  
            

}
