package gui;

import auth.Client;

import documents.RevueBuilder;

import javax.swing.*;
import java.awt.event.*;


public class RevueGUI {
    private JFrame frame;
    private JTextField titreField;
    private JTextField auteurField;
    private JTextField genreField;
    private JTextField anneeField;
    private Client client;

    public RevueGUI(Client client) {
        this.client = client;

        frame = new JFrame("Ajouter une Revue");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titreLabel = new JLabel("Titre:");
        titreLabel.setBounds(50, 30, 80, 25);
        panel.add(titreLabel);

        titreField = new JTextField();
        titreField.setBounds(150, 30, 200, 25);
        panel.add(titreField);

        JLabel auteurLabel = new JLabel("Auteur:");
        auteurLabel.setBounds(50, 60, 80, 25);
        panel.add(auteurLabel);

        auteurField = new JTextField();
        auteurField.setBounds(150, 60, 200, 25);
        panel.add(auteurField);

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(50, 90, 80, 25);
        panel.add(genreLabel);

        genreField = new JTextField();
        genreField.setBounds(150, 90, 200, 25);
        panel.add(genreField);

        JLabel anneeLabel = new JLabel("Année:");
        anneeLabel.setBounds(50, 120, 80, 25);
        panel.add(anneeLabel);

        anneeField = new JTextField();
        anneeField.setBounds(150, 120, 200, 25);
        panel.add(anneeField);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(150, 160, 100, 30);
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterRevue();
            }
        });
        panel.add(ajouterButton);
    }

    private void ajouterRevue() {
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        String genre = genreField.getText();
        String anneeStr = anneeField.getText();

        // Vérification des champs obligatoires
        if (titre.isEmpty() || auteur.isEmpty() || genre.isEmpty() || anneeStr.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tous les champs doivent être remplis.");
            return;
        }

        int annee = Integer.parseInt(anneeStr);

        RevueBuilder builder = new RevueBuilder();
        builder.buildTitre(titre);
        builder.buildAuteur(auteur);
        builder.buildGenre(genre);
        builder.buildAnnee(annee);

        builder.ajouterDocument(client.getId());

        JOptionPane.showMessageDialog(frame, "Revue ajoutée avec succès!");
        frame.dispose(); // Fermer la fenêtre après l'ajout
    }
}
