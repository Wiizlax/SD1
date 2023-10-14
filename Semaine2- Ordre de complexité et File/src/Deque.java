import java.util.NoSuchElementException;

public interface Deque <E>{


    /**
     * renvoie le nombre d'elements qui se trouvent sur la Deque
     * @return le nombre d'éléments
     */
    public int size();


    /**
     * @return true si la Deque est vide, false sinon
     */
    public boolean estVide();


    /**
     * Ajoute sans aucune conditions l'element en debut de Deque
     * @param element element a ajouter
     */
    public void addFirst(E element);


    /**
     * Ajoute sans aucune conditions l'element en fin de Deque
     * @param element l'élément ajouté en fin de Deque
     */
    public void addLast(E element);


    /**
     * renvoie l'element qui se trouve en tete de la Deque et l'enleve
     * @return l'element supprime
     * @throws NoSuchElementException si la Deque est vide
     */
    public E removeFirst() throws NoSuchElementException;

    /**
     * renvoie l'element qui se trouve en fin de la Deque et l'enleve
     * @return l'element supprime
     * @throws NoSuchElementException si la deque est vide
     */
    public E removeLast() throws NoSuchElementException;

    /**
     * renvoie l'element qui se trouve en tete de la Deque sans l'enlever
     * @return l'element en tete de la Deque
     * @throws NoSuchElementException si la Deque est vide
     */
    public E getFirst() throws NoSuchElementException;

    /**
     * renvoie l'element qui se trouve en fin de la Deque sans l'enlever
     * @return l'element en fin de deque
     * @throws NoSuchElementException si la deque est vide
     */
    public E getLast() throws NoSuchElementException;
}
