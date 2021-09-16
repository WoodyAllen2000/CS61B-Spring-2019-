import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> idols = new Queue<>();
        idols.enqueue("Lewis");
        idols.enqueue("Ronaldo");
        idols.enqueue("Sancho");
        idols.enqueue("Allen");
        idols.enqueue("Guan");
        assertTrue(isSorted(QuickSort.quickSort(idols)));
    }

    @Test
    public void testMergeSort() {
        Queue<String> idols = new Queue<>();
        idols.enqueue("Lewis");
        idols.enqueue("Ronaldo");
        idols.enqueue("Sancho");
        idols.enqueue("Allen");
        idols.enqueue("Guan");
        assertTrue(isSorted(MergeSort.mergeSort(idols)));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
