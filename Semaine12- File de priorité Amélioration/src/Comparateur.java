import java.util.Comparator;

public class Comparateur implements Comparator<ClientEnAttente> {

    @Override
    public int compare(ClientEnAttente o1, ClientEnAttente o2) {
        if (o1.getClient().getPriorite() > o2.getClient().getPriorite())
            return -1;
        if (o1.getClient().getPriorite() < o2.getClient().getPriorite())
            return 1;
        if (o1.getClient().getPriorite() == o2.getClient().getPriorite()) {
            if (o1.getNumArrivee() < o2.getNumArrivee()) {
                return -1;
            } else if (o1.getNumArrivee() > o2.getNumArrivee()) {
                return 1;
            }
        }
        return 0;
    }
    //return Integer.compare(o2.getClient().getPriorite(), o1.getClient().getPriorite());

}
