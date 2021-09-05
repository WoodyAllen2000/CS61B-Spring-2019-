import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        LinkedList<String> A = new LinkedList<>();
        LinkedList<String> B = new LinkedList<>();
        for (String x : g.labels()) {
            if (g.neighbors(x).size() > 0) {
                if (!A.contains(x) && !B.contains(x)) {
                    A.add(x);
                    for (String m : g.neighbors(x)) {
                        if (!A.contains(m) && !B.contains(m)) {
                            B.add(m);
                        }
                    }
                }
            }else {
                if (!A.contains(x) && !B.contains(x)) {
                    A.add(x);
                }
            }
        }
        for (String x : A) {
            for (String m : A) {
                if (g.neighbors(x).contains(m)) {
                    return false;
                }
            }
        }
        for (String x : B) {
            for (String m : B) {
                if (g.neighbors(x).contains(m)) {
                    return false;
                }
            }
        }
        return true;
    }


    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
