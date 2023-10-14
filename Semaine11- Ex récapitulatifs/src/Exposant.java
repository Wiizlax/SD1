
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class Exposant {
    private String nom;
    private String email;
    private LinkedList<Emplacement> emplacements;

    public Exposant(String nom, String email) {
        this.nom = nom;
        this.email = email;
        emplacements = new LinkedList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exposant exposant = (Exposant) o;
        return nom.equals(exposant.nom) && email.equals(exposant.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, email);
    }


    @Override
    public String toString() {
        return "nom : " + nom + " | "+ "mail : " + email;
    }

    public Iterator<Emplacement> tousLesEmplacements(){
        return emplacements.iterator();
    }

    public int nbrEmplacements() {
        return emplacements.size();
    }

    public boolean ajouterEmplacement(Emplacement emplacement) {
        if (contientEmplacement(emplacement)) return false;
        return emplacements.add(emplacement);
    }

    public boolean enleverEmplacement(Emplacement emplacement) {
        return emplacements.remove(emplacement);
    }

    public boolean contientEmplacement(Emplacement emplacement) {
        return emplacements.contains(emplacement);
    }

}

