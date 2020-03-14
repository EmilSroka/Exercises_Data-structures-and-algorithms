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
    private void swap(Object[] array, int indexA, int indexB){
        Object tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }

    private boolean isGrater(Object a, Object b){
        return ((E)a).compareTo((E)b) > 0;
    }
}
