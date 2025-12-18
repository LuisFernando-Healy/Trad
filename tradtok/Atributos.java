public class Atributos {
    String numero; // "S" (Singular), "P" (Plural)
    String genero; // "M" (Masc), "F" (Fem), "N" (Neutro/Ambos)
    String tipo;   // "Pronombre", "Verbo", "Adjetivo"

    public Atributos(String n, String g, String t) {
        this.numero = n; this.genero = g; this.tipo = t;
    }
}
