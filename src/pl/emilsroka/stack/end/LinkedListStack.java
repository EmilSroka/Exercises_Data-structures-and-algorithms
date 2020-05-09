package pl.emilsroka.stack.end;

import pl.emilsroka.linkedList.end.LinkedList;

public class LinkedListStack<E> {
    LinkedList<E> list = new LinkedList<>();

    public LinkedListStack<E> push(E element){
        list.addFirst(element);

        return this;
    }

    public E pop(){
        var value = pick();
        list.deleteFirst();
        return value;
    }

    public E pick(){
        if (empty())
            throw new IllegalStateException();

        return list.get(0);
    }

    public boolean empty(){
        return list.size() == 0;
    }
}
