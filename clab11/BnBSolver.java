import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> bears;
    List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears = bears;
        this.beds = beds;
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return QuickSortedBearsHelper(bears);
    }

    private List<Bear> QuickSortedBearsHelper(List<Bear> bears) {
        if (bears.size() <= 1) {
            return bears;
        }
        Bed pivot = getRandomBed(bears);
        List<Bear> less = new ArrayList<>();
        List<Bear> equal = new ArrayList<>();
        List<Bear> greater = new ArrayList<>();
        partitionBear(bears, pivot, less, equal, greater);
        less = QuickSortedBearsHelper(less);
        greater = QuickSortedBearsHelper(greater);
        List<Bear> catenated = catenateBear(less, equal);
        return catenateBear(catenated, greater);
    }

    private List<Bear> catenateBear(List<Bear> l1, List<Bear> l2) {
        List<Bear> catenated = new ArrayList<>();
        catenated.addAll(l1);
        catenated.addAll(l2);
        return catenated;
    }

    private Bed getRandomBed(List<Bear> bears) {
        int pivotIndex = (int) (Math.random() * bears.size());
        int BedSize = bears.get(pivotIndex).getSize();
        return new Bed(BedSize);
    }

    private void partitionBear(List<Bear> unsorted, Bed pivot,
                               List<Bear> less, List<Bear> equal, List<Bear> greater) {
        for (Bear x : unsorted) {
            if (x.compareTo(pivot) < 0) {
                less.add(x);
            }else if (x.compareTo(pivot) > 0) {
                greater.add(x);
            }else {
                equal.add(x);
            }
        }
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return QuickSortedBedsHelper(beds);
    }

    private List<Bed> QuickSortedBedsHelper(List<Bed> beds) {
        if (beds.size() <= 1) {
            return beds;
        }
        Bear pivot = getRandomBear(beds);
        List<Bed> less = new ArrayList<>();
        List<Bed> equal = new ArrayList<>();
        List<Bed> greater = new ArrayList<>();
        partitionBed(beds, pivot, less, equal, greater);
        less = QuickSortedBedsHelper(less);
        greater = QuickSortedBedsHelper(greater);
        List<Bed> catenated = catenateBed(less, equal);
        return catenateBed(catenated, greater);
    }

    private List<Bed> catenateBed(List<Bed> l1, List<Bed> l2) {
        List<Bed> catenated = new ArrayList<>();
        catenated.addAll(l1);
        catenated.addAll(l2);
        return catenated;
    }

    private Bear getRandomBear(List<Bed> beds) {
        int pivotIndex = (int) (Math.random() * beds.size());
        int BearSize = beds.get(pivotIndex).getSize();
        return new Bear(BearSize);
    }

    private void partitionBed(List<Bed> unsorted, Bear pivot,
                               List<Bed> less, List<Bed> equal, List<Bed> greater) {
        for (Bed x : unsorted) {
            if (x.compareTo(pivot) < 0) {
                less.add(x);
            }else if (x.compareTo(pivot) > 0) {
                greater.add(x);
            }else {
                equal.add(x);
            }
        }
    }

}
