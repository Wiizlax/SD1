
public class DrapeauBelge {
	
	private NoeudCouleur premierNoir;	
	private NoeudCouleur dernierJaune;
	// NE PAS AJOUTER D'AUTRES ATTRIBUTS!!!
	
	/**
	 * construit une chaine contenant 3 noeuds avec les caracteres 'n', 'j' et 'r' (dans cet ordre)
	 */
	public DrapeauBelge() {
		NoeudCouleur noeudNoir = new NoeudCouleur('n');
		NoeudCouleur noeudJaune = new NoeudCouleur('j');
		NoeudCouleur noeudRouge = new NoeudCouleur('r');
		premierNoir = noeudNoir;
		dernierJaune = noeudJaune;
		premierNoir.suivant = dernierJaune;
		dernierJaune.suivant = noeudRouge;
	}

	/**
	 * ajoute un noeud avec la couleur passee en parametre dans la chaine
	 * La chaine doit respecter les couleurs du  drapeau belge : noir/jaune/rouge
	 * @param couleur un caractere representant une couleur du drapeau belge : 'n', 'j' ou 'r'
	 * @throws IllegalArgumentException si le caractere ne correspond pas a un des 3 caracteres : 'n', 'j' ou 'r'
	 */
	public void ajouter(char couleur) {
		if (couleur != 'n' && couleur != 'j' && couleur != 'r') throw new IllegalArgumentException();
		NoeudCouleur noeud = new NoeudCouleur(couleur);
		if (couleur == 'n') {
			noeud.suivant = premierNoir.suivant;
			premierNoir.suivant = noeud;
		} else if (couleur == 'j') {
			noeud.suivant = dernierJaune.suivant;
			dernierJaune.suivant = noeud;
			dernierJaune = noeud;
		} else {
			noeud.suivant = dernierJaune.suivant;
			dernierJaune.suivant = noeud;
		}
	}

	// A NE PAS MODIFIER. VA SERVIR POUR LES TESTS
	public String toString(){
		String drapeau="";
		NoeudCouleur baladeur = premierNoir;
		int cpt = 0;
		while(baladeur!=null){
			cpt++;
			if(cpt==100){
				return "boucle infinie dans toString(), chainage a verifier";
			}
			drapeau+=baladeur.couleur;
			baladeur = baladeur.suivant;
		}
		return drapeau;
	}
	
	private class NoeudCouleur{
		
		private char couleur;
		private NoeudCouleur suivant;
		
		private NoeudCouleur(char couleur){
			this.couleur = couleur;
			this.suivant = null;
		}
		
		private NoeudCouleur(char couleur, NoeudCouleur suivant){
			this.couleur = couleur;
			this.suivant = suivant;
		}

	}
}
