package gui;

import auth.Client;
import db.DatabaseConnection;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteDocumentGUI {
    private JFrame frame;
    private JComboBox<String> documentTypeComboBox;
    private JTextField titleField;
    private JTextField authorField;
    private JButton deleteButton;
    private JButton backButton;
    private Client client;

    public DeleteDocumentGUI(Client client) {
        this.client = client;

        frame = new JFrame("Supprimer un document");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Titre:");
        titleLabel.setBounds(50, 30, 80, 25);
        panel.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 30, 200, 25);
        panel.add(titleField);

        JLabel authorLabel = new JLabel("Auteur:");
        authorLabel.setBounds(50, 60, 80, 25);
        panel.add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(150, 60, 200, 25);
        panel.add(authorField);

        JLabel documentTypeLabel = new JLabel("Type de document:");
        documentTypeLabel.setBounds(50, 90, 120, 25);
        panel.add(documentTypeLabel);

        String[] documentTypes = {"Livre", "Revue", "Video"};
        documentTypeComboBox = new JComboBox<>(documentTypes);
        documentTypeComboBox.setBounds(180, 90, 170, 25);
        panel.add(documentTypeComboBox);

        deleteButton = new JButton("Supprimer");
        deleteButton.setBounds(50, 150, 100, 30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteDocument();
            }
        });
        panel.add(deleteButton);

        backButton = new JButton("Retour");
        backButton.setBounds(250, 150, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fermer la fenêtre actuelle
                new MenuPrincipalGUI(client); // Ouvrir le menu principal
            }
        });
        panel.add(backButton);
    }

    private void deleteDocument() {
        String title = titleField.getText();
        String author = authorField.getText();
        String documentType = (String) documentTypeComboBox.getSelectedItem();

        // Perform deletion based on document type
        switch (documentType) {
            case "Livre":
                deleteLivre(title, author);
                break;
            case "Revue":
                deleteRevue(title, author);
                break;
            case "Video":
                deleteVideo(title, author);
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Type de document non pris en charge.");
        }
    }

    private void deleteLivre(String title, String author) {
        String sql = "DELETE FROM livre WHERE titre = ? AND auteur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Livre supprimé avec succès.");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucun livre trouvé avec ces détails.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression du livre.");
        }
    }

    private void deleteRevue(String title, String author) {
        String sql = "DELETE FROM revue WHERE titre = ? AND auteur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Revue supprimée avec succès.");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucune revue trouvée avec ces détails.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression de la revue.");
        }
    }

    private void deleteVideo(String title, String author) {
        String sql = "DELETE FROM video WHERE titre = ? AND auteur = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Vidéo supprimée avec succès.");
            } else {
                JOptionPane.showMessageDialog(frame, "Aucune vidéo trouvée avec ces détails.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression de la vidéo.");
        }
    }
}
