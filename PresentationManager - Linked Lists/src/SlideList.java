/**
 * The SlideList Class implements a doubly linked-list data structure. It 
 * maintains a list of Slides by chaining together a series of 
 * SlideListNodes using a head and tail reference. A cursor is also used
 * to allow the user to traverse through the list. It can also select 
 * individual SlideListNodes to insert and delete as well as manipulate 
 * the data of the Slides held within each SlideListNode. The class also 
 * has the capability to display the information of the list along with
 * its size, total duration, and total sum of the number of bullet points
 * within the SlideList.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */
public class SlideList {
	private SlideListNode head; // Head node of the List
	private SlideListNode tail; // Tail node of the List
	private SlideListNode cursor; // Cursor node
	int slideCount; // Number of SlideListNodes in the list
	
	/**
	 * Returns an instance of the SlideList as an empty slide
	 * 
	 * @postconditions
	 *    The SlideList has been initialized with head, tail, and cursor all 
	 *    set to null
	 */
	public SlideList() {
		head = null;
		tail = null;
		cursor = null;
		slideCount = 0;
	}
	
	/**
	 * Returns the total number of Slides in the slide show
	 * 
	 * @return
	 *    Returns the count of all Slides in the slide show as an Integer
	 */
	public int size() {
		return slideCount;
	}
	
	/**
	 * Returns the total duration of the slide show
	 * 
	 * @return
	 *    Returns the sum of all individual Slide durations in the slide show
	 *    as an Double
	 */
	public double duration() {
		double duration = 0;
		SlideListNode pointer = head;
		while(pointer != null) {
			duration += pointer.getData().getDuration();
			pointer = pointer.getNext();
		}
		return duration;
	}
	
	/**
	 * Returns the total number of bullet points in the slide show
	 * 
	 * @return
	 *    Returns the sum of all bullet points of all individual Slides in the
	 *    slide show as an Integer
	 */
	public int numBullets() {
		int bulletCount  = 0;
		SlideListNode pointer = head;
		while(pointer != null) {
			bulletCount += pointer.getData().getNumBullets();
			pointer = pointer.getNext();
		}
		return bulletCount;
	}
	
	/**
	 * Gets the reference to the Slide wrapped by the SlideListNode currently 
	 * referenced by cursor
	 * 
	 * @return
	 *    Returns the reference of the Slide within the SlideListNode currently
	 *    referenced by the cursor. If the cursor returns null, then this 
	 *    method should also return null as the cursor is not currently
	 *    referencing a slide.
	 */
	public Slide getCursorSlide() {
		if (cursor == null) {
			return null;
		}
		else {
			return cursor.getData();
		}
	}
	
	/**
	 * Returns the cursor to the start of the list
	 * 
	 * @postconditions
	 *    If the head is not null, then the cursor now references the first 
	 *    SlideListNode in the list. On the other hand, if the head is null, 
	 *    then it means that there are no Slides in the list, so the cursor
	 *    is set to null as well.
	 */
	public void resetCursorToHead() {
		if (head == null) {
			cursor = null;
		}
		else {
			cursor = head;
		}
	}
	
	/**
	 * Moves the cursor to select the next SlideListNode in the list.
	 * 
	 * @throws EndOfListException
	 *    Indicates that the cursor is at the tail of the list, so the cursor
	 *    cannot move forward
	 */
	public void cursorFoward() throws EndOfListException {
		if (cursor == tail || (cursor == null && tail == null)) {
			throw new EndOfListException("End of list cannot move forward");
		}
		else {
			cursor = cursor.getNext();
		}
	}
	
	/**
	 * Moves the cursor to select the previous SlideListNode in the list
	 * 
	 * @throws EndOfListException
	 *    Indicates that the cursor is at the head of the list, so the cursor
	 *    cannot move backward.
	 */
	public void cursorBackward() throws EndOfListException {
		if (cursor == head || (cursor == null && head == null)) {
			throw new EndOfListException("End of list cannot move backward");
		}
		else {
			cursor = cursor.getPrev();
		}
	}
	
	/**
	 * Inserts an indicated slide before the cursor
	 * 
	 * @param newSlide
	 *    The Slide Object to be wrapped and inserted into the list before 
	 *    cursor
	 *    
	 * @precondition
	 *    newSldie is not null
	 *    
	 * @postcondition
	 *    newSlide has been wrapped in a new SlideListNode Object
	 *    If the cursor was not previously null, the newly created node has
	 *    been inserted before the cursor.
	 *    If the cursor was previously null, the newly created node has been
	 *    set as the new head and tail of the list
	 *    The cursor now references the newly created SlideListNode
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates that the newSlide input is null
	 */
	public void insertBeforeCursor(Slide newSlide) 
			throws IllegalArgumentException {
		if (newSlide == null) {
			throw new IllegalArgumentException("New slide is null");
		}
		else {
			slideCount++;
			SlideListNode insert = new SlideListNode(newSlide);
			if (cursor == null) {
				head = insert;
				tail = insert;
				cursor = insert;
			}
			else if (cursor != null) {
				if (cursor == head) {
					head = insert;
					insert.setNext(cursor);
					cursor.setPrev(insert);
					cursor = cursor.getPrev();
				}
				else if (cursor != head){
					insert.setPrev(cursor.getPrev());
					cursor.getPrev().setNext(insert);
					insert.setNext(cursor);
					cursor.setPrev(insert);
					cursor = cursor.getPrev();
				}
			}
		}
	}
	
	/**
	 * Inserts the indicated Slide after the tail of the list
	 * 
	 * @param newSlide
	 *    The Slide object to be wrapped and inserted into the list after the
	 *    tail of the list
	 *    
	 * @precondition
	 *    the newSlide is not null
	 *    
	 * @postcondition
	 *    newSlide has been wrapped in a new SlideListNode Object
	 *    if the tail was not previously null the newly created node has been 
	 *    inserted into the list after the tail.
	 *    If the tail was previously null, the newly created node has been set
	 *    as the new head and tail of the list.
	 *    The tail now references the newly created node.
	 *    
	 * @throws IllegalArgumentException
	 *    Indicates the newSlide input is null
	 */
	public void appendToTail(Slide newSlide) throws IllegalArgumentException {
		if (newSlide == null) {
			throw new IllegalArgumentException("New slide is null");
		}
		else {
			SlideListNode insert = new SlideListNode(newSlide);
			slideCount++;
			if (tail == null) {
				head = insert;
				tail = insert;
				cursor = insert;
			}
			else if (tail != null) {
				tail.setNext(insert);
				insert.setPrev(tail);
				tail = insert;
			}
		}
	}
	
	/**
	 * Removes the SlideListNode referenced by the cursor and returns the 
	 * Slide inside 
	 * 
	 * @precondition
	 *    cursor is not null
	 *    
	 * @postcondition
	 *    The SlideListNode referenced by the cursor has been removed from 
	 *    the list
	 *    All other SlideListNodes in the list exist in the same order as 
	 *    before
	 *    The cursor now references the previous SlideListNode or the head
	 *    if the cursor previously referenced the head of the list
	 *    
	 * @return
	 *    Returns the reference to the Slide within the SlideListNode that
	 *    was just removed from the list
	 * 
	 * @throws EndOfListException
	 *    Indicates that the cursor is null
	 */
	public Slide removeCursor() throws EndOfListException {
		if (cursor == null) {
			throw new EndOfListException("cursor is null");
		}
		else {
			Slide hold = cursor.getData();
			slideCount--;
			if (slideCount == 0) {
				cursor = null;
				return hold;
			}
			if (cursor == head) {
				cursor.getNext().setPrev(null);
				head = cursor.getNext();
				cursor = head;
				return hold;
			}
			else if (cursor == tail) {
				cursor.getPrev().setNext(null);
				tail = cursor.getPrev();
				cursor = tail;
				return hold;
			}
			else {
				cursor.getNext().setPrev(cursor.getPrev());
				cursor.getPrev().setNext(cursor.getNext());
				cursor = cursor.getPrev();
				return hold;
			}
		}
	}
	
	/**
	 * Returns a string that contains the details of all the slides in the 
	 * list
	 * 
	 * @returns
	 *    Return the title, number of bullets, and duration of each Slide in
	 *    the slide show
	 */
	public String toString() {
		SlideListNode pointer = head;
		String output = "";
		int count = 1;
		while (pointer != null) {
			if (pointer == cursor) {
				output += String.format("%-11s%-14s%-11s%-10s", "->" 
						+ count, pointer.getData().getTitle(), 
						pointer.getData().getDuration(), 
						pointer.getData().getNumBullets());
			}
			else if (pointer != cursor) {
				output += String.format("%-11s%-14s%-11s%-10s", "  " + count,
						pointer.getData().getTitle(), 
						pointer.getData().getDuration(), 
						pointer.getData().getNumBullets());
			}
			output += "\n";
			count++;
			pointer = pointer.getNext();
		}
		return output;
	}
}

