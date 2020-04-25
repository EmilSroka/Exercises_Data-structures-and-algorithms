package pl.emilsroka.dynamicArray.end.sorting;

import java.util.Arrays;

public class HeapSort<E extends Comparable<E>> implements SortingStrategy {
    @Override
    public void sort(Object[] array, int firstIndex, int lastIndex) {

        for(var i = startingIndex(firstIndex, lastIndex); i>=firstIndex; i--){
            heapify(array, i, lastIndex);
        }

        while(lastIndex > firstIndex){
            swap(array, firstIndex, lastIndex);
            lastIndex--;
            heapify(array, firstIndex, lastIndex);
        }
    }

    private void heapify(Object[] array, int currentIndex, int lastIndex){
        int max = currentIndex;
        int leftChild = getLeftChildIndex(currentIndex);
        int rightChild = getRightChildIndex(currentIndex);

        if(leftChild <= lastIndex && isGrater(array[leftChild],array[max])){
            max = leftChild;
        }

        if(rightChild <= lastIndex && isGrater(array[rightChild],array[max])){
            max = rightChild;
        }

        if(max != currentIndex){
            swap(array, max, currentIndex);
            heapify(array, max, lastIndex);
        }

    }

    private int startingIndex(int firstIndex, int lastIndex){
        int size = length(firstIndex, lastIndex);
        int mid = size / 2 + 1;
        return firstIndex + mid;
    }

    private int getLeftChildIndex(int parentIndex){
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex){
        return 2 * parentIndex + 2;
    }
}
