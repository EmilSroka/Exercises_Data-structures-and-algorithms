package pl.emilsroka.stack.end;

public class ArrayStack<E> {
    private Object[] list;
    private int size = 0;

    public ArrayStack(int size){
        list = new Object[size];
    }

    public int push(E element){
        if(size == list.length)
            throw new StackOverflowError();

        list[size] = element;
        size += 1;
        return size;
    }

    public E pop() {
        if(empty())
            throw new IllegalStateException();

        size -= 1;
        return (E)list[size];
    }

    public E peek(){
        if(empty())
            throw new IllegalStateException();

        return (E)list[size - 1];
    }

    public boolean empty(){
        return size == 0;
    }

}
