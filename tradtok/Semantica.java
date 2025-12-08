public class Semantica {

    public static void main(String[] args) {

    }

    public static boolean AnalisarSemantica(String frase) {

        String fraseLimpia = frase.toLowerCase().trim();
        String[] palabras = fraseLimpia.split(" ");

        if (palabras.length < 3) {
            return false;
        }

        String sujeto = palabras[0];
        String verbo = palabras[1];
        String adjetivo = palabras[2];

        if (Arboles.getcategoria(sujeto) != Arboles.categoria.PRONOMBRE)
            return false;
        if (Arboles.getcategoria(verbo) != Arboles.categoria.VERBO)
            return false;
        if (Arboles.getcategoria(adjetivo) != Arboles.categoria.ADJETIVO)
            return false;

        return validacionConcordancia(sujeto, verbo, adjetivo);

    }

public static boolean validacionConcordancia(String s, String v, String a ){

    if(s.equals("yo")){ 
        boolean verboValido =  v.equals("soy") 
        || v.equals("estoy") ;
        boolean adjValido = esSingularM(a);
        return verboValido && adjValido;
    }

    if(s.equals("tu")){
        boolean verboValido = v.equals("eres") ||
        v.equals("estas");
        boolean adjValido = esPluralF(a);
        return verboValido && adjValido;
}

    if(s.equals("el")){
        boolean verboValido = v.equals("es") ||
         v.equals("esta");
        boolean adjValido = esSingularM(a);
        return verboValido && adjValido;
      
    }

    if(s.equals("ella")){
        boolean verboValido = v.equals("es") ||
        v.equals("esta");
        boolean adjValido = esSingularF(a);
        return verboValido && adjValido;
    }

    if(s.equals("ellos")){
        boolean verboValido = v.equals("estan") ||
        v.equals("son");
        boolean adjValido = esPluralM(a);
        return verboValido && adjValido;

    }

    if(s.equals("ellas")){

        boolean verboValido = v.equals("son") ||
        v.equals("estan");
        boolean adjValido = esPluralF(a);
        return verboValido && adjValido;
    }
    if(s.equals("nosotros")){
        boolean verboValido = v.equals("somos") ||
        v.equals("estamos");
        boolean adjValido = esPluralM(a);
        return verboValido && adjValido;
    }

    if(s.equals("nosotras")){
        boolean verboValido = v.equals("somos") ||
        v.equals("estamos");
        boolean adjValido = esPluralF(a);
        return verboValido && adjValido;
    }

    if(s.equals("ustedes")){
        boolean verboValido = v.equals("son") ||
        v.equals("estan");
        boolean adjValido = esPluralM(a);
        return verboValido && adjValido;
    }



        return false;
}



public static  boolean esSingularF (String palabra){
    return !palabra.endsWith("s") && palabra.endsWith("a");
}

public static  boolean esSingularM (String palabra){
    return !palabra.endsWith("s") && palabra.endsWith("o");
}

public static boolean esPluralM(String palabra){
return palabra.endsWith("os");

}

public static boolean esPluralF(String palabra){
return palabra.endsWith("as");

}

}
