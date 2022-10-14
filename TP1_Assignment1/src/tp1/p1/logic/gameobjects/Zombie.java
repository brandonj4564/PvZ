package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;

public class Zombie {
	public static final int DAMAGE = 1;
	public static final int RESISTENCE = 5;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean getAttackedByPeashooter;
	private int currMovementCycle;
	
	public Zombie(int r, int c, Game currGame) {
		row = r;
		col = c;
		game = currGame;
		remainingLive = RESISTENCE;
		currMovementCycle = -1;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	
	public boolean isDead() {
		return remainingLive == 0;
	}
	
	public String toString() {
		return "Z[0" + remainingLive + "]";
	}
	
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	public void update() {
		currMovementCycle++;
		if (game.checkIfAttackedByPeashooter(row, col)) {
			remainingLive = remainingLive - Peashooter.DAMAGE;
		}
		
		if (currMovementCycle == 2) {
			System.out.println(canMoveForward(row, col));
			if (canMoveForward(row, col)) {
				col--;
				currMovementCycle = 0;
			}
			else {
				currMovementCycle--;
			}
		}
	}
	
	private boolean canMoveForward(int row, int col) {
		return game.doesAllowZombieMoveForward(row, col);
	}
	
	public boolean hasReachedLHS() {
		return col == 0;
	}
	
}
