import javax.swing.*;
import java.awt.*;

public class Retrait {
    private JFrame fenetre;
    private JPanel panelPrincipal;
    private GridBagConstraints gbc;
    private Compte c;
    private Compte[] comptes;

    public Retrait(Compte c, Compte[] comptes) {
    	this.comptes = comptes;
    	this.c = c;
    	
        fenetre = new JFrame("SwiterBank - Retrait");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel h1 = new JLabel("Retrait d'argent");
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        panelPrincipal.add(h1, gbc);

        JLabel Montant = new JLabel("Entrez la somme à déposer sur votre compte :");
        gbc.gridy = 1;
        panelPrincipal.add(Montant, gbc);

        JTextField NbMontant = new JTextField(15);
        gbc.gridy = 2;
        panelPrincipal.add(NbMontant, gbc);

        JButton validerButton = new JButton("Valider");
        gbc.gridy = 3;
        panelPrincipal.add(validerButton, gbc);

        validerButton.addActionListener(e -> {
            String montant = NbMontant.getText();
            if (VerifNb(montant)) {
                c.retrait(Double.parseDouble(montant));
                new Operations(this.c, this.comptes);
                fenetre.dispose();
            } else {
                JOptionPane.showMessageDialog(fenetre, "Veuillez entrer un montant valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        fenetre.add(panelPrincipal);
        fenetre.setVisible(true);
    }

    public boolean VerifNb(String nb) {
        int compt_virg = 0;

        for (int i = 0; i < nb.length(); i++) {
            char c = nb.charAt(i);

            if (!Character.isDigit(c) && c != '.') return false;
            if (c == '.') {
                compt_virg++;
                if (compt_virg > 1) return false;
                if (i == 0 || nb.charAt(i - 1) == '.') return false;
            }
        }
        return true;
    }
}
