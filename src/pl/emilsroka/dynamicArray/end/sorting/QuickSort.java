package pl.emilsroka.dynamicArray.end.sorting;

import java.util.Random;

public class QuickSort<E extends Comparable<E>> implements SortingStrategy {
    @Override
    public void sort(Object[] array, int firstIndex, int lastIndex) {
        if(length(firstIndex, lastIndex) < 2){
            return;
        }

        int pivotIndex = partitioning(array, firstIndex, lastIndex);
        sort(array, firstIndex, pivotIndex);
        sort(array, pivotIndex + 1, lastIndex);
    }

    private int partitioning(Object[] array, int firstIndex, int lastIndex) {
        Random generator = new Random();
        var pivotIndex = firstIndex + generator.nextInt(length(firstIndex, lastIndex));
        Object pivot = array[pivotIndex];
        int left = firstIndex - 1;
        int right = lastIndex + 1;

        while(true){
            do {
                left++;
            } while(  isGrater(pivot, array[left]) );

            do {
                right--;
            } while( isGrater(array[right], pivot) );

            if(left < right){
                swap(array, left, right);
            } else {
                break;
            }
        }

        return right;
    }
}
