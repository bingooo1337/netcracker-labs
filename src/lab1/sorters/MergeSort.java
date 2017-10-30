package lab1.sorters;

import java.util.Arrays;

public class MergeSort extends Sort {

    public int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        int len = result.length;
        int n = 1;
        int shift;
        int two_size;
        int[] arr2;
        while (n < len) {
            shift = 0;
            while (shift < len) {
                if (shift + n >= len) break;
                two_size = (shift + n * 2 > len) ? (len - (shift + n)) : n;
                arr2 = merge(Arrays.copyOfRange(result, shift, shift + n),
                        Arrays.copyOfRange(result, shift + n, shift + n + two_size));
                for (int i = 0; i < n + two_size; ++i)
                    result[shift + i] = arr2[i];
                shift += n * 2;
            }
            n *= 2;
        }
        return result;
    }

    /**
     * @param array1 Array for sorting
     * @param array2 Buffer array with size equal array1.size
     * @return Sorted array
     */
    public static int[] merge(int[] array1, int array2[]) {
        int n = array1.length + array2.length;
        int[] array = new int[n];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; i++) {
            if (i1 == array1.length) {
                array[i] = array2[i2++];
            } else if (i2 == array2.length) {
                array[i] = array1[i1++];
            } else {
                if (array1[i1] < array2[i2]) {
                    array[i] = array1[i1++];
                } else {
                    array[i] = array2[i2++];
                }
            }
        }
        return array;
    }
}
