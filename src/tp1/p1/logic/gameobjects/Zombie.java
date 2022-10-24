package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;
import tp1.p1.view.Messages;

/**
 * Model a zombie in a game
 *
 */
public class Zombie {
	/**
	 * Number of damage points that a zombie causes on a plant
	 */
	public static final int DAMAGE = 1;
	
	/**
	 * Initial resistence of a zombie
	 */
	public static final int RESISTENCE = 5;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean getAttackedByPeashooter;
	private int currMovementCycle;
	
	/**
	 * Construct and initialize a zombie
	 * 
	 * @param r row position of the zombie
	 * @param c column position of the zombie 
	 * @param currGame the game which the zombie lives in
	 */
	public Zombie(int r, int c, Game currGame) {
		row = r;
		col = c;
		game = currGame;
		remainingLive = RESISTENCE;
		currMovementCycle = -1;
	}	
	
	/**
	 * Check if the zombie is dead
	 * 
	 * @return <code>true</code> if the zombie is dead
	 */
	public boolean isDead() {
		return remainingLive == 0;
	}
	
	/**
	 * Get the string representation of the sunflower
	 */
	public String toString() {
		return Messages.ZOMBIE_ICON.formatted(remainingLive);
	}
	
	/**
	 * Check if the zombie is at the tile <code>(r, c)</code>
	 * 
	 * @param r row position of the tile
	 * @param c column position of the tile 
	 * 
	 * @return <code>true</code> if the zombie is at the tile <code>(r, c)</code>
	 */
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	/**
	 * Update the state of the sunflower
	 */
	public void update() {
		currMovementCycle++;
		if (game.checkIfAttackedByPeashooter(row, col)) {
			remainingLive = remainingLive - Peashooter.DAMAGE;
		}
		
		if (currMovementCycle == 2) {
			if (canMoveForward(row, col)) {
				col--;
				currMovementCycle = 0;
			}
			else {
				currMovementCycle--;
			}
		}
	}
	
	/**
	 * Check if there are any game objects next to and on the left of the zombie so that it can move forward
	 * 
	 * @param row row position of the zombie
	 * @param col column position of the zombie if there are any game objects next to and on the left of the zombie
	 * 
	 * @return <code>true<code/>
	 */
	private boolean canMoveForward(int row, int col) {
		return game.doesAllowZombieMoveForward(row, col);
	}
	
	/**
	 * Check if the zombie has reached L.H.S
	 * 
	 * @return <code>true</code> if the zombie has reached L.H.S
	 */
	public boolean hasReachedLHS() {
		return col == 0;
	}
	
}
