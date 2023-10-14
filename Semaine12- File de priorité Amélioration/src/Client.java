import java.util.Objects;

public class Client {

    private String nom;
    private int priorite;

    public Client(String nom, int priorite) {
        if (nom==null||nom.isEmpty()) throw new IllegalArgumentException();
        this.nom = nom;
        if (priorite<0) throw new IllegalArgumentException();
        this.priorite = priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public String getNom() {
        return nom;
    }

    public int getPriorite() {
        return priorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(nom, client.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", priorite=" + priorite +
                '}';
    }
}
