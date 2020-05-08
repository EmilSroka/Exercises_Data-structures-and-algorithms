package pl.emilsroka.stack.end;

public class MinStack<E extends Comparable> {
    private ArrayStack<Object> stack;
    private ArrayStack<Object> minStack;

    public MinStack(int size){
        if(size < 1)
            throw new IllegalStateException("Size must be greater than 0");

        stack = new ArrayStack<>(size);
        minStack = new ArrayStack<>(size);
    }

    public void push(E item) {
        stack.push(item);

        if (minStack.empty())
            minStack.push(item);
        else if (isLower(item, (E)minStack.peek()))
            minStack.push(item);
    }

    public E pop() {
        var top = stack.pop();

        if (minStack.peek() == top)
            minStack.pop();

        return (E)top;
    }

    public E min() {
        return (E)minStack.peek();
    }

    private boolean isLower(E element1, E element2){
        return element1.compareTo(element2) < 0;
    }
}
