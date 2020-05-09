package pl.emilsroka.stack.end;

public class DoubleStack<E> {
    private Object[] array;
    private int size1 = 0;
    private int size2 = 0;

    public DoubleStack(int size){
        if(size < 1)
            throw new IllegalStateException("Size must be greater than 0");

        array = new Object[size];
    }

    public int push1(E element){
        if(full())
            throw new StackOverflowError();

        array[size1] = element;
        size1 += 1;
        return size1;
    }

    public E pop1() {
        if(empty1())
            throw new IllegalStateException();

        size1 -= 1;
        return (E)array[size1];
    }

    public E peek1(){
        if(empty1())
            throw new IllegalStateException();

        return (E)array[size1 - 1];
    }

    public boolean empty1(){
        return size1 == 0;
    }

    public int push2(E element){
        if(full())
            throw new StackOverflowError();

        array[size1] = element;
        size2 += 1;
        return size1;
    }

    public E pop2() {
        if(empty2())
            throw new IllegalStateException();

        size2 -= 1;
        return (E)array[translate(size2)];
    }

    public E peek2(){
        if(empty2())
            throw new IllegalStateException();

        return (E)array[translate(size2) + 1];
    }

    public boolean empty2(){
        return size2 == 0;
    }

    private boolean full(){
        return size1 + size2 == array.length;
    }

    private int translate(int index){
        return array.length - index - 1;
    }
}