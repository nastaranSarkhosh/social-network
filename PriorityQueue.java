import java.util.ArrayList;
import java.util.LinkedList;

class PriorityQueue{
    class Entry{
        double key;
        String value;
        Entry next;
        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public double getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }
    }
    public LinkedList<Entry> list=new LinkedList<>();
    int size=0;
    public Entry findMin(){
        Entry min=list.getFirst();
        Entry entry=list.getFirst();
        for(int i=0;i<size;i++){
            if(list.get(i).key>min.key)
                min=list.get(i);
        }
        return min;
    }
    public double findMinKey(){
        Entry min=list.getFirst();
        Entry entry=list.getFirst();
        for(int i=0;i<size;i++){
            if(list.get(i).key>min.key)
                min=list.get(i);
        }
        return min.key;
    }
    public String removeMin(){
        Entry min=findMin();
        list.remove(min);
        size--;
        return min.value;
    }
    public Entry insert(int key,String value){
        Entry newEntry=new Entry(key,value);
        if(size>0)
            list.getLast().next=newEntry;
        list.addLast(newEntry);
        size++;
        return newEntry;
    }
    public Entry contain(String string) {
        for (int i = 0; i < size; i++) {
            if (list.get(i).value.compareTo(string) == 0) {
                return list.get(i);
            }
        }
        return null;
    }
}
