package pl.emilsroka.dynamicArray.end;

public class DynamicArray<E> {
    private Object[] memory;
    private int lastIndex;

    public DynamicArray(int size){
        lastIndex = -1;
        memory = new Object [size];
    }

    public void set(int index, E element){
        validateIndex(index);
        memory[index] = element;
    }

    public E get(int index){
        validateIndex(index);
        return (E)memory[index];
    }

    public void insert(E element){
        lastIndex += 1;
        if(lastIndex == memory.length){
            extendMemory();
        }
        memory[lastIndex] = element;
    }

    public void removeAt(int index){
        validateIndex(index);
        shiftLeft(index + 1, lastIndex, 1);
    }

    public void insertAt(int index, E element){
        validateIndex(index);
        shiftRight(index, lastIndex, 1);
        memory[index] = element;
    }

    public int indexOf(E wanted){
        for(var i=0; i<=lastIndex; i++){
            if(memory[i].equals(wanted)){
                return i;
            }
        }
        return -1;
    }

    private void validateIndex(int index){
        if (index > lastIndex || index < 0){
            throw new IndexOutOfBoundsException(
                String.format(
                    "Given index (%d) is is out of range (0 - %d).",
                    index,
                    lastIndex
                )
            );
        }
    }

    private void extendMemory(){
        var newSize = 2 * memory.length;
        Object[] newMemory = new Object[newSize];
        System.arraycopy(memory, 0, newMemory, 0, memory.length);
        memory = newMemory;
    }

    private void shiftLeft(int start, int end, int offset){
        if(start - offset < 0) {
            throw new IndexOutOfBoundsException(
                String.format(
                    "Element was moved out of array boundary (%d)",
                    start - offset
                )
            );
        }

        for(var i=start; i<=end; i++){
            memory[i - offset] = memory[i];
            memory[i] = null;
        }

        if(end == lastIndex){
            lastIndex = end - offset;
        }
    }

    private void shiftRight(int start, int end, int offset){
        if(end + offset > lastIndex){
            extendMemory();
        }

        for(var i=end; i>=start; i--){
            memory[i + offset] = memory[i];
            memory[i] = null;
        }

        if(end + offset > lastIndex){
            lastIndex = end + offset;
        }
    }
}