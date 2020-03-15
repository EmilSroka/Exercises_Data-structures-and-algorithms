package pl.emilsroka.dynamicArray.end.sorting;

public class BubbleSort<E extends Comparable<E>> implements SortingStrategy<E>{
    @Override
    public void sort(Object[] array,  int firstIndex, int lastIndex) {
        boolean isSorted;
        for(int sortedPart = lastIndex; sortedPart > firstIndex; sortedPart--){ //?
            isSorted = true;
            for(int i = firstIndex; i < sortedPart; i++){
                if( isGrater(array[i], array[i+1]) ){
                    isSorted = false;
                    swap(array, i, i+1);
                }
            }
            if(isSorted){
                return;
            }
        }
    }
}
