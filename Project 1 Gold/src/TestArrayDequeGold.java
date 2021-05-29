import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
public class TestArrayDequeGold {

    @Test
    public void testTask1(){
        StudentArrayDeque<Integer> c = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> d = new ArrayDequeSolution<>();

        //addLast

        for (int i = 0; i < 10; i += 1){
            int random = StdRandom.uniform(100);
            c.addLast(random);
            d.addLast(random);
        }
        for (int i = 0; i < 10; i += 1){
            int actual = c.get(i);
            int expected = d.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        //addFirst

        for (int i = 0; i < 10; i += 1){
            int random = StdRandom.uniform(100);
            c.addFirst(random);
            d.addFirst(random);
        }
        for (int i = 0; i < 10; i += 1){
            int actual = c.get(i);
            int expected = d.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        //removeFirst

        ArrayList<Integer> actualList = new ArrayList<>();
        ArrayList<Integer> expectedList = new ArrayList<>();

        for (int i = 0; i < 10; i += 1){
            actualList.add(c.removeFirst());
            expectedList.add(d.removeFirst());
        }
        for (int i = 0; i < 10; i += 1){
            int actual = c.get(i);
            int expected = d.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }
        for (int i=0; i<10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }

        //removeLast

        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i += 1){
            actualList.add(c.removeLast());
            expectedList.add(d.removeLast());
        }
        int actual = c.size();
        int expected = d.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actual
                        + " not equal to " + expected + "!",
                expected, actual);
        for (int i=0; i<10; i++) {
            actual = actualList.get(i);
            expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeLast():\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);
        }
    }

    @Test
    public void testTask2(){
        ArrayDequeSolution<Integer> c = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> d = new StudentArrayDeque<>();
        int random = StdRandom.uniform(100);
        c.addFirst(random);
        d.addFirst(random);
        assertEquals("addFirst("+random+")", c.get(0), d.get(0));
        System.out.println("addFirst("+random+")");

        random = StdRandom.uniform(100);
        c.addLast(random);
        d.addLast(random);
        assertEquals("addLast("+random+")", c.get(1), d.get(1));
        System.out.println("addLast("+random+")");

        int actual = d.removeFirst();
        int expected = c.removeFirst();
        assertEquals("removeFirst()", expected, actual);
        System.out.println("removeFirst()");

        actual = d.removeLast();
        expected = c.removeLast();
        assertEquals("removeLast()", expected, actual);
        System.out.println("removeLast()");
    }
}
