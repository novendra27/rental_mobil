package util;

import model.MobilModel;

public class MobilQueue {
    private LinkedList linkedList;

    public MobilQueue() {
        linkedList = new LinkedList();
    }

    public void insert(MobilModel data) {
        linkedList.insertLast(data);
    }

    public MobilModel peekFront() {
        if (!isEmpty()) {
            return linkedList.getFirst().data;
        }
        return null;
    }
    
    public MobilModel remove() {
        if (!isEmpty()) {
            return linkedList.removeFirst().data;
        }
        return null;
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    
    public int getSize() {
        return linkedList.size();
    }

    public void displayQueue() {
        linkedList.displayList();
    }
    
    public static void main(String[] args) {
        MobilQueue queue = new MobilQueue();
        queue.insert(new MobilModel("MO001", "Dee", false));
        queue.insert(new MobilModel("MO002", "Deaja", false));
        queue.displayQueue();
    }
}
