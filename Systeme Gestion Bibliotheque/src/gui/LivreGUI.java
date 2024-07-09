package gui;

import auth.Client;
import db.DatabaseConnection;
import documents.LivreBuilder;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LivreGUI {
    private JFrame frame;
    private JTextField titreField;
    private JTextField auteurField;
    private JTextField anneeField;
    private JTextField genreField;
    private Client client;

    public LivreGUI(Client client) {
        this.client = client;

        frame = new JFrame("Ajouter un Livre");
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

        JLabel anneeLabel = new JLabel("Année:");
        anneeLabel.setBounds(50, 90, 80, 25);
        panel.add(anneeLabel);

        anneeField = new JTextField();
        anneeField.setBounds(150, 90, 200, 25);
        panel.add(anneeField);

        JLabel genreLabel = new JLabel("Genre:");
        genreLabel.setBounds(50, 120, 80, 25);
        panel.add(genreLabel);

        genreField = new JTextField();
        genreField.setBounds(150, 120, 200, 25);
        panel.add(genreField);

        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(150, 160, 100, 30);
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterLivre();
            }
        });
        panel.add(ajouterButton);
    }

    private void ajouterLivre() {
        String titre = titreField.getText();
        String auteur = auteurField.getText();
        int annee = Integer.parseInt(anneeField.getText());
        String genre = genreField.getText();

        LivreBuilder builder = new LivreBuilder();
        builder.buildTitre(titre);
        builder.buildAuteur(auteur);
        builder.buildAnnee(annee);
        builder.buildGenre(genre);

        builder.ajouterLivre(client.getId());

        JOptionPane.showMessageDialog(frame, "Livre ajouté avec succès!");
        frame.dispose(); // Fermer la fenêtre après l'ajout
    }
}
