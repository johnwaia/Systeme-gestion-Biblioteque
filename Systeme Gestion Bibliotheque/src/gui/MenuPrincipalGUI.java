package gui;

import auth.Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuPrincipalGUI {
    private JFrame frame;
    private Client client;

    public MenuPrincipalGUI(Client client) {
        this.client = client;

        frame = new JFrame("Menu Principal");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        JLabel welcomeLabel = new JLabel("Bienvenue, " + client.getNom());
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Utilisation d'un BoxLayout vertical pour les boutons
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Création d'un Box pour les boutons centrés horizontalement
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.setAlignmentX(Box.CENTER_ALIGNMENT);
        buttonPanel.add(buttonBox);

        JButton addDocumentButton = new JButton("Ajouter un document");
        addDocumentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddDocumentGUI(client);
                frame.dispose(); // Fermer le menu principal
            }
        });
        buttonBox.add(addDocumentButton);

        JButton searchDocumentButton = new JButton("Rechercher un document");
        searchDocumentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SearchDocumentGUI(client);
                frame.dispose();
            }
        });
        buttonBox.add(searchDocumentButton);

        if (client.isAdmin()) {
            JButton deleteDocumentButton = new JButton("Supprimer un document");
            deleteDocumentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DeleteDocumentGUI(client);
                    frame.dispose();
                }
            });
            buttonBox.add(deleteDocumentButton);
        }

        JButton logoutButton = new JButton("Déconnexion");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new ConnexionInscriptionGUI();
            }
        });
        panel.add(logoutButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

}
