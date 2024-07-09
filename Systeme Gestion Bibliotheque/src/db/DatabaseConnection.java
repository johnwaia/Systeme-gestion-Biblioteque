package db;

import auth.Client;
import auth.PasswordUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bibliotheque";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getPublicConnection() throws SQLException {
        return getConnection();
    }

    public static void inscrire(Client client, boolean isAdmin) {
        String sql = "INSERT INTO client (nom, mot_de_passe, is_admin) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, PasswordUtils.encrypt(client.getMotDePasse()));
            pstmt.setBoolean(3, isAdmin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'inscription du client.");
        }
    }

    public static String searchLivre(String titre, String auteur) {
        if (titre.isEmpty() || auteur.isEmpty()) {
            return "Titre et auteur doivent être spécifiés.";
        }

        String result = "";
        String sql = "SELECT titre, auteur, annee, genre, client_id FROM livre WHERE titre = ? AND auteur = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            pstmt.setString(2, auteur);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int annee = rs.getInt("annee");
                String genre = rs.getString("genre");
                int idClient = rs.getInt("client_id");

                result = "Titre: " + titre + "\nAuteur: " + auteur + "\nAnnée: " + annee + "\nGenre: " + genre;
                if (idClient != 0) {
                    result += "\nCe livre a été ajouté par un client avec l'ID: " + idClient;
                } else {
                    result += "\nCe livre n'a pas encore été attribué à un client.";
                }
            } else {
                result = "Aucun livre trouvé avec le titre '" + titre + "' et l'auteur '" + auteur + "'.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche du livre.");
        }

        return result;
    }

    public static String searchRevue(String titre, String auteur) {
        String result = "";
        String sql = "SELECT titre, auteur, annee, genre, client_id FROM revue WHERE titre = ? AND auteur = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            pstmt.setString(2, auteur);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int annee = rs.getInt("annee");
                String genre = rs.getString("genre");
                int idClient = rs.getInt("client_id");

                result = "Titre: " + titre + "\nAuteur: " + auteur + "\nAnnée: " + annee + "\nGenre: " + genre;
                if (idClient != 0) {
                    result += "\nCette revue a été ajoutée par un client avec l'ID: " + idClient;
                } else {
                    result += "\nCette revue n'a pas encore été attribuée à un client.";
                }
            } else {
                result = "Aucune revue trouvée avec le titre '" + titre + "' et l'auteur '" + auteur + "'.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche de la revue.");
        }

        return result;
    }

    public static String searchVideo(String titre, String auteur) {
        String result = "";
        String sql = "SELECT titre, auteur, annee, genre, client_id FROM video WHERE titre = ? AND auteur = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titre);
            pstmt.setString(2, auteur);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int annee = rs.getInt("annee");
                String genre = rs.getString("genre");
                int idClient = rs.getInt("client_id");

                result = "Titre: " + titre + "\nAuteur: " + auteur + "\nAnnée: " + annee + "\nGenre: " + genre;
                if (idClient != 0) {
                    result += "\nCette vidéo a été ajoutée par un client avec l'ID: " + idClient;
                } else {
                    result += "\nCette vidéo n'a pas encore été attribuée à un client.";
                }
            } else {
                result = "Aucune vidéo trouvée avec le titre '" + titre + "' et l'auteur '" + auteur + "'.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la recherche de la vidéo.");
        }

        return result;
    }

    public static void supprimerLivre(int livreId) {
        String sql = "DELETE FROM livre WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, livreId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression du livre.");
        }
    }

    public static void supprimerRevue(int revueId) {
        String sql = "DELETE FROM revue WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, revueId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la revue.");
        }
    }

    public static void supprimerVideo(int videoId) {
        String sql = "DELETE FROM video WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, videoId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la suppression de la vidéo.");
        }
    }
}
