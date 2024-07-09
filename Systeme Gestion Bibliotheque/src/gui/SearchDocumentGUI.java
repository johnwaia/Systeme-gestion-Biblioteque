package gui;

import auth.Client;
import db.DatabaseConnection;
import documents.LivreBuilder;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchDocumentGUI {
    private JFrame frame;
    private JComboBox<String> documentTypeComboBox;
    private JTextField titleField;
    private JTextField authorField;
    private JButton searchButton;
    private JButton backButton; // Nouveau bouton Retour
    private JTextArea resultArea;
    private JPanel panel;
    private Client client;

    public SearchDocumentGUI(Client client) {
        this.client = client;

        frame = new JFrame("Rechercher un document");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
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

        searchButton = new JButton("Rechercher");
        searchButton.setBounds(150, 130, 120, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDocument();
            }
        });
        panel.add(searchButton);

        backButton = new JButton("Retour");
        backButton.setBounds(300, 130, 120, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fermer la fenêtre de recherche
                new MenuPrincipalGUI(client); // Réouvrir le menu principal
            }
        });
        panel.add(backButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 170, 500, 150);
        panel.add(scrollPane);
    }

    private void searchDocument() {
        String title = titleField.getText();
        String author = authorField.getText();
        String documentType = (String) documentTypeComboBox.getSelectedItem();

        resultArea.setText("");

        switch (documentType) {
            case "Livre":
                searchLivre(title, author);
                break;
            case "Revue":
                searchRevue(title, author);
                break;
            case "Video":
                searchVideo(title, author);
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Type de document non pris en charge.");
        }
    }

    private void searchLivre(String title, String author) {
        String sql = "SELECT * FROM livre WHERE titre LIKE ? AND auteur LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String result = "Titre: " + rs.getString("titre") +
                                    ", Auteur: " + rs.getString("auteur") +
                                    ", Genre: " + rs.getString("genre") +
                                    ", Année: " + rs.getInt("annee");
                    String filePath = rs.getString("fichier_pdf");
                    if (filePath != null) {
                        result += " [PDF disponible]";
                    }
                    resultArea.append(result + "\n");

                    if (filePath != null) {
                        JButton downloadButton = new JButton("Télécharger PDF");
                        downloadButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                downloadPDF(filePath);
                            }
                        });
                        panel.add(downloadButton);
                        frame.validate();
                        frame.repaint();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la recherche de livres.");
        }
    }

    private void searchRevue(String title, String author) {
        String sql = "SELECT * FROM revue WHERE titre LIKE ? AND auteur LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String result = "Titre: " + rs.getString("titre") +
                                    ", Auteur: " + rs.getString("auteur") +
                                    ", Genre: " + rs.getString("genre") +
                                    ", Année: " + rs.getInt("annee");
                    String filePath = rs.getString("fichier_pdf");
                    if (filePath != null) {
                        result += " [PDF disponible]";
                    }
                    resultArea.append(result + "\n");

                    if (filePath != null) {
                        JButton downloadButton = new JButton("Télécharger PDF");
                        downloadButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                downloadPDF(filePath);
                            }
                        });
                        panel.add(downloadButton);
                        frame.validate();
                        frame.repaint();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la recherche de revues.");
        }
    }

    private void searchVideo(String title, String author) {
        String sql = "SELECT * FROM video WHERE titre LIKE ? AND auteur LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + author + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String result = "Titre: " + rs.getString("titre") +
                                    ", Auteur: " + rs.getString("auteur") +
                                    ", Genre: " + rs.getString("genre") +
                                    ", Année: " + rs.getInt("annee");
                    resultArea.append(result + "\n");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de la recherche de vidéos.");
        }
    }

    private void downloadPDF(String filePath) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File(filePath));
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File destFile = fileChooser.getSelectedFile();
            try {
                Files.copy(Paths.get(filePath), new FileOutputStream(destFile));
                JOptionPane.showMessageDialog(frame, "PDF téléchargé avec succès.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors du téléchargement du PDF.");
            }
        }
    }
}
