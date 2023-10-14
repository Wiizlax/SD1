import java.util.ArrayList;

public class EnsembleTrieImpl<E> implements EnsembleTrie<E> {

	private Noeud racine;
	private int taille;

	public EnsembleTrieImpl() {
		racine = null;
		taille = 0;
	}

	public boolean estVide() {
		return racine == null;
	}

	public int taille() {
		return taille;
	}

	public E min() {
		if (estVide()) return null;
		Noeud baladeur = racine;
		while (baladeur.gauche != null)
			baladeur = baladeur.gauche;
		return baladeur.element;

	}

	public boolean contient(E element) {
		if (estVide()) return false;
		Noeud baladeur = racine;
		while (baladeur != null) {
			if (baladeur.element == element) return true;
			//si le baladeur est inf a l'element passe en parametres
			if (((Comparable<E>) baladeur.element).compareTo(element) < 0) {
				if (baladeur.droit == null) return false;
				baladeur = baladeur.droit;
			}
			//si le baladeur est sup a l'element passe en parametres
			if (((Comparable<E>) baladeur.element).compareTo(element) > 0) {
				if (baladeur.gauche == null) return false;
				baladeur = baladeur.gauche;
			}
		}
		//si il ne l'a pas trouve return false
		return false;
	}

	public boolean ajouter(E element) {
		if (estVide()) {
			racine = new Noeud(null, element, null);
			taille++;
			return true;
		}
		Noeud baladeur = racine;
		while (baladeur != null) {
			if (((Comparable<E>) baladeur.element).compareTo(element) < 0) {
				if (baladeur.droit == null) {
					baladeur.droit = new Noeud(null, element, null);
					taille++;
					return true;
				} else {
					baladeur = baladeur.droit;
				}
			}
			if (((Comparable<E>) baladeur.element).compareTo(element) > 0) {
				if (baladeur.gauche == null) {
					baladeur.gauche = new Noeud(null, element, null);
					taille++;
					return true;
				} else {
					baladeur = baladeur.gauche;
				}
			}
		}
		return false;
	}

	public E predecesseur(E element) {
		//TODO
		// Suggestion :
		// remplir une "arrayList" via un parcours en in-ordre
		// le predecesseur de l'element est l'element qui se trouve juste avant lui dans l'arrayList !
		// cette methode peut donc appeler la methode "private" remplirListe() qui suit
		return null;
	}

	private void remplirListe(Noeud noeud,ArrayList<E> liste) {
		// suggestion de methode a appeler par la methode predecesseur()

	}

	// A NE PAS MODIFIER!!!
	// VA SERVIR POUR LES TESTS!!!
	public String toString() {
		return "[ " + toString(racine) + " ]";
	}

	private String toString(Noeud n) {
		if (n == null)
			return "";
		if (n.gauche == null && n.droit == null)
			return "" + n.element;
		if (n.gauche == null)
			return " [ ] " + n.element + " [ " + toString(n.droit) + " ] ";
		if (n.droit == null)
			return " [ " + toString(n.gauche) + " ] " + n.element + " [ ] ";
		return " [ " + toString(n.gauche) + " ] " + n.element + " [ " + toString(n.droit) + " ] ";
	}

	// A NE PAS MODIFIER! VA SERVIR POUR LES TESTS
	// permet de construire l'ensembleTrie de l'enonce

	public EnsembleTrieImpl(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {
		Noeud nG = new Noeud(null, e2, new Noeud(e5));
		Noeud nG1 = new Noeud(new Noeud(e7), e4, new Noeud(e6));
		Noeud nD = new Noeud(nG1, e3, null);
		racine = new Noeud(nG, e1, nD);
		taille = 7;
	}


	public class Noeud {

		private E element;
		private Noeud gauche;
		private Noeud droit;

		private Noeud(E element) {
			this.element = element;
			this.gauche = null;
			this.droit = null;
		}

		private Noeud(Noeud gauche, E element, Noeud droit) {
			this.element = element;
			this.gauche = gauche;
			this.droit = droit;
		}
	}

}
