package tp1.p1.logic;

import java.util.Random;

import tp1.p1.control.Level;
import tp1.p1.logic.Game;
import tp1.p1.logic.gameobjects.Zombie;
import tp1.p1.logic.gameobjects.ZombieList;
import tp1.p1.view.Messages;

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

	/**
	 * Construct and initial a ZombiesManager
	 * 
	 * @param game  the current Game that the ZombiesManager works for
	 * @param level the Level of the current Game
	 * @param rand  the psedu-random generator
	 */
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

	/**
	 * Generate a random row for adding a new zombie and add the zombie to the
	 * current game
	 * 
	 * @return <code>true</code> if the new zombie is successfully added to the game
	 */
	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	/**
	 * Add a new zombie to the current Game
	 * 
	 * @param row random row position of the new zombie
	 * 
	 * @return <code>true</code> if the zombie is successfully added to the game.
	 */
	public boolean addZombie(int row) {
		// Newly-added zombies will be put in the queue or in column Game.NUM_COLS
		// before advancing into the board
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && isPositionEmpty(Game.NUM_COLS, row);
		if (canAdd) {
			Zombie newZombie = new Zombie(row, Game.NUM_COLS, game);
			boolean addingResult = zombies.addZombie(newZombie);
			if (addingResult) {
				remainingZombies--;
			}
		}
		return canAdd;
	}

	/**
	 * Get the remaining number of zombies
	 * 
	 * @return the remaining number of zombies
	 */
	private int getRemainingZombies() {
		return remainingZombies;
	}

	// This one seems to be redundant as it has the same purpose as
	// checkForEmptyTileAt()
	// The reason we switch from isPositionEmpty() to checkForEmptyTileAt() is
	// because it is more natural to say row first and column second
	// Remember that isPositionEmpty(int col, int row) is what's expected in the
	// provided code
	/**
	 * Check if tile at position <code> (col, row) </code> is not occupied by a
	 * zombie
	 * 
	 * @param col column position of the tile
	 * @param row row position of this tile
	 * 
	 * @return <code>true</code> if the tile is not occupied by a zombie
	 */
	public boolean isPositionEmpty(int col, int row) {
		return checkForEmptyTileAt(row, col);
	}

	/**
	 * Get string representation of a zombie at position <code>(row, col)</code>
	 * 
	 * @param row row position of the zombie
	 * @param col column position of the zombie
	 * 
	 * @return string representation of the zombie
	 */
	public String getZombieStringAt(int row, int col) {
		return zombies.getZombieStringAt(row, col);
	}

	/**
	 * Check if a zombie is not occupying the tile at position
	 * <code>(row, col)</code>
	 * 
	 * @param row row position of the tile
	 * @param col column position of the tile
	 * 
	 * @return <code>true</code> if no zombie is occupying the tile
	 */
	public boolean checkForEmptyTileAt(int row, int col) {
		return zombies.isZombieAt(row, col) == false;
	}

	/**
	 * Remove dead zombies
	 */
	public void removeDeadZombies() {
		zombies.removeDeadZombies();
	}

	/**
	 * Update the ZombiesManager state
	 */
	public void update() {
		zombies.update();
	}

	/**
	 * Check if Zombie has lost the current Game or the player has won the current
	 * Game
	 * 
	 * @return <code>true</code> if Zombie has lost the current Game
	 */
	public boolean haveZombiesLost() {
		return remainingZombies == 0 && zombies.isEmpty();
	}

	/**
	 * Check if Zombie has won the current Game
	 * 
	 * @return <code>true</code> if Zombie has won the current Game
	 */
	public boolean checkIfZombiesWon() {
		return zombies.checkIfZombiesWon();
	}

	/**
	 * Restart the ZombiesManager to the initial state
	 * 
	 * @param newRand the initial pseudo-random generator
	 */
	public void restart(Random newRand) {
		rand = newRand;
		remainingZombies = level.getNumberOfZombies();
		zombies.restart();
	}

	/**
	 * Get information about the remaining number of zombies
	 * 
	 * @return string information about the remaining number of zombies
	 */
	public String getRemainingZombiesInfo() {
		return Messages.REMAINING_ZOMBIES + " " + remainingZombies + "\n";
	}
}
