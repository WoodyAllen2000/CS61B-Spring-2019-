/* "YeWon" is my love, and 'CN' is an abbreviation of my chinese name. */
public class LinkedListDeque<Item> implements Deque<Item> {
    public CN sentinel;
    public int size;
    public class CN{
        public CN prev;
        public Item item;
        public CN next;
        public CN(CN prev0, Item item0, CN next0){
            prev = prev0;
            item = item0;
            next = next0;
        }
    }

    public LinkedListDeque(){
        sentinel = new CN(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(Item c){
        sentinel.next = new CN(sentinel, c, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(Item c){
        sentinel.prev = new CN(sentinel.prev, c, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        int number = 0;
        CN k = sentinel.next;
        while (number < size){
            System.out.print(k.item + " ");
            System.out.println();
            number += 1;
            k = k.next;
        }
    }

    @Override
    public Item removeFirst(){
        if (size == 0){
            return null;
        }
        else {
            Item k = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return k;
        }
    }

    @Override
    public Item removeLast(){
        if (size == 0){
            return null;
        }
        else {
            Item k = sentinel.prev.item;
            sentinel.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return k;
        }
    }

    @Override
    public Item get(int index){
        CN k = sentinel.next;
        int number = 0;
        while (number < index){
            k = k.next;
            number += 1;
        }
        return k.item;
    }
    public LinkedListDeque(LinkedListDeque other){
        sentinel = new CN(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size; i += 1){
            addLast((Item) other.get(i));
        }
    }
    private Item getRecursiveHelper(CN p, int k, int index){
        if (k == index + 1){
            return p.item;
        }
        else {
            return getRecursiveHelper(p.next,k + 1, index);
        }
    }
    public Item getRecursive(int index){
        return getRecursiveHelper(sentinel, 0, index);
    }
}
