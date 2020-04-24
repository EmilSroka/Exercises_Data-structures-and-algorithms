package pl.emilsroka.dynamicArray.end.sorting;

public interface SortingStrategy<E extends Comparable<E>> {
    void sort(Object [] array, int firstIndex, int lastIndex);

    default void swap(Object[] array, int indexA, int indexB){
        Object tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }

    default boolean isGrater(Object a, Object b){
        return ((E)a).compareTo((E)b) > 0;
    }

    default boolean isGraterOrEqual(Object a, Object b){
        return ((E)a).compareTo((E)b) >= 0;
    }

    default int length(int first, int last){
        return last - first + 1;
    }
}
