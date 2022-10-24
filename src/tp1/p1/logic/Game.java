package tp1.p1.logic;
import tp1.p1.control.Level;
import tp1.p1.logic.gameobjects.*;
import tp1.p1.view.Messages;

import java.util.Random;



/**
 * Encapsulate the logic and state of the game
 *
 */
public class Game {
	/**
	 * Number of rows in a game
	 */
	public static final int NUM_ROWS = 4;
	/**
	 * Number of columns in a game
	 */
	public static final int NUM_COLS = 8;
	
	private int currCycleNumber;
	private PlantsManager plantsManager;
	private Random rand;
	private ZombiesManager zombiesManager;
	private long seed;
	private Level level;
	private int sunCoins;
	
	/**
	 * Construct and initial a game
	 * 
	 * @param inputSeed input seed used for pseudo-random generator 
	 * @param inputLevel input level of the game
	 */
	public Game(long inputSeed, Level inputLevel) {
		seed = inputSeed;
		level = inputLevel;
		rand = new Random(seed);
		
		plantsManager = new PlantsManager(this);
		zombiesManager = new ZombiesManager(this, level, rand);
		
		currCycleNumber = 0;
		sunCoins = 50;
	}
	
	/**
	 * Get string representation of a game object at position <code>(col, row)</code> if it exists
	 * 
	 * @param col column position of the game object
	 * @param row row position of the game object
	 * 
	 * @return string representation of a game object if it exists at position <code>(col, row)</code>, otherwise return an empty string
	 */
	public String positionToString(int col, int row) {
		// This procedure acts as an short-circuit evaluation
		String output = "";
		output = plantsManager.getSunflowerStringAt(row, col);
		if (output.isEmpty()) {
			output = plantsManager.getPeashooterStringAt(row, col);
		}
		if (output.isEmpty()) {
			output = zombiesManager.getZombieStringAt(row, col);
		}
		
		return output;
	}
	
	/**
	 * Add new plant based one the provided name and position and pay for the new plant is the adding is successful
	 * 
	 * @param plantName name of the new plant
	 * @param row row position of the new plant
	 * @param col column position of the new plant
	 * 
	 * @return <code>true</code> if the new plant is successfully added to the game
	 */
	public boolean addNewPlant(String plantName, int row, int col) {
		boolean addingResult = plantsManager.addPlant(plantName, row, col);
		if (addingResult) {
			payForPlant(plantName);
		}
		return addingResult;
	}
	
	
	/**
	 * Add new zombie to game
	 * 
	 * @return <code>true</code> if the new zombie is successfully added to the game
	 */
	public boolean addNewZombie() {
		return zombiesManager.addZombie();
	}
	
	/**
	 * Update number of sun coins after paying a cost
	 * 
	 * @param cost number of coins to pay
	 */
	private void updateBalanceAfterPay(int cost) {
		sunCoins -= cost;
	}
	
	/**
	 * Pay for a plant based on its name
	 * 
	 * @param plantName name of a plant
	 */
	private void payForPlant(String plantName) {
		if (plantName.equals("sunflower")) {
			updateBalanceAfterPay(Sunflower.COST);
		} 
		if (plantName.equals("peashooter")) {
			updateBalanceAfterPay(Peashooter.COST);
		}
	}
	
	/**
	 * Check if number of coins is enough to buy a plant
	 * 
	 * @param cost number of coins required to buy a plant
	 * 
	 * @return <code>true</code> if the number of coins left is enough to buy the plant
	 */
	public boolean hasEnoughMoney(int cost) {
		return sunCoins >= cost;
	}
	
	
	/**
	 * Check if the plant at position <code>(row, col)</code> is attacked by a zombie
	 * 
	 * @param row row position of the plant
	 * @param col column position of the plant
	 * 
	 * @return <code>true</code> if the plant at position <code>(row, col)</code> is attacked by a zombie
	 */
	public boolean checkIfAttackedByZombie(int row, int col) {
		return zombiesManager.checkForEmptyTileAt(row, col + 1) == false;
	}
	
	/**
	 * Check if a peashooter attacks the zombie at position (row, col)
	 * 
	 * @param row row position of the zombie
	 * @param col column position of the zombie
	 * 
	 * @return <code>true</code> if a peashooter attacks the zombie at position (row, col) 
	 */
	public boolean checkIfAttackedByPeashooter(int row, int col) {
		// Peashooters only attack on-board zombies 
		if (col == Game.NUM_COLS) {
			return false;
		}
		return plantsManager.anyoneAttackZombieAt(row, col);
	}
	
	/**
	 * Check if there are no plants and other zombies next to and on the left of the zombie at position <code>(row, col)</code>
	 * @param row row condition of the zombie
	 * @param col column condition of the zombie
	 * 
	 * @return <code>true</code> f there are no plants and other zombies next to and on the left of the zombie at position <code>(row, col)</code>
	 */
	public boolean doesAllowZombieMoveForward(int row, int col) {
		return plantsManager.checkForEmptyTileAt(row, col) && zombiesManager.checkForEmptyTileAt(row, col - 1);
	}
	
	/**
	 * Check whether a tile is not occupied by a zombie
	 * 
	 * @param row row position of the tile
	 * @param col column condition of the tile
	 * 
	 * @return <code>true</code> if the tile is not occupied by a zombie
	 */
	public boolean isTileEmptyFromZombie(int row, int col) {
		return zombiesManager.checkForEmptyTileAt(row, col);
	}
	
	/**
	 * Update game state
	 */
	public void update() {
		cycleCount();
		
		plantsManager.update();
		zombiesManager.update();
		
		removeDeadEntities();
	}
	
	/**
	 * Remove dead game objects at the end of each cycle
	 */
	private void removeDeadEntities() {
		plantsManager.removeDeadPlants();
		zombiesManager.removeDeadZombies();
	}
	
	/**
	 * Update game cycle number
	 */
	private void cycleCount() {
		currCycleNumber++;
	}
	
	/**
	 * Perform action after receiving new sun coins
	 */
	public void receiveNewSunCoins() {
		sunCoins += Sunflower.PRODUCED_SUN_COINS;
	}
	
	/**
	 * Check if the player wins 
	 * 
	 * @return <code>true</code> if the player wins
	 */
	public boolean hasPlayerWon() {
		return zombiesManager.haveZombiesLost();
	}
	
	/**
	 * Check if the zombies win
	 * 
	 * @return <code>true</code> if the zombies win
	 */
	public boolean hasZombiesWon() {
		return zombiesManager.checkIfZombiesWon();
	}
	
	/**
	 * Check if the game winner has been found or if the game should end
	 * 
	 * @return <code>true</code> if the game winner has been found
	 */
	public boolean hasFoundWinner() {
		return hasPlayerWon() || hasZombiesWon();
	}
	
	/**
	 * Restart game to initial configuration
	 */
	public void restart() {
		currCycleNumber = 0;
		sunCoins = 50;
		rand = new Random(seed);
		
		plantsManager.restart();
		zombiesManager.restart();
	}
	
	/**
	 * Get information about current the current number of cycle
	 * 
	 * @return string information about the current number of cycle
	 */
	public String getNumOfCyclesInfo() {
		return Messages.NUMBER_OF_CYCLES + " " + currCycleNumber + "\n";
	}
	
	/**
	 * Get information about the current number of sun coins
	 * 
	 * @return string information about the current number of sun coins
	 */
	public String getSunCoinsInfo() {
		return Messages.NUMBER_OF_COINS + " " + sunCoins + "\n";
	}
	
	/**
	 * Get information about the remaining zombies
	 * 
	 * @return string information about the remaining zombies
	 */
	public String getRemainingZombiesInfo() {
		return zombiesManager.getRemainingZombiesInfo();
	}
	
}
