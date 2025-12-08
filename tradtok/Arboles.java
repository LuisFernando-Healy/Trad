public class Arboles {

    // TODO Luper tiene que cambiale el nombre de las variables y a los metodos por que si te das cuenta hay palabras dentro
    //de la funcion Palabras que se repiten asi que corrije eso 
    //apura TODO LUPE
    public static boolean validarFrase(String frase) {

        String fraseLimpia = frase.trim().toLowerCase();
        String[] palabras = fraseLimpia.split(" ");
 
        if (palabras.length != 3) {
            return false;
        }
        boolean palabra1 = palabras(palabras[0]);
        boolean palabra2 = palabras(palabras[1]);
        boolean palabra3 = palabras(palabras[2]);

        return palabra1 && palabra2 && palabra3;
    }

    private static boolean palabras(String palabra) {
        return esYo(palabra) || esTu(palabra) || esEl(palabra) ||
                esNosotros(palabra) || esUstedes(palabra) || esAmable(palabra) ||
                esTenemos(palabra) || esEl(palabra) || esSomos(palabra) ||
                esComo(palabra) || esVivo(palabra) || esVives(palabra) || esVive(palabra) || 
                esVivimos(palabra) || esViven(palabra) || esAmable(palabra) || esHermoso(palabra) 
                ||esPequenos(palabra) || esGrandes(palabra) || esEl(palabra) ||
                esFelices(palabra) || esTristes(palabra) ||
                esRapidos(palabra) || esEl(palabra) ||
                esInteligentes(palabra) || esHermoso(palabra) || esAmable(palabra);
    }

  

  

    private static boolean esYo(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'a') e = 1;
            else if (e == 1 && l == 'm') e = 2;
            else if (e == 2 && l == 'a') e = 3;
            else if (e == 3 && l == 'b') e = 4;
            else if ((e == 1 || e == 4) && l == 'l') e = 5; // Cruce: A-l(to) o Amab-l(e)
            else if (e == 5 && l == 'e') e = 6;
            else if (e == 6 && l == 's') e = 7;
            // Rama de "Alto" (venía en tu código original dentro de esAmable)
            else if (e == 5 && l == 't') e = 8;
            else if (e == 8 && l == 'o') e = 9;
            else if (e == 8 && l == 'a') e = 10;
            else if ((e == 9 || e == 10) && l == 's') e = 11;
            else return false;
        }
        return e == 6 || e == 7 || e == 9 || e == 10 || e == 0 || e == 11;
    }

    // Palabras con C: Como, Comer, Comemos...
    private static boolean esPalabraConC(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'c') e = 1;
            else if (e == 1 && l == 'o') e = 2;
            else if (e == 2 && l == 'm') e = 3;
            else if (e == 3 && l == 'o') e = 4; // Final: Como
            else if (e == 3 && l == 'e') e = 5;
            else if (e == 5 && l == 's') e = 6; // Final: Comes
            else if (e == 5 && l == 'm') e = 7;
            else if (e == 7 && l == 'o') e = 8;
            else if (e == 8 && l == 's') e = 9; // Final: Comemos
            else if (e == 5 && l == 'n') e = 10; // Final: Comen
            else return false;
        }
        return e == 4 || e == 6 || e == 5 || e == 9 || e == 10;
    }

    private static boolean esEl(String p) {

        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'e' || e == 0 && l == 'é')
                e = 1;
            else if (e == 1 && l == 'l')
                e = 2;
            else if (e == 2 && l == 'l') {
                e = 3;
            } else if (e == 3 && l == 'a') {
                e = 4;
            } else if (e == 4 && l == 'o') {
                e = 5;
            } else if (e == 4 || e == 5 || e == 1 || e == 12 && l == 's') {
                e = 6;
            } else if (e == 6 && l == 't') {
                e = 7;
            } else if (e == 7 && l == 'a') {
                e = 8;
            } else if (e == 8 && l == 'n') {
                e = 9;
            } else if (e == 8 && l == 'r') {
                e = 10;
            } else if (e == 8 && l == 's') {
                e = 11;
            } else if (e == 7 || e == 14 && l == 'o') {
                e = 12;
            } else if (e == 12 && l == 'y') {
                e = 13;
            } else if (e == 8 && l == 'm') {
                e = 14;
            } else
                return false;
        }
        return e == 2 || e == 4 || e == 8 || e == 9 || e == 10 || e == 11 || e == 13 || e == 6;
    }







    // Palabras con F: Feliz, Felices
    private static boolean esPalabraConF(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'f') e = 1;
            else if (e == 1 && l == 'e') e = 2;
            else if (e == 2 && l == 'l') e = 3;
            else if (e == 3 && l == 'i') e = 4;
            else if (e == 4 && l == 'c') e = 5;
            else if (e == 5 && l == 'e') e = 6;
            else if (e == 6 && l == 's') e = 7; // Final: Felices
            else if (e == 4 && l == 'z') e = 8; // Final: Feliz
            else return false;
        }
        return e == 7 || e == 8;
    }

    // Palabras con G: Grande, Grandes
    private static boolean esPalabraConG(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'g') e = 1;
            else if (e == 1 && l == 'r') e = 2;
            else if (e == 2 && l == 'a') e = 3;
            else if (e == 3 && l == 'n') e = 4;
            else if (e == 4 && l == 'd') e = 5;
            else if (e == 5 && l == 'e') e = 6; // Final: Grande
            else if (e == 6 && l == 's') e = 7; // Final: Grandes
            else return false;
        }
        return e == 7 || e == 6;
    }

    // Palabras con H: Hermoso, Hermosa...
    private static boolean esPalabraConH(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'h') e = 1;
            else if (e == 1 && l == 'e') e = 2;
            else if (e == 2 && l == 'r') e = 3;
            else if (e == 3 && l == 'm') e = 4;
            else if (e == 4 && l == 'o') e = 5;
            else if (e == 5 && l == 's') e = 6;
            else if (e == 6 && l == 'o') e = 7; // Final: Hermoso
            else if (e == 6 && l == 'a') e = 8; // Final: Hermosa
            else if ((e == 7 || e == 8) && l == 's') e = 9; // Final: Hermosos/as
            else return false;
        }
        return e == 7 || e == 8 || e == 9;
    }

    // Palabras con I: Inteligente...
    private static boolean esPalabraConI(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'i') e = 1;
            else if (e == 1 && l == 'n') e = 2;
            else if (e == 2 && l == 't') e = 3;
            else if (e == 3 && l == 'e') e = 4;
            else if (e == 4 && l == 'l') e = 5;
            else if (e == 5 && l == 'i') e = 6;
            else if (e == 6 && l == 'g') e = 7;
            else if (e == 7 && l == 'e') e = 8;
            else if (e == 8 && l == 'n') e = 9;
            else if (e == 9 && l == 't') e = 10;
            else if (e == 10 && l == 'e') e = 11; // Final: Inteligente
            else if (e == 11 && l == 's') e = 12; // Final: Inteligentes
            else return false;
        }
        return e == 12 || e == 11;
    }

    // Palabras con N: No, Nos, Nosotros...
    private static boolean esPalabraConN(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'n') e = 1;
            else if (e == 1 && l == 'o') e = 2;
            else if (e == 2 && l == 's') e = 3;
            else if (e == 3 && l == 'o') e = 4;
            else if (e == 4 && l == 't') e = 5;
            else if (e == 5 && l == 'r') e = 6;
            else if (e == 6 && l == 'o') e = 7;
            else if (e == 6 && l == 'a') e = 8;
            else if ((e == 8 || e == 7) && l == 's') e = 9;
            else return false;
        }
        return e == 9;
    }

    // Palabras con P: Pequeño, Pequeños...
    private static boolean esPalabraConP(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'p') e = 1;
            else if (e == 1 && l == 'e') e = 2;
            else if (e == 2 && l == 'q') e = 3;
            else if (e == 3 && l == 'u') e = 4;
            else if (e == 4 && l == 'e') e = 5;
            else if (e == 5 && l == 'ñ') e = 6;
            else if (e == 6 && l == 'o') e = 7; // Final: Pequeño
            else if (e == 6 && l == 'a') e = 8; // Final: Pequeña
            else if ((e == 8 || e == 7) && l == 's') e = 9; // Final: Pequeños/as
            else return false;
        }
        return e == 8 || e == 7 || e == 9;
    }

    // Palabras con R: Rapido...
    private static boolean esPalabraConR(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'r') e = 1;
            else if ((e == 1 && l == 'a') || (e == 1 && l == 'á')) e = 2;
            else if (e == 2 && l == 'p') e = 3;
            else if (e == 3 && l == 'i') e = 4;
            else if (e == 4 && l == 'd') e = 5;
            else if (e == 5 && l == 'o') e = 6;
            else if (e == 5 && l == 'a') e = 7;
            else if ((e == 7 || e == 6) && l == 's') e = 8;
            else return false;
        }
        return e == 7 || e == 6 || e == 8;
    }

    // Palabras con S: Soy, Somos, Son
    private static boolean esPalabraConS(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 's') e = 1;
            else if (e == 1 && l == 'o') e = 2;
            else if (e == 2 && l == 'm') e = 3;
            else if (e == 3 && l == 'o') e = 4;
            else if (e == 4 && l == 's') e = 5; // Final: Somos
            else if (e == 2 && l == 'y') e = 6; // Final: Soy
            else if (e == 2 && l == 'n') e = 7; // Final: Son
            else return false;
        }
        return e == 5 || e == 6 || e == 7;
    }

    // Palabras con T: Tu, Tenemos, Triste, Tiene...
    // UNIFICACION: Tu, Tenemos... y Tristes
    private static boolean esPalabraConT(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 't') e = 1;
            
            // --- CRUCE DE CAMINOS EN 'T' ---
            else if (e == 1 && l == 'u') e = 17; // Final: Tu
            else if (e == 1 && l == 'e') e = 2;  // Camino: Tenemos/Tengo
            else if (e == 1 && l == 'i') e = 8;  // Camino: Tiene...
            else if (e == 1 && l == 'r') e = 18; // Camino: Triste (Nuevo)

            // Rama Tenemos / Tengo
            else if (e == 2 && l == 'n') e = 3;
            else if (e == 3 && l == 'e') e = 4;
            else if (e == 4 && l == 'm') e = 5;
            else if (e == 5 && l == 'o') e = 6;
            else if (e == 6 && l == 's') e = 7; // Final: Tenemos
            
            // Rama Tengo
            else if (e == 3 && l == 'g') e = 14;
            else if (e == 14 && l == 'o') e = 15; // Final: Tengo
            else if (e == 15 && l == 'u') e = 16; // Final: Tengou
            
            // Rama Tiene/s/n
            else if (e == 8 && l == 'e') e = 9;
            else if (e == 9 && l == 'n') e = 10;
            else if (e == 10 && l == 'e') e = 11; // Final: Tiene
            else if (e == 11 && l == 's') e = 12; // Final: Tienes
            else if (e == 11 && l == 'n') e = 13; // Final: Tienen

            // Rama Triste (Añadida aquí para completar el grafo T)
            else if (e == 18 && l == 'i') e = 19;
            else if (e == 19 && l == 's') e = 20;
            else if (e == 20 && l == 't') e = 21;
            else if (e == 21 && l == 'e') e = 22; // Final: Triste
            else if (e == 22 && l == 's') e = 23; // Final: Tristes
            
            else return false;
        }
        return e == 7 || e == 11 || e == 12 || e == 15 || e == 13 || e == 16 || e == 17 || e == 22 || e == 23;
    }

    // Palabras con U: Usted, Ustedes
    private static boolean esPalabraConU(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'u') e = 1;
            else if (e == 1 && l == 's') e = 2;
            else if (e == 2 && l == 't') e = 3;
            else if (e == 3 && l == 'e') e = 4;
            else if (e == 4 && l == 'd') e = 5; // Final: Usted
            else if (e == 5 && l == 'e') e = 6;
            else if (e == 6 && l == 's') e = 7; // Final: Ustedes
            else return false;
        }
        return e == 7 || e == 5;
    }

    // AMABLE
    private static boolean esAmable(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'a')
                e = 1;
            else if (e == 1 && l == 'm')
                e = 2;
            else if (e == 2 && l == 'a')
                e = 3;
            else if (e == 3 && l == 'b')
                e = 4;
            else if (e == 1 || e == 4 && l == 'l')
                e = 5;
            else if (e == 5 && l == 'e')
                e = 6;
            else if (e == 6 && l == 's')
                e = 7;
            else if (e == 5 && l == 't') {
                e = 8;
            } else if (e == 8 && l == 'o') {
                e = 9;
            } else if (e == 8 && l == 'a') {
                e = 10;
            } else if (e == 9 || e == 10 && l == 's') {
                e = 11;
            } else
                return false;
        }
        return e == 4 || e == 5 || e == 6 || e == 7 || e == 11;
    }

    // Palabras con Y: Yo
    private static boolean esPalabraConY(String p) {
        int e = 0;
        for (char l : p.toCharArray()) {
            if (e == 0 && l == 'y') e = 1;
            else if (e == 1 && l == 'o') e = 2; // Final: Yo
            else return false;
        }
        return e == 2;
    }
}