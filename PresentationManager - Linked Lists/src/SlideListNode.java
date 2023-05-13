/**
 * The SlideListNode class acts as a object that can be inserted into a 
 * doubly linked-list data structure contains a Slide object reference 
 * along with two other SlideListNodes that serve as links to the 
 * previous and next SlideListNodes within the list.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
public class SlideListNode {
	private Slide data; // Side object reference
	private SlideListNode next; // The next node listed in the list
	private SlideListNode prev; // The previous node listed in the list
	
	/**
	 * Returns an instance of a SlideListNode with its own Slide data linked
	 * to no other SlideListNodes.
	 * 
	 * @param initData
	 *    The data within the SlideListNode
	 *    
	 * @precondition
	 *    initData is not null
	 *    
	 * @postcondition
	 *    The SlideListNode has been initialized to wrap the indicated Slide 
	 *    and the previous and next nodes are set to null.
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that the initData slide is null
	 */
	public SlideListNode(Slide initData) throws IllegalArgumentException {
		if (initData == null) {
			throw new IllegalArgumentException("The initial data is null.");
		}
		else {
			data = initData;
			next = null;
			prev = null;
		}
	}
	
	/**
	 * Returns the SlideListNode's reference to the data variable containing 
	 * the slide
	 * 
	 * @return
	 *    The reference of the data member variable as a Slide Object
	 */
	public Slide getData() {
		return data;
	}
	
	/**
	 * Sets a new data member variable with a reference to a new Slide Object
	 * 
	 * @param newData
	 *    Reference to a new Slide object to update the data member variable
	 *    
	 * @precondition
	 *    newData is not null
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that newData is null
	 */
	public void setData(Slide newData) throws IllegalArgumentException{
		if (newData == null) {
			throw new IllegalArgumentException("The new data is null");
		}
		else {
			data = newData;
		}
	}
	
	/**
	 * Returns the reference to the next member variable of the list node. In 
	 * the case that it returns null, it means that there is no next 
	 * SlideListNode in the list, so you are at the tail of the list.
	 * 
	 * @return
	 *    The reference of the next member variable
	 */
	public SlideListNode getNext() {
		return next;
	}
	
	/**
	 * Updates the next member variable with a new SlideListNode reference
	 * 
	 * @param newNext
	 *    References to a new SlideListNode Object to update the next member
	 *    variable.
	 */
	public void setNext(SlideListNode newNext) {
		next = newNext;
	}
	
	/**
	 * Returns the reference to the previous member variable of the list node.
	 * In the case that it returns null, it means that there is no previous
	 * SlideListNode in the list hence you are at the head of the list.
	 * 
	 * @return
	 *    The reference of the prev member variable.
	 */
	public SlideListNode getPrev() {
		return prev;
	}
	
	/**
	 * Updates the next member variable with a new SlideListNode reference
	 * 
	 * @param newPrev
	 *    References to a new SlideListNode Object to update the previous
	 *    member variable
	 */
	public void setPrev(SlideListNode newPrev) {
		prev = newPrev;
	}
}
