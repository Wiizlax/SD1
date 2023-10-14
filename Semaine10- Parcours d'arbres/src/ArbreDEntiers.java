import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArbreDEntiers implements Iterable<Integer>{
	protected NoeudEntier racine; 
	protected int taille;
	
	public ArbreDEntiers () {
		this.racine=null ;
		this.taille=0;
	}

	public ArbreDEntiers (int entier) {
		this.racine=new NoeudEntier(entier) ;
		this.taille=1;
	}
	
	public ArbreDEntiers (ArbreDEntiers gauche, int entier, ArbreDEntiers droit) {
		this.racine=new NoeudEntier(gauche.racine,entier,droit.racine) ;
		this.taille= 1 + gauche.taille + droit.taille;
	}

	public boolean estVide () {
		return  this.racine == null;
	}

	public int taille () {
		return taille;
	}

	public Iterator<Integer> preIterateur () {
		return new PreIterateur();
	}

	//Cet 2 iterateur s’implemente de façon similaire à l’iterateur en pre-ordre.
	public Iterator<Integer> postIterateur () {
		return new PostIterateur();
	}

	// iterateur in ordre
	// Cet iterateur a ete selectionne comme iterateur par defaut
	public Iterator<Integer> iterator () {
		return new InIterateur();
	}
	
	public Iterator<Integer> iterateurParNiveau () {
		return new NivIterateur(this);
	}
	
	
	protected class NoeudEntier {
		protected int entier; 
		protected NoeudEntier gauche;
		protected NoeudEntier droit;

		private NoeudEntier (int entier) {
			this.entier = entier;
			this.gauche = null;
			this.droit = null;
		}
		
		private NoeudEntier (NoeudEntier g,int entier,NoeudEntier d) {
			this.entier = entier;
			this.gauche = g;
			this.droit = d;
		}
	}
	
	private class PreIterateur implements Iterator<Integer> {
		
		private ArrayDeque<Integer> fileDEntiers;
		
		public PreIterateur () {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			remplirFilePre(racine);
			//La classe PreIterator possede un attribut : une file d’entiers (ArrayDeque<Integer>).
			//Le constructeur de la classe va s’occuper de remplir cette file avec tous les entiers contenus dans l’arbre.
			//Il construit la file et appelle la methode recursive remplirFile()
		}

		//C’est la methode remplirFile() qui se charge de remplir la file.
		//Il s’agit d’une methode recursive !
		//Le but de cet iterateur est de parcourir l’arbre en pre-ordre !
		//Il faut donc « enfiler » les objets dans la file de façon a respecter ce parcours.
		private void remplirFilePre(NoeudEntier n) {
			if (n == null) return;
			fileDEntiers.addLast(n.entier);
			remplirFilePre(n.gauche);
			remplirFilePre(n.droit);
		}

		//cette methode verifie si la file est non vide.
		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		//cette methode "defile"
		public Integer next () {
			return fileDEntiers.removeFirst();
		}
	}

	private class PostIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public PostIterateur () {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			remplirFilePost(racine);
			//La classe PreIterator possede un attribut : une file d’entiers (ArrayDeque<Integer>).
			//Le constructeur de la classe va s’occuper de remplir cette file avec tous les entiers contenus dans l’arbre.
			//Il construit la file et appelle la methode recursive remplirFile()
		}

		//C’est la methode remplirFile() qui se charge de remplir la file.
		//Il s’agit d’une methode recursive !
		//Le but de cet iterateur est de parcourir l’arbre en post-ordre !
		//Il faut donc « enfiler » les objets dans la file de façon a respecter ce parcours.
		private void remplirFilePost(NoeudEntier n) {
			if (n == null) return;
			remplirFilePost(n.gauche);
			remplirFilePost(n.droit);
			fileDEntiers.addLast(n.entier);
		}

		//cette methode verifie si la file est non vide.
		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		//cette methode "defile"
		public Integer next () {
			return fileDEntiers.removeFirst();
		}
	}

	private class InIterateur implements Iterator<Integer> {

		private ArrayDeque<Integer> fileDEntiers;

		public InIterateur () {
			fileDEntiers = new ArrayDeque<Integer>(taille);
			remplirFileIn(racine);
			//La classe PreIterator possede un attribut : une file d’entiers (ArrayDeque<Integer>).
			//Le constructeur de la classe va s’occuper de remplir cette file avec tous les entiers contenus dans l’arbre.
			//Il construit la file et appelle la methode recursive remplirFile()
		}

		//C’est la methode remplirFile() qui se charge de remplir la file.
		//Il s’agit d’une methode recursive !
		//Le but de cet iterateur est de parcourir l’arbre en post-ordre !
		//Il faut donc « enfiler » les objets dans la file de façon a respecter ce parcours.
		private void remplirFileIn(NoeudEntier n) {
			if (n == null) return;
			remplirFileIn(n.gauche);
			fileDEntiers.addLast(n.entier);
			remplirFileIn(n.droit);
		}

		//cette methode verifie si la file est non vide.
		public boolean hasNext () {
			return !fileDEntiers.isEmpty();
		}

		//cette methode "defile"
		public Integer next () {
			return fileDEntiers.removeFirst();
		}
	}

	private class NivIterateur implements Iterator<Integer> {

		private ArrayDeque<NoeudEntier> fileNoeuds;

		public NivIterateur(ArbreDEntiers a) {
			fileNoeuds = new ArrayDeque<>(taille);
			if (!a.estVide()) fileNoeuds.add(a.racine);
			//La classe PreIterator possede un attribut : une file d’entiers (ArrayDeque<Integer>).
			//Le constructeur de la classe va s’occuper de remplir cette file avec tous les entiers contenus dans l’arbre.
			//Il construit la file et appelle la methode recursive remplirFile()
		}

		public boolean hasNext() {
			return !fileNoeuds.isEmpty();
		}

		//cette methode "defile"
		public Integer next() {
			if (hasNext()) {
				NoeudEntier noeud = fileNoeuds.removeFirst();
				if (noeud.gauche != null) {
					fileNoeuds.addLast(noeud.gauche);
				}
				if (noeud.droit != null) {
					fileNoeuds.addLast(noeud.droit);
				}
				return noeud.entier;
			} else throw new NoSuchElementException();
		}
	}

}
