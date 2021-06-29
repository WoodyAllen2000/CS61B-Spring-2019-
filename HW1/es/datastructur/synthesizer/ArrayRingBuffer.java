package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[capacity];
    }

    /**
     * Return size of the ring buffer.
     */
    @Override
    public int capacity(){
        return rb.length;
    }

    /**
     * Return number of items currently in the ring buffer.
     */
    @Override
    public int fillCount(){
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()){
            throw new RuntimeException("Ring buffer over flow");
        }
        else {
            rb[last] = x;
            fillCount += 1;
            last = (last + 1) % capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        else {
            T re = rb[first];
            fillCount -= 1;
            first = (first + 1) % capacity();
            return re;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        else {
            return rb[first];
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int currPos;
        private int count;

        public ArrayRingBufferIterator(){
            currPos = first;
            count = 0;
        }

        @Override
        public boolean hasNext(){
            return count < fillCount();
        }

        @Override
        public T next(){
            T currItem = rb[currPos];
            currPos = (currPos + 1) % capacity();
            count += 1;
            return currItem;
        }
    }

    @Override
    public boolean equals(Object other){
        if (other == this){
            return true;
        }
        if (other == null){
            return false;
        }
        if (other.getClass() != this.getClass()){
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount() != this.fillCount()){
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = o.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()){
            if (thisIterator.next() != otherIterator.next()){
                return false;
            }
        }
        return true;
    }
}
