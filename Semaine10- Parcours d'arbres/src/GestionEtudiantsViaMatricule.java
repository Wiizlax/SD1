import java.util.Comparator;
import java.util.TreeSet;

public class GestionEtudiantsViaMatricule {
    //private static Scanner scanner = new Scanner(System.in);
    private static final MonScanner scanner = new MonScanner("InputA.txt");
    private static final ComparateurMatricule comparateurMatricule = new ComparateurMatricule();
    private static final TreeSet<Etudiant> etudiants = new TreeSet<>(comparateurMatricule);

    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("Gestion des etudiants");
        System.out.println("*********************");
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> ajouter un etudiant");
            System.out.println("2 -> afficher tous les etudiants");

            System.out.println();
            System.out.print("Entrez votre choix : ");

            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    ajout();
                    break;
                case 2:
                    tous();
                    break;

                default:
                    break;
            }
        } while (choix >= 1 && choix <= 2 );


    }

    private static void tous() {
        System.out.println();
        for (Etudiant etudiant : etudiants) {
            System.out.println(etudiant.getNumeroMatricule()+ " " +etudiant.getNom() + " " +etudiant.getPrenom());
        }
    }

    private static void ajout() {
        System.out.println();
        System.out.print("Entrez le numero de matricule : ");
        int numero = scanner.nextInt();
        System.out.print("Entrez le nom : ");
        String nom = scanner.next();
        System.out.println();
        System.out.print("Entrez le prenom : ");
        String prenom = scanner.next();
        System.out.println();
        etudiants.add(new Etudiant(numero,nom,prenom));
    }

    private static class ComparateurMatricule implements Comparator<Etudiant> {

        @Override
        public int compare(Etudiant etudiant1, Etudiant etudiant2) {
            return Integer.compare(etudiant1.getNumeroMatricule(), etudiant2.getNumeroMatricule());
        }
    }
}
