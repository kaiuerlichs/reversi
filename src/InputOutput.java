import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class supplying a number of input and output handling static methods
 * 
 * @author Kai Uerlichs
 * @version 1.0
 */

public class InputOutput {

	/**
	 * Request a user input that has to be a numeric value between two integer numbers
	 * 
	 * @param a Lower bound for the requested number
	 * @param b Upper bound for the requested number
	 * @param message The text that will be displayed to prompt the user to input
	 * @return User input value, a number between a and b
	 */
	public static int readIntInBounds(int a, int b, String message) {
		Scanner s1 = new Scanner(System.in);
		System.out.println();
		System.out.print(message);
		
		try {
			int input = s1.nextInt();
			if(input>=a && input<=b) {
				return input;
			}
			else {
				System.out.println("Invalid input, try again.");
				return readIntInBounds(a,b, message);
			}
		} catch (Exception e) {
			System.out.println("Invalid input, try again.");
			return readIntInBounds(a,b, message);
		}
	}
	
	/**
	 * Method to read in String inputs
	 * @param message The text that will be displayed to prompt the user to input
	 * @return The user's input
	 */
	public static String getInput(String message) {
		System.out.print(message);
		Scanner sc = new Scanner(System.in);
		try {
			String input = sc.nextLine();
			return input;
		} catch (NoSuchElementException e) {
			System.out.println("There was an issue reading your input, please try again.");
			return getInput(message);
		}
	}
	
	/**
	 * Method to read in String inputs from set of defined Strings
	 * @param message The text that will be displayed to prompt the user to input
	 * @param values An array of Strings that are possible values the user may enter
	 * @return The user's input
	 */
	public static String getInputFromList(String message, String[] values) {
		System.out.print(message);
		Scanner sc = new Scanner(System.in);
		try {
			String input = sc.nextLine();
			input = input.toLowerCase();
			
			boolean found = false;
			for(int i=0; i < values.length; i++) {
				if(values[i].toLowerCase().equals(input)) {
					found = true;
					break;
				}
			}
			
			if(found == true) {
				return input;
			}
			else {
				System.out.println("Please enter a valid input.");
				return getInputFromList(message, values);
			}
		} catch (NoSuchElementException e) {
			System.out.println("There was an issue reading your input, please try again.");
			return getInputFromList(message, values);
		}
	}
	
	/**
	 * Get alphanumeric value from user that corresponds to a field on the Reversi board
	 * @param sizeX The X size of the board
	 * @param sizeY The Y size of the board
	 * @param message The input prompt output to the user
	 * @return The user's input
	 */
	public static int[] getValidAlphanumeric(int sizeX, int sizeY, String message) {
		int[] coords = {-1,-1};
		
		String input = getInput(message);
		if(input.length() != 2) {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumeric(sizeX, sizeY, message);
		}
		
		String[] split = input.split("(?!^)");
		
		try {
			int x = Integer.parseInt(split[1]);
			if(x <= sizeX && x > 0) {
				coords[0] = x-1;
			}
		} catch (NumberFormatException e) {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumeric(sizeX, sizeY, message);
		}
		
		for(int i = 0; i < Grid.LETTER_REFERENCE.length; i++) {
			if(split[0].equals(Grid.LETTER_REFERENCE[i]) || split[0].equals(Grid.LETTER_REFERENCE[i].toLowerCase())) {
				if(i<sizeY) {
					coords[1] = i;
					break;
				}
				else {
					System.out.println("This field is invalid, please try again.");
					return getValidAlphanumeric(sizeX, sizeY, message);
				}
			}
		}
		
		if(coords[0] != -1 && coords[1] != -1) {
			return coords;
		}
		else {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumeric(sizeX, sizeY, message);
		}
		
	}
	
	/**
	 * Get alphanumeric value from user that corresponds to a field on the Reversi board (8x8)
	 * @param message The input prompt output to the user
	 * @return The user's input
	 */
	public static int[] getValidAlphanumericCentre(String message) {
		int[] coords = {-1,-1};
		
		String input = getInput(message);
		if(input.length() != 2) {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumericCentre(message);
		}
		
		String[] split = input.split("(?!^)");
		
		try {
			int x = Integer.parseInt(split[1]);
			if(x == 4 || x == 5) {
				coords[0] = x-1;
			}
		} catch (NumberFormatException e) {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumericCentre(message);
		}
		
		for(int i = 0; i < Grid.LETTER_REFERENCE.length; i++) {
			if(split[0].equals(Grid.LETTER_REFERENCE[i]) || split[0].equals(Grid.LETTER_REFERENCE[i].toLowerCase())) {
				if(i == 3 || i == 4) {
					coords[1] = i;
					break;
				}
				else {
					System.out.println("This field is invalid, please try again.");
					return getValidAlphanumericCentre(message);
				}
			}
		}
		
		if(coords[0] != -1 && coords[1] != -1) {
			return coords;
		}
		else {
			System.out.println("This field is invalid, please try again.");
			return getValidAlphanumericCentre(message);
		}
		
	}
	
}
