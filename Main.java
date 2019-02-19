package hw2_cs3310_Allevato;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

public class Main {
	private static Stack<Integer> stack;
	private static Queue<Integer> queue;
	private static MyStack<Integer> myStack;
	private static MyQueue<Integer> myQueue;

	public static void main(String[] args) {
		stack = new Stack<>();
		queue = new Queue<>();
		myStack = new MyStack<>();
		myQueue = new MyQueue<>();
		
		Long totalStackPushTime;
		Long totalStackPoptime;
		Long totalQueueEnqueueTime;
		Long totalQueueDequeueTime;
		
		int stackPushCount;
		int stackPopCount;
		int queueEnqueueCount;
		int queueDequeueCount;
		
		
		if(args.length == 0) {	
			stack.push(20);
			stack.push(30);
			stack.push(10);
			stack.pop();
			stack.pop();
			stack.push(25);
			System.out.println(stack.getMax());
			stack.pop();
			System.out.println(stack.getMax());
		} else {
			for(int i = 0; i < args.length; i++) {
				switch(args[i]) {
					case "-f":
						readFile(args[++i]);
						break;
					case "-h":
						System.out.println("help");
						break;
					default:
						System.out.println("def");
				}
			}
		}

	}
	
	private static void readFile(String fileName) {
			try(Scanner fileInput = new Scanner(new File(fileName))){
				String command;
				int next = 0;
				
				while(fileInput.hasNext()) {
					command = fileInput.next();
	
					try {
						switch (command.toUpperCase()) {
							case "PUSH":
								next = fileInput.nextInt();
								
								stack.push(next);
								//add time to totalStackPushTime
								//add 1 to stackPushCount
								break;
							case "POP":
								next = stack.pop();
								//add to totalStackPoptime
								//add 1 to stackPopCount
								break;
							case "FINDMAX":
								next = stack.getMax();
								//Calculate max time
								break;
							case "ENQUEUE":
								next = fileInput.nextInt();
								
								queue.enqueue(next);
								//add to totalQueueEnqueueTime
								//add 1 to queueEnqueueCount
								break;
							case "DEQUEUE":
								next = queue.dequeue();
								//add to totalQueueDequeueTime
								//add 1 to queueEnqueueCount
								break;
						}
						
						System.out.printf("%s: %d\n", command.toUpperCase(), next);
					} catch (EmptyStackException e) {
						System.out.println("Stack Empty!");
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}
}
