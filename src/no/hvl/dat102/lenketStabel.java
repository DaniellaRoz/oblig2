package no.hvl.dat102;

import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 5.0
 *
 *      Fikset litt på av Lars-Petter Helland, januar 2024
 */

public final class lenkettabel<T> implements StabelADT<T> {
    private Node topNode;

    public lenketStabel() {
        topNode = null;
    }

    @Override
    public void push(T newEntry) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }
}
