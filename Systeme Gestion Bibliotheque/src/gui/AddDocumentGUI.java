package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DatabaseConnection;
import auth.Client;
import documents.DocumentFactory;
import documents.LivreFactory;
import documents.RevueFactory;
import documents.VideoFactory;

public class AddDocumentGUI {
    private JFrame frame;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField yearField;
    private JComboBox<String> documentTypeComboBox;
    private JButton selectPdfButton;
    private JLabel selectedPdfLabel;
    private File selectedPdfFile;
    private JButton addButton;
    private JButton backButton;
    private Client client;

    private DocumentFactory documentFactory; // Abstract Factory reference

    public AddDocumentGUI(Client client) {
        this.client = client;
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Ajouter un document");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
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

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(50, 90, 80, 25);
        panel.add(genreLabel);

        genreField = new JTextField();
        genreField.setBounds(150, 90, 200, 25);
        panel.add(genreField);

        JLabel yearLabel = new JLabel("Année:");
        yearLabel.setBounds(50, 120, 80, 25);
        panel.add(yearLabel);

        yearField = new JTextField();
        yearField.setBounds(150, 120, 200, 25);
        panel.add(yearField);

        JLabel documentTypeLabel = new JLabel("Type de document:");
        documentTypeLabel.setBounds(50, 150, 120, 25);
        panel.add(documentTypeLabel);

        String[] documentTypes = {"Livre", "Revue", "Video"};
        documentTypeComboBox = new JComboBox<>(documentTypes);
        documentTypeComboBox.setBounds(180, 150, 170, 25);
        panel.add(documentTypeComboBox);

        selectPdfButton = new JButton("Sélectionner PDF");
        selectPdfButton.setBounds(50, 180, 150, 30);
        selectPdfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedPdfFile = fileChooser.getSelectedFile();
                    selectedPdfLabel.setText("Fichier PDF sélectionné: " + selectedPdfFile.getName());
                }
            }
        });
        panel.add(selectPdfButton);

        selectedPdfLabel = new JLabel("Aucun fichier PDF sélectionné");
        selectedPdfLabel.setBounds(210, 180, 200, 30);
        panel.add(selectedPdfLabel);

        addButton = new JButton("Ajouter");
        addButton.setBounds(50, 220, 100, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDocument();
            }
        });
        panel.add(addButton);

        backButton = new JButton("Retour");
        backButton.setBounds(250, 220, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fermer la fenêtre actuelle
                new MenuPrincipalGUI(client); // Ouvrir le menu principal
            }
        });
        panel.add(backButton);

        // Sélection de la factory en fonction du type de document choisi
        documentTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) documentTypeComboBox.getSelectedItem();
                switch (selectedType) {
                    case "Livre":
                        documentFactory = new LivreFactory();
                        break;
                    case "Revue":
                        documentFactory = new RevueFactory();
                        break;
                    case "Video":
                        documentFactory = new VideoFactory();
                        break;
                    default:
                        documentFactory = null;
                        break;
                }
            }
        });

        frame.setVisible(true);
    }

    private void addDocument() {
        if (documentFactory == null) {
            JOptionPane.showMessageDialog(frame, "Sélectionnez un type de document.");
            return;
        }

        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        int year = Integer.parseInt(yearField.getText());
        String pdfFilePath = selectedPdfFile != null ? selectedPdfFile.getAbsolutePath() : null;
        int clientId = client.getId(); // Assuming Client has a getId() method

        String documentType = (String) documentTypeComboBox.getSelectedItem();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(documentFactory.getInsertQuery())) {

            documentFactory.setParameters(pstmt, title, author, genre, year, pdfFilePath, clientId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, documentType + " ajouté avec succès.");
            } else {
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du " + documentType.toLowerCase() + ".");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du " + documentType.toLowerCase() + ".");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Erreur: Année doit être un nombre entier.");
        }
    }


}
