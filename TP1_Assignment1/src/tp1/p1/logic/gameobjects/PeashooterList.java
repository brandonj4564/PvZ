package tp1.p1.logic.gameobjects;

/**
 * Manage a list of peashooters 
 *
 */
public class PeashooterList {
	/**
	 * maximum number of the list of peashooters
	 */
	private static final int CAPACITY = 32;
	private Peashooter[] peashooterList;
	
	// current number of peashooters and the first free index in the list
	private int counter = 0;
	
	/**
	 * Construct and initilize a list of peashooters
	 */
	public PeashooterList() {
		peashooterList = new Peashooter[CAPACITY];
	}
	
	/**
	 * Check if the list of peashooters is full
	 * 
	 * @return <code>true</code> if the list of peashooters is full
	 */
	public boolean isFull() {
		return counter == CAPACITY;
	}
	
	/**
	 * Check if the list of peashooters is empty
	 * 
	 * @return <code>true</code> if the list of peashooters is empty
	 */
	public boolean isEmpty() {
		return counter == 0;
	}
	
	/**
	 * Get the current number of peashooters in the list
	 * 
	 * @return current number of peashooters in the list
	 */
	public int size() {
		return counter;
	}
	
	/**
	 * Add a peashooter to the list
	 * 
	 * @param p input peashooter
	 * 
	 * @return <code>true</code> if the input peashooter is successfully added to the list
	 */
	public boolean addPeashooter(Peashooter p) {
		if (isFull() == true) {
			return false;
		}
		peashooterList[counter] = p;
		counter++;
		return true;
	}
	
	/**
	 * Remove a peashooter from the list
	 * 
	 * @param idx index of the peashooter which needs removing 
	 * 
	 * @return <code>true</code> if the peashooter exists and is successfully removed
	 */
	public boolean removePeashooter(int idx) {
		if (idx < 0 || idx > counter -1) {
			return false; 
		}
		for (int i = idx; i < counter - 1; i++) {
			peashooterList[i] = peashooterList[i + 1];
		}
		counter--;
		return true;
	}
	
	/**
	 * Update the peashooters in the list
	 */
	public void update() {
		for (int i = 0; i < counter; i++) {
			peashooterList[i].update();
		}
	}
	
	
	/**
	 * Get string representation of the peashooter at the tile <code>(row, col)</code> if exists
	 * 
	 * @param row row position of the tile
	 * @param col column position the tile
	 * 
	 * @return string representation of the peashooter at the tile <code>(row, col)</code> if exists, otherwise return an empty string
	 */
	public String getPeashooterStringAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].amIAt(row, col)) {
				return peashooterList[i].toString();
			}
		}
		return "";
	}
	
	/**
	 * Remove dead peashooters from the list
	 */
	public void removeDeadPeashooters() {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].isDead()) {
				removePeashooter(i);
			}
		}
	}
	
	/**
	 * Check if there is a peashooter in the list which can attack a zombie at position <code>(row, col)</code>
	 * 
	 * @param row row position of the zombie
	 * @param col column position of the zombie
	 * 
	 * @return <code>true</code> if there is a peashooter in the list which can attack a zombie at position <code>(row, col)</code>
	 */
	public boolean doesAttackZombieAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].canAttackZombieAt(row, col)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * restart the list to its initial state
	 */
	public void restart() {
		for (int i = 0; i < counter; i++) {
			peashooterList[i] = null;
			counter = 0;
		}
	}
	
	/**
	 * Check if there is a peashoter at tile <code>(row, col)</code>
	 * 
	 * @param row row position of the tile
	 * @param col column position of the tile
	 * 
	 * @return <code>true</code> if there is a peashooter at tile <code>(row, col)</code>
	 */
	public boolean isPeashooterAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].amIAt(row, col)) {
				return true;
			}
		}
		return false;
	}
}
