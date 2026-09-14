import javax.swing.*;
import java.awt.*;

public class Virement {
	private static final int NB_COMPTES = 20;
	
    private JFrame fenetre;
    private JPanel panelPrincipal;
    private GridBagConstraints gbc;
    private Compte c;
    private Compte[] comptes;

    public Virement(Compte c, Compte[] comptes) {
    	this.comptes = comptes;
    	this.c = c;
    	
        fenetre = new JFrame("SwiterBank - Virement");
        fenetre.setSize(500, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        panelPrincipal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel h1 = new JLabel("Virement");
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        h1.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridy = 0;
        panelPrincipal.add(h1, gbc);
        
        JLabel Destinataire = new JLabel("N° de compte du destinataire : ");
        gbc.gridy = 1;
        panelPrincipal.add(Destinataire, gbc);
        
        JTextField numCompte = new JTextField(4);
        gbc.gridy = 2;
        panelPrincipal.add(numCompte, gbc);

        JLabel Montant = new JLabel("Entrez la somme à déposer sur le compte séléctionné :");
        gbc.gridy = 3;
        panelPrincipal.add(Montant, gbc);

        JTextField NbMontant = new JTextField(15);
        gbc.gridy = 4;
        panelPrincipal.add(NbMontant, gbc);

        JButton validerButton = new JButton("Valider");
        gbc.gridy = 5;
        panelPrincipal.add(validerButton, gbc);

        validerButton.addActionListener(e -> {
            String montant = NbMontant.getText();
            String numero = numCompte.getText();
            if (VerifNb(montant) && VerifNbCompt(numero)) {
                c.transaction(comptes[Integer.parseInt(numero) - 1], Double.parseDouble(montant));
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
    
    public boolean VerifNbCompt(String nb) {
    	if(nb.length() != 4) return false;
    	int value = Integer.parseInt(nb);
    	if(value < 0 || value > NB_COMPTES) return false;
    	else return true;
    }
}
