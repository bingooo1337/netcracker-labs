package lab1.sorters;

/**
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 */
abstract public class Sort {
    /**
     * @param array Array for sorting
     * @return      Return sorted array
     */
    abstract public int[] sort(int[] array);

    /**
     * @param array Array in which need to swap two values
     * @param from  Index of the first value that need to swap with the second value
     * @param to    Index of the second value that need to swap with the first value
     */
    protected static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
