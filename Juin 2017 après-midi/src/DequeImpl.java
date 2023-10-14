import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

// METTEZ ICI VOTRE NOM ET VOTRE PRENOM!!!!

public class DequeImpl<E> implements Deque<E>{

	private Object[] table;
	private int indicePremier; 
	private int indiceDernier;
	private int taille;
	private int numVersion = 0; // pour l'iterateur

	public DequeImpl(){
		table = new Object[5];
		indicePremier = 0;
		indiceDernier = 0;
		taille = 0;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public DequeImpl(Object[] tableARecopier){
		if(tableARecopier==null)
			throw new IllegalArgumentException();
		table = new Object[tableARecopier.length+4];
		for (int i = 0; i < tableARecopier.length; i++) {
			table[i+2]=tableARecopier[i];
		}
		indicePremier = 2;
		indiceDernier = indicePremier+tableARecopier.length-1;
		taille = tableARecopier.length;
	}

	// Va servir pour les tests 
	// A ne pas modifier!!!
	public String toString(){
		String aRenvoyer = "";
		for (int i = indicePremier; i <= indiceDernier; i++) {
			aRenvoyer += " "+table[i];
		}
		return aRenvoyer;
	}

	@Override
	public boolean estVide() {
		// TODO Auto-generated method stub
		return taille==0;
	}

	@Override
	public int taille() {
		// TODO Auto-generated method stub
		return taille;
	}

	private void agrandirTable() {
		Object[] nvTable = new Object[taille * 2];
		for (int i = 0; i < table.length; i++) {
			nvTable[table.length / 4 + i] = table[i];
		}
	}

	@Override
	public void ajouterEnPremier(E element) {
		// TODO Auto-generated method stub
		numVersion++;
	}


	@Override
	public void ajouterEnDernier(E element) {
		// TODO Auto-generated method stub
		numVersion++;
	}

	@Override
	public E premier() {
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public E dernier(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E supprimerPremier(){
		// TODO Auto-generated method stub
		numVersion++;
		return null;
	}

	@Override
	public E supprimerDernier() {
		// TODO Auto-generated method stub
		numVersion++;
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new IterateurImpl<E>();
	}

	private class IterateurImpl<E> implements Iterator<E>{

		private int indiceCourant;
		private int version;

		private IterateurImpl() {
			indiceCourant = indicePremier;
			version = numVersion;
		}

		@Override
		public boolean hasNext() {
			// TODO
			return false;
		}

		@Override
		public E next() {
			// TODO
			return null;
		}

		@Override
		// A NE PAS COMPLETER : Les suppressions sont interdites
		public void remove() {
			throw new UnsupportedOperationException();			
		}

	}
}
