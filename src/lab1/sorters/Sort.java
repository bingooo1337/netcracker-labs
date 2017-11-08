package lab1.sorters;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        String name = this.getClass().getSimpleName();
        ArrayList<String> words = new ArrayList<>();
        int i = 0;
        while (name.length() > 1) {
            if (Character.isUpperCase(name.charAt(i))) {
                i++;
                while (!Character.isUpperCase(name.charAt(i))) {
                    if (i == name.length() - 1) {
                        i++;
                        break;
                    }
                    i++;
                }
                words.add(name.substring(0, i));
                name = name.substring(i);
                if (name.length() < 1)
                    break;
                i = 0;
            }
        }
        for (String word : words) {
            name += word + " ";
        }
        return name;
    }
}
