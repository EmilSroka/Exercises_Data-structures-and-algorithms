package pl.emilsroka.dynamicArray.end.sorting;

public class InsertionSort<E extends Comparable<E>> implements SortingStrategy<E> {
    @Override
    public void sort(Object[] array, int firstIndex, int lastIndex) {
        for(int lastSorted=firstIndex; lastSorted<lastIndex; lastSorted++){
            Object current = array[lastSorted + 1];
            int newPosition = shift(array, firstIndex, lastSorted, current);
            array[newPosition] = current;
        }
    }

    private int shift(Object[] array, int firstIndex, int lastIndex, Object limiter){
        for(int i=lastIndex; i>=firstIndex; i--){
            if(isGraterOrEqual(limiter, array[i])){
                return i+1;
            }
            array[i+1] = array[i];
        }
        return firstIndex;
    }
}
