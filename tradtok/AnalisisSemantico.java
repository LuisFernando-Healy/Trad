public class AnalisisSemantico {

    public static String validarSemantica(String frase) {
        String[] palabras = frase.trim().toLowerCase().split("\\s+");
        
        // El árbol debe tener exactamente 3 ramas en el orden correcto
        if (palabras.length != 3) {
            return "ERROR: La estructura debe ser [Pronombre] [Verbo] [Adjetivo].";
        }

        // 1. Extraer información validando la POSICIÓN (Jerarquía del Árbol)
        Atributos p1 = extraerInfo(palabras[0]); // Rama 1: Sujeto
        Atributos p2 = extraerInfo(palabras[1]); // Rama 2: Verbo
        Atributos p3 = extraerInfo(palabras[2]); // Rama 3: Adjetivo

        // 2. Validación de ORDEN (Si se altera el orden, el árbol es inválido)
        if (p1 == null || !p1.tipo.equals("Pronombre")) {
            return "ERROR DE ORDEN: La primera palabra debe ser un Pronombre.";
        }
        if (p2 == null || !p2.tipo.equals("Verbo")) {
            return "ERROR DE ORDEN: La segunda palabra debe ser un Verbo.";
        }
        if (p3 == null || !p3.tipo.equals("Adjetivo")) {
            return "ERROR DE ORDEN: La tercera palabra debe ser un Adjetivo.";
        }

        
        // Regla: Sujeto y Verbo deben coincidir en Número
        if (!p1.numero.equals(p2.numero)) {
            return "ERROR: El sujeto es " + p1.numero + " pero el verbo es " + p2.numero + ".";
        }
        // Regla: Sujeto y Adjetivo deben coincidir en Número y Género
        if (!p1.numero.equals(p3.numero)) {
            return "ERROR: El adjetivo debe ser " + (p1.numero.equals("S") ? "Singular" : "Plural") + ".";
        }
        if (!p3.genero.equals("N") && !p1.genero.equals("N") && !p1.genero.equals(p3.genero)) {
            return "ERROR: El género del adjetivo no coincide con el sujeto.";
        }

        return "FRASE SEMÁNTICAMENTE CORRECTA";
    }

    private static Atributos extraerInfo(String p) {
        // --- CATEGORÍA: PRONOMBRES ---
        if (p.matches("yo|él|ella|usted|nosotros|nosotras|ellos|ellas|ustedes")) {
            String num = p.matches("yo|él|ella|usted") ? "S" : "P";
            String gen = p.matches("él|ellos") ? "M" : (p.matches("ella|ellas") ? "F" : "N");
            return new Atributos(num, gen, "Pronombre");
        }
        
        // --- CATEGORÍA: VERBOS ---
        if (p.matches("soy|es|está|estamos|son|están|eres|somos|vive|viven|vivimos|tengo|tiene|tienen|tenemos")) {
            String num = p.matches("soy|es|está|eres|vive|tengo|tiene") ? "S" : "P";
            return new Atributos(num, "N", "Verbo");
        }

        // --- CATEGORÍA: ADJETIVOS (Incluyendo Hermoso, Inteligente, etc.) ---
        // Inteligente, Grande, Triste, Feliz son Neutros ("N")
        if (p.matches("inteligente|inteligentes|grande|grandes|triste|tristes|feliz|felices|amable|amables")) {
            return new Atributos(p.endsWith("s") || p.equals("felices") ? "P" : "S", "N", "Adjetivo");
        }
        // Hermoso, Alto, Pequeño tienen Género (M/F)
        if (p.matches("hermoso|hermosa|hermosos|hermosas|alto|alta|altos|altas|pequeño|pequeña|pequeños|pequeñas|rapido|rapida|rapidos|rapidas")) {
            String num = (p.endsWith("os") || p.endsWith("as")) ? "P" : "S";
            String gen = (p.endsWith("o") || p.endsWith("os")) ? "M" : "F";
            return new Atributos(num, gen, "Adjetivo");
        }
        
        return null;
    }
}