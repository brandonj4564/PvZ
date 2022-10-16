package tp1.p1.logic.gameobjects;

/**
 * Manage a list of sunflowers 
 *
 */
public class SunflowerList {
	/**
	 * maximum number of the list of sunflowers
	 */
	private static final int CAPACITY = 32;
	private Sunflower[] sunflowerList;
	
	// current number of sunflowers and the first free index in the list
	private int counter = 0;
	
	/**
	 * Construct and initilize a list of sunflowers
	 */
	public SunflowerList() {
		sunflowerList = new Sunflower[CAPACITY];
	}
	
	/**
	 * Check if the list of sunflowers is full
	 * 
	 * @return <code>true</code> if the list of sunflowers is full
	 */
	public boolean isFull() {
		return counter == CAPACITY;
	}
	
	/**
	 * Check if the list of sunflowers is empty
	 * 
	 * @return <code>true</code> if the list of sunflowers is empty
	 */
	public boolean isEmpty() {
		return counter == 0;
	}
	
	/**
	 * Get the current number of sunflowers in the list
	 * 
	 * @return current number of sunflowers in the list
	 */
	public int size() {
		return counter;
	}
	
	
	/**
	 * Add a sunflower to the list
	 * 
	 * @param s input sunflower
	 * 
	 * @return <code>true</code> if the input sunflower is successfully added to the list
	 */
	public boolean addSunflower(Sunflower s) {
		if (isFull() == true) {
			return false;
		}
		sunflowerList[counter] = s;
		counter++;
		return true;
	}
	
	
	/**
	 * Remove a sunflower from the list
	 * 
	 * @param idx index of the sunflower which needs removing 
	 * 
	 * @return <code>true</code> if the sunflower exists and is successfully removed
	 */
	public boolean removeSunflower(int idx) {
		if (idx < 0 || idx > counter -1) {
			return false; 
		}
		for (int i = idx; i < counter - 1; i++) {
			sunflowerList[i] = sunflowerList[i + 1];
		}
		counter--;
		return true;
	}
	
	/**
	 * Remove dead sunflowers from the list
	 */
	public void removeDeadSunflowers() {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].isDead()) {
				removeSunflower(i);
			}
		}
	}

	
	/**
	 * Get string representation of the sunflower at the tile <code>(row, col)</code> if exists
	 * 
	 * @param row row position of the tile
	 * @param col column position the tile
	 * 
	 * @return string representation of the sunflower at the tile <code>(row, col)</code> if exists, otherwise return an empty string
	 */
	public String getSunflowerStringAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].amIAt(row, col)) {
				return sunflowerList[i].toString();
			}
		}
		return "";
	}
	
	
	/**
	 * Update the sunflowers in the list
	 */
	public void update() {
		for (int i = 0; i < counter; i++) {
			sunflowerList[i].update();
		}
	}
	
	/**
	 * restart the list to its initial state
	 */
	public void restart() {
		for (int i = 0; i < counter; i++) {
			sunflowerList[i] = null;
			counter = 0;
		}
	}
	
	
	/**
	 * Check if there is a sunflower at tile <code>(row, col)</code>
	 * 
	 * @param row row position of the tile
	 * @param col column position of the tile
	 * 
	 * @return <code>true</code> if there is a sunflower at tile <code>(row, col)</code>
	 */
	public boolean isSunflowerAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].amIAt(row, col)) {
				return true;
			}
		}
		return false;
	}
}
