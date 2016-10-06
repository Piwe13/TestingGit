package deques;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>{
	private int N = 0;
	private Node<Item> first, last;
	
	private class Node<Item>{
		Node<Item> next, before;
		Item item;
	}
	
	public Deque(){
		// construct an empty deque
	}
	
	public boolean isEmpty(){
		if(N == 0)
			return true;
		else 
			return false;
	}
	
	// return the number of items on the deque
	public int size(){
		return N;
	}
	
	// insert the item at the front
	public void addFirst(Item item){
		if(item == null){ throw new NullPointerException(); }
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		
		if(isEmpty())
			last = first;
		else{
			first.next = oldFirst;
			oldFirst.before = first;
		}
		N++;
	}
	
	// insert the item at the end
	public void addLast(Item item){
		if(item == null){ throw new NullPointerException(); }
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		
		if(isEmpty())
			first = last;
		else{
			oldLast.next = last;
			last.before = oldLast;
		}
		N++;
	}
	
	// delete and return the item at the front
	public Item removeFirst(){
		if(isEmpty()){ throw new NoSuchElementException(); }
		Item item = first.item;
		first = first.next;
		--N;
		return item;
	}
	
	// delete and return the item at the end
	public Item removeLast(){
		if(isEmpty()){ throw new NoSuchElementException(); }
		Item item = last.item;
		last = last.before;
		last.next = null;
		--N;
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new DequeIterator<Item>(first);
	}
	
	public class DequeIterator<Item> implements Iterator<Item>{
		Node<Item> current;
		
		public DequeIterator(Node<Item> first){
			current = first;
		}
		
		public boolean hasNext(){		
			if(current == null)
				return false;
			else
				return true;
		}
		
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args){
		// unit testing
		Deque<String> link = new Deque<String>();
		link.addFirst("hello");
		link.addFirst("two");
		link.addFirst("three");
		link.addFirst("four");
		link.addFirst("five");
		link.addLast("last");
		link.removeFirst();
		link.addFirst("First");
		link.addLast("hand");
		link.removeLast();
		link.removeFirst();
		link.addFirst("front");
		link.removeLast();
		
		for(String el : link){
			System.out.println(el);
		}
	}
}
