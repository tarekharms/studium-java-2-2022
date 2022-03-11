package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahl = new GrosseZahl("573");
        System.out.println("A: " + zahl.toString());

        GrosseZahl zahlInt = new GrosseZahl(574);
        System.out.println("B: " + zahlInt.toString());

        if(zahl.less(zahlInt))
            System.out.println("A Kleiner als B");
    }
}
