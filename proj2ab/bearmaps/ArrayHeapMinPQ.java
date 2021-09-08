package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    public ArrayList<PriorityNode> items;
    public HashMap<T, Integer> connections;

    public ArrayHeapMinPQ() {
        this.items = new ArrayList<>();
        this.connections = new HashMap<>();
    }

    @Override
    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        connections.put(item, items.size() - 1);
        swim(size() - 1);
    }

    @Override
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return connections.containsKey(item);
    }

    @Override
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.get(0).item;
    }

    @Override
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (items.isEmpty()) {
            throw new NoSuchElementException();
        }
        T toRemove = items.get(0).item;
        swap(0, size() - 1);
        connections.remove(items.get(size() - 1).item);
        items.remove(size() - 1);
        sink(0);
        return toRemove;
    }

    @Override
    /* Returns the number of items in the PQ. */
    public int size() {
        return items.size();
    }

    @Override
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = connections.get(item);
        items.get(index).priority = priority;
        swim(index);
        sink(index);
    }

    private class PriorityNode implements Comparable<PriorityNode> {

        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(priority, other.priority);
        }
    }

    // find the index of parent
    private int parent(int k) {
        return (k - 1) / 2;
    }

    // swap two elements in the list
    private void swap(int i, int j) {
        PriorityNode temp = items.get(j);
        items.set(j, items.get(i));
        items.set(i, temp);
        connections.put(items.get(i).item, i);
        connections.put(items.get(j).item, j);
    }

    // swap the smaller element to the right position
    private void swim(int k) {
        while (k > 0 && items.get(k).compareTo(items.get(parent(k))) < 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    // swap the bigger element to the right position
    private void sink(int k) {
        if (leftChild(k) > size() - 1) {
            return;
        }
        if (leftChild(k) == size() - 1) {
            if (items.get(k).compareTo(items.get(leftChild(k))) <= 0) {
                return;
            }
            swap(k, leftChild(k));
        }
        if (items.get(k).compareTo(items.get(smaller(k))) <= 0) {
            return;
        }
        swap(k, smaller(k));
        sink(smaller(k));
    }

    // find the index of left child
    private int leftChild(int k) {
        return 2 * k + 1;
    }

    // find the index of right child
    private int rightChild(int k) {
        return 2 * k + 2;
    }

    // find the smaller child
    private int smaller(int k) {
        if (items.get(leftChild(k)).compareTo(items.get(rightChild(k))) <= 0) {
            return leftChild(k);
        }
        return rightChild(k);
    }
}
