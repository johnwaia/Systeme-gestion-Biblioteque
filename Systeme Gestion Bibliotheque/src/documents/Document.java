package documents;

public abstract class Document {
    public abstract void setTitre(String titre);
    public abstract void setAuteur(String auteur);
    public abstract void setAnnee(int annee);
    public abstract void setGenre(String genre);
    public abstract String getTitre();
    public abstract String getAuteur();
    public abstract int getAnnee();
    public abstract String getGenre();
    
}
