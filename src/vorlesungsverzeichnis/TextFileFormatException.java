package vorlesungsverzeichnis;

public class TextFileFormatException extends Exception {
    String s;

    public TextFileFormatException(String s)
    {
        super(s);
        this.s = s;
    }
}
