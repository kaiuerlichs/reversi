// Import Java libraries
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class representing an instance of the Reversi application
 * 
 * @author Kai Uerlichs
 * @version 1.0
 */
public class ReversiApp {
	
	/**
	 * Main method creating an instance of the Reversi application and launching it
	 * @param args Command line arguments (not applicable)
	 */
	public static void main(String[] args) {
		ReversiApp app = new ReversiApp();
		app.run();
	}
	
	/**
	 * While this is false, the instance will continue to run
	 */
	private boolean exit;
	private static Statistic stats;
	
	/**
	 * Constructor for the ReversiApp class, initialising the exit field
	 */
	public ReversiApp() {
		exit = false;
	}
	
	/**
	 * Launches the main menu and keeps displaying it until the game is quit
	 */
	public void run() {
		stats = loadStats();
		while(exit == false) {
			mainMenu();
		}
	}
	
	/**
	 * Displays the main menu
	 */
	public void mainMenu() {
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                          Reversi                           |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|     (1)     Create new game                                |");
		System.out.println("|     (2)     Load existing game                             |");
		System.out.println("|     (3)     Delete save files                              |");
		System.out.println("|                                                            |");
		System.out.println("|     (4)     Statistics                                     |");
		System.out.println("|                                                            |");
		System.out.println("|     (5)     Help                                           |");
		System.out.println("|     (0)     Exit                                           |");
		System.out.println("+------------------------------------------------------------+");
		processMainMenu();
	}
	
	/**
	 * When called, takes a user input for main menu selection and processes the users choice
	 */
	public void processMainMenu() {
		// Get user's choice of menu options
		int input = InputOutput.readIntInBounds(0, 5, "Please select one of the options above: ");
		
		// Handle different choices
		switch (input) {
		// Launch new game menu
		case 1: {
			newGameMenu();
			break;
		}
		// Launch load game menu
		case 2: {
			loadGame();
			break;
		}
		// Launch delete save menu
		case 3: {
			deleteSave();
			break;
		}
		// Launch statistics menu
		case 4: {
			viewStats();
			break;
		}
		// Launch help menu
		case 5: {
			helpMenu();
			break;
		}
		// Exit app
		case 0: {
			exit = true;
			System.out.println();
			System.out.println("See you soon!");
			break;
		}
		// Fallback option in case a wrong input is returned
		default:
			System.out.println("That input was invalid. Please try again.");
			 processMainMenu();
		}
	}
	
	/**
	 * Shows a number of menu screens in order to set up a new game of Reversi
	 */
	public void newGameMenu() {
		
		// Declare required fields
		int playerCount;
		String player1 = "";
		String player2 = "";
		int gamemode;
		int colours;
		int startingPlayer;
		int hints;
		
		
		// Display first menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                          New game                          |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|     (1)     Singleplayer                                   |");
		System.out.println("|     (2)     Multiplayer                                    |");
		System.out.println("|                                                            |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get player count from user
		playerCount = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
		
		// Cancel setup if playerCount is 0
		if(playerCount == 0) {
			return;
		}
		
		// Name players depending on playerCount
		if(playerCount == 1) {
			player1 = InputOutput.getInput("Please enter the name of Player 1: ");
			player2 = "Computer";
		}
		else if(playerCount == 2) {
			player1 = InputOutput.getInput("Please enter the name of Player 1: ");
			player2 = InputOutput.getInput("Please enter the name of Player 2: ");
		}
		else if(playerCount == 3) {
			player1 = "Computer";
			player2 = "Computer";
		}
		
		// Display second menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                          Gamemode                          |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|     (1)     Othello                                        |");
		System.out.println("|     (2)     Traditional                                    |");
		System.out.println("|                                                            |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get gamemode from user
		gamemode = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
		
		// Cancel setup if gamemode is 0
		if(gamemode == 0) {
			return;
		}
		
		// Display third menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                          Colours                           |");
		System.out.println("+------------------------------------------------------------+");
		System.out.printf("|     (1)     %-10.10s (X)          %-10.10s (O)         |\n", player1, player2);
		System.out.printf("|     (2)     %-10.10s (X)          %-10.10s (O)         |\n", player2, player1);
		System.out.println("|                                                            |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get colour option from user
		colours = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
		
		// Cancel setup if colour option is 0
		if(colours == 0) {
			return;
		}
		
		
		// Display fourth menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                      Who plays first?                      |");
		System.out.println("+------------------------------------------------------------+");
		System.out.printf("|     (1)     %-42.42s     |\n", player1);
		System.out.printf("|     (2)     %-42.42s     |\n", player2);
		System.out.println("|                                                            |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get starting player option from user
		startingPlayer = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
		
		// Cancel setup if starting player option is 0
		if(startingPlayer == 0) {
			return;
		}
		
		// Display fifth menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                       Turn on hints?                       |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|     (1)     No                                             |");
		System.out.println("|     (2)     Yes                                            |");
		System.out.println("|                                                            |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get hints from user
		hints = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
		
		// Cancel setup if hints is 0
		if(hints == 0) {
			return;
		}
		
		// Runs a new game with the predefined parameters
		runGame(playerCount, gamemode, player1, player2, colours, startingPlayer, hints);
	}
	
	/**
	 * Set up a new game of Reversi and run it
	 * @param playerCount Defines the player count of the game: Singleplayer (1) or Multiplayer (2)
	 * @param gamemode Defines the gamemode of the game: Othello (1) or Traditional (2)
	 * @param player1 Defines the first players name
	 * @param player2 Defines the second players name, if applicable
	 * @param colours Defines the colour set
	 * @param startingPlayer Defines which player starts the game
	 * @param hints Defines the hints settings
	 */
	public void runGame(int playerCount, int gamemode, String player1, String player2, int colours, int startingPlayer, int hints) {
		
		// Create a new game with parameters
		Game game = new Game(playerCount, gamemode, player1, player2, colours, startingPlayer, hints);
		
		// Run game instance
		runGame(game);
		
	}
	
	/**
	 * Runs a game of Reversi
	 * @param game The game instance to be run
	 */
	public void runGame(Game game) {
		
		// Run the game
		boolean gameOver = game.run();
		
		if(!gameOver) {
			// Display exit menu
			System.out.println();
			System.out.println("+------------------------------------------------------------+");
			System.out.println("|                         Exit game                          |");
			System.out.println("+------------------------------------------------------------+");
			System.out.println("|     (1)     Save                                           |");
			System.out.println("|     (2)     Don't save                                     |");
			System.out.println("+------------------------------------------------------------+");
			
			// Get user input for above options
			int save = InputOutput.readIntInBounds(0, 2, "Please select one of the options above: ");
			
			// Switch case statement handling user input 
			switch (save) {
			// Save the game instance
			case 1: {
				saveGame(game);
				break;
			}
			// Do not save the game instance
			case 2: {
				System.out.println();
				System.out.println("Your game will not be saved.");
				break;
			}
			// Fallback error message in case wrong input is returned
			default:
				System.out.println("Something went wrong. Your game could not be saved.");
			}
		}
		else {
			if(game.getPlayers() == 1) {
				stats.addSingleplayer(game.getWinner());
			}
			else if(game.getPlayers() == 2) {
				stats.addMultiplayer(game.getWinner());
			}
		}
		
	}
	
	/**
	 * Save a game of Reversi 
	 * @param game The game to be saved
	 */
	public void saveGame(Game game) {
		
		// Get existing save files
		File saves = new File("./saves");
		String[] saveFiles = saves.list();
		boolean exists = false;
		
		// Get filename for save file from user
		System.out.println();
		String saveName = InputOutput.getInput("Enter a name for your save file: ");
		saveName = saveName + ".ser";
		
		// Check whether save file with same name exists
		for(int i = 0; i < saveFiles.length; i++) {
			if(saveName.equals(saveFiles[i])) {
				exists = true;
				break;
			};
		}
		
		// If save file exists, request new name from user until they input a name that does not exist yet
		while(exists) {
			exists = false;
			
			// Request new file name
			System.out.println();
			System.out.println("This save file already exists.");
			saveName = InputOutput.getInput("Enter a name for your save file: ");
			saveName = saveName + ".ser";
			
			// Check whether save file with same name exists
			for(int i = 0; i < saveFiles.length; i++) {
				if(saveName.equals(saveFiles[i])) {
					exists = true;
					break;
				};
			}
		}
		
		// Declare output streams for saving game
		FileOutputStream fileOut;
		ObjectOutputStream out;
		
		try {
			// Save game instance using serialisation
			fileOut = new FileOutputStream("./saves/"+ saveName);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
			fileOut.close();
			System.out.println();
			System.out.println("Your game has been saved as " + saveName.replaceFirst("[.][^.]+$", ""));
			System.out.println();
			InputOutput.getInput("Press Enter to continue...");
		} 
		catch (IOException i) {
			// Output error if something goes wrong. Could not close streams in finally block since they throw IO warnings themselves
			System.out.println();
			System.out.println("Something went wrong. Your game could not be saved.");
			System.out.println();
			InputOutput.getInput("Press Enter to continue...");
		}
	}
	
	/**
	 * Load a saved game and run it
	 */
	public void loadGame() {
		
		// Get all save files
		File saves = new File("./saves");
		String[] saveFiles = saves.list();
		
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                         Save files                         |");
		System.out.println("+------------------------------------------------------------+");
		
		// Return to main menu if no files are found
		if(saveFiles.length == 0) {
			System.out.println("|                    No save files found                     |");
			System.out.println("+------------------------------------------------------------+");
			
			System.out.println();
			InputOutput.getInput("Press Enter to continue...");
		}
		
		// Display list of save files for user to choose from
		else {
			for(int i = 0; i < saveFiles.length; i++) {
				System.out.printf("|     %-7s %-40s       |\n","("+(i+1)+")", saveFiles[i].replaceFirst("[.][^.]+$", ""));
			}
			System.out.println("|                                                            |");
			System.out.println("|     (0)     Back to main menu                              |");
			System.out.println("+------------------------------------------------------------+");
			
			// Get user selection
			int save = InputOutput.readIntInBounds(0, saveFiles.length, "Please select a save file: ");
			
			// Return to main menu if user enters 0
			if(save == 0) {
				return;
			}
			
			// Open savefile
			else {
				// Declare filename and input streams
				String filename = saveFiles[save-1];
				FileInputStream fileIn;
		        ObjectInputStream in;
		        
		        // Declare game object
		        Game game = null;
		        
		        try {
		        	// Try reading in Game using serialisation
					fileIn = new FileInputStream("./saves/"+filename);
					in = new ObjectInputStream(fileIn);
					game = (Game) in.readObject();
					in.close();
			        fileIn.close();
				} catch (ClassNotFoundException c) {
					// If this exception is thrown, the Game class of this project is faulty
		            System.out.println("Your save file could not be loaded. The game might be corrupted.");
		            System.out.println();
					InputOutput.getInput("Press Enter to continue...");
		            return;
		        } catch (IOException i) {
		        	// If this is thrown, there was a problem reading from the file. Could not close streams in finally block since they throw IO warnings themselves
					System.out.println("Your save file could not be loaded. The save might be corrupted.");
					System.out.println();
					InputOutput.getInput("Press Enter to continue...");
					return;
		        } 
		        
		        // Output that game was loaded successfully
		        System.out.println();
		        System.out.println(filename.replaceFirst("[.][^.]+$", "") + " was loaded successfully.");
		        
		        System.out.println();
				InputOutput.getInput("Press Enter to continue...");
		        
				// Run the game
		        runGame(game);
		        
			}
		}
	}
	
	/**
	 * Show a menu to delete a save file
	 */
	public void deleteSave() {
		
		// Get all save files
		File saves = new File("./saves");
		String[] saveFiles = saves.list();
		
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                     Delete save files                      |");
		System.out.println("+------------------------------------------------------------+");
		
		// Return to main menu if no files are found
		if(saveFiles.length == 0) {
			System.out.println("|                    No save files found                     |");
			System.out.println("+------------------------------------------------------------+");
			
			System.out.println();
			InputOutput.getInput("Press Enter to continue...");
		}
		
		// Display list of save files for user to choose from
		else {
			for(int i = 0; i < saveFiles.length; i++) {
				System.out.printf("|     %-7s %-40s       |\n","("+(i+1)+")", saveFiles[i].replaceFirst("[.][^.]+$", ""));
			}
			System.out.println("|                                                            |");
			System.out.println("|     (0)     Back to main menu                              |");
			System.out.println("+------------------------------------------------------------+");
			
			// Get user selection
			int save = InputOutput.readIntInBounds(0, saveFiles.length, "Please select a save file to delete: ");
			
			// Return to main menu if user enters 0
			if(save == 0) {
				return;
			}
			
			// Delete savefile
			else {
				// Initialise File object
				File file = new File("./saves/"+saveFiles[save-1]);
				
				// Try to delete file
		        if(file.delete()) {
		        	System.out.println("The save file " + saveFiles[save-1].replaceFirst("[.][^.]+$", "") + " was deleted successfully.");
		        }
		        else {
		        	System.out.println("The save file " + saveFiles[save-1].replaceFirst("[.][^.]+$", "") + " could not be deleted.");
		        }
		        
		        System.out.println();
				InputOutput.getInput("Press Enter to continue...");
		        
			}
		}
	}
	
	/**
	 * Method running the help menu for the Reversi app
	 */
	public void helpMenu() {

		// Display help menu
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                            Help                            |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|     (1)     What is Reversi?                               |");
		System.out.println("|     (2)     Reversi or Othello?                            |");
		System.out.println("|     (3)     Managing save files                            |");
		System.out.println("|                                                            |");
		System.out.println("|     (4)     Secret debugger (PC vs. PC)                    |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get user's choice of menu options
		int input = InputOutput.readIntInBounds(0, 4, "Please select one of the options above: ");
		
		// Handle different choices
		switch (input) {
		// Show Reversi info
		case 1: {
			
			System.out.println();
			System.out.println("+------------------------------------------------------------+");
			System.out.println("|     Reversi and Othello are names for an abstract          |");
			System.out.println("|     strategy board game which involves play by two         |");
			System.out.println("|     players on an eight-by-eight square grid with          |");
			System.out.println("|     pieces that have two distinct sides. Pieces            |");
			System.out.println("|     typically appear coin-like, but with a light (X)       |");
			System.out.println("|     and a dark (O) face, each side representing one        |");
			System.out.println("|     player. The objective of the game is to make your      |");
			System.out.println("|     pieces constitute a majority of the pieces on the      |");
			System.out.println("|     board at the end of the game, by turning over as       |");
			System.out.println("|     many of your opponent's pieces as possible. Each       |");
			System.out.println("|     of the two sides corresponds to one player. If it      |");
			System.out.println("|     is dark's turn, dark must place a piece with the       |");
			System.out.println("|     dark side up on the board, in such a position that     |");
			System.out.println("|     there exists at least one straight (horizontal,        |");
			System.out.println("|     vertical, or diagonal) line between the new piece      |");
			System.out.println("|     and another dark piece, with one or more contiguous    |");
			System.out.println("|     light pieces between them. After placing the piece,    |");
			System.out.println("|     dark turns over (flips, captures) all light pieces     |");
			System.out.println("|     lying on a straight line between the new piece and     |");
			System.out.println("|     any anchoring dark pieces. All reversed pieces now     |");
			System.out.println("|     show the dark side, and dark can use them in later     |");
			System.out.println("|     moves -- unless light has reversed them back in the    |");
			System.out.println("|     meantime.                                              |");
			System.out.println("+------------------------------------------------------------+");
			
			helpMenu();
			break;
		}
		// Different rulesets
		case 2: {
			
			System.out.println();
			System.out.println("+------------------------------------------------------------+");
			System.out.println("|     The more common version of Reversi played              |");
			System.out.println("|     today is called Othello.                               |");
			System.out.println("|                                                            |");
			System.out.println("|     In Othello, the game starts with the centre four       |");
			System.out.println("|     fields already populated: Two pieces of each           |");
			System.out.println("|     colour are placed diagonally, where it is              |");
			System.out.println("|     convention to place the first white piece in the       |");
			System.out.println("|     top left, resulting in the following pattern:          |");
			System.out.println("|                                                            |");
			System.out.println("|                         +---+---+                          |");
			System.out.println("|                         | X | O |                          |");
			System.out.println("|                         +---+---+                          |");
			System.out.println("|                         | O | X |                          |");
			System.out.println("|                         +---+---+                          |");
			System.out.println("|                                                            |");
			System.out.println("|     Now, the game continues just like the rules of         |");
			System.out.println("|     Reversi say. However, if a player cannot make a        |");
			System.out.println("|     move, they simply skip. The game only end, when        |");
			System.out.println("|     neither player can make a move.                        |");
			System.out.println("|                                                            |");
			System.out.println("|     The tradtional way of playing Reversi differs only     |");
			System.out.println("|     slightly. Instead of starting with the four centre     |");
			System.out.println("|     fields already populated, the players get to place     |");
			System.out.println("|     their pieces freely in the middle during the first     |");
			System.out.println("|     two rounds (no captures are being made).               |");
			System.out.println("|     In Traditional Reversi, the game ends as soon as       |");
			System.out.println("|     one player cannot make another move.                   |");
			System.out.println("+------------------------------------------------------------+");
			
			helpMenu();
			break;
		}
		// Explain save files
		case 3: {
			
			System.out.println();
			System.out.println("+------------------------------------------------------------+");
			System.out.println("|     To save a game, simply select \"Save\" as you exit     |");
			System.out.println("|     the game screen. The program will ask you to name      |");
			System.out.println("|     your save file and automatically store the current     |");
			System.out.println("|     position of the pieces as well as your settings.       |");
			System.out.println("|                                                            |");
			System.out.println("|     When you decide to continue your game, simply          |");
			System.out.println("|     select \"Load existing game\" in the main menu. The    |");
			System.out.println("|     program will show you all the existing save files      |");
			System.out.println("|     and allow you to pick one to load. You can pick up     |");
			System.out.println("|     right where you left off.                              |");
			System.out.println("|                                                            |");
			System.out.println("|     Similarly, to delete a save file, select \"Delete      |");
			System.out.println("|     save files\" in the main menu. Select to file to       |");
			System.out.println("|     delete and the game information will be removed        |");
			System.out.println("|     from the list.                                         |");
			System.out.println("+------------------------------------------------------------+");
			
			helpMenu();
			break;
		}
		// Launch computer vs computer game
		case 4: {
			runGame(3, 1, "", "", 1, 1, 2);
			break;
		}
		// Return to main menu
		case 0: {
			return;
		}
		// Fallback option in case a wrong input is returned
		default:
			System.out.println("That input was invalid. Please try again.");
			helpMenu();
		}
	}
	
	/**
	 * Load statistics from file
	 * @return The statistics
	 */
	public Statistic loadStats() {
		
		Statistic stat = null;
		
		// Get statistics file
		File statistics = new File("./stats/statistics.ser");
		
		if(statistics.exists()) {
			FileInputStream fileIn;
	        ObjectInputStream in;
	        
	        try {
	        	// Try reading in Statistic using serialisation
				fileIn = new FileInputStream(statistics);
				in = new ObjectInputStream(fileIn);
				stat = (Statistic) in.readObject();
				in.close();
		        fileIn.close();
			} catch (ClassNotFoundException c) {
				// If this exception is thrown, the Statistics class of this project is faulty
	            System.out.println("Your statistic file could not be loaded. The game might be corrupted.");
	            System.out.println();
				InputOutput.getInput("Press Enter to continue...");
	        } catch (IOException i) {
	        	// If this is thrown, there was a problem reading from the file. Could not close streams in finally block since they throw IO warnings themselves
				System.out.println("Your statistic file could not be loaded. The file will be resey.");
				System.out.println();
				InputOutput.getInput("Press Enter to continue...");
	        } 
	        
		}

		// If no statistic exists, create empty one
		if(stat == null) {
			stat = new Statistic();
		}

		return stat;
	}
	
	/**
	 * Save the current statistics to file
	 */
	public static void saveStats() {
		
		// Get statistics file
		File statistics = new File("./stats/statistics.ser");
		
		// Declare output streams for saving stats
		FileOutputStream fileOut;
		ObjectOutputStream out;
		
		try {
			// Save stats instance using serialisation
			fileOut = new FileOutputStream(statistics);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(stats);
			out.close();
			fileOut.close();
		} 
		catch (IOException i) {
			// Output error if something goes wrong. Could not close streams in finally block since they throw IO warnings themselves
			System.out.println();

			System.out.println(i.getMessage());
			System.out.println("Statistics for this game could not be saved.");
			System.out.println();
			InputOutput.getInput("Press Enter to continue...");
		}
		
	}
	
	/**
	 * View the statistics menu
	 */
	public void viewStats() {
		
		System.out.println();
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                         Statistics                         |");
		System.out.println("+------------------------------------------------------------+");
		System.out.println("|                        Singleplayer                        |");
		System.out.println("|                                                            |");
		System.out.printf("|     Games won: %9d          Games lost: %8d     |\n",stats.getSpGamesWon(),stats.getSpGamesLost());
		System.out.printf("|     Draws: %13d          Total: %13d     |\n",stats.getSpDraws(),stats.getSpAnalysedGames());
		System.out.printf("|     Win rate:       %.2f                                   |\n", stats.getSpWinRate());
		System.out.println("|                                                            |");
		System.out.println("|                        Multiplayer                         |");
		System.out.println("|                                                            |");
		System.out.printf("|     Player 1 won: %6d          Player 2 won: %6d     |\n",stats.getMpPlayer1Wins(),stats.getMpPlayer2Wins());
		System.out.printf("|     Draws: %13d          Total: %13d     |\n",stats.getMpDraws(),stats.getMpAnalysedGames());
		System.out.printf("|     Win rate P1:    %.2f          Win rate P2:    %.2f     |\n", stats.getMpPlayer1WinRate(), stats.getMpPlayer2WinRate());
		System.out.println("|                                                            |");
		System.out.println("|     (1)     Reset statistics                               |");
		System.out.println("|     (0)     Back to main menu                              |");
		System.out.println("+------------------------------------------------------------+");
		
		// Get user's choice of menu options
		int input = InputOutput.readIntInBounds(0, 1, "Please select one of the options above: ");
		
		// Handle different choices
		switch (input) {
		case 1: {
			deleteStats();
			break;
		}
		case 0: {
			return;
		}
		}
		
	}
	
	/**
	 * Reset Statistics to 0
	 */
	public void deleteStats() {
		stats.reset();
		saveStats();
		System.out.println();
		System.out.println("Statistics have been reset.");
		System.out.println();
		InputOutput.getInput("Press Enter to continue...");
	}
	
}
