package hw2_cs3310_Allevato;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<Item extends Comparable<Item>> implements Iterable<Item> {
	private Queue<Item> q1;
	private Queue<Item> q2;
	
	private Long pushTime;
	private Long popTime;
	
	public MyStack() {
		q1 = new Queue<>();
		q2 = new Queue<>();
	}
	
	public boolean isEmpty() {
		return q1.isEmpty() && q2.isEmpty();
	}
	
	public int size() {
		return q1.size() + q2.size();
	}
	
	public void push(Item item) {
		Long pushStart = System.nanoTime();
		q1.enqueue(item);
		pushTime = System.nanoTime() - pushStart;
	}
	
	public Item pop() {
		Long popStart = System.nanoTime();
		if(q2.isEmpty()) {
			while(!q1.isEmpty()) {
				q2.enqueue(q1.dequeue());
			}
		}
		popTime = System.nanoTime() - popStart;
		return (Item) q1.dequeue();
	}

	@Override
	public Iterator<Item> iterator() {
		return null;
	}
	
}
