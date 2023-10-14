import java.util.Arrays;

// implementation de l'interface File via une table circulaire

/**
 * @author 
 *
 */

public class FileImplViaTable<E> implements File<E>{

	private Object[] table;  // ne modifiez pas cet identifiant, la classe test l'utilise					
	private int indiceTete;  // ne modifiez pas cet identifiant, la classe test l'utilise			
	private int taille;		// ne modifiez pas cet identifiant, la classe test l'utilise	
	// N'ajoutez pas d'autres attributs, la classe test risquerait de ne pas fonctionner
	

	public FileImplViaTable(){
		table = new Object[4];
		taille = 0;
		indiceTete = 0;
	}
	

	public boolean estVide(){
		return taille == 0;
	}


	public int taille(){
		return taille;
	}

	public E premier() throws FileVideException {
		if (estVide()) throw new FileVideException();
		return (E) table[indiceTete];
	}


	public E defile() throws FileVideException {
		if (estVide()) throw new FileVideException();
		Object objet = table[indiceTete];
		table[indiceTete] = null;
		indiceTete++;
		if (indiceTete > table.length - 1) {
			indiceTete = 0;
		}
		taille--;
		return (E) objet;
	}


	public void enfile(E element) {
		if (taille == table.length) {
			Object[] tab2 = new Object[table.length * 2];
			for (int i = 0; i < table.length; i++) {
				tab2[i] = table[indiceTete];
				indiceTete++;
				if (indiceTete > table.length - 1) {
					indiceTete = 0;
				}
			}
			table = tab2;
			indiceTete = 0;
		}
		table[(indiceTete + taille) % table.length] = element;
		taille++;
	}

} 
