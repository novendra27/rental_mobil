package util;


import model.MobilModel;

public class LinkedList {
    private Link first;
    private Link last;

    public LinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public void insertLast(MobilModel data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
    }
    
    public Link getFirst() {
        return first;
    }

    public Link removeFirst() {
        Link temp = first;
        if (first.next == null) {
            last = null;
        }
        first = first.next;
        return temp;
    }
    
    public int size() {
        int count = 0;
        Link current = first;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public void displayList() {
        System.out.println("Queue (front-->rear):");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.next;
        }
        System.out.println();
    }
}
