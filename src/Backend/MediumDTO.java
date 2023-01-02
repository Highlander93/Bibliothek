package Backend;


public class MediumDTO {
    String table = "medium";
    String anzahl = "Anzahl";
    String titel = "Titel";
    String autor = "Autor";
    String digital = "Digital";
    String erscheinungsdatum = "Erscheinungsdatum";

    public void setAnzahl(String anzahl) {
        this.anzahl = anzahl;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public void setErscheinungsdatum(String erscheinungsdatum) {
        this.erscheinungsdatum = erscheinungsdatum;
    }

    public String getAnzahl() {
        return anzahl;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public String getDigital() {
        return digital;
    }

    public String getErscheinungsdatum() {
        return erscheinungsdatum;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
