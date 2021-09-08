package bearmaps;

public class NaiveMinPQTest {
    public static void main(String[] args) {
        NaiveMinPQ<Integer> sample = new NaiveMinPQ<>();
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
