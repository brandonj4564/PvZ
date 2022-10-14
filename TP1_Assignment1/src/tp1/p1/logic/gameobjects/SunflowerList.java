package tp1.p1.logic.gameobjects;

public class SunflowerList {
	private static final int CAPACITY = 32;
	private Sunflower[] sunflowerList;
	
	// the current number of elements and the first free position (index)
	private int counter = 0;
	
	public SunflowerList() {
		sunflowerList = new Sunflower[CAPACITY];
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
	
	public boolean addSunflower(Sunflower s) {
		if (isFull() == true) {
			return false;
		}
		sunflowerList[counter] = s;
		counter++;
		return true;
	}
	
	
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
	
	public void removeDeadSunflowers() {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].isDead()) {
				removeSunflower(i);
			}
		}
	}
	
	public boolean checkForEmptyPile(int row, int col) {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].amIAt(row, col)) {
				return false;
			}
		}
		return true;
	}
	
	public String getSunflowerAt(int col, int row) {
		for (int i = 0; i < counter; i++) {
			if (sunflowerList[i].amIAt(row, col)) {
				return sunflowerList[i].toString();
			}
		}
		return "";
	}
	
	
	public void update() {
		for (int i = 0; i < counter; i++) {
			sunflowerList[i].update();
		}
	}
}
