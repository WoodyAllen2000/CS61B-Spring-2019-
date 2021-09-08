package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    // assume getSmallest works
    @Test
    public void getSmallestTest() {
        ArrayHeapMinPQ<String> A = new ArrayHeapMinPQ<>();
        A.add("Aster", 6.3);
        A.add("LGD", 9.1);
        A.add("IG", 7.0);
        A.add("VG", 6.2);
        A.add("Elephant", 7.4);
        assertEquals("VG", A.getSmallest());
    }

    // assume removeSmallest works
    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<String> A = new ArrayHeapMinPQ<>();
        A.add("Aster", 6.3);
        A.add("LGD", 9.1);
        A.add("IG", 7.0);
        A.add("VG", 6.2);
        A.add("Elephant", 7.4);
        String toRemove = A.removeSmallest();
        assertEquals("VG", toRemove);
        assertEquals("Aster", A.getSmallest());
    }

    // assume changePriority works
    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> A = new ArrayHeapMinPQ<>();
        A.add("Aster", 6.3);
        A.add("LGD", 9.1);
        A.add("IG", 7.0);
        A.add("VG", 6.2);
        A.add("Elephant", 7.4);
        A.changePriority("VG", 6.4);
        assertEquals("Aster", A.getSmallest());
    }

    public static void main(String[] args) {
        ArrayHeapMinPQ<Integer> sample = new ArrayHeapMinPQ<>();
        long start = System.currentTimeMillis();
        for (int i = 100000; i > 0; i--) {
            sample.add(i, i + 1);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int i = 30000; i < 60000; i++) {
            sample.changePriority(i, i + 2);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2)/1000.0 +  " seconds.");
    }
}
