package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;

/**
 * Manage a list of zombies 
 *
 */
public class ZombieList {
	private Zombie[] zombieList;
	
	// current number of sunflowers and the first free index in the list
	private int counter;
	
	/**
	 * Construct and initialize a list of zombies
	 * 
	 * @param remainingZombie the number of remaining zombies
	 */
	public ZombieList(int remainingZombie) {
		zombieList = new Zombie[remainingZombie];
		counter = 0;
	}
	
	/**
	 * Check if the list of zombies is full
	 * 
	 * @return <code>true</code> if the list of zombies is full
	 */
	public boolean isFull() {
		return counter == zombieList.length;
	}
	
	/**
	 * Check if the list of zombies is empty
	 * 
	 * @return <code>true</code> if the list of zombies is empty
	 */
	public boolean isEmpty() {
		return counter == 0;
	}
	
	/**
	 * Get the current number of zombies in the list
	 * 
	 * @return current number of zombies in the list
	 */
	public int size() {
		return counter;
	}
	
	/**
	 * Add a zombie to the list
	 * 
	 * @param z input sunflower
	 * 
	 * @return <code>true</code> if the input zombie is successfully added to the list
	 */
	public boolean addZombie(Zombie z) {
		if (isFull() == true) {
			return false;
		}
		zombieList[counter] = z;
		counter++;
		return true;
	}
	
	/**
	 * Remove a zombie from the list
	 * 
	 * @param idx index of the zombie which needs removing 
	 * 
	 * @return <code>true</code> if the zombie exists and is successfully removed
	 */
	public boolean removeZombie(int idx) {
		if (idx < 0 || idx > counter -1) {
			return false; 
		}
		for (int i = idx; i < counter - 1; i++) {
			zombieList[i] = zombieList[i + 1];
		}
		counter--;
		return true;
	}
	
	/**
	 * Get string representation of the zombie at the tile <code>(row, col)</code> if exists
	 * 
	 * @param row row position of the tile
	 * @param col column position the tile
	 * 
	 * @return string representation of the zombie at the tile <code>(row, col)</code> if exists, otherwise return an empty string
	 */
	public String getZombieStringAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			Zombie currZombie = zombieList[i];
			if (currZombie.amIAt(row, col)) {
				return currZombie.toString();
			}
		}
		return "";
	}
	
	/**
	 * Check if there is a zombie at tile <code>(row, col)</code>
	 * 
	 * @param row row position of the tile
	 * @param col column position of the tile
	 * 
	 * @return <code>true</code> if there is a zombie at tile <code>(row, col)</code>
	 */
	public boolean isZombieAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].amIAt(row, col)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Remove dead zombies from the list
	 */
	public void removeDeadZombies() {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].isDead()) {
				removeZombie(i);
			}
		}
	}
	
	/**
	 * Update the zombies in the list
	 */
	public void update() {
		for (int i = 0; i < counter; i++) {
			zombieList[i].update();
		}
	}
	
	/**
	 * Check if the zombies have won the game
	 * 
	 * @return <code>true</code> if at least one zombie has reached the L.H.S
	 */
	public boolean checkIfZombiesWon() {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].hasReachedLHS()) {
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
			zombieList[i] = null;
			counter  = 0;
		}
	}
}
