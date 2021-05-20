public class ArrayDeque<Yewon>{
    int size;
    Yewon[] items;
    int nextFirst;
    int nextLast;
    int r = 2;
    int initCapacity = 8;
    double usage_factor = 0.25;

    public ArrayDeque(){
        size = 0;
        items = (Yewon[]) new Object[initCapacity];
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity){
        Yewon[] k = (Yewon[]) new Object[capacity];
        int c = plusOne(nextFirst);
        for (int i = 0; i < size; i += 1){
            k[i] = items[c];
            c = plusOne(c);
        }
        items = k;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private int minusOne(int index){
        return (index - 1) % items.length;
    }

    private int plusOne(int index){
        return (index + 1) % items.length;
    }

    public void addFirst(Yewon c){
        if (nextFirst == nextLast){
            resize(items.length * r);
        }
        items[nextFirst] = c;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(Yewon c){
        if (nextFirst == nextLast){
            resize(items.length * r);
        }
        items[nextLast] = c;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int c = plusOne(nextFirst);
        while (c != nextLast){
            System.out.print(items[c] + " ");
            c = plusOne(c);
        }
        System.out.println();
    }

    public Yewon removeFirst(){
        if (size == 0){
            return null;
        }
        Yewon k = items[plusOne(nextFirst)];
        items[nextFirst] = null;
        nextFirst = plusOne(nextFirst);
        size -= 1;
        if (items.length >= 16 && size < items.length * usage_factor){
            resize(items.length / 2);
        }
        return k;
    }

    public Yewon removeLast(){
        if (size == 0){
            return null;
        }
        Yewon k = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        nextLast = minusOne(nextLast);
        size -= 1;
        if (items.length >= 16 && size < items.length * usage_factor){
            resize(items.length / 2);
        }
        return k;
    }

    public Yewon get(int index){
        if (index >= size){
            return null;
        }
        index = (plusOne(nextFirst) + index) % items.length;
        return items[index];
    }

    public ArrayDeque(ArrayDeque other){
        items = (Yewon[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }
}
