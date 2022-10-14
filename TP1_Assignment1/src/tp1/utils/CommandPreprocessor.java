package tp1.utils;

public class CommandPreprocessor {
	public static String[] splitInput(String input) {
		return input.trim().toLowerCase().split("\\s+");
	}
	
	public static boolean checkValidity(String[] input) {
		if (input[0].equals("add")) {
			
			if (input.length != 4) {
				System.out.println("Invalid command");
				return false;
			}
			
			String plantName = input[1];
			int col = Integer.parseInt(input[2]);
			int row = Integer.parseInt(input[3]);
			
			if (!plantName.equals("sunflower") && !plantName.equals("peashooter")) {
				System.out.println("Plant name is not valid");
				return false;
			}
			
			if (row > 3) {
				System.out.println("Row is out of bound");
				return false;
			}
			
			if (col > 7) {
				System.out.println("Column is out of bound");
				return false;
			}
		} 
		else if (input[0].equals("none") || input[0].equals("exit")) {
			if (input.length != 1) {
				System.out.println("Invalid command");
				return false;
			}
		}
		else {
			System.out.println("Invalid command");
			return false;
		}
		
		return true;
	}
}
