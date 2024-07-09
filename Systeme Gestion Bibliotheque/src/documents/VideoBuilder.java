package documents;
import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VideoBuilder implements DocumentBuilder {
    private Video video;

    public VideoBuilder() {
        this.video = new Video();
    }

    @Override
    public void buildTitre(String titre) {
        video.setTitre(titre);
    }

    @Override
    public void buildAuteur(String auteur) {
        video.setAuteur(auteur);
    }

    @Override
    public void buildAnnee(int annee) {
        video.setAnnee(annee);
    }

    @Override
    public void buildGenre(String genre) {
        video.setGenre(genre);
    }

    @Override
    public Document getResult() {
        return video;
    }

    public void ajouterDocument(int clientId) {
        if (video.getTitre() == null || video.getAuteur() == null || video.getGenre() == null) {
            System.out.println("All fields must be filled");
            return;
        }

        String sql = "INSERT INTO video (titre, auteur, annee, genre, client_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, video.getTitre());
            pstmt.setString(2, video.getAuteur());
            pstmt.setInt(3, video.getAnnee());
            pstmt.setString(4, video.getGenre());
            pstmt.setInt(5, clientId);
            pstmt.executeUpdate();
            System.out.println("Video ajoutée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
