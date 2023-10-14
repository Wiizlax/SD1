import java.util.NoSuchElementException;

public class DequeImpl<E> implements Deque<E>{

    private Object[] table;
    private int taille;
    private int indiceTete;
    private int indiceQueue;

    public DequeImpl() {
        table = new Object[4];
        taille = 0;
        indiceTete = 1;
        indiceQueue = 0;
    }

    @Override
    public int size() {
        return taille;
    }

    @Override
    public boolean estVide() {
        return taille == 0;
    }

    @Override
    public void addFirst(Object element) {
        if (indiceTete>0||indiceQueue<indiceTete){
            table[indiceTete-1] = element;
            indiceTete--;
        }
    }

    @Override
    public void addLast(Object element) {
        table[indiceQueue + 1] = element;
        taille++;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
        if (estVide()) throw new NoSuchElementException();
        Object premier = table[indiceTete];
        table[indiceTete] = null;
        taille--;
        indiceTete++;
        return (E) premier;
    }

    @Override
    public E removeLast() throws NoSuchElementException {
        if (estVide()) throw new NoSuchElementException();
        Object dernier = table[indiceQueue];
        table[indiceQueue] = null;
        taille--;
        indiceQueue--;
        return (E) dernier;
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        if (estVide()) throw new NoSuchElementException();
        return (E) table[indiceTete];
    }

    @Override
    public E getLast() throws NoSuchElementException {
        if (estVide()) throw new NoSuchElementException();
        return (E) table[indiceQueue];
    }
}
