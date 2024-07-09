package documents;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RevueBuilder implements DocumentBuilder {
    private Revue revue;

    public RevueBuilder() {
        this.revue = new Revue();
    }

    @Override
    public void buildTitre(String titre) {
        revue.setTitre(titre);
    }

    @Override
    public void buildAuteur(String auteur) {
        revue.setAuteur(auteur);
    }

    @Override
    public void buildAnnee(int annee) {
        revue.setAnnee(annee);
    }

    @Override
    public void buildGenre(String genre) {
        revue.setGenre(genre);
    }

    @Override
    public Document getResult() {
        return revue;
    }

    public boolean validateFields() {
        return revue.getTitre() != null && !revue.getTitre().isEmpty() &&
               revue.getAuteur() != null && !revue.getAuteur().isEmpty() &&
               revue.getGenre() != null && !revue.getGenre().isEmpty();
    }

    public void ajouterDocument(int clientId) {
        if (revue.getTitre() == null || revue.getTitre().isEmpty() ||
            revue.getAuteur() == null || revue.getAuteur().isEmpty() ||
            revue.getGenre() == null || revue.getGenre().isEmpty()) {
            System.out.println("All fields must be filled");
            return;
        }
    
        // Insérer dans la base de données
        String sql = "INSERT INTO revue (titre, auteur, annee, genre, client_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getPublicConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, revue.getTitre());
            pstmt.setString(2, revue.getAuteur());
            pstmt.setInt(3, revue.getAnnee());
            pstmt.setString(4, revue.getGenre());
            pstmt.setInt(5, clientId);
            pstmt.executeUpdate();
            System.out.println("Revue ajoutée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
