
public class Token {

    private final char caracter;

    public Token(char caracter) {
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }

    @Override
    public String toString() {
        return " " + caracter + " ";
    }

}