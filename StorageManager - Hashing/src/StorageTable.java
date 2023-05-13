/**
 * The StorageTable class extends the HashMap class and acts as a database of 
 * Storages. It provides constant time for both insertion and deletion. The 
 * class also implements the Serializable interface.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.io.Serializable;
import java.util.HashMap;

public class StorageTable extends HashMap<Integer, Storage> 
implements Serializable {
	
	/**
	 * Manually inserts a Storage object into the table using the specified 
	 * key.
	 * 
	 * @param storageId
	 *    The unique key for the Storage object
	 *    
	 * @param storage
	 *    The storage object to insert into the table
	 *    
	 * @precondition
	 *    storageId ≥ 0 and does not already exist in the table.
	 *    Storage is not equal to null
	 *    
	 * @postcondition
	 *    The Storage has been inserted into the table with the indicated key.
	 *    
	 * @throws IllegalArgumentException
	 *    if any of the preconditions is not met.
	 */
	public void putStorage(int storageId, Storage storage) 
			throws IllegalArgumentException {
		if (storage == null) {
			throw new IllegalArgumentException("storage is null");
		}
		else if (storageId < 0 || super.containsKey(storageId)) {
			throw new IllegalArgumentException("storage id is not valid or it "
					+ "already exsits on the table");
		}
		else {
			super.put(storageId, storage);
		}
	}
	
	/**
	 * Retrieve the Storage from the table having the indicated storageID. If 
	 * the requested storageID does not exist in the StorageTable, return null.
	 * 
	 * @param storageId
	 *    Key of the Storage to retrieve from the table.
	 *    
	 * @return
	 *    A Storage object with the given key, null otherwise.
	 */
	public Storage getStorage(int storageId) {
		return super.get(storageId);
	}
}
