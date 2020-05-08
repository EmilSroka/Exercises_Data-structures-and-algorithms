package pl.emilsroka.stack.end;

public class ArrayStack<E> {
    private Object[] array;
    private int size = 0;

    public ArrayStack(int size){
        array = new Object[size];
    }

    public int push(E element){
        if(size == array.length)
            throw new StackOverflowError();

        array[size] = element;
        size += 1;
        return size;
    }

    public E pop() {
        if(empty())
            throw new IllegalStateException();

        size -= 1;
        return (E)array[size];
    }

    public E peek(){
        if(empty())
            throw new IllegalStateException();

        return (E)array[size - 1];
    }

    public boolean empty(){
        return size == 0;
    }

}
