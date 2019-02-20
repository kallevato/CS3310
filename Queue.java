package hw2_cs3310_Allevato;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item extends Comparable<Item>> implements Iterable<Item> {
	private int n;
	private Node first;
	private Node last;
	
	public Long enqueueTime;
	public Long dequeueTime;
	
	private class Node {
		private Item item;
		private Node next;
	}
	
	public Queue() {
		first = null;
		last = null;
		n = 0;
	}
	
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return n;
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }
    
    public void enqueue(Item item) {
    	Long enqueueStart = System.nanoTime();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
	enqueueTime = System.nanoTime() - enqueueStart;
    }
    
    public Item dequeue() {
   	Long dequeueStart = System.nanoTime();
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
	dequeueTime = System.nanoTime() - dequeueStart;
        return item;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }
    
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item> {
        private Node current = first;  // node containing current item

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
