package gui;

import auth.Client;
import auth.ClientAuthentication;
import db.DatabaseConnection;

import javax.swing.*;
import java.awt.event.*;

public class ConnexionInscriptionGUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox adminCheckBox; // New checkbox for admin selection

    public ConnexionInscriptionGUI() {
        frame = new JFrame("Connexion / Inscription");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Utilisateur:");
        userLabel.setBounds(50, 30, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 200, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setBounds(50, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 200, 25);
        panel.add(passwordField);

        adminCheckBox = new JCheckBox("Administrateur");
        adminCheckBox.setBounds(150, 90, 150, 25);
        panel.add(adminCheckBox);

        JButton loginButton = new JButton("Connexion");
        loginButton.setBounds(50, 130, 100, 30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        panel.add(loginButton);

        JButton registerButton = new JButton("Inscription");
        registerButton.setBounds(200, 130, 100, 30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        panel.add(registerButton);

        JButton quitButton = new JButton("Quitter");
        quitButton.setBounds(125, 180, 150, 30);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Ferme la fenêtre principale
                System.exit(0); // Termine l'application
            }
        });
        panel.add(quitButton);
    

    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        Client client = ClientAuthentication.verifierConnexion(username, password);
        if (client != null) {
            JOptionPane.showMessageDialog(frame, "Connexion réussie!");
            frame.dispose();
            new MenuPrincipalGUI(client);
        } else {
            JOptionPane.showMessageDialog(frame, "Connexion échouée!");
        }
    }

    private void register() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdmin = adminCheckBox.isSelected(); // Check if admin checkbox is selected

        Client client = new Client(username, password);
        DatabaseConnection.inscrire(client, isAdmin);
        JOptionPane.showMessageDialog(frame, "Inscription réussie! Veuillez vous connecter.");
    }
}
