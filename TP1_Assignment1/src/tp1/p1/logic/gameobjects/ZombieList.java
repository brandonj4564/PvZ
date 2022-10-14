package tp1.p1.logic.gameobjects;

import tp1.p1.logic.Game;

public class ZombieList {
	private Zombie[] zombieList;
	
	// the current number of elements and the first free position (index)
	private int counter;
	
	public ZombieList(int remainingZombie) {
		zombieList = new Zombie[remainingZombie];
		counter = 0;
	}
	
	public boolean isFull() {
		return counter == zombieList.length;
	}
	
	public boolean isEmpty() {
		return counter == 0;
	}
	
	public int size() {
		return counter;
	}
	
	public boolean addZombie(Zombie z) {
		if (isFull() == true) {
			return false;
		}
		zombieList[counter] = z;
		counter++;
		return true;
	}
	
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
	
	
	public String getZombieAt(int col, int row) {
		for (int i = 0; i < counter; i++) {
			Zombie currZombie = zombieList[i];
			if (currZombie.amIAt(row, col)) {
				return currZombie.toString();
			}
		}
		return "";
	}
	
	public boolean isZombieAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].amIAt(row, col)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkForEmptyTile(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].amIAt(row, col)) {
				return false;
			}
		}
		return true;
	}
	
	public void removeDeadZombies() {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].isDead()) {
				removeZombie(i);
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < counter; i++) {
			zombieList[i].update();
		}
	}
	
	public boolean checkIfZombiesWon() {
		for (int i = 0; i < counter; i++) {
			if (zombieList[i].hasReachedLHS()) {
				return true;
			}
		}
		return false;
	}
}
