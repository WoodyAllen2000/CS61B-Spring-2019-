/* "YeWon" is my love, and 'CN' is an abbreviation of my chinese name. */
public class LinkedListDeque<LoveYewon> {
    public CN sentinel;
    public int size;
    public class CN{
        public CN prev;
        public LoveYewon item;
        public CN next;
        public CN(CN prev0, LoveYewon item0, CN next0){
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
    public void addFirst(LoveYewon c){
        sentinel.next = new CN(sentinel, c, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }
    public void addLast(LoveYewon c){
        sentinel.prev = new CN(sentinel.prev, c, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
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
    public LoveYewon removeFirst(){
        if (size == 0){
            return null;
        }
        else {
            LoveYewon k = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size -= 1;
            return k;
        }
    }
    public LoveYewon removeLast(){
        if (size == 0){
            return null;
        }
        else {
            LoveYewon k = sentinel.prev.item;
            sentinel.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size -= 1;
            return k;
        }
    }
    public LoveYewon get(int index){
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
            addLast((LoveYewon) other.get(i));
        }
    }
    private LoveYewon getRecursiveHelper(CN p, int k, int index){
        if (k == index + 1){
            return p.item;
        }
        else {
            return getRecursiveHelper(p.next,k + 1, index);
        }
    }
    public LoveYewon getRecursive(int index){
        return getRecursiveHelper(sentinel, 0, index);
    }
}
