package fillers;

import java.util.Random;

/**
 * Class that provides fillers methods for generating arrays of ints values
 * in different ways.
 *
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 */
public class Filler {

    /**
     * Return sorted array.
     *
     * @param size     quantity of elements in the array.
     * @param minValue minimum value of elements in the array.
     * @param maxValue maximum value of elements in the array.
     * @return sorted array.
     */
    @FillerMethod
    public static int[] sortedArray(int size, int minValue, int maxValue) {
        if (size < 1)
            return null;

        if (maxValue - minValue <= 0) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = minValue;
            }
            return array;
        }

        int[] array = new int[size];

        Random random = new Random();
        array[0] = random.nextInt(Math.round((maxValue - minValue) / size) + 1) + minValue + 1;

        for (int i = 1; i < size; i++) {
            array[i] = array[i - 1] + random.nextInt(Math.round((maxValue - array[i - 1]) / (size - i)) + 1);
        }

        return array;
    }

    /**
     * Return sorted array with random last element.
     *
     * @param size     quantity of elements in the array.
     * @param minValue minimum value of elements in the array.
     * @param maxValue maximum value of elements in the array.
     * @return sorted array with random last element.
     */
    @FillerMethod
    public static int[] sortedArrayWithRandomEnd(int size, int minValue, int maxValue) {
        int[] array = sortedArray(size, minValue, maxValue);

        if (array == null)
            return null;

        if (maxValue - minValue <= 0)
            return array;

        array[size - 1] = new Random().nextInt(maxValue - minValue) + minValue + 1;

        return array;
    }

    /**
     * Return sorted array in reverse order.
     *
     * @param size     quantity of elements in the array.
     * @param minValue minimum value of elements in the array.
     * @param maxValue maximum value of elements in the array.
     * @return sorted array in reverse order.
     */
    @FillerMethod
    public static int[] reverseSortedArray(int size, int minValue, int maxValue) {
        int[] array = sortedArray(size, minValue, maxValue);

        if (array == null)
            return null;

        if (maxValue - minValue <= 0)
            return array;

        int temp;
        for (int i = 0, j = size - 1; i < j; i++, j--) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }

    /**
     * Return array with random values.
     *
     * @param size     quantity of elements in the array.
     * @param minValue minimum value of elements in the array.
     * @param maxValue maximum value of elements in the array.
     * @return array with random values.
     */
    @FillerMethod
    public static int[] randomArray(int size, int minValue, int maxValue) {
        if (size < 1)
            return null;

        if (maxValue - minValue <= 0) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = minValue;
            }
            return array;
        }

        int[] array = new int[size];

        Random random = new Random();

        int randomRange = maxValue - minValue + 1;
        for (int i = 0; i < size; i++) {
            array[i] = minValue + random.nextInt(randomRange);
        }

        return array;
    }
}
