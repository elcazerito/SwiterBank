
public class Compte {
    private static double decMax = 1000.0;
    private int num_compte;
    private double solde_compte;
    private String id_compte;
    private String mdp;
    private String nom;
    private static int num = 1;

    public Compte(double solde) {
        assert (solde >= 0);
        this.solde_compte = solde;
        this.num_compte = num++;
        this.generateIdCompte();
        this.mdp = "mdp" + this.id_compte;
        this.nom = "";
    }

    public Compte() {
        this(0);
    }

    private void generateIdCompte() {
        this.id_compte = String.format("%04d", this.num_compte);
    }

    public static void setMaxDec(double montant) {
        if (montant >= 0) decMax = montant;
    }

    public boolean authentification(String id, String mdp) {
        return this.id_compte.equals(id) && this.mdp.equals(mdp);
    }

    public void retrait(double montant) {
        if (this.solde_compte + decMax >= montant && montant > 0) {
            this.solde_compte -= montant;
        }
    }

    public void depot(double montant) {
        if (montant > 0) {
            this.solde_compte += montant;
        }
    }

    public void transaction(Compte c, double montant) {
        if (this.solde_compte + decMax >= montant) {
            c.solde_compte += montant;
            this.solde_compte -= montant;
        } else {
            System.out.println("Le compte créditeur n'a pas assez d'argent !");
        }
    }
    
    public void changePassword(String newMdp) {
        this.mdp = newMdp;
    }

    public void declarationNom(String nom) {
        this.nom = nom;
    }

    public String getIdCompte() {
        return this.id_compte;
    }

    public double getSolde() {
        return this.solde_compte;
    }

    public String getPassword() {
        return this.mdp;
    }

    public String getNom() {
        return this.nom;
    }
}
