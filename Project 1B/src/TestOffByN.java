import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    CharacterComparator offBy5 = new OffByN(5);
    CharacterComparator offBy4 = new OffByN(4);

    @Test
    public void testEqualChars(){
        assertTrue(offBy5.equalChars('a', 'f'));
        assertFalse(offBy5.equalChars('a', 'F'));
        assertTrue(offBy5.equalChars('f', 'a'));
        assertFalse(offBy5.equalChars('a', 'e'));
        assertTrue(offBy4.equalChars('a', 'e'));
        assertTrue(offBy4.equalChars('e','a'));
        assertFalse(offBy4.equalChars('E', 'a'));
        assertFalse(offBy4.equalChars('f', 'a'));
    }
}
