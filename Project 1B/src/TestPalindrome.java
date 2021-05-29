import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome("Aoa"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void testIsPalindromeOffByOne(){
        CharacterComparator c = new OffByOne();
        assertTrue(palindrome.isPalindrome("abcihdab", c));
        assertTrue(palindrome.isPalindrome("ffyeg", c));
        assertTrue(palindrome.isPalindrome("", c));
        assertFalse(palindrome.isPalindrome("afgB", c));
        assertFalse(palindrome.isPalindrome("kbak", c));
        assertTrue(palindrome.isPalindrome("%gmnf&", c));
        assertFalse(palindrome.isPalindrome("cat", c));
    }
}