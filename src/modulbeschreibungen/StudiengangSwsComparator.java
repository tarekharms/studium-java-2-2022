package modulbeschreibungen;

import java.util.Comparator;

public class StudiengangSwsComparator implements Comparator<Studiengang> {
    @Override
    public int compare(Studiengang o1, Studiengang o2) {
        int swsDiff = o1.getSws() - o2.getSws();

        if(swsDiff == 0)
        {
            return o1.getBezeichnung().compareTo(o2.getBezeichnung());
        }

        return swsDiff;
    }
}

