package documents;

public class Livre extends Document {
    private String titre;
    private String auteur;
    private int annee;
    private String genre;

    @Override
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @Override
    public void setGenre(String genre) {
        this.genre = genre; // Implémentation de setGenre
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getAuteur() {
        return auteur;
    }

    @Override
    public int getAnnee() {
        return annee;
    }

    @Override
    public String getGenre() {
        return genre;
    }
}
