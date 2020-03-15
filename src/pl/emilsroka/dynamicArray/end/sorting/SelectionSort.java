package pl.emilsroka.dynamicArray.end.sorting;

public class SelectionSort<E extends Comparable<E>> implements SortingStrategy<E> {
    @Override
    public void sort(Object[] array, int firstIndex, int lastIndex) {
        for(int target=firstIndex; target<lastIndex; target++){
            int lowest = findMinIndex(array, target, lastIndex);
            swap(array, target, lowest);
        }
    }

    private int findMinIndex(Object[] array, int firstIndex, int lastIndex){
        int lowest=firstIndex;
        for(int i=lowest; i<=lastIndex; i++){
            if(isGrater(array[lowest], array[i])){
                lowest = i;
            }
        }
        return lowest;
    }
}
