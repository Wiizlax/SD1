import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Brocante {

    private int phase = 1;

    private Emplacement[] tableEmplacements;
    private HashMap<String, Integer> mapRiverains;
    private HashMap<String,Exposant> mapExposants;
    private HashMap<Character,ArrayDeque<Emplacement>> mapEmplacementsLibres;

    //private String tableRiverains[] 
    //inutile, regardez bien les schemas, cette table n'apparait pas !


    public int getPhase() {
        return phase;
    }

    /**
     * initialise une brocante avec nombre emplacements
     * @param tableRiverains la table des riverains
     * @throws IllegalArgumentException si le nombre d'emplacements est negatif ou nul ou si la table des riverains est null
     */
    public Brocante(char[] typesEmpl, String[] tableRiverains) {
        if (typesEmpl == null) throw new IllegalArgumentException();
        if (tableRiverains == null) throw new IllegalArgumentException();
        tableEmplacements = new Emplacement[typesEmpl.length];
        mapRiverains = new HashMap<>();
        mapExposants = new HashMap<>();
        mapEmplacementsLibres = new HashMap<>();
        for (int i = 0; i < tableRiverains.length; i++) {
            mapRiverains.put(tableRiverains[i], 0);
        }
        for (int i = 0; i < typesEmpl.length; i++) {
            tableEmplacements[i] = new Emplacement(i,typesEmpl[i]);
        }
    }

    /**
     * reserve l'emplacement qui porte le numero passe en parametre au demandeur passe en parametre
     * La reservation reussit si
     *     l'emplacement est libre
     *     le demandeur est bien un riverain
     *     le riverain n'a pas encore 3 emplacements
     * @param demandeur le riverain qui demande un emplacement
     * @param numeroEmplacement le numero de l'emplacement souhaite
     * @return true si la reservation a reussi, false sinon
     * @throws IllegalArgumentException si le numero de l'emplacement n'existe pas
     * @throws IllegalStateException si on n'est pas en phase 1
     */
    public boolean reserver(Exposant demandeur, int numeroEmplacement) {
        if (phase != 1) throw new IllegalStateException();
        if (numeroEmplacement < 0 || numeroEmplacement >= tableEmplacements.length)
            throw new IllegalArgumentException();
        if (tableEmplacements[numeroEmplacement].getExposant() != null) return false;
        if (!estUnRiverain(demandeur.getNom())) return false;
        if (!mapExposants.containsKey(demandeur.getNom())){
            mapExposants.put(demandeur.getNom(),demandeur);
        }
        if (mapRiverains.get(demandeur.getNom()) == 3) return false;
        tableEmplacements[numeroEmplacement].setExposant(demandeur);
        demandeur.ajouterEmplacement(tableEmplacements[numeroEmplacement]);
        Integer nombreEmplacements = mapRiverains.get(demandeur.getNom());
        mapRiverains.put(demandeur.getNom(), ++nombreEmplacements);
        return true;
    }
        //Attention pour augmenter le nombre d'emplacements
        //Solution ko:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //mapRiverains.put(demandeur, nombreEmplacements++);
        //Solutions ok:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //mapRiverains.put(demandeur, ++nombreEmplacements);
        //ou:
        //Integer nombreEmplacements = mapRiverains.get(demandeur);
        //nombreEmplacements++;
        //mapRiverains.put(demandeur, nombreEmplacements);


    /**
     * attribue automatiquement un emmplacement libre au demandeur passe en parametre
     * @param demandeur le demandeur d'un emplacement
     * @return le numero de l'emplacement attribue ou -1 si plus d'emplacement libre
     * @throws IllegalStateException si on n'est pas en phase 2
     */
    public int attribuerAutomatiquementEmplacement(Exposant demandeur, char type) {
        if (phase != 2) throw new IllegalStateException();
        if (mapEmplacementsLibres.isEmpty()) return -1;
        if (!mapExposants.containsKey(demandeur.getNom())){
            mapExposants.put(demandeur.getNom(),demandeur);
        }
        int emplacement = mapEmplacementsLibres.get(type).remove().getNumero();
        tableEmplacements[emplacement].setExposant(demandeur);
        demandeur.ajouterEmplacement(tableEmplacements[emplacement]);
        return emplacement;
    }

    public boolean libererEmplacement(Exposant exposant, int numEmplacement) {
        if (tableEmplacements[numEmplacement].getExposant() == null) return false;
        if (!mapExposants.containsKey(exposant.getNom())) return false;
        if (!exposant.contientEmplacement(tableEmplacements[numEmplacement])) return false;
        tableEmplacements[numEmplacement].setExposant(null);
        exposant.enleverEmplacement(tableEmplacements[numEmplacement]);
        if (mapRiverains.containsKey(exposant.getNom())) {
            Integer nombreEmplacements = mapRiverains.get(exposant.getNom());
            nombreEmplacements--;
            mapRiverains.put(exposant.getNom(), nombreEmplacements);
        }
        return true;
    }


    /**
     * a comme effet de passer de la phase 1 a la phase 2
     * si deja en phase 2, rien ne doit etre fait
     */
    public void changerPhase() {
        if (phase != 1) return;
        phase = 2;
        for (int i = 0; i < tableEmplacements.length; i++) {
            if (!mapEmplacementsLibres.containsKey(tableEmplacements[i].getTypeEmpl())) {
                mapEmplacementsLibres.put(tableEmplacements[i].getTypeEmpl(), new ArrayDeque<>());
            }
            if (tableEmplacements[i].getExposant() == null) {
                mapEmplacementsLibres.get(tableEmplacements[i].getTypeEmpl()).push(tableEmplacements[i]);
            }
        }
    }

    @Override
    public String toString() {
        String aRenvoyer="";
        for (int i = 0; i < tableEmplacements.length; i++) {
            if(tableEmplacements[i].getExposant()==null){
                aRenvoyer +=  ("\n"+i+" : /");
            }else{
                aRenvoyer +=  ("\n"+i+" : "+tableEmplacements[i].getExposant() + " | type emplacement : " + tableEmplacements[i].getTypeEmpl());
            }
        }
        return aRenvoyer;
    }

    //Pour le debug
    public String donnerTableEmplacements() {
        if(tableEmplacements==null)
            return "null";
        return Arrays.toString(tableEmplacements);
    }

    //Pour le debug
    public String donnerMapRiverains() {
        if(mapRiverains==null)
            return "null";
        return mapRiverains.toString();
    }

    //Pour le debug
    public String donnerPileEmplacementsLibres() {
        if(mapEmplacementsLibres ==null)
            return "null";
        return mapEmplacementsLibres.toString();
    }

    public boolean estUnRiverain(String nom){
        return mapRiverains.containsKey(nom);
    }

    public boolean estLibre( int numEmplacement){
        return tableEmplacements[numEmplacement].getExposant() == null;
    }

    public boolean emplacementLibre(char type) {
        for (Emplacement tableEmplacement : tableEmplacements) {
            if (tableEmplacement.getTypeEmpl() == type) {
                return true;
            }
        }
        return false;
    }

    public Exposant getExposant(String nom) {
        return mapExposants.get(nom);
    }

    public Iterator<Exposant> tousLesExposants(){
        return mapExposants.values().iterator();
    }
  
}
