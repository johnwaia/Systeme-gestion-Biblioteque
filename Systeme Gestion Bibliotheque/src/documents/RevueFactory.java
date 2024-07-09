package documents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Fabrique concrète pour les Revues
public class RevueFactory implements DocumentFactory {
    @Override
    public String getInsertQuery() {
        return "INSERT INTO revue (titre, auteur, genre, annee, fichier_pdf, client_id) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void setParameters(PreparedStatement pstmt, String title, String author, String genre, int year, String pdfFilePath, int clientId) throws SQLException {
        pstmt.setString(1, title);
        pstmt.setString(2, author);
        pstmt.setString(3, genre);
        pstmt.setInt(4, year);
        if (pdfFilePath != null) {
            pstmt.setString(5, pdfFilePath);
        } else {
            pstmt.setNull(5, java.sql.Types.VARCHAR);
        }
        pstmt.setInt(6, clientId);
    }
}
