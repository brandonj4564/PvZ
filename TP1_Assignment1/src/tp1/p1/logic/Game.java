package tp1.p1.logic;
import tp1.p1.control.Level;
import tp1.p1.logic.gameobjects.*;
import java.util.Random;

public class Game {
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	
	private int currCycleNumber;
	private int currSuncoinNumber;
	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	// private ZombieList zombieList;
	private Random rand;
	private ZombiesManager zombiesManager;
	private long seed;
	private Level level;
	private int sunCoins;
	
	public Game(long inputSeed, Level inputLevel) {
		seed = inputSeed;
		level = inputLevel;
		rand = new Random(seed);
		
		sunflowerList = new SunflowerList();
		peashooterList = new PeashooterList();
		zombiesManager = new ZombiesManager(this, level, rand);
		
		currCycleNumber = 0;
		sunCoins = 50;
	}
	
	public String positionToString(int col, int row) {
		String output = "";
		output = sunflowerList.getSunflowerAt(col, row);
		if (output.isEmpty()) {
			output = peashooterList.getPeashooterAt(col, row);
		}
		if (output.isEmpty()) {
			output = zombiesManager.checkAndGetZombieAt(col, row);
		}
		return output;
	}
	
	public int getSunCoins() {
		return sunCoins;
	}
	
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}
	
	
	public void addSunflower(int row, int col) {
		Sunflower newSunflower = new Sunflower(row, col, this);
		sunflowerList.addSunflower(newSunflower);
	}
	
	public void addPeashooter(int row, int col) {
		Peashooter newPeashooter = new Peashooter(row, col, this);
		peashooterList.addPeashooter(newPeashooter);
	}
	
	
	public boolean addPlant(String plantName, int row, int col) {
		boolean canAddPlant = canAdd(plantName, row, col);
		
		if (canAddPlant) {
			if (plantName.equals("sunflower")) {
				addSunflower(row, col);
				payForPlant(Sunflower.COST);
			}
			else {
				addPeashooter(row, col);
				payForPlant(Peashooter.COST);
			}
		}
		
		return canAddPlant;
		
	}
	
	private boolean canAdd(String plantName, int row, int col) {
		// Using short-circuit evaluation for optimization
		boolean checkResult = canPayPlant(plantName) && sunflowerList.checkForEmptyPile(row, col) && peashooterList.checkForEmptyPile(row, col) 
				&& zombiesManager.checkIfTileEmpty(row, col);
		return checkResult;
	}
	
	private boolean canPayPlant(String plantName) {
		if (plantName.equals("sunflower")) {
			if (sunCoins >= Sunflower.COST) {
				return true;
			}
		} 
		
		if (plantName.equals("peashooter")) {
			if (sunCoins >= Peashooter.COST) {
				return true;
			}
		}
		return false;
	}
	
	private void cycleCount() {
		currCycleNumber++;
	}
	
	public boolean addNewZombie() {
		return zombiesManager.addZombie();
	}
	
	public void printGameStateInfo() {
		System.out.println("\nNumber of cycles: " + currCycleNumber);
		System.out.println("Sun coins: " + sunCoins);
		zombiesManager.printRemainingZombies();
	}
	
	public void receiveNewSunCoins() {
		sunCoins += Sunflower.PRODUCED_SUN_COINS;
	}
	
	private void payForPlant(int cost) {
		sunCoins -= cost;
	}
	
	public boolean checkIfAttackedByZombie(int row, int col) {
		col++;
		return zombiesManager.isZombieAt(row, col);
	}
	
	public boolean checkIfAttackedByPeashooter(int row, int col) {
		// Peashooters only attack on-board zombies 
		if (col == Game.NUM_COLS) {
			return false;
		}
		return peashooterList.doesAttackZombieAt(row, col);
	}
	
	public boolean doesAllowZombieMoveForward(int row, int col) {
		return sunflowerList.checkForEmptyPile(row, col - 1) && peashooterList.checkForEmptyPile(row, col - 1) 
				&& zombiesManager.checkIfTileEmpty(row, col - 1);
	}
	
	
	public void update() {
		cycleCount();
		
		sunflowerList.update();
		peashooterList.update();
		zombiesManager.update();
		
		removeDeadEntities();
	}
	
	private void removeDeadEntities() {
		sunflowerList.removeDeadSunflowers();
		peashooterList.removeDeadPeashooters();
		zombiesManager.removeDeadZombies();
	}
	
	public boolean hasPlayerWon() {
		return zombiesManager.haveZombiesLost();
	}
	
	public boolean hasZombiesWon() {
		return zombiesManager.checkIfZombiesWon();
	}
	
}
