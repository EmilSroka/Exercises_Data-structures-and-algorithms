package pl.emilsroka.dynamicArray.end;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class DynamicArray<E extends Comparable<E>> implements Iterable<E>{
    private Object[] memory;
    private int lastIndex;

    public DynamicArray(int size){
        if (size < 0){
            throw new IllegalArgumentException(
                String.format(
                    "Given size (%d) is illegal.",
                    size
                )
            );
        }
        lastIndex = -1;
        memory = new Object [size];
    }

    public DynamicArray(){
        this(1);
    }

    public DynamicArray(DynamicArray<E> origin){
        this(1);
        for(var element : origin){
            insert(element);
        }
    }

    public void set(int index, E element){
        validateIndex(index);
        memory[index] = element;
    }

    public E get(int index){
        validateIndex(index);
        return (E)memory[index];
    }

    public DynamicArray<E> insert(E element){
        lastIndex += 1;
        if(lastIndex == memory.length){
            extendMemory();
        }
        memory[lastIndex] = element;
        return this;
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

    public int indexOf(E element){
        for(var i=0; i<=lastIndex; i++){
            if(memory[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E element){
        return indexOf(element) != -1;
    }

    public DynamicArray<E> intersection(DynamicArray<E> anotherArray){
        var result = new DynamicArray<E>();
        for(var element : anotherArray){
            if(contains(element)){
                result.insert(element);
            }
        }
        return result;
    }

    public DynamicArray<E> union(DynamicArray<E> anotherArray){
        var result = new DynamicArray<E>(this);
        for(var element : anotherArray){
            if(!result.contains(element)){
                result.insert(element);
            }
        }
        return result;
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

    public DynamicArray<E> minmax(){
        if(lastIndex == -1){
            throw new RuntimeException("Array is empty");
        }

        E currentMin = (E)memory[0];
        E currentMax = (E)memory[0];
        int startingIndex = (lastIndex % 2 == 0) ? 1 : 0;

        for(var i=startingIndex; i<=lastIndex; i += 2) {
            var sorted = order(memory[i], memory[i+1]);
            E greater = (E)sorted[0];
            E smaller = (E)sorted[1];

            if (((E) currentMax).compareTo(greater) < 0) {
                currentMax = greater;
            }
            if (((E) currentMin).compareTo(smaller) > 0) {
                currentMin = smaller;
            }
        }

        return new DynamicArray<E>(2).insert(currentMin).insert(currentMax);
    }

    public DynamicArray<E> alternativeMinmax(){
        if(lastIndex == -1){
            throw new RuntimeException("Array is empty");
        }

        Object currentMin = memory[0];
        Object currentMax = memory[0];
        for(var i=1; i<=lastIndex; i++){
            if( ((E)memory[i]).compareTo((E)currentMin) < 0){
                currentMin = memory[i];
            }
            if( ((E)memory[i]).compareTo((E)currentMax) > 0){
                currentMax = memory[i];
            }
        }
        return new DynamicArray<E>(2).insert((E)currentMin).insert((E)currentMax);
    }

    public E nthElement(int n){
        if (n > lastIndex + 1 || n < 1){
            throw new IllegalArgumentException(
                String.format(
                    "Given order statistic (%d) is illegal (%d - %d).",
                    n,
                    1,
                    lastIndex+1
                )
            );
        }

        Object[] memoryCopy = new Object[lastIndex+1];
        System.arraycopy(memory, 0, memoryCopy, 0, lastIndex+1);
        return randomizedSelect(n, memoryCopy, 0, lastIndex);
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator();
    }

    public class DynamicArrayIterator implements Iterator<E>{
        Object[] itMemory;
        int indicator = 0;

        DynamicArrayIterator(){
            itMemory = new Object[lastIndex+1];
            System.arraycopy(memory, 0, itMemory, 0, lastIndex+1);
        }

        @Override
        public boolean hasNext() {
            return indicator < itMemory.length;
        }

        @Override
        public E next() {
            return (E)itMemory[indicator++];
        }
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

    private E randomizedSelect(int n, Object[] array, int firstElement, int lastElement){
        if(firstElement == lastElement) {
            return (E) array[firstElement];
        }
        int pivot = randomizedPartitioning(array, firstElement, lastElement);
        int leftPartLength = pivot - firstElement + 1;
        if(n <= leftPartLength){
            return randomizedSelect(n, array, firstElement, pivot);
        } else {
            return randomizedSelect(n - leftPartLength, array, pivot+1, lastElement);
        }
    }

    private int randomizedPartitioning(Object[] array, int firstElement, int lastElement){
        Random generator = new Random();
        int length = lastElement - firstElement + 1;
        Object pivot = array[firstElement + generator.nextInt(length)];
        int p = firstElement - 1;
        int q = lastElement + 1;
        while(true) {
            do{
                q--;
            } while (((E) array[q]).compareTo((E) pivot) > 0);

            do{
                p++;
            } while (((E) array[p]).compareTo((E) pivot) < 0);

            if(p < q){
                swap(array, p, q);
            } else {
                return q;
            }
        }
    }

    private void swap(Object[] array, int indexA, int indexB){
        Object tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }

    private Object[] order(Object element1, Object element2){
        Object max, min;
        if( ((E)element1).compareTo((E)element2) > 0){
            max = element1;
            min = element2;
        } else {
            max = element1;
            min = element2;
        }
        return new Object[]{min, max};
    }
}