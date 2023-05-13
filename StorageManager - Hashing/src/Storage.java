/**
 * The Storage class contains three private data fields: int id, String 
 * client, String contents, along with public getter and setter methods for 
 * each of these fields. This class will be used to represent a storage box 
 * registered with the company. It also implements the Serializable interface.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.io.Serializable;

public class Storage implements Serializable {
	private int id; // unique id for the storage
	private String client; // client name
	private String contents; // description of the contents in the box
	
	/**
	 * Default constructor that returns an instance of a storage object
	 * 
	 * @param id
	 *    id of the storage
	 *    
	 * @param client
	 *    client of the storage
	 * 
	 * @param contents
	 *    contents of the storage
	 */
	public Storage(int id, String client, String contents) {
		this.id = id;
		this.client = client;
		this.contents = contents;
	}
	
	/**
	 * Returns the id of the storage
	 * 
	 * @return
	 *    Returns the id of the storage as an Integer
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets a new id for the storage
	 * 
	 * @param id
	 *    the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Returns the client name of the storage 
	 * 
	 * @return
	 *    Returns the name of the client as a String
	 */
	public String getClient() {
		return client;
	}
	
	/**
	 * Sets a new client name for the storage
	 * 
	 * @param client
	 *    The new client name
	 */
	public void setClient(String client) {
		this.client = client;
	}
	
	/**
	 * Returns the contents of the storage
	 * 
	 * @return
	 *    Returns the content as a String
	 */
	public String getContents() {
		return contents;
	}
	
	/**
	 * Sets new content for the storage
	 * 
	 * @param contents
	 *    The new contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
}
