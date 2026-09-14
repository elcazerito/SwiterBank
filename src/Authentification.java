import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Authentification {
    private static final int NB_COMPTES = 9999;
    private JFrame fenetre;
    private JPanel panelPrincipal;
    private GridBagConstraints gbc;

    public Authentification(Compte[] comptes) {
        fenetre = new JFrame("SwiterBank - Authentification");
        fenetre.setSize(400, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel h1 = new JLabel("Authentification");
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        panelPrincipal.add(h1, gbc);

        JLabel idLabel = new JLabel("Entrez votre identifiant :");
        gbc.gridy = 1;
        panelPrincipal.add(idLabel, gbc);

        JTextField inputId = new JTextField(15);
        gbc.gridy = 2;
        panelPrincipal.add(inputId, gbc);

        JLabel mdpLabel = new JLabel("Entrez votre mot de passe :");
        gbc.gridy = 3;
        panelPrincipal.add(mdpLabel, gbc);

        JPasswordField inputMdp = new JPasswordField(15);
        gbc.gridy = 4;
        panelPrincipal.add(inputMdp, gbc);

        JButton bouton = new JButton("Valider");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idIndex = extractId(inputId.getText());
                if (idIndex != -1) {
                    verifAuthentification(comptes[idIndex], inputId.getText(), new String(inputMdp.getPassword()));
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Identifiant invalide !");
                }
            }

            private void verifAuthentification(Compte c, String id, String mdp) {
                if (!c.authentification(id, mdp)) {
                    JOptionPane.showMessageDialog(fenetre, "Identifiant ou mot de passe incorrect !");
                } else {
                    fenetre.dispose();
                    new Operations(c, comptes);
                }
            }
        });
        gbc.gridy = 5;
        panelPrincipal.add(bouton, gbc);

        fenetre.add(panelPrincipal);
        fenetre.setVisible(true);
    }

    public int extractId(String id) {
        try {
            int idNum = Integer.parseInt(id);
            if (idNum >= 1 && idNum <= NB_COMPTES) {
                return idNum - 1;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
