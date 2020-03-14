package pl.emilsroka.dynamicArray.end.sorting;

public interface SortingStrategy<E extends Comparable<E>> {
    void sort(Object [] array, int firstIndex, int lastIndex);
}
