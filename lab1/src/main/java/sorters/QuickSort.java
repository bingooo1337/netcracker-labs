package sorters;

import java.util.Arrays;

/**
 * Class for sorting arrays of ints using the quick sort method.
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 * @see Sort
 */
public class QuickSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);
        Arrays.sort(result);
        return result;
    }
}
