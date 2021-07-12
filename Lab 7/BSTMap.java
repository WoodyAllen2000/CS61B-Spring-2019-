import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    public Node root;

    private class Node{
        private int size;
        private Node left, right;
        private K key;
        private V val;

        public Node(K key, V val){
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    public BSTMap(){
    }

    /* Remove all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (key == null){
            throw new IllegalArgumentException("Wrong Key!");
        }
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return getHelper(root, key);
    }

    private V getHelper(Node x, K key){
        if (key == null){
            throw new IllegalArgumentException("Wrong Key!");
        }
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return getHelper(x.left, key);
        }else if (cmp > 0){
            return getHelper(x.right, key);
        }
        else {
            return x.val;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return sizeHelper(root);
    }

    private int sizeHelper(Node x){
        if (x == null){
            return 0;
        }
        else {
            return x.size;
        }
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if (key == null){
            throw new IllegalArgumentException("Wrong Key!");
        }else if (value == null){
            throw new IllegalArgumentException("Wrong Value!");
        }else {
            root = putHelper(root, key, value);
        }
    }

    private Node putHelper(Node x, K key, V val){
        if (x == null){
            x = new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = putHelper(x.left, key, val);
        }else if (cmp > 0){
            x.right = putHelper(x.right, key, val);
        }else {
            x.val = val;
        }
        x.size = 1 + sizeHelper(x.left) + sizeHelper(x.right);
        return x;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            BSTSet.add(select(i).key);
        }
        return BSTSet;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        if (!containsKey(key)){
            return null;
        }
        V value = get(key);
        root = removeHelper(root, key);
        return value;
    }


    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        if (!containsKey(key)){
            return null;
        }
        if (!get(key).equals(value)){
            return null;
        }
        root = removeHelper(root, key);
        return value;
    }

    private Node removeHelper(Node x, K key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = removeHelper(x.left, key);
        }else if (cmp > 0){
            x.right = removeHelper(x.right, key);
        }else {
            if (x.right == null){
                return x.left;
            }
            if (x.left == null){
                return x.right;
            }
            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.size = 1 + sizeHelper(x.right) + sizeHelper(x.left);
        return x;
    }

    /* Return the smallest key of the node x. */
    private Node min(Node x){
        if (x == null){
            return null;
        }
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }

    /* Delete the smallest key of the node x. */
    private Node deleteMin(Node x){
        if (x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = 1 + sizeHelper(x.left) + sizeHelper(x.right);
        return x;
    }

    /* Print the BSTMap (key, vale) pairs in increasing of order of key. */
    public void printInOrder() {
        for (int i = 0; i < size(); i += 1) {
            System.out.println(select(i).key + " " + select(i).val);
        }
    }

    private Node select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k);
    }

    private Node select(Node x, int k){
        if (x == null){
            return null;
        }
        int t = sizeHelper(x.left);
        if (t > k){
            return select(x.left, k);
        }else if (t < k){
            return select(x.right, k - t - 1);
        }else {
            return x;
        }
    }
    /* Iterator of the BSTMap. */
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node src) {
            while (src != null) {
                // Push root node and all left nodes to the stack.
                stack.push(src);
                src = src.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();

            if (curr.right != null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }
}

