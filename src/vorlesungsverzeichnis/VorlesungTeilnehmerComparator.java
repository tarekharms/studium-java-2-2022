package vorlesungsverzeichnis;

import java.util.Comparator;

public class VorlesungTeilnehmerComparator implements Comparator<Vorlesung> {
    @Override
    public int compare(Vorlesung o1, Vorlesung o2) {
        return o1.getTeilnehmerZahl() - o2.getTeilnehmerZahl();
    }
}
