package grosseZahl;

public class Main {
    public static void main(String[] args) {
        GrosseZahl zahlA = new GrosseZahl("66");
        System.out.println("A: " + zahlA.toString());

        GrosseZahl zahlB = new GrosseZahl(955);
        System.out.println("B: " + zahlB.toString());

        if(zahlA.less(zahlB))
            System.out.println("A Kleiner als B");
        else
            System.out.println("A ist nicht kleiner als B");

        GrosseZahl zahlC = zahlA.add(zahlB);

        System.out.println("A + B = " + zahlC.toString());

    }
}
