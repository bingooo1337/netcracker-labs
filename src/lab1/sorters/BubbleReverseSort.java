package lab1.sorters;

import java.util.NoSuchElementException;

public class BubbleReverseSort extends BubbleSort {

    @Override
    protected OuterIterator getOuterLoopIterator(int[] array) {
        return new OuterIterator(array) {
            @Override
            public boolean hasNext() {
                return current >= 0;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return values[current--];
            }

            @Override
            void initializeCurrent() {
                current = values.length - 1;
            }
        };
    }

    @Override
    protected InnerIterator getInnerLoopIterator(int[] array, int outerIteratorStage) {
        return new InnerIterator(array, outerIteratorStage) {
            @Override
            int getPrevious() {
                return values[current + 1];
            }

            @Override
            int getCurrent() {
                return values[current];
            }

            @Override
            int getPreviousIndex() {
                return current + 1;
            }

            @Override
            public boolean hasNext() {
                return current >= values.length - 1 - outerIteratorStage;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return values[current--];
            }

            @Override
            void initializeCurrent() {
                current = values.length - 2;
            }
        };
    }

    @Override
    protected boolean needToSwap(int current, int previous) {
        return current > previous;
    }
}
