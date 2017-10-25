package lab1.sorters;

import java.util.Arrays;

public class MergeSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) return array;
        int m = array.length / 2;
        int[] array1 = Arrays.copyOfRange(array, 0, m);
        int[] array2 = Arrays.copyOfRange(array, m, array.length);
        return merge(sort(array1), sort(array2));
    }

    public static int[] merge(int[] array1, int array2[]) {
        int n = array1.length + array2.length;
        int[] arr = new int[n];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < n; i++) {
            if (i1 == array1.length) {
                arr[i] = array2[i2++];
            } else if (i2 == array2.length) {
                arr[i] = array1[i1++];
            } else {
                if (array1[i1] < array2[i2]) {
                    arr[i] = array1[i1++];
                } else {
                    arr[i] = array2[i2++];
                }
            }
        }
        return arr;
    }
}
