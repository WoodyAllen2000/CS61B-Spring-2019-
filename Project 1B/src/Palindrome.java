public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> items = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1){
            items.addLast(word.charAt(i));
        }
        return items;
    }

    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque<Character> Dong) {
        if (Dong.size() == 1 || Dong.size() == 0) {
            return true;
        }
        char first = Dong.removeFirst();
        char last = Dong.removeLast();
        if (first == last) {
            return isPalindromeHelper(Dong);
        }
        return false;
    }

    // Overload.
    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private boolean isPalindromeHelper(Deque<Character> Dong, CharacterComparator cc){
        if (Dong.size() == 1 || Dong.size() == 0){
            return true;
        }
        char first = Dong.removeFirst();
        char last = Dong.removeLast();
        if (cc.equalChars(first, last)){
            return isPalindromeHelper(Dong, cc);
        }
        return false;
    }
}
