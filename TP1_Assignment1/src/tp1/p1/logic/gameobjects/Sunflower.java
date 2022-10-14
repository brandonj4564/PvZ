package tp1.p1.logic.gameobjects;
import tp1.p1.logic.Game;

public class Sunflower {
	public static final int ENDURANCE = 1;
	public static final int DAMAGE = 0;
	public static final int COST = 20;
	public static final int GROWING_TIME = 3;
	public static final int PRODUCED_SUN_COINS = 10;
	
	private int row;
	private int col;
	private int remainingLive;
	private Game game;
	private boolean isAttackedByZombie;
	private int currGrowingTime;
	
	public Sunflower(int r, int c, Game currGame) {
		row = r;
		col = c;
		game = currGame;
		remainingLive = ENDURANCE;
		isAttackedByZombie = false;
		currGrowingTime = -1;
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
		return "S[0" + remainingLive + "]";
	}
	
	public boolean amIAt(int r, int c) {
		return row == r && col == c;
	}
	
	
	private int getProducedNewSunCoins() {
		if (currGrowingTime == Sunflower.GROWING_TIME) {
			currGrowingTime = 0;
			return Sunflower.PRODUCED_SUN_COINS;
		}
		return 0;
	}
		
	
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
	
	private void updateCurrGrowingTime() {
		if (remainingLive > 0) {
			currGrowingTime++;
		}
	}

	private void updateSunCoinsProduction() {
		int receivedSunCoins = getProducedNewSunCoins();
		if (receivedSunCoins != 0) {
			game.receiveNewSunCoins();
		}
	}
	
}
