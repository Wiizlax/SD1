
// Author : Tom Simonis

public class RallyeAutomobile {

    private ListeSDImpl<String> listePilotes;
    private String[] classement;
    int nbrFinalistes = 0;

    public RallyeAutomobile (String[] lesPilotes){
        listePilotes = new ListeSDImpl<>();
        for (String pilote : lesPilotes) {
            listePilotes.insererEnQueue(pilote);
        }
        classement = new String[listePilotes.taille()];
    }


    /**
     * @return le pilote en tete de course
     */
    public String piloteTete(){
        return listePilotes.premier();
    }


    /**
     * @return affiche la course
     */
    public String afficherCourse (){
        return listePilotes.toString();
    }


    /**
     * @param pilote le pilote qui dépasse
     */
    public void enregistrerDepassement(String pilote) {
        listePilotes.permuter(listePilotes.donnerPrecedent(pilote), pilote);
    }

    public boolean contient (String pilote){
        return listePilotes.contient(pilote);
    }


    /**
     * @param pilote le pilote a supprimer de la liste
     * @return false si le pilote n'etait pas present, true sinon
     */
    public boolean supprimerPilote(String pilote) {
        if (!listePilotes.contient(pilote)) return false;
        listePilotes.supprimer(pilote);
        return true;
    }


    /**
     * @param pilote le pilote recherche
     * @return la position du pilote, -1 si il n'est pas présent dans la course
     */
    public int donnerPositionPilote(String pilote) {
        int position = 1;
        for (String p : listePilotes) {
            if (pilote.equals(p)) {
                return position;
            }
            position++;
        }
        return -1;
    }

    /**
     * @param pilote le pilote a remettre dans la course
     * @param pilotePrec le pilote avant le pilote a inserer
     * @return false si le pilote est deja present ou si le pilote avant n'existe pas; true sinon
     */
    public boolean remettreEnCourse(String pilote, String pilotePrec) {
        if ( listePilotes.contient(pilote) || !listePilotes.contient(pilotePrec)) return false;
        listePilotes.insererApres(pilotePrec, pilote);
        return true;
    }

    /**
     * supprime le pilote en tete
     */
    public void faireFranchirLigneArrivee(){
        String premier = piloteTete();
        listePilotes.supprimer(listePilotes.premier());
        classement[nbrFinalistes] = premier;
        nbrFinalistes++;
    }

    public String afficherClassement(){
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < classement.length ; i++) {
            result.append(i).append(" - ").append(classement[i - 1]).append("\n");
        }
        return result.toString();
    }

}
