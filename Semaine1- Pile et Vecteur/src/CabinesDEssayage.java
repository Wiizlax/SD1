/**
 * @author 
 *
 */
public class CabinesDEssayage {
	
	private Pile<Integer> pileNumerosCabinesLibres;  // cette pile d'entiers contient les numeros des cabines libres
	private boolean[] tableOccupations;              // attention la numerotation des cabines commence a 1
												     // si tableOccupations[0] contient TRUE --> la cabine 1 est occupee
												     // ...
	// N'ajoutez pas d'autres attributs!
		
	
	/**
	 * construit nombreCabines libres
	 * La numerotation des cabines commence a 1
	 * @param nombreCabines : le nombre de cabines d'essayage
	 * @throws IllegalArgumentException si le nombre de cabines est negatif ou nul
	 */
	public CabinesDEssayage(int nombreCabines) {
		if (nombreCabines <= 0)
			throw new IllegalArgumentException();
		pileNumerosCabinesLibres = new PileImpl<Integer>();
		tableOccupations = new boolean[nombreCabines];
		for (int i = 1; i <= nombreCabines ; i++) {
			pileNumerosCabinesLibres.push(i);
		}
	}
	
	/**
	 * renvoie le nombre de cabines libres
	 * @return le nombre de cabines libres
	 */
	public int nombreCabinesLibres(){
		int nbrLibre = 0;
		for (int i = 0; i < tableOccupations.length ; i++) {
			if (!tableOccupations[i]){
				nbrLibre++;
			}
		}
		return nbrLibre;
	}
	
	/**
	 * attribue une cabine libre
	 * @return le numero d'une cabine libre ou -1 si toutes les cabines sont occupees
	 */
	public int attribuerCabineLibre(){
		if (nombreCabinesLibres()==0) return -1;
		int num = pileNumerosCabinesLibres.sommet();
		pileNumerosCabinesLibres.pop();
		tableOccupations[num-1] = true;
		return num;
	}
	
	/**
	 * libere une cabine
	 * @param numeroCabine le numero de la cabine a liberer
	 * @throws IllegalArgumentException si aucune cabine porte ce numero
	 * @throws IllegalStateException si la cabine a liberer n'est pas occupee
	 */
	public void libererCabine(int numeroCabine) throws IllegalArgumentException, IllegalStateException {
		if (numeroCabine <= 0 || numeroCabine >= tableOccupations.length) throw new IllegalArgumentException();
		if (!tableOccupations[numeroCabine - 1]) throw new IllegalStateException();
		pileNumerosCabinesLibres.push(numeroCabine);
		tableOccupations[numeroCabine - 1] = false;
	}
}

