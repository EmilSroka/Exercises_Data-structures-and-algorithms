package pl.emilsroka.dynamicArray.end.sorting;

import java.util.Arrays;

public class MergeSort<E extends Comparable<E>> implements SortingStrategy {

    @Override
    public void sort(Object[] array, int firstIndex, int lastIndex) {
        if(length(firstIndex, lastIndex) < 2)
            return;

        var parts = divide(array, firstIndex, lastIndex);
        sort(parts[0]);
        sort(parts[1]);
        join(array, firstIndex, parts[0], parts[1]);
    }

    private int length(int first, int last){
        return last - first + 1;
    }

    private Object[][] divide(Object[] array, int firstIndex, int lastIndex){
        int mid = firstIndex + length(firstIndex, lastIndex) / 2;
        var leftPart = Arrays.copyOfRange(array, firstIndex, mid);
        var rightPart = Arrays.copyOfRange(array, mid, lastIndex + 1);
        return new Object[][] {leftPart, rightPart};
    }

    private void sort(Object[] array){
        sort(array, 0, array.length - 1);
    }

    private void join(Object[] target, int firstIndex, Object[] leftPart, Object[] rightPart){
        int leftPointer = 0;
        int rightPointer = 0;
        int targetPointer = firstIndex;

        while(leftPointer < leftPart.length && rightPointer < rightPart.length){
            if( isGrater(leftPart[leftPointer], rightPart[rightPointer]) )
                target[targetPointer ++] = rightPart[rightPointer ++];
            else
                target[targetPointer ++] = leftPart[leftPointer ++];
        }

        while(leftPointer < leftPart.length)
            target[targetPointer ++] = leftPart[leftPointer ++];

        while(rightPointer < rightPart.length)
            target[targetPointer ++] = rightPart[rightPointer ++];
    }
}
