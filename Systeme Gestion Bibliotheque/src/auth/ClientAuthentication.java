package auth;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAuthentication {
    public static Client verifierConnexion(String nom, String motDePasse) {
        String sql = "SELECT id, mot_de_passe, is_admin FROM client WHERE nom = ?";
    
        try (Connection conn = DatabaseConnection.getPublicConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nom);
    
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedEncryptedPassword = rs.getString("mot_de_passe");
                String decryptedPassword = PasswordUtils.decrypt(storedEncryptedPassword);
                boolean isAdmin = rs.getBoolean("is_admin");

                if (decryptedPassword != null && decryptedPassword.equals(motDePasse)) {
                    int id = rs.getInt("id");
                    return new Client(id, nom, motDePasse, isAdmin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Improve error handling as needed
        }
        return null;
    }
}
