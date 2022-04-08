package vorlesungsverzeichnis;

import java.util.Objects;

public class Vorlesung {
    private String studienGruppe;
    private String titel;
    private String dozent;
    private int teilnehmerZahl;

    public String getStudienGruppe() {
        return studienGruppe;
    }

    public String getDozent() {
        return dozent;
    }

    public String getTitel() {
        return titel;
    }

    public int getTeilnehmerZahl() {
        return teilnehmerZahl;
    }

    public Vorlesung(String studienGruppe, String titel, String dozent, int teilnehmerZahl) {
        this.studienGruppe = studienGruppe;
        this.titel = titel;
        this.dozent = dozent;
        this.teilnehmerZahl = teilnehmerZahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vorlesung vorlesung = (Vorlesung) o;
        return teilnehmerZahl == vorlesung.teilnehmerZahl && studienGruppe.equals(vorlesung.studienGruppe) && titel.equals(vorlesung.titel) && dozent.equals(vorlesung.dozent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studienGruppe, titel, dozent, teilnehmerZahl);
    }
}
