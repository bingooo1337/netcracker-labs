package lab1.sorters;

import java.util.Arrays;

public class RecursiveMergeSort extends MergeSort {

    @Override
    public int[] sort(int[] array) {
        if (array.length < 2)
            return array;
        int m = array.length / 2;
        int[] array1 = Arrays.copyOfRange(array, 0, m);
        int[] array2 = Arrays.copyOfRange(array, m, array.length);
        return merge(sort(array1), sort(array2));
    }
}
