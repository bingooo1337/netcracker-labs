package lab1.sorters;

import java.util.Arrays;

public class QuickSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        Arrays.sort(result);
        return result;
    }
}
