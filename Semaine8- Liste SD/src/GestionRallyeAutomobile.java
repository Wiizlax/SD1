import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/*
 Author : Tom Simonis
 */

public class GestionRallyeAutomobile {

    private final static Scanner scanner = new Scanner(System.in);
    private static RallyeAutomobile course;
    private static ArrayList<String> pilotesSupp;

    public static void main(String[] args) {
        System.out.println("*****************************************");
        System.out.println("Programme de gestion de rallye automobile");
        System.out.println("*****************************************");

        System.out.print("Entrez le nombre de pilotes : ");
        int nbrPilotes = scanner.nextInt();
        String[] listePilotes = new String[nbrPilotes];
        for (int i = 1; i <= nbrPilotes; i++) {
            System.out.print("Entrez le nom du pilote n°" + i + " : ");
            String nom = scanner.next();
            listePilotes[i - 1] = nom;
        }
        pilotesSupp = new ArrayList<>();
        course = new RallyeAutomobile(listePilotes);
        int choix = 0;
        do {
            System.out.println();
            System.out.println("1 -> Afficher toute la course ");
            System.out.println("2 -> Afficher le pilote en tête ");
            System.out.println("3 -> Enregistrer un dépassement ");
            System.out.println("4 -> Retirer un pilote de la course ");
            System.out.println("5 -> Donner la position d'un pilote ");
            System.out.println("6 -> Faire franchir la ligne d’arrivée au pilote de tête ");
            System.out.println("7 -> Remettre un pilote en course (Après un autre pilote)");
            System.out.println("8 -> Afficher les pilotes hors course");
            System.out.println("9 -> Afficher le classement");
            System.out.println("10 -> Quitter ");
            System.out.println();
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    afficherCourse();
                    break;
                case 2:
                    piloteEnTete();
                    break;
                case 3:
                    enregistrerDepassement();
                    break;
                case 4:
                    supprimerPilote();
                    break;
                case 5:
                    positionPilote();
                    break;
                case 6:
                    franchirLigne();
                    break;
                case 7:
                    remettreEnCourse();
                    break;
                case 8:
                    afficherPilotesHorsCourse();
                    break;
                case 9:
                    afficherClassement();
                    break;
                case 10:
                    break;
            }

        } while (choix != 10);
        System.out.println("Fin");
    }

    private static void afficherCourse() {
        System.out.println(course.afficherCourse());
    }

    private static void piloteEnTete() {
        System.out.println("Le pilote en tête de course est : " + course.piloteTete());
    }

    private static void enregistrerDepassement() {
        System.out.print("Entrez le pilote qui dépasse : ");
        String pilote = scanner.next();
        course.enregistrerDepassement(pilote);
        System.out.println("Dépassement enregistré");
    }

    private static void supprimerPilote() {
        System.out.print("Entrez le pilote à supprimer : ");
        String pilote = scanner.next();
        if (!course.contient(pilote)) {
            System.out.println("Le pilote n'est pas dans la course");
            return;
        }
        pilotesSupp.add(pilote);
        course.supprimerPilote(pilote);
        System.out.println("Le pilote a bien été supprimé");
    }

    private static void positionPilote() {
        System.out.print("Entrez le pilote dont vous voulez la position : ");
        String pilote = scanner.next();
        if (!course.contient(pilote)) {
            System.out.println("Le pilote ne fais pas partie de la course");
            return;
        }
        int pos = course.donnerPositionPilote(pilote);
        if (pos > 1) {
            System.out.println(pilote + " se trouve en " + pos + " ème position");
        } else if (pos == 1) {
            System.out.println("Bravo au pilote " + pilote + " qui se trouve en première position!");
        }
    }

    private static void franchirLigne() {
        System.out.println(course.piloteTete() + " a finis la course avec succès");
        course.faireFranchirLigneArrivee();
    }

    public static void remettreEnCourse() {
        System.out.print("Entrez le pilote à remettre en course : ");
        String pilote = scanner.next();
        System.out.print("Entrez le pilote avant le pilote à réinsérer : ");
        String piloteAvant = scanner.next();
        course.remettreEnCourse(pilote, piloteAvant);
        pilotesSupp.remove(pilote);
        System.out.println("Le pilote à bien été rajouté : ");
        afficherCourse();
    }

    public static void afficherPilotesHorsCourse() {
        System.out.println(Arrays.toString(pilotesSupp.toArray()));
    }

    public static void afficherClassement() {
        System.out.println(course.afficherClassement());
    }
}
