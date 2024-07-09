package documents;

public interface DocumentBuilder {
    void buildTitre(String titre);
    void buildAuteur(String auteur);
    void buildAnnee(int annee);
    void buildGenre(String genre);
    Document getResult();
}
