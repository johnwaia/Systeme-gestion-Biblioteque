package documents;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivreBuilder implements DocumentBuilder {
    private Livre livre;

    public LivreBuilder() {
        this.livre = new Livre();
    }

    @Override
    public void buildTitre(String titre) {
        livre.setTitre(titre);
    }

    @Override
    public void buildAuteur(String auteur) {
        livre.setAuteur(auteur);
    }

    @Override
    public void buildAnnee(int annee) {
        livre.setAnnee(annee);
    }

    @Override
    public void buildGenre(String genre) {
        livre.setGenre(genre);
    }

    @Override
    public Document getResult() {
        return livre;
    }

    public void ajouterLivre(int clientId) {
        if (livre.getTitre() == null || livre.getAuteur() == null || livre.getGenre() == null) {
            System.out.println("All fields must be filled");
            return;
        }

        String sql = "INSERT INTO livre (titre, auteur, annee, genre, client_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getPublicConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livre.getTitre());
            pstmt.setString(2, livre.getAuteur());
            pstmt.setInt(3, livre.getAnnee());
            pstmt.setString(4, livre.getGenre());
            pstmt.setInt(5, clientId);
            pstmt.executeUpdate();
            System.out.println("Livre ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
