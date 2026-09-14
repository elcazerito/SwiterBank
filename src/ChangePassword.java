import javax.swing.*;
import java.awt.*;

public class ChangePassword {
    private JFrame fenetre;
    private JPanel panelPrincipal;
    private GridBagConstraints gbc;

    public ChangePassword(Compte c, Compte[] comptes) {
        if (c == null) {
            throw new IllegalArgumentException("Le compte passé au constructeur est null.");
        }

        fenetre = new JFrame("SwiterBank - Changement de mot de passe");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel h1 = new JLabel("Déclarer un changement de nom");
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        panelPrincipal.add(h1, gbc);

        JLabel idLabel = new JLabel("Entrez votre nouveau nom :");
        gbc.gridy = 1;
        panelPrincipal.add(idLabel, gbc);

        JTextField inputId = new JTextField(20);
        gbc.gridy = 2;
        panelPrincipal.add(inputId, gbc);

        JButton bouton = new JButton("Valider");
        bouton.addActionListener(e -> {
            String nouveauMotDePasse = inputId.getText();
            if (nouveauMotDePasse.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Le mot de passe ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
                c.changePassword(nouveauMotDePasse);
                JOptionPane.showMessageDialog(fenetre, "Mot de passe changé avec succès !");
                new Operations(c, comptes);
                fenetre.dispose();
            }
        });
        gbc.gridy = 3;
        panelPrincipal.add(bouton, gbc);

        fenetre.add(panelPrincipal);
        fenetre.setVisible(true);
    }
}
