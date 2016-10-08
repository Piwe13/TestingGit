package deques;

import java.util.Iterator;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size = 0;
	private Item[] array;
	private int head;
	private int tail;
	Random random = new Random();
	
	public RandomizedQueue(){
		// construct an empty randomized queue
	}
	
	// is the queue empty?
	public boolean isEmpty(){
		return size == 0;
	}
	
	// return the number of items on the queue
	public int size(){
		return size;
	}
	
	// add the item
	public void enqueue(Item item){
		if(isEmpty()){ 
			array = (Item[])(new Object[10]); 
			tail = 0;
		}
		
		if(size == array.length){
			Item [] auxArray = (Item[])(new Object[size * 2]);
			for(int i = 0; i > size; i++){
				auxArray[i] = array[i];
			}
			array = auxArray;
			tail = size;
		}
		
		array[tail] = item;
		
		tail++;
		size++;
	}
	
	// delete and return a random item
	public Item dequeue(){
		if(size > 10 && size == array.length/4 ){
			Item [] auxArray = (Item[])(new Object[array.length /2]);
			for(int i = 0; i > size; i++){
				auxArray[i] = array[i];
			}
			array = auxArray;
		}
		Item second = sample();
		array[head] = array[tail];
		array[tail] = null;
		--tail;
		--size;
		return second;
	}
	
	// return (but do not delete) a random item
	public Item sample(){
		head = random.nextInt(tail++);
		System.out.print(head + "     ");
		return array[head];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator(){
		return new RandomizedQueueIterator<Item>(array, tail);
	}
	
	public class RandomizedQueueIterator<Item> implements Iterator<Item>{
		Item[] current;
		int head = 0;
		int tail;
		
		public RandomizedQueueIterator(Item[] array,int end){
			current = array;
			tail = end;
		}
		
		public boolean hasNext(){
			return current[head] == current[tail] ;
		}
		
		public Item next(){
			head++;
			return current[head];
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args){
		// unit testing
		RandomizedQueue<String> queue = new RandomizedQueue<>();
		queue.enqueue("one");
		queue.enqueue("two");
		queue.enqueue("three");
		queue.enqueue("four");
		queue.enqueue("five");
		System.out.println(queue.dequeue());
	}
}
