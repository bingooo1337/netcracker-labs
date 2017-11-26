package sorters;

import java.util.ArrayList;

/**
 * Abstract class for sorting arrays of int values.
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 */
abstract public class Sort {
    /**
     * @param array Array of ints for sorting
     * @return sorted array
     */
    abstract public int[] sort(int[] array);

    /**
     * @param array Array in which need to swap two elements
     * @param from  Index of the first element that need to swap with the second element
     * @param to    Index of the second element that need to swap with the first element
     */
    protected static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }


    /**
     * Method that take name of the class and update it with <br>
     * spaces between words.
     * @return name of the class with spaces between words.
     */
    public String getClassName() {
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
