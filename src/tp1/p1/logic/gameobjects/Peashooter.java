package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

/**
 * Model a peashooter in a game
 *
 */
public class Peashooter {
	/**
	 * Initial endurance of a peashooter
	 */
	
	public static final int ENDURANCE = 3;
	
	/**
	 * Number of damage points that a peashooter causes on a zombie
	 */
	public static final int DAMAGE = 1;
	
	/**
	 * Number of coins to buy a peashooter
	 */
	public static final int COST = 50;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean isAttackedByZombie; 
	
	/**
	 * Construct and initialize a peashooter 
	 * 
	 * @param r row position of the peashooter 
	 * @param c column position of the peashooter 
	 * @param currentGame the game which the peashooter lives in
	 */
	public Peashooter(int r, int c, Game currentGame) {
		row = r;
		col = c;
		game = currentGame;
		remainingLive = ENDURANCE;
	}
	
	
	/**
	 * get the decription of the peashooter
	 * 
	 * @return description of the peashooter
	 */
	public static String getDescription() {
		return Messages.PEASHOOTER_DESCRIPTION.formatted(Peashooter.COST, Peashooter.DAMAGE, Peashooter.ENDURANCE);
	}
	
	
	/**
	 * Check if the peashooter is dead
	 * 
	 * @return <code>true</code> if the peashooter is dead
	 */
	public boolean isDead() {
		return remainingLive == 0;
	}
	
	
	/**
	 * Get the string representation of the peashooter
	 */
	public String toString() {
		return Messages.PEASHOOTER_ICON.formatted(remainingLive);
	}
	
	
	/**
	 * Check if the peashooter is at the tile <code>(r, c)</code>
	 * 
	 * @param r row position of the tile
	 * @param c column position of the tile 
	 * 
	 * @return <code>true</code> if the peashooter is at the tile <code>(r, c)</code>
	 */
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	
	/**
	 * Update the state of the peashooter
	 */
	public void update() {
		if (game.checkIfAttackedByZombie(row, col)) {
			if (col == Game.NUM_COLS - 1) {
				remainingLive = 0;
			} else {
				remainingLive = remainingLive - Zombie.DAMAGE;
			}
		}
	}
	
	/**
	 * Check if the peashooter can attack the zombie at <code>(row, col)</code>
	 * 
	 * @param row row position of the zombie 
	 * @param col column position of the zombie
	 * 
	 * @return <code>true</code> if the peashooter can attack the zombie at <code>(row, col)</code>
	 */
	public boolean canAttackZombieAt(int row, int col) {
		return this.row == row && this.col < col;
	}
}
