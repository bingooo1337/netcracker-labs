package sorters;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Abstract class for sorting arrays of ints using the universal bubble sort method.
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 * @see Sort
 */
abstract public class BubbleSort extends Sort {

    @Override
    public final int[] sort(int[] array) {
        int[] result = Arrays.copyOf(array, array.length);

        OuterIterator outerIterator = getOuterLoopIterator(result);
        while (outerIterator.hasNext()) {
            InnerIterator innerIterator = getInnerLoopIterator(result, outerIterator.getCurrentIndex());
            while (innerIterator.hasNext()) {
                if (needToSwap(innerIterator.getCurrent(), innerIterator.getPrevious())) {
                    swap(result, innerIterator.getCurrentIndex(), innerIterator.getPreviousIndex());
                }
                innerIterator.next();
            }
            outerIterator.next();
        }

        return result;
    }

    /**
     * Create and return instance of iterator for outer loop for sorting.
     *
     * @param array array for sorting
     * @return instance of iterator for outer loop for sorting
     */
    protected abstract OuterIterator getOuterLoopIterator(int[] array);

    /**
     * Create and return instance of iterator for inner loop for sorting.
     *
     * @param array              array for sorting
     * @param outerIteratorStage stage of the outer loop
     * @return instance of iterator for inner loop for sorting
     */
    protected abstract InnerIterator getInnerLoopIterator(int[] array, int outerIteratorStage);

    /**
     * Method for determining whether to perform a swap.
     *
     * @param current  array element, which is considered
     * @param previous previous array element, which is considered
     * @return true, if a swap needs to be performed
     */
    protected abstract boolean needToSwap(int current, int previous);

    /**
     * Iterator for outer loop when sorting.
     */
    protected abstract class OuterIterator implements Iterator<Integer> {
        /**
         * Array, for which iterator will be used.
         */
        protected int[] values;
        /**
         * Index of the array element, which is considered.
         */
        protected int current;

        /**
         * Method for initializing value of {@link #current} field.
         */
        abstract void initializeCurrent();

        /**
         * Create iterator with array and invoke {@link #initializeCurrent()}
         * @param values array, for which need to create iterator
         */
        public OuterIterator(int[] values) {
            this.values = values;
            initializeCurrent();
        }

        /**
         * @return index of current element in the array.
         */
        int getCurrentIndex() {
            return current;
        }
    }

    /**
     * Iterator for inner loop when sorting.
     */
    protected abstract class InnerIterator implements Iterator<Integer> {
        /**
         * Array, for which iterator will be used.
         */
        protected int[] values;
        /**
         * Index of the array element, which is considered.
         */
        protected int current;

        /**
         * Method for initializing value of {@link #current} field.
         */
        abstract void initializeCurrent();

        /**
         * Create iterator with array and invoke {@link #initializeCurrent()}
         * @param values             array, for which need to create iterator
         * @param outerIteratorStage stage of the outer loop, need to initialize current
         */
        InnerIterator(int[] values, int outerIteratorStage) {
            this.values = values;
            initializeCurrent();
        }

        /**
         * @return index of current element in the array.
         */
        int getCurrentIndex() {
            return current;
        }

        /**
         * @return previous element in the array, which was considered.
         */
        abstract int getPrevious();

        /**
         * @return current element in the array.
         */
        abstract int getCurrent();

        /**
         * @return index of previous element in the array, which was considered.
         */
        abstract int getPreviousIndex();
    }
}