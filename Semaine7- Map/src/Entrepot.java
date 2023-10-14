import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Simonis Tom											-------> METTEZ ICI VOS NOM ET PRENOM
 *
 *
 */

public class Entrepot {

	private HashMap<Integer, Societe> mapSociete;
	private Hangar[] tableHangars;
	private int nbrHangarsOccupe = 0;
	private EnsembleVoituresAutorisees voituresAutorisees;


	/**
	 * construit un entrepot contenant nombreHangars
	 * @param nombreHangars le nombre d'hangars
	 * @throws IllegalArgumentException si le nombre d'hangars est negatif ou nul
	 */
	public Entrepot(int nombreHangars) {
		if (nombreHangars <= 0)
			throw new IllegalArgumentException();
		mapSociete = new HashMap<>(nombreHangars);
		tableHangars = new Hangar[nombreHangars];
		voituresAutorisees = new EnsembleVoituresAutorisees();
		for (int i = 0; i < nombreHangars; i++) {
			Hangar hangar = new Hangar(i);
			tableHangars[i] = hangar;
		}
	}

	/**
	 * renvoie le nombre d'hangars libres
	 * @return le nombre d'hangars libres
	 */
	public int nombreHangarsLibres(){
		return tableHangars.length - nbrHangarsOccupe;
	}


	/**
	 * renvoie le nombre de societes presentes
	 * @return le nombre de societes presentes
	 */
	public int nombreSocietesPresentes(){
		return mapSociete.size();
	}

	/**
	 * renvoie la societe dont le numero est passe en parametre
	 * @param numeroSociete le numero de la societe
	 * @return la societe recherchee ou null si aucune societe presente ne possede ce numero
	 */
	public Societe getSociete(int numeroSociete){
		return mapSociete.get(numeroSociete);
	}

	/**
	 * attribue un hangar a la societe passee en parametre
	 * Si l'attribution a pu se faire :
	 * la societe est enregistree comme presente (si pas encore presente)
	 * le hangar lui est ajoute
	 * Pour une meilleure repartition des hangars occupes dans l'entrepot,
	 * veuillez suivre les indications donnees dans l'enonce!
	 * @param numeroSociete le numero de la societe
	 * @param nomSociete le nom de la societe
	 * @return le numero du hangar attribue ou -1 s'il n'y en a plus de libre
	 */
	public int attribuerHangar(int numeroSociete, String nomSociete) {
		/*if (tableHangars.length == nbrHangarsOccupe) return -1;
		Societe societe = mapSociete.get(numeroSociete);
		if (societe == null) {
			Societe societeAAjt = new Societe(numeroSociete, nomSociete);
			mapSociete.put(numeroSociete, societeAAjt);
			societe = societeAAjt;
		}
		if (numeroSociete <= tableHangars.length - 1) {
			if (tableHangars[numeroSociete].getSociete() == null) {
				tableHangars[numeroSociete].setSociete(societe);
				societe.ajouterHangar(numeroSociete);
				nbrHangarsOccupe++;
				return numeroSociete;
			}
		}
		for (int i = numeroSociete; i <= tableHangars.length; i++) {
			if (i == tableHangars.length) {
				i = 0;
			}
			if (tableHangars[i].getSociete() == null) {
				tableHangars[i].setSociete(societe);
				societe.ajouterHangar(i);
				nbrHangarsOccupe++;
				return i;
			}
			if (i == numeroSociete - 1) return -1;
		}
		return -1;
	}*/
		if (!verifHangarDispo()) return -1;
		Societe societeTemp = new Societe(numeroSociete, nomSociete);
		if (!mapSociete.containsKey(numeroSociete))
			mapSociete.put(numeroSociete, societeTemp);
		int numeroHangar;
		if (numeroSociete >= tableHangars.length) numeroHangar = numeroSociete % tableHangars.length;
		else numeroHangar = numeroSociete;
		while (tableHangars[numeroHangar].getSociete() != null) {
			if (numeroHangar >= tableHangars.length - 1) numeroHangar = -1;
			numeroHangar++;
		}
		mapSociete.get(numeroSociete).ajouterHangar(numeroHangar);
		tableHangars[numeroHangar].setSociete(societeTemp);
		nbrHangarsOccupe++;
		return numeroHangar;
	}

	public boolean verifHangarDispo() {
		for (int i = 0 ; i < tableHangars.length-1 ; i++) {
			if (tableHangars[i].getSociete() == null) return true;
		}
		return false;
	}

	public boolean libererHangar(int numeroHangar) {
		if (tableHangars[numeroHangar].getSociete() == null) return false;
		tableHangars[numeroHangar].setSociete(null);
		nbrHangarsOccupe--;
		return true;
	}

	public boolean hangarVide(int numeroHangar){
		return tableHangars[numeroHangar].getSociete() == null;
	}


	/**
	 * @param plaque la plaque a vérifier
	 * @return true si elle est comprise dans les voitures autorisées, false sinon
	 */
	public boolean estAutorise(String plaque){
		return voituresAutorisees.voitureAutorisee(plaque);
	}

	/**
	 * @param plaque la plaque de la voiture a ajouter aux voitures autorisées
	 * @return true si elle n'y etait pas deja, false sinon
	 */
	private boolean ajouterVehicule(String plaque){
		if (voituresAutorisees.voitureAutorisee(plaque)) return false;
		Proprietaire proprio = new Proprietaire("");
		voituresAutorisees.ajouterVoiture(plaque,proprio);
		return true;
	}

	@Override
	public String toString() {
		return "Entrepot{" +
				"tableHangars=" + Arrays.toString(tableHangars) +
				'}';
	}
}
