package pl.emilsroka.linkedList.end;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>{
    private Node<E> head = null;
    private Node<E> tail = null;
    private int length = 0;

    public LinkedList<E> add(E value, int index) throws IndexOutOfBoundsException{
        if(!correctInsertionPosition(index)){
            throw new IndexOutOfBoundsException();
        }

        var newNode = new Node(value);
        var previous = get(index - 1);
        insert(previous, newNode);

        handleHead(previous, newNode, index);
        handleTail(newNode, index);
        length += 1;

        return this;
    }

    public LinkedList<E> addFirst(E value){
        return add(value, 0);
    }

    public LinkedList<E> addLast(E value){
        return add(value, length);
    }

    public LinkedList<E> delete(int index) {
        if(!correctDeletionPosition(index)){
            throw new NoSuchElementException();
        }

        var previous = get(index - 1);
        var current = previous.next;

        handleHead(current, current.next, index);
        remove(previous, current);
        length -= 1;
        handleTail(previous, index);

        return this;
    }

    public LinkedList<E> deleteFirst(){
        return delete(0);
    }


    public LinkedList<E> deleteLast(){
        return delete(length - 1);
    }

    public boolean contains(E value){
        return indexOf(value) != -1;
    }

    public int indexOf(E value){
        var iterator = new LinkedListIterator();
        while(iterator.hasNext()){
            E nodeValue = iterator.next();
            if(nodeValue.equals(value)){
                return iterator.getIndex();
            }
        }
        return -1;
    }

    public String toString(){
        var stringRepresentation = new StringBuilder();
        stringRepresentation.append("[ ");
        var currentNode = head;
        while(currentNode != null){
            stringRepresentation.append(currentNode.value.toString());
            if(currentNode.next != null){
                stringRepresentation.append(" -> ");
            }
            currentNode = currentNode.next;
        }
        stringRepresentation.append(" ]");
        return stringRepresentation.toString();
    }

    private Node<E> get(int index) throws IndexOutOfBoundsException{
        if(index < -1 || index >= length){
            return null;
        } else if(index == -1){
            return new Node(null, head);
        }else if(index == length - 1){
            return tail;
        } else {
            var iterator = new LinkedListIterator();

            while(iterator.hasNext()){
                iterator.next();
                if(iterator.getIndex() == index){
                    return iterator.getNode();
                }
            }
        }

        return null; // fallback
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<E>{
        private Node<E> current = new Node(null, head);
        private int index = -1;

        public boolean hasNext() {
            return current != null && current.next != null;
        }

        public E next(){

            if(hasNext()){
                current = current.next;
                index++;
                return current.value;
            }
            return null;
        }

        private int getIndex(){
            return index;
        }

        private Node<E> getNode(){
            return current;
        }
    }


    private class Node<E>{
        public Node(E value, Node<E> next){
            this.next = next;
            this.value = value;
        }

        public Node(E value){
            this(value, null);
        }

        public Node (){
            this(null, null);
        }

        public E value;
        public Node<E> next;
    }

    /* helpers */
    private boolean correctInsertionPosition(int index){
        return index >= 0 && index <= length;
    }

    private boolean correctDeletionPosition(int index){
        return index >= 0 && index < length;
    }


    private void insert(Node predecessor, Node element){
        if(predecessor != null){
            element.next = predecessor.next;
            predecessor.next = element;
        }
    }

    private void remove(Node predecessor, Node element){
        if(predecessor != null){
            predecessor.next = element.next;
        }
        element.next = null;
    }

    private void handleHead(Node previous, Node newHead, int index){
        if( index == 0 ){
            head = newHead;
            previous.next = null; // Only due to java garbage collector
        }
    }

    private void handleTail(Node newElement, int index){
        if(index == length && length > 0){
            tail = newElement;
        }
    }
}
