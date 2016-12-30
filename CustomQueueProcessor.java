package com.pocketmath;

import java.util.Stack;


public class CustomQueueProcessor {
	
	public static void main(String[] args) {
		CustomQueue<Object> queue = new CustomQueue<Object>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue("pocket");
		queue.enqueue(5);
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.enqueue("math");
		queue.enqueue("bhavesh");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
	}
	
}

class CustomQueue<T> {

	private Stack<T> firstStack = new Stack<T>();
	
    private Stack<T> secondStack = new Stack<T>();

	public void enqueue(T item) {
        firstStack.push(item);
    }

    public T dequeue() {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
               secondStack.push(firstStack.pop());
            }
        }
        return secondStack.pop();
    }
}
