package vorlesungsverzeichnis;

import java.util.Comparator;

public class VorlesungTitelComparator implements Comparator<Vorlesung> {
    @Override
    public int compare(Vorlesung o1, Vorlesung o2) {
        return o1.getTitel().compareToIgnoreCase(o2.getTitel());
    }
}
