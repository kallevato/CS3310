package hw2_cs3310_Allevato;

import java.util.Iterator;

public class MyQueue<Item extends Comparable<Item>> implements Iterable<Item> {
	private Stack<Item> s1;
	private Stack<Item> s2;
	
	private Long enqueueTime;
	private Long dequeueTime;
	
	public MyQueue() {
		s1 = new Stack<>();
		s2 = new Stack<>();
	}
	
	public boolean isEmpty() {
		return s1.isEmpty() && s2.isEmpty();
	}
	
	public int size() {
		return s1.size() + s2.size();
	}
	
	public void enqueue(Item item) {
		Long enqueueStart = System.nanoTime();
		s1.push(item);
		dequeueTime = System.nanoTime() - enqueueStart;
	}
	
	public Item dequeue() {
		Long dequeueStart = System.nanoTime();
		if(s2.isEmpty()) {
			while(!s1.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		dequeueTime = System.nanoTime() - dequeueStart;
		return (Item) s2.pop();
	}
	
 

	@Override
	public Iterator<Item> iterator() {
		return null;
	} 
}
