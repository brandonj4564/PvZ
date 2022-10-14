package tp1.p1.logic;

import java.util.Random;

import tp1.p1.control.Level;
import tp1.p1.logic.gameobjects.Zombie;
import tp1.p1.logic.gameobjects.ZombieList;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList(this.remainingZombies);
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}
	
	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(Game.NUM_ROWS);
	}
	
	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(Game.NUM_COLS, row);
		System.out.println("ZombieManager > addZombie()");
		System.out.println("Can add zombie at " + row + "? " + canAdd + "\n");
		if(canAdd) {
			Zombie newZombie = new Zombie(row, Game.NUM_COLS, game);
			boolean addingResult = zombies.addZombie(newZombie);
			remainingZombies--;
		}
		return canAdd;
	}
	
	// TODO fill your code
	// Get the remaining zombies
		public int getRemainingZombies() {
			return remainingZombies;
		}
		

	private boolean isPositionEmpty(int col, int row) {
		if (zombies.isZombieAt(row, col)) {
			return false;
		}
		return true;
	}	
	
	public String checkAndGetZombieAt(int col, int row) {
		return zombies.getZombieAt(col, row);
	}
	
	public void printRemainingZombies() {
		System.out.println("Remaining zombies: " + remainingZombies);
	}
	
	public boolean isZombieAt(int row, int col) {
		return zombies.isZombieAt(row, col);
	}
	
	public boolean checkIfTileEmpty(int row, int col) {
		return zombies.checkForEmptyTile(row, col);
	}
	
	public void removeDeadZombies() {
		zombies.removeDeadZombies();
	}
	
	public void update() {
		zombies.update();
	}
	
	public boolean haveZombiesLost() {
		return remainingZombies == 0 && zombies.isEmpty();
	}
	
	public boolean checkIfZombiesWon() {
		return zombies.checkIfZombiesWon();
	}
}
