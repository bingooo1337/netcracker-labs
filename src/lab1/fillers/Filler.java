package lab1.fillers;
import java.util.Random;

public class Filler {

    //return sorted array of n int values in ascending order
    public static int[] sortedArray(int n) {
        int[] array = new int[n];
        Random random = new Random();
        array[0] = random.nextInt(n) + 1;

        for (int i = 1; i < n; i++) {
            array[i] = array[i - 1] + random.nextInt(n) + 1;
        }

        return array;
    }

    //return sorted array of n int values in ascending order with random element in the end
    public static int[] sortedArrayWithRandomEnd(int n) {
        int[] array = sortedArray(n);
        Random random = new Random();
//        array[n - 1] = random.nextInt(Arrays.stream(array).max().getAsInt()) + 1;
        array[n - 1] = random.nextInt(n * n) + 1;

        return array;
    }

    //return sorted array of n int values in descending order
    public static int[] reverseSortedArray(int n) {
        int[] array = sortedArray(n);

        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

    //return array of n int random values
    public static int[] randomArray(int n) {
        int[] array = new int[n];
        Random random = new Random();

        int maxValue = n * n;
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(maxValue) + 1;
        }

        return array;
    }
}
