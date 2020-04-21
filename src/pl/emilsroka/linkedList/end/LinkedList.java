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

        var siblings = getNewSiblings(index);
        var previous = (Node<E>)siblings[0];
        var next = (Node<E>)siblings[1];
        var newNode = new Node<>(value);

        insert(previous, newNode, next);

        handleHead(newNode, index);
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

        var siblings = getSiblings(index);
        var previous = (Node<E>)siblings[0];
        var current = (Node<E>)siblings[1];
        var next = (Node<E>)siblings[2];

        remove(previous, current);

        handleHead(next, index);
        handleTail(previous, index+1);
        length -= 1;

        return this;
    }

    public LinkedList<E> deleteFirst(){
        return delete(0);
    }

    public LinkedList<E> deleteLast(){
        return delete(length - 1);
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

    public boolean contains(E value){
        return indexOf(value) != -1;
    }

    public int size(){
        return length;
    }

    public void reverse() {
        reversePart(head);
        swapHeadAndTail();
    }

    public E get(int index) {
        if(index == length - 1){
            return tail.value;
        }

        var iterator = new LinkedListIterator();
        while(iterator.hasNext()){
            var value = iterator.next();
            if(iterator.getIndex() == index){
                return value;
            }
        }

        throw new IndexOutOfBoundsException();
    }

    public E getFromTheEnd(int index) {
        var current = head;
        var guard = getGuard(index, current);

        while(guard.next != null){
            guard = guard.next;
            current = current.next;
        }

        return current.value;
    }

    public Object[] getMiddleElements(){
        if(length == 0){
            throw new NoSuchElementException();
        }

        var slow = head;
        var fast = head;
        var evenIteration = true;

        while(fast != null && fast.next != null){
            evenIteration = !evenIteration;
            if(evenIteration){
                slow = slow.next;
            }
            fast = fast.next;
        }

        if(evenIteration){
            return new Object[] {slow.value};
        } else {
            return new Object[] {slow.value, slow.next.value};
        }

    }

    public E getLoopStart(){
        // Floyd’s Cycle-finding Algorithm
        var tortoise = head;
        var hare = head;

        do{
            if(hare == null || hare.next == null)
                return null;

            hare = hare.next.next;
            tortoise = tortoise.next;
        } while(hare != tortoise);

        hare = head;
        while(hare != tortoise){
            hare = hare.next;
            tortoise = tortoise.next;
        }

        return tortoise.value;
    }

    public boolean hasLoop(){
        return getLoopStart() != null;
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

    public Object[] toArray(){
        var array = new Object[length];
        var iterator = new LinkedListIterator();
        while(iterator.hasNext()){
            var value = iterator.next();
            array[iterator.getIndex()] = value;
        }
        return array;
    }

    /* helpers */

    private boolean correctInsertionPosition(int index){
        return index >= 0 && index <= length;
    }

    private Object[] getNewSiblings(int destination){
        var iterator = new LinkedListIterator();
        int currentIndex = -1;

        Node<E> previous = null;
        Node<E> next = null;

        while(currentIndex < destination && iterator.hasNext()){
            iterator.next();
            currentIndex++;

            previous = next;
            next = iterator.getNode();
        }

        if(currentIndex < destination){
            previous = next;
            next = null;
        }

        return new Object[]{ previous, next };
    }

    private void insert(Node predecessor, Node newNode, Node next){
        if(predecessor != null){
            predecessor.next = newNode;
        }
        newNode.next = next;
    }

    private void handleHead(Node<E> element, int index){
        if( index == 0 ){
            head = element;
        }
    }

    private void handleTail(Node<E> element, int index){
        if(index == length){
            tail = element;
        }
    }

    private boolean correctDeletionPosition(int index){
        return index >= 0 && index < length;
    }

    private Object[] getSiblings(int position){
        var iterator = new LinkedListIterator();
        int currentIndex = -2;

        Node<E> previous = null;
        Node<E> current = null;
        Node<E> next = null;

        while(currentIndex < position && iterator.hasNext()){
            iterator.next();
            currentIndex++;

            previous = current;
            current = next;
            next = iterator.getNode();
        }

        if(currentIndex < position){
            previous = current;
            current = next;
            next = null;
        }

        return new Object[]{ previous, current, next };
    }

    private void remove(Node predecessor, Node element){
        if(predecessor != null){
            predecessor.next = element.next;
        }
        element.next = null;
    }

    private void reversePart(Node start){
        if(start != null && start.next != null){
            reversePart(start.next);
            start.next.next = start;
            start.next = null;
        }
    }

    private void swapHeadAndTail(){
        var tmp = head;
        head = tail;
        tail = tmp;
    }

    private Node<E> getGuard(int offset, Node<E> start){
        Node<E> guard = start;
        while(offset > 0 && guard != null && guard.next != null){
            guard = guard.next;
            offset -= 1;
        }

        if(offset > 0){
            throw new IndexOutOfBoundsException();
        }

        return guard;
    }

    /* structures */

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
                index += 1;
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
}
