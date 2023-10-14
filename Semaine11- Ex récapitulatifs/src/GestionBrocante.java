import java.util.Iterator;
	import java.util.Scanner;

public class GestionBrocante {

	private static Scanner scanner = new Scanner(System.in);
	private static Brocante brocante;

	public static void main(String[] args) {

		System.out.println("**********************");
		System.out.println("gestion d'une brocante");
		System.out.println("**********************");
		System.out.println();

		// configuration de la brocante
		System.out.println("configuration de la brocante");
		System.out.println("----------------------------");
		System.out.print("Entrez le nombre d'emplacements : ");
		int nombreEmplacements = scanner.nextInt();
		scanner.nextLine();
		char[] tableEmplacements = new char[nombreEmplacements];
		for (int i = 0; i < nombreEmplacements; i++) {
			System.out.print("Type de l'emplacement n°" + i + ": ");
			char type = scanner.next().charAt(0);
			scanner.nextLine();
			tableEmplacements[i] = type;
		}
		System.out.print("Entrez le nombre de riverains : ");
		int nombreRiverains = scanner.nextInt();
		scanner.nextLine();
		String[] tableRiverains = new String[nombreRiverains];
		for (int i = 0; i < tableRiverains.length; i++) {
			System.out.print("Entrez le nom du riverain "+ (i+1) + ": ");
			tableRiverains[i] = scanner.nextLine();
		}
		brocante = new Brocante(tableEmplacements, tableRiverains);
		System.out.println();

		// Phase 1
		System.out.println("Phase 1");
		System.out.println("-------");
		int choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> consulter un exposant");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> libérer un emplacement");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase1();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					consulterExposant();
					break;
				case 4:
					listerTousLesExposants();
					break;
				case 5:
					libererEmplacement();
					break;
			}

		} while (choix >= 1 && choix <= 5);
//////////////////////////////////////////////////////////////////////////////
		brocante.changerPhase();
//////////////////////////////////////////////////////////////////////////////
		System.out.println();
		System.out.println();

		// Phase 2
		System.out.println("Phase 2");
		System.out.println("-------");
		choix = 0;
		do {
			System.out.println();
			System.out.println("1 -> reserver un emplacement");
			System.out.println("2 -> afficher la brocante");
			System.out.println("3 -> consulter un exposant");
			System.out.println("4 -> lister tous les exposants");
			System.out.println("5 -> libérer un emplacement");
			System.out.println();
			System.out.print("Votre choix : ");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch (choix) {
				case 1:
					reserverPhase2();
					break;
				case 2:
					afficherTout();
					break;
				case 3:
					consulterExposant();
					break;
				case 4:
					listerTousLesExposants();
					break;
				case 5:
					libererEmplacement();
					break;
			}

		} while (choix >= 1 && choix <= 5);

		System.out.println("Fin de la brocante!");
	}

	private static void reserverPhase1() {
		System.out.print("Entrez le nom : ");
		String nom = scanner.nextLine();
		if (!brocante.estUnRiverain(nom)) {
			System.out.println("Seul un riverain peut réserver un emplacement pour l'instant");
			return;
		}
		System.out.print("Entrez le numero de l'emplacement : ");
		int numero = scanner.nextInt();
		scanner.skip("\n");
		if (!brocante.estLibre(numero)) {
			System.out.println("Cet emplacement n'est pas libre !");
			return;
		}
		Exposant exposant = brocante.getExposant(nom);
		if (exposant==null){
			System.out.print("Entrez l'email : ");
			String email = scanner.nextLine();
			exposant = new Exposant(nom,email);
		}
		scanner.nextLine();
		if (brocante.reserver(exposant, numero)) {
			System.out.println("L'enregistrement à bien été effectué");
		} else {
			System.out.println("Il y a eu un problème lors de l'enregistrement");
		}
	}

	private static void reserverPhase2() {
		System.out.print("Entrez le type d'emplacement que vous desirez : ");
		char type = scanner.nextLine().charAt(0);
		if (brocante.emplacementLibre(type)) {
			System.out.print("Entrez le nom : ");
			String nom = scanner.nextLine();
			Exposant exposant = brocante.getExposant(nom);
			if (exposant==null){
				System.out.print("Entrez l'email : ");
				String email = scanner.nextLine();
				exposant = new Exposant(nom,email);
			}
			brocante.attribuerAutomatiquementEmplacement(exposant,type);
		}else{
			System.out.println("Désolé, il ne reste plus d'emplacements disponibles");
		}
	}

	private static void afficherTout() {
		System.out.println("Emplacements :") ;
		System.out.println("--------------") ;
		System.out.println() ;
		System.out.println(brocante) ;
	}

	private static void consulterExposant() {
		System.out.print("Entrez le nom de l'exposant recherché : ");
		String nomExposant = scanner.nextLine();
		Exposant exposant = brocante.getExposant(nomExposant);
		if (exposant == null) {
			System.out.println("L'exposant n'existe pas !");
			return;
		}
		System.out.println(exposant);
	}

	public static void listerTousLesExposants() {
		System.out.println("Voici la liste des exposants : ");
		if (!brocante.tousLesExposants().hasNext()) {
			System.out.println("Il n'y a aucun exposants..");
			return;
		}
		Iterator<Exposant> it = brocante.tousLesExposants();
		while (it.hasNext()) {
			Exposant exposant = it.next();
			System.out.println(exposant);
		}
	}

	public static void libererEmplacement(){
		System.out.print("Entrez l'exposant qui souhaite libérer l'emplacement : ");
		String nomExposant = scanner.nextLine();
		System.out.print("Entrez le numéro de l'emplacement à libérer : ");
		int num = scanner.nextInt();
		Exposant exposant = brocante.getExposant(nomExposant);
		if (brocante.libererEmplacement(exposant,num)){
			System.out.println("L'emplacement a bien été libéré !");
		}else{
			System.out.println("L'emplacement n'a pas été libéré..");
		}

	}
}
