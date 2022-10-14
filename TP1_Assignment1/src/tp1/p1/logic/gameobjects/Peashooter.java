package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;

public class Peashooter {
	public static final int ENDURANCE = 3;
	public static final int DAMAGE = 1;
	public static final int COST = 50;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean isAttackedByZombie; 
	
	public Peashooter(int r, int c, Game currentGame) {
		row = r;
		col = c;
		game = currentGame;
		remainingLive = ENDURANCE;
	}
	
	public static String getDescription() {
		return "";
	}
	
	public void getAttackedByZombie(boolean isAttacked) {
		isAttackedByZombie = isAttacked;
	}
	
	public boolean isDead() {
		return remainingLive == 0;
	}
	
	public String toString() {
		return "P[0" + remainingLive + "]";
	}
	
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	public void update() {
		if (game.checkIfAttackedByZombie(row, col)) {
			if (col == Game.NUM_COLS - 1) {
				remainingLive = 0;
			} else {
				remainingLive = remainingLive - Zombie.DAMAGE;
			}
		}
	}
	
	public boolean canAttackZombieAt(int row, int col) {
		return this.row == row && this.col < col;
	}
}
