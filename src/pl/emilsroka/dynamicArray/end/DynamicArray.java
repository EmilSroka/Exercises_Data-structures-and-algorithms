package pl.emilsroka.dynamicArray.end;

import java.lang.reflect.Array;

public class DynamicArray<E extends Comparable<E>> {
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

    public E max(){
        if(lastIndex == -1){
            throw new RuntimeException("Array is empty");
        }

        Object currentMax =  memory[0];
        for(var i=1; i<=lastIndex; i++){
            if( ((E)memory[i]).compareTo((E)currentMax) > 0){
                currentMax = memory[i];
            }
        }
        return (E)currentMax;
    }

    public E min(){
        if(lastIndex == -1){
            throw new RuntimeException("Array is empty");
        }

        Object currentMin = memory[0];
        for(var i=1; i<=lastIndex; i++){
            if( ((E)memory[i]).compareTo((E)currentMin) < 0){
                currentMin = memory[i];
            }
        }
        return (E)currentMin;
    }

    public Object[] minmax(){
        if(lastIndex == -1){
            throw new RuntimeException("Array is empty");
        }

        Object currentMin;// =  lastIndex >= 0 ? memory[0] : new Object();
        Object currentMax;
        int startingIndex;

        if( lastIndex % 2 == 0 ){
            currentMax = currentMin = memory[0];
            startingIndex = 1;
        } else {
            if( ((E)memory[0]).compareTo((E)memory[1]) > 0){
                currentMax = memory[0];
                currentMin = memory[1];
            } else {
                currentMax = memory[1];
                currentMin = memory[0];
            }
            startingIndex = 2;
        }


        for(var i=startingIndex; i<=lastIndex; i += 2) {
            E greater, smaller;
            if (((E) memory[i]).compareTo((E) memory[i+1]) > 0) {
                greater = (E) memory[i];
                smaller = (E) memory[i+1];
            } else {
                greater = (E) memory[i+1];
                smaller = (E) memory[i];
            }

            if (((E) currentMax).compareTo(greater) < 0) {
                currentMax = greater;
            }
            if (((E) currentMin).compareTo(smaller) > 0) {
                currentMin = smaller;
            }
        }

        return  new Object[]{currentMin, currentMax};
    }

    public String toString(){
        var builder = new StringBuilder();
        builder.append("[");
        for(var i = 0; i<=lastIndex; i++){
            builder.append(memory[i].toString());
            if(i != lastIndex){
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
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