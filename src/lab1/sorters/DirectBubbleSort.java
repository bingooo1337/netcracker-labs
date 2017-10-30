package lab1.sorters;

import java.util.Arrays;

public class DirectBubbleSort extends BubbleSort{

    @Override
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        int n = result.length;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (result[j - 1] > result[j]) {
                    swap(result, j - 1, j);
                }
            }
        }
        return result;
    }
}
