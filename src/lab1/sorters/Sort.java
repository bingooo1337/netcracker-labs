package lab1.sorters;

abstract public class Sort {
    abstract public int[] sort(int[] array);

    public static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
