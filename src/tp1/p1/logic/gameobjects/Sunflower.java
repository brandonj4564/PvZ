package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

/**
 * Model a sunflower in a game
 *
 */
public class Sunflower {
	/**
	 * Initial endurance of a sunflower
	 */
	public static final int ENDURANCE = 1;
	
	/**
	 * Number of damage points that a sunflower causes on a zombie
	 */
	public static final int DAMAGE = 0;
	
	/**
	 * Number of coins to buy a sunflower
	 */
	public static final int COST = 20;
	
	/**
	 * Number of cycles needed for a sunflower to produce new sun coins
	 */
	public static final int GROWING_TIME = 3;
	
	/**
	 * Number of sun coins that a sunflower can produce each time
	 */
	public static final int PRODUCED_SUN_COINS = 10;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean isAttackedByZombie;
	private int currGrowingTime;
	
	/**
	 * Construct and initialize a sunflower 
	 * 
	 * @param r row position of the sunflower 
	 * @param c column position of the sunflower 
	 * @param currGame the game which the sunflower lives in
	 */
	public Sunflower(int r, int c, Game currGame) {
		row = r;
		col = c;
		game = currGame;
		remainingLive = ENDURANCE;
		isAttackedByZombie = false;
		currGrowingTime = -1;
	}
	
	
	/**
	 * get the decription of the sunflower
	 * 
	 * @return description of the sunflower
	 */
	public static String getDescription() {
		return Messages.SUNFLOWER_DESCRIPTION.formatted(Sunflower.COST, Sunflower.DAMAGE , Sunflower.ENDURANCE);
	}	
	
	/**
	 * Check if the sunflower is dead
	 * 
	 * @return <code>true</code> if the sunflower is dead
	 */
	public boolean isDead() {
		return remainingLive == 0;
	}
	
	/**
	 * Get the string representation of the sunflower
	 */
	public String toString() {
		return Messages.SUNFLOWER_ICON.formatted(remainingLive);
	}
	
	/**
	 * Check if the sunflower is at the tile <code>(r, c)</code>
	 * 
	 * @param r row position of the tile
	 * @param c column position of the tile 
	 * 
	 * @return <code>true</code> if the sunflower is at the tile <code>(r, c)</code>
	 */
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	
	/**
	 * Get the number of sun coins produced by the sunflower after the current cycle
	 * 
	 * @return the number of sun coins produced by the sunflower after the current cycle
	 */
	private int getProducedNewSunCoins() {
		if (currGrowingTime == Sunflower.GROWING_TIME) {
			currGrowingTime = 0;
			return Sunflower.PRODUCED_SUN_COINS;
		}
		return 0;
	}
		
	
	/**
	 * Update the state of the sunflower
	 */
	public void update() {
		updateCurrGrowingTime();
		updateSunCoinsProduction();
		
		if (game.checkIfAttackedByZombie(row, col)) {
			if (col == Game.NUM_COLS - 1) {
				remainingLive = 0;
			} else {
				remainingLive = remainingLive - Zombie.DAMAGE;
			}
		}
	}
	
	/**
	 * Update the current growing time
	 */
	private void updateCurrGrowingTime() {
		if (remainingLive > 0) {
			currGrowingTime++;
		}
	}

	/**
	 * Update the information about the production of new sun coins and inform it to game
	 */
	private void updateSunCoinsProduction() {
		int receivedSunCoins = getProducedNewSunCoins();
		if (receivedSunCoins != 0) {
			game.receiveNewSunCoins();
		}
	}
	
}
