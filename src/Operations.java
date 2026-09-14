import javax.swing.*;
import java.awt.*;

public class Operations {
    private JFrame fenetre;
    private JPanel panelPrincipal;
    private GridBagConstraints gbc;
    private Compte[] comptes; 
    private Compte c;
    
    public Operations(Compte c, Compte[] comptes) {
        this.comptes = comptes;
        this.c = c;
        fenetre = new JFrame("SwiterBank - Opérations");
        fenetre.setSize(600, 400);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton Deconnexion = new JButton("Déconnexion");
        topPanel.add(Deconnexion);
        Deconnexion.addActionListener(e -> Deconnexion());

        mainPanel.add(topPanel, BorderLayout.NORTH);

        panelPrincipal = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel labelBienvenue = new JLabel("Bienvenue sur votre espace membre !");
        labelBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
        labelBienvenue.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panelPrincipal.add(labelBienvenue, gbc);

        JLabel labelMembre = new JLabel("Vous êtes connecté en tant que Mr/Mme " + c.getNom());
        if ( c.getNom().isEmpty()) labelMembre.setText("Vous êtes connecté en tant que Membre " + c.getIdCompte());
        labelMembre.setHorizontalAlignment(SwingConstants.CENTER);
        labelMembre.setFont(new Font("Arial", Font.ITALIC, 15));
        gbc.gridy = 1;
        panelPrincipal.add(labelMembre, gbc);

        JLabel labelArgent = new JLabel("Vous avez : " + c.getSolde() + " FS");
        labelArgent.setHorizontalAlignment(SwingConstants.CENTER);
        labelArgent.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.gridy = 2;
        panelPrincipal.add(labelArgent, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcButtons = new GridBagConstraints();
        gbcButtons.fill = GridBagConstraints.HORIZONTAL;
        gbcButtons.insets = new Insets(10, 10, 10, 10);
        gbcButtons.weightx = 1.0;
        gbcButtons.ipadx = 50;

        gbcButtons.gridy = 0;

        JButton Retrait = new JButton("Retrait");
        gbcButtons.gridx = 0;
        buttonPanel.add(Retrait, gbcButtons);
        Retrait.addActionListener(e -> Retirer());

        JButton Depot = new JButton("Dépôt");
        gbcButtons.gridx = 1;
        buttonPanel.add(Depot, gbcButtons);
        Depot.addActionListener(e -> Deposer());

        JButton Transaction = new JButton("Virement");
        gbcButtons.gridx = 2;
        buttonPanel.add(Transaction, gbcButtons);
        Transaction.addActionListener(e -> Virer());

        gbcButtons.gridy = 1;

        JButton ChangerMotDePasse = new JButton("Changement de mot de passe");
        gbcButtons.gridx = 0;
        gbcButtons.gridwidth = 2;
        buttonPanel.add(ChangerMotDePasse, gbcButtons);
        ChangerMotDePasse.addActionListener(e -> ChangePassword());

        JButton ChangerNom = new JButton("Changement de nom");
        gbcButtons.gridx = 2;
        gbcButtons.gridwidth = 1;
        buttonPanel.add(ChangerNom, gbcButtons);
        ChangerNom.addActionListener(e -> ChangeName());

        gbc.gridy = 3;
        panelPrincipal.add(buttonPanel, gbc);

        mainPanel.add(panelPrincipal, BorderLayout.CENTER);
        fenetre.add(mainPanel);
        fenetre.setVisible(true);
    }

    private void Deconnexion() {
        new Authentification(comptes);
        fenetre.dispose();
    }
    
    private void Retirer() {
    	new Retrait(c, comptes);
    	fenetre.dispose();
    }
    
    private void Deposer() {
    	new Depot(c, comptes);
    	fenetre.dispose();
    }
    
    private void Virer() {
    	new Virement(c, comptes);
    	fenetre.dispose();
    }
    
    private void ChangePassword() {
    	new ChangePassword(c, comptes);
    	fenetre.dispose();
    }
    
    private void ChangeName() {
    	new ChangeName(c, comptes);
        fenetre.dispose();
    }
}
