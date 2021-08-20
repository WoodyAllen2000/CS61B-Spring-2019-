import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private final static int R = 256; // extended ASCII

    private Node root = new Node(false); // root of trie

    // R-way trie Node
    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> map;

        Node(boolean b) {
            this.isKey = b;
            this.map = new HashMap<>();
        }
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node(false);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.map.get(c) == null) {
                return false;
            }else {
                curr = curr.map.get(c);
            }
        }
        return curr.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (curr.map.get(c) == null) {
                curr.map.put(c, new Node(false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException();
        }
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.map.get(c) == null) {
                return null;
            }
            curr = curr.map.get(c);
        }
        List<String> ans = new LinkedList<>();
        keysWithPrefixHelper(prefix, curr, ans);
        return ans;
    }

    private void keysWithPrefixHelper(String s, Node x, List<String> ans) {
        if (x.isKey) {
            ans.add(s);
        }
        for (char c : x.map.keySet()) {
            keysWithPrefixHelper(s + c, x.map.get(c), ans);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key){
        throw new UnsupportedOperationException();
    }
}
