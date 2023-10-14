import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * Algorithme de tri : UnshuffleSort 

 * Cet algorithme de tri necessite l’utilisation d’une liste de deques.
 * Cet algorithme de tri comporte deux etapes. La premiere consiste a repartir 
 * les entiers a trier dans un nombre variable de deques. Lorsque tous
 * les entiers auront ete repartis, la deuxieme etape se chargera de remplir la
 * table a renvoyer.
 * 
 * Les 2 etapes sont basees sur le principe suivant : La liste des deques devra
 * toujours etre triee en utilisant le premier entier de chaque deque comme clef de tri.
 * Les deques aussi sont tries.
 * 
 * 
 */
public class UnshuffleSort {
	
	private LinkedList<ArrayDeque<Integer>>  listeDeDeques;

	public UnshuffleSort() {
		this.listeDeDeques = new LinkedList<ArrayDeque<Integer>>();
	}

	/**
	 * tri de la table d'entiers reçue en parametre
	 * 
	 * @param tableATrier la table a trier
	 * @return table contenant les entiers tries
	 */
	public int[] trier(int[] tableATrier) {
		remplirDeques(tableATrier);
		return viderDeques(tableATrier.length);
	}

	/**
	 * 1ere etape du tri : repartition des entiers dans des deques
	 * @param tableATrier la table a trier
	 */
	private void remplirDeques(int[] tableATrier) {
		// Pour le debug:
		System.out.println("etape1");
		for (int entier : tableATrier) {
			placerEntier(entier);
		}
	}

	public void placerEntier(int entier) {
		for (ArrayDeque<Integer> deque : listeDeDeques) {
			if (deque.getFirst() >= entier) {
				deque.addFirst(entier);
				System.out.println(listeDeDeques);
				return;
			} else if (deque.getLast() <= entier) {
				deque.addLast(entier);
				System.out.println(listeDeDeques);
				return;
			}
		}
		ArrayDeque<Integer> repartitionDeque = new ArrayDeque<Integer>();
		repartitionDeque.addFirst(entier);
		listeDeDeques.add(repartitionDeque);
		// Pour le debug:
		System.out.println(listeDeDeques);
	}
	

	/**
	 * 2eme etape du tri : on vide les deques
	 * 
	 * @param taille nombre d'entiers de la table a trier
	 * @return table contenant les entiers tries
	 */
	private int[] viderDeques(int taille) {
		// Pour le debug:
		System.out.println("etape2");

	    return null;
		// TODO

		// pour plus de lisibilite cette methode pourrait appeler les methodes suivantes :

	}
	
	private int supprimerPlusPetitEntier(){
		return 0;
	}
	
	private void reorganiserListe(){

		// Pour le debug:
		System.out.println(listeDeDeques);

		// pour plus de lisibilite cette methode pourrait appeler la methode suivante :

	}
		
	private void deplacerPremierDeque() {

	}

	// A NE PAS MODIFIER
	// VA SERVIR POUR LES TESTS
	public String toString(){
		return listeDeDeques.toString();
	}

}
