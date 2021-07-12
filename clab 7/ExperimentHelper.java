import java.security.Key;
import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int optimalIPL = 0;
        int count = 0;
        for (int i = 0; count < N; i++){
            for (int k = 0; k < Math.pow(2, i) && count < N; k++){
                optimalIPL += i;
                count += 1;
            }
        }
        return optimalIPL;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        int optimalIPL = optimalIPL(N);
        return ((double) optimalIPL) / N;
    }

    public static void insertAndDeleteSuccessor(BST<Integer> bst) {
        Random r = new Random();
        int length = bst.size();

        // delete an item
        while (length == bst.size()) {
            int item = r.nextInt(5000);
            if (bst.contains(item)){
                bst.deleteTakingSuccessor(item);
            }
        }

        // insert an item
        length = bst.size(); // size has changed
        while (length == bst.size()) {
            int item = r.nextInt(5000);
            if (!bst.contains(item)){
                bst.add(item);
            }
        }
    }

    public static void insertAndDeleteRandom(BST<Integer> bst) {

        Random r = new Random();
        int length = bst.size();

        // delete an item
        while (length == bst.size()) {
            int item = r.nextInt(5000);
            if (bst.contains(item)){
                bst.deleteTakingRandom(item);
            }
        }

        // insert an item
        length = bst.size(); // size has changed
        while (length == bst.size()) {
            int item = r.nextInt(5000);
            if (!bst.contains(item)){
                bst.add(item);
            }
        }
    }
}
