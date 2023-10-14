
import java.util.Arrays;
import java.util.Scanner;

public class GestionEntrepot {
    private static Scanner scanner = new Scanner(System.in);
    //private static MonScanner scanner = new MonScanner("commandes.txt");
    private static Entrepot entrepot;

    public static void main(String[] args) {
        System.out.println("*********************");
        System.out.println("Gestion d'un entrepot");
        System.out.println("*********************");
        System.out.println();
        System.out.print("Entrez le nombre d'hangars : ");
        int nombreHangars = scanner.nextInt();
        entrepot = new Entrepot(nombreHangars);
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> attribuer un hangar");
            System.out.println("2 -> lister les hangars d'une societe");
            System.out.println("3 -> libérer un hangar");
            System.out.println("4 -> ajouter un vehicule à une société");
            System.out.println("5 -> vérifier une plaque d'immatriculation");
            System.out.println("6 -> quitter");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    attribuerUnHangar();
                    break;
                case 2:
                    listerLesHangars();
                    break;
                case 3:
                    libererHangar();
                    break;
                case 4:
                    ajouterVehicule();
                    break;
                case 5:
                    estAutorise();
                case 6:
                    break;
            }

        } while (choix!=6);

        System.out.println("Fin");
    }

    private static void attribuerUnHangar() {
        if (entrepot.nombreHangarsLibres()==0) {
            System.out.println("Desole, tous les hangars sont occupes !");
        } else {
            System.out.print("Entrez le numero de la societe : ");
            int numeroSociete = scanner.nextInt();
            Societe societe = entrepot.getSociete(numeroSociete);
            String nomSociete;
            if(societe==null){
                System.out.print("Entrez le nom de la societe : ");
                nomSociete = scanner.next();
            }else{
                nomSociete = societe.getNom();
            }
            System.out.println();
            int numeroHangar = entrepot.attribuerHangar(numeroSociete,nomSociete);
            System.out.println("Le numero du hangar attribue : " + numeroHangar);
        }
    }

    private static void listerLesHangars() {
        System.out.println(entrepot.toString());
    }

    private static void libererHangar(){
        if (entrepot.nombreHangarsLibres()==5){
            System.out.println("Tous les hangars sont libres");
        }else{
            System.out.println("Entrez le numéro du hangar à libérer : ");
            int numeroHangar = scanner.nextInt();
            if (entrepot.hangarVide(numeroHangar)){
                System.out.println("Le hangar est déja vide !");
            }
            entrepot.libererHangar(numeroHangar);
            if (!entrepot.hangarVide(numeroHangar)){
                System.out.println("Le hangar contient toujours la société !");
            }
            System.out.println("Le hangar "+ numeroHangar+ " a bien été libéré");
        }

    }

   private static void ajouterVehicule(){

   }

   private static void estAutorise(){

   }

}
