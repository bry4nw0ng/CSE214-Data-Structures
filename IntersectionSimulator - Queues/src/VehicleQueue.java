/**
 * The VehicleQueue class models the lanes in a simulator as a Queue of 
 * Vehicles. It is inherited from the LinkedList class.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
import java.util.LinkedList;

public class VehicleQueue extends LinkedList<Vehicle>{
	
	/**
	 * Adds a vehicle to to the tail of the queue
	 * 
	 * @param v
	 *    Vehicle that is being added
	 */
	public void enqueue(Vehicle v) {
		super.offer(v);
	}
	
	/**
	 * Removes a vehicle at the head of the queue
	 * 
	 * @return
	 *    The vehicle removed from the queue
	 */
	public Vehicle dequeue() {
		return super.poll();
	}
	
	/**
	 * Returns the size of the queue
	 * 
	 * @return
	 *    The amount of elements within the queue as an Integer
	 */
	public int size() {
		return super.size();
	}
	
	/**
	 * CHecks if the queue is empty or not
	 * 
	 * @return
	 *    True if the queue is empty and false if it has objects within it
	 */
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
}
