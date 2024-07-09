package documents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Interface abstraite pour la fabrique de documents
public interface DocumentFactory {
    String getInsertQuery();
    void setParameters(PreparedStatement pstmt, String title, String author, String genre, int year, String pdfFilePath, int clientId) throws SQLException;
}
