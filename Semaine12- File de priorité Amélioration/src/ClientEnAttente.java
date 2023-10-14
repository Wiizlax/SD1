public class ClientEnAttente {

    private Client client;
    private int numArrivee;

    public ClientEnAttente(Client client, int numArrivee) {
        if (client == null) throw new IllegalArgumentException();
        this.client = client;
        this.numArrivee = numArrivee;
    }

    public Client getClient() {
        return client;
    }

    public  int getNumArrivee() {
        return numArrivee;
    }

    @Override
    public String toString() {
        return client.getNom();
    }
}
