package sorters;

import java.util.NoSuchElementException;

/**
 * Class for sorting arrays of ints using the bubble sort method with a direct passage through the array.
 * @author Kamyshanov Volodymyr bingooo1337@gmail.com
 */
public class DirectBubbleSort extends BubbleSort {

    @Override
    protected OuterIterator getOuterLoopIterator(int[] array) {
        return new OuterIterator(array) {
            @Override
            public boolean hasNext() {
                return current < values.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return values[current++];
            }

            @Override
            void initializeCurrent() {
                current = 0;
            }
        };
    }

    @Override
    protected InnerIterator getInnerLoopIterator(int[] array, int outerIteratorStage) {
        return new InnerIterator(array, outerIteratorStage) {
            @Override
            int getPrevious() {
                return values[current - 1];
            }

            @Override
            int getCurrent() {
                return values[current];
            }

            @Override
            int getPreviousIndex() {
                return current - 1;
            }

            @Override
            public boolean hasNext() {
                return current < values.length - outerIteratorStage;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return values[current++];
            }

            @Override
            void initializeCurrent() {
                current = 1;
            }
        };
    }

    @Override
    protected boolean needToSwap(int current, int previous) {
        return current < previous;
    }

}
