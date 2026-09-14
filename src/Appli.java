
public class Appli {
    private static final int NB_COMPTES = 9999;
    public static Compte[] comptes;

    public static void main(String[] args) {
        comptes = new Compte[NB_COMPTES];
        for (int i = 0; i < comptes.length; i++) {
            comptes[i] = new Compte();
        }

        new Authentification(comptes);
    }
}