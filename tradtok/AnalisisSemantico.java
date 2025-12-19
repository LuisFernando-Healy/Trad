import java.util.HashMap;

public class AnalisisSemantico {

    public static String validarSemantica(String frase) {
        String[] palabras = frase.trim().toLowerCase().split("\\s+");
        
        if (palabras.length != 3) {
            return "ERROR: La estructura debe ser [Pronombre] [Verbo] [Adjetivo/Gerundio].";
        }

        Atributos p1 = extraerInfo(palabras[0]); 
        Atributos p2 = extraerInfo(palabras[1]); 
        Atributos p3 = extraerInfo(palabras[2]); 

        // 1. VALIDACIÓN DE ESTRUCTURA
        if (p1 == null || !p1.tipo.equals("Pronombre")) return "ERROR: Falta Pronombre inicial.";
        if (p2 == null || !p2.tipo.equals("Verbo")) return "ERROR: Falta Verbo en la posición 2.";
        if (p3 == null) return "ERROR: Tercera palabra no reconocida.";

        // 2. VALIDACIÓN DE CONCORDANCIA
        if (!p1.numero.equals(p2.numero)) {
            return "ERROR: El sujeto es " + p1.numero + " pero el verbo es " + p2.numero + ".";
        }
        if (p3.tipo.equals("Adjetivo")) {
            if (!p1.numero.equals(p3.numero)) return "ERROR: El adjetivo no coincide en número.";
            if (!p3.genero.equals("N") && !p1.genero.equals("N") && !p1.genero.equals(p3.genero)) {
                return "ERROR: El adjetivo no coincide en género.";
            }
        }

        // 3. TRADUCCIÓN (Si todo lo anterior es correcto)
        String t1 = traducir(palabras[0]);
        String t2 = traducir(palabras[1]);
        String t3 = traducir(palabras[2]);

        return "CORRECTO. Traducción: " + t1 + " " + t2 + " " + t3;
    }

    private static String traducir(String p) {
        HashMap<String, String> dic = new HashMap<>();
        // Pronombres
        dic.put("yo", "io"); dic.put("él", "lui"); dic.put("el", "lui"); dic.put("ella", "lei");
        dic.put("nosotros", "noi"); dic.put("nosotras", "noi"); dic.put("ellos", "loro"); 
        dic.put("ellas", "loro"); dic.put("usted", "lei"); dic.put("ustedes", "voi"); dic.put("tu", "tu");
        // Verbos
        dic.put("soy", "sono"); dic.put("es", "è"); dic.put("está", "sta"); dic.put("estan", "stanno");
        dic.put("están", "stanno"); dic.put("somos", "siamo"); dic.put("estamos", "stiamo"); 
        dic.put("son", "sono"); dic.put("eres", "sei"); dic.put("como", "mangio");
        dic.put("comen", "mangiano"); dic.put("comiendo", "mangiando");
        // Adjetivos
        dic.put("hermoso", "bellissimo"); dic.put("hermosa", "bellissima");
        dic.put("hermosos", "bellissimi"); dic.put("hermosas", "bellissime");
        dic.put("inteligente", "intelligente"); dic.put("felices", "felici");
        dic.put("feliz", "felice"); dic.put("alto", "alto"); dic.put("alta", "alta");

        return dic.getOrDefault(p, p);
    }

    private static Atributos extraerInfo(String p) {
        if (p.matches("yo|él|el|ella|usted|nosotros|nosotras|ellos|ellas|ustedes|tu")) {
            String num = p.matches("yo|él|el|ella|usted|tu") ? "S" : "P";
            String gen = p.matches("él|el|ellos|nosotros") ? "M" : (p.matches("ella|ellas|nosotras") ? "F" : "N");
            return new Atributos(num, gen, "Pronombre");
        }
        if (p.matches("soy|es|está|estan|estamos|son|están|eres|somos|como|comen")) {
            String num = p.matches("soy|es|está|eres|como") ? "S" : "P";
            return new Atributos(num, "N", "Verbo");
        }
        if (p.equals("comiendo")) return new Atributos("N", "N", "Verbo");
        if (p.matches("inteligente|inteligentes|feliz|felices")) return new Atributos(p.endsWith("s") || p.equals("felices") ? "P" : "S", "N", "Adjetivo");
        if (p.matches("hermoso|hermosa|hermosos|hermosas|alto|alta|altos|altas")) {
            String num = (p.endsWith("os") || p.endsWith("as")) ? "P" : "S";
            String gen = (p.endsWith("o") || p.endsWith("os")) ? "M" : "F";
            return new Atributos(num, gen, "Adjetivo");
        }
        return null;
    }
}