package hw2_cs3310_Allevato;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.EmptyStackException;

public class Stack<Item extends Comparable> implements Iterable<Item>{
	private int n;
	private Node first;
	private Item max;
	
	private Long pushTime;
	private Long popTime;
	
	private class Node {
		private Item item;
		private Node next;
	}
	
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return n;
    }
    
    public Item getMax() {
	if(isEmpty()) throw new EmptyStackException();
    	return max;
    }
    
    public void newMax(){
	Stack<Item> maxFinder = new Stack<>();
	while(!this.isEmpty()) {
		maxFinder.push(this.pop());
	}
	while(!secondaryStack.isEmpty()) {
		this.push(maxFinder.pop());
	}
    }

	public Stack() {
		first = null;
		int n = 0;
	}
	
    public void push(Item item) {
	pushStart = System.nanoTime();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        if(first.item.compareTo(oldfirst.item) > 0) {
        	max = (Item) first;
        }
        first.next = oldfirst;
        n++;
	Long pushTime = Systemm.nanoTime() - pushStart;
    }
	
    public Item pop() {
	Long popStart = System.nanoTime();
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        n--;
	    
	if(max != null && data.compareTo(max.data) == 0) {
		max = null;
		newMax();
		}
	popTime = System.nanoTime() - popStart;
        return item;                   // return the saved item
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator()  { return new ListIterator();  }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}
