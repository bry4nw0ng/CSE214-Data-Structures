/**
 * The StoryTreeNode class represents a segment of the story of the StoryTree.
 * The StoryTreeNode class contains 3 StoryTreeNode references, leftChild, 
 * middleChild, and rightChild. In addition, the class should contain three 
 * String member variables: position, which indicates the position of the 
 * node in the tree; option, which represents the text that should be 
 * displayed to the user to describe what choice this node represents (this 
 * will be read from the parent node), and the message, which is the message 
 * displayed when the user chooses this option (i.e: the consequence of 
 * taking the option).
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 *		Recitation#04
 */
public class StoryTreeNode {
	static final String WIN_MESSAGE = "YOU WIN"; 
	static final String LOSE_MESSAGE = "YOU LOSE";

	String position; // node position
	String option; // the option that selects the current node
	String message; // the message displayed after this node is chosen
	
	StoryTreeNode leftChild;
	StoryTreeNode middleChild;
	StoryTreeNode rightChild;
	
	/**
	 * Default constructor that returns an instance of the StoryTreeNode
	 * 
	 * @param position
	 *    The position of the StoryTreeNode
	 * 
	 * @param option
	 *    The option of the StoryTreeNode
	 * 
	 * @param message
	 *    The message of the StoryTreeNode
	 */
	public StoryTreeNode(String position, String option, String message) {
		this.position = position;
		this.option = option;
		this.message = message;
	}

	/**
	 * Returns the position of the StoryTreeNode
	 * 
	 * @return
	 *    Returns the position as a String
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Sets a new position for the StoryTreeNode
	 * 
	 * @param position
	 *    The new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Returns the option of the StoryTreeNode
	 * 
	 * @return
	 *    Returns the position as a String
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Sets a new option for the StoryTreeNode
	 * 
	 * @param option
	 *    The new option
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Returns the message of the StoryTreeNode
	 * 
	 * @return
	 *    Returns the message as a String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets a new message for the StoryTreeNode
	 * 
	 * @param message
	 *    The new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the node linked as the left child
	 * 
	 * @return
	 *    Returns the left child as a StoryTreeNode
	 */
	public StoryTreeNode getLeftChild() {
		return leftChild;
	}

	/**
	 * Sets a link to a new StoryTreeNode as the left child
	 * 
	 * @param leftChild
	 *    the new linked Left child
	 */
	public void setLeftChild(StoryTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * Returns the node linked as the middle child
	 * 
	 * @return
	 *    Returns the middle child as a StoryTreeNode
	 */
	public StoryTreeNode getMiddleChild() {
		return middleChild;
	}

	/**
	 * Sets a link to a new StoryTreeNode as the middle child
	 * 
	 * @param middleChild
	 *    the new linked middle child
	 */
	public void setMiddleChild(StoryTreeNode middleChild) {
		this.middleChild = middleChild;
	}

	/**
	 * Returns the node linked as the right child
	 * 
	 * @return
	 *    Returns the right child as a StoryTreeNode
	 */
	public StoryTreeNode getRightChild() {
		return rightChild;
	}

	/**
	 * Sets a link to a new StoryTreeNode as the right child
	 * 
	 * @param rightChild
	 *    the new linked right child
	 */
	public void setRightChild(StoryTreeNode rightChild) {
		this.rightChild = rightChild;
	}
	
	/**
	 * Checks if the node has any children
	 * 
	 * @preconditions
	 *    the node is initialized
	 *    
	 * @postconditions
	 *    the tree remains unchanged
	 *    
	 * @return
	 *    true if there are no children
	 *    false if there is at least one child
	 */
	public boolean isLeaf() {
		if (leftChild == null && middleChild == null && rightChild == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if this is a winning node. In order to be a winning node, this 
	 * node must be a leaf and contain the winning message.
	 * 
	 * @preconditions
	 *    the node is initialized
	 *    
	 * @postconditions
	 *    the tree remains unchanged
	 * 
	 * @return
	 *    true if this node is a leaf and contains WIN_MESSAGE in the message.
	 *    false otherwise
	 */
	public boolean isWinningNode() {
		if (isLeaf() && message.contains(WIN_MESSAGE)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if this is a losing node. In order to be a losing node, this 
	 * node must be a leaf and contain the losing message.
	 * 
	 * @preconditions
	 *    the node is initialized
	 *    
	 * @postconditions
	 *    the tree remains unchanged
	 * 
	 * @return
	 *    True if this node is a leaf and does NOT contain WIN_MESSAGE in the 
	 *    message.
	 *    false otherwise
	 */
	public boolean isLosingNode() {
		if (isLeaf() && !message.contains(WIN_MESSAGE)) {
			return true;
		}
		return false;
	}
}


