package lab1.sorters;

import java.util.Arrays;

public class BubbleReverseSort extends BubbleSort {

    @Override
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        int n = result.length - 1;

        for (int i = n; i >= 0; i--) {
            for (int j = n - 1; j >= n - i; j--) {
                if (result[j + 1] < result[j]) {
                    swap(result, j + 1, j);
                }
            }
        }
        return result;
    }
}
