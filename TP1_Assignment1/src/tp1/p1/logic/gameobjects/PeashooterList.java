package tp1.p1.logic.gameobjects;

public class PeashooterList {
	private static final int CAPACITY = 32;
	private Peashooter[] peashooterList;
	
	// the current number of elements and the first free position (index)
	private int counter = 0;
	
	public PeashooterList() {
		peashooterList = new Peashooter[CAPACITY];
	}
	
	public boolean isFull() {
		return counter == CAPACITY;
	}
	
	public boolean isEmpty() {
		return counter == 0;
	}
	
	public int size() {
		return counter;
	}
	
	public boolean addPeashooter(Peashooter p) {
		if (isFull() == true) {
			return false;
		}
		peashooterList[counter] = p;
		counter++;
		return true;
	}
	
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
	
	public void update() {
		for (int i = 0; i < counter; i++) {
			peashooterList[i].update();
		}
	}
	
	
	public boolean checkForEmptyPile(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].amIAt(row, col)) {
				return false;
			}
		}
		return true;
	}
	
	public String getPeashooterAt(int col, int row) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].amIAt(row, col)) {
				return peashooterList[i].toString();
			}
		}
		return "";
	}
	
	public void removeDeadPeashooters() {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].isDead()) {
				removePeashooter(i);
			}
		}
	}
	
	public boolean doesAttackZombieAt(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (peashooterList[i].canAttackZombieAt(row, col)) {
				return true;
			}
		}
		return false;
	}
}
