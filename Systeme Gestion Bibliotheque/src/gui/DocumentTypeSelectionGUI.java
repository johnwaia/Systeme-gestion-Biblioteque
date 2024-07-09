package gui;
import auth.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DocumentTypeSelectionGUI {
    private JFrame frame;
    private Client client;

    public DocumentTypeSelectionGUI(Client client) {
        this.client = client;

        frame = new JFrame("Sélectionnez le type de document");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel selectLabel = new JLabel("Sélectionnez le type de document:");
        selectLabel.setBounds(50, 30, 250, 25);
        panel.add(selectLabel);

        JButton revueButton = new JButton("Revue");
        revueButton.setBounds(50, 70, 120, 25);
        revueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RevueGUI(client);
                frame.dispose();
            }
        });
        panel.add(revueButton);

        JButton videoButton = new JButton("Vidéo");
        videoButton.setBounds(200, 70, 120, 25);
        videoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VideoGUI(client);
                frame.dispose();
            }
        });
        panel.add(videoButton);


        JButton livreButton = new JButton("Livre");
        livreButton.setBounds(350, 70, 120, 25);
        livreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LivreGUI(client);
                frame.dispose();
            }
        });
        panel.add(livreButton);

    }
}
