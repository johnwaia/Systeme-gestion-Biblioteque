package documents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Fabrique concrète pour les Vidéos
public class VideoFactory implements DocumentFactory {
    @Override
    public String getInsertQuery() {
        return "INSERT INTO video (titre, auteur, genre, annee, client_id) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void setParameters(PreparedStatement pstmt, String title, String author, String genre, int year, String pdfFilePath, int clientId) throws SQLException {
        pstmt.setString(1, title);
        pstmt.setString(2, author);
        pstmt.setString(3, genre);
        pstmt.setInt(4, year);
        pstmt.setInt(5, clientId);
    }
}
