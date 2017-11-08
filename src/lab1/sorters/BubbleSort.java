package lab1.sorters;

import java.util.Arrays;
import java.util.Iterator;

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

    protected abstract OuterIterator getOuterLoopIterator(int[] array);

    protected abstract InnerIterator getInnerLoopIterator(int[] array, int outerIteratorStage);

    protected abstract boolean needToSwap(int current, int previous);

    protected abstract class OuterIterator implements Iterator<Integer> {
        protected int[] values;
        int current;

        abstract void initializeCurrent();

        public OuterIterator(int[] values) {
            this.values = values;
            initializeCurrent();
        }

        int getCurrentIndex() {
            return current;
        }
    }

    protected abstract class InnerIterator implements Iterator<Integer> {
        protected int[] values;
        int current;

        abstract void initializeCurrent();

        InnerIterator(int[] values, int outerIteratorStage) {
            this.values = values;
            initializeCurrent();
        }

        int getCurrentIndex() {
            return current;
        }

        abstract int getPrevious();

        abstract int getCurrent();

        abstract int getPreviousIndex();
    }

}