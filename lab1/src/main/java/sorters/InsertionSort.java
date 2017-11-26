package sorters;

import java.util.Arrays;

/**
 * Class for sorting arrays of ints using the insertion sort method.
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 * @see Sort
 */
public class InsertionSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        for (int i = 1; i < result.length; i++) {
            for (int j = i; j > 0; j--) {
                if (result[j] < result[j - 1]) {
                    swap(result, j - 1, j);
                }
            }
        }
        return result;
    }
}
