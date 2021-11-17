/**
 * Class representing a game of Reversi
 * 
 * @author Kai Uerlichs
 * @version 1.0
 */
public class Game implements java.io.Serializable {

	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = -2063930278330066318L;

	// Declare fields of object
	private Grid grid = new Grid();
	
	private Player player1;
	private Player player2;
	
	private int gamemode;
	private int rounds;
	private int startingPlayer;
	private int hints;
	private boolean gameOver;
	private int players;
	
	/**
	 * Constructor for building a new game
	 * @param pPlayers The number of players
	 * @param pGamemode The gamemode to be played
	 * @param pPlayer1 The name of the first player
	 * @param pPlayer2 The name of the second player
	 * @param pColours The colour options
	 * @param pStartingPlayer The player to make the first move
	 * @param pHints The hints option
	 */
	public Game(int pPlayers, int pGamemode, String pPlayer1, String pPlayer2, int pColours, int pStartingPlayer, int pHints) {
		
		// Set fields
		rounds = 0;
		gameOver = false;
		startingPlayer = pStartingPlayer;
		gamemode = pGamemode;
		
		// Set player colours
		int colour1 = 0;
		int colour2 = 0;
		
		if(pColours==1) {
			colour1 = 1;
			colour2 = -1;
		}
		else if(pColours==2) {
			colour1 = -1;
			colour2 = 1;
		}
		
		// Set hint mode
		hints = pHints;
		
		players = pPlayers;
		
		// Initialise player objects
		if(players == 1) {
			player1 = new HumanPlayer(colour1, pPlayer1);
			player2 = new ComputerPlayer(colour2);
		}
		if(players == 2) {
			player1 = new HumanPlayer(colour1, pPlayer1);
			player2 = new HumanPlayer(colour2, pPlayer2);
		}
		if(players == 3) {
			player1 = new ComputerPlayer(colour1);
			player2 = new ComputerPlayer(colour2);
		}
		
		// Initialise board according to gamemode
		if(gamemode == 1) {
			initialiseOthello();
		}
		else if(gamemode == 2) {
			initialiseTraditional();
		}
	}
	
	/**
	 * Initialise the game for Othello mode
	 */
	public void initialiseOthello() {
		grid.initialiseOthello();
	}
	
	/**
	 * Initialise the game for Traditional mode
	 */
	public void initialiseTraditional() {
		if(startingPlayer == 1) {
			for(int i = 0; i < 2; i++) {
				grid.display();
				System.out.println();
				player1.placeCentrePiece(grid);
				
				grid.display();
				System.out.println();
				player2.placeCentrePiece(grid);
				
			}
		}
		else {
			for(int i = 0; i < 2; i++) {
				grid.display();
				System.out.println();
				player2.placeCentrePiece(grid);
				
				grid.display();
				System.out.println();
				player1.placeCentrePiece(grid);
				
			}
		}
	}
	
	/**
	 * Runs the game
	 * @return Whether the game has concluded (Game Over)
	 */
	public boolean run() {
		
		// Decides which ruleset to run the game with
		switch (gamemode) {
		case 1: {
			// Check whether game has to be run with hints
			if(hints == 1) {
				runOthello();
			}
			else {
				runOthelloHints();
			}
			break;
		}
		case 2: {
			// Check whether game has to be run with hints
			if(hints == 1) {
				runTraditional();
			}
			else {
				runTraditionalHints();
			}
			break;
		}
		}
		return gameOver;
		
	}
	
	/**
	 * Runs the game with the Othello ruleset
	 */
	public void runOthello() {

		grid.display();
		while(!gameOver) {
			
			// Increment rounds counter
			rounds++;
			
			// Player order if player1 starts
			if(startingPlayer == 1) {
				
				// Player 1 makes move
				System.out.println();
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					player1.skipMove();
				}
				grid.display();
				System.out.println();
				
				// Player 2 makes move
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					player2.skipMove();
				}
				grid.display();
				
				// End game if neither player can make a move
				if(!grid.checkValidMoves()) {
					gameOver = true;
				}
				
			}
			// Player order if player2 starts
			else {
				
				// Player 2 makes move
				System.out.println();
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					player2.skipMove();
				}
				grid.display();
				System.out.println();
				
				// Player 1 makes move
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					player1.skipMove();
				}
				grid.display();
				
				// End game if neither player can make a move
				if(!grid.checkValidMoves()) {
					gameOver = true;
				}
				
			}
			
			if(gameOver) {
				System.out.println();
				int[] results = grid.getResults();
				if(player1.getColour() == results[0]) {
					System.out.println(player1.getName() + " won the game.");
				}
				else if(player2.getColour() == results[0]) {
					System.out.println(player2.getName() + " won the game.");
				}
				else {
					System.out.println("It's a draw!");
				}
				System.out.println("The final score is " + results[1] + ":" + results[2] +".");
				break;
			}
			
			// Display end of round text
			System.out.println();
			int[] score = grid.getResults();
			System.out.println("Round " + rounds + ": The score is " + score[1] + ":" + score[2] +".");
			
			// Ask user to continue or exit
			System.out.println();
			String[] promptValues = {"","Exit"};
			String promptReply = InputOutput.getInputFromList("Press Enter to continue | Type Exit to save and exit ", promptValues);;
			
			if(promptReply.equals("exit")) {
				break;
			}
			
		}
		
	}
	
	/**
	 * Run the game with hints and the Othello ruleset
	 */
	public void runOthelloHints() {

		if(startingPlayer == 1) {
			grid.display(player1.getColour());
		}
		else {
			grid.display(player2.getColour());
		}
		while(!gameOver) {
			
			// Increment rounds counter
			rounds++;
			
			// Player order if player1 starts
			if(startingPlayer == 1) {
				
				// Player 1 makes move
				System.out.println();
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					player1.skipMove();
				}
				grid.display(player2.getColour());
				System.out.println();
				
				// Player 2 makes move
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					player2.skipMove();
				}
				grid.display(player1.getColour());
				
				// End game if neither player can make a move
				if(!grid.checkValidMoves()) {
					gameOver = true;
				}
				
			}
			// Player order if player2 starts
			else {
				
				// Player 2 makes move
				System.out.println();
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					player2.skipMove();
				}
				grid.display(player1.getColour());
				System.out.println();
				
				// Player 1 makes move
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					player1.skipMove();
				}
				grid.display(player2.getColour());
				
				// End game if neither player can make a move
				if(!grid.checkValidMoves()) {
					gameOver = true;
				}
				
			}
			
			if(gameOver) {
				System.out.println();
				int[] results = grid.getResults();
				if(player1.getColour() == results[0]) {
					System.out.println(player1.getName() + " won the game.");
				}
				else if(player2.getColour() == results[0]) {
					System.out.println(player2.getName() + " won the game.");
				}
				else {
					System.out.println("It's a draw!");
				}
				System.out.println("The final score is " + results[1] + ":" + results[2] +".");
				break;
			}
			
			// Display end of round text
			System.out.println();
			int[] score = grid.getResults();
			System.out.println("Round " + rounds + ": The score is " + score[1] + ":" + score[2] +".");
			
			// Ask user to continue or exit
			System.out.println();
			String[] promptValues = {"","Exit"};
			String promptReply = InputOutput.getInputFromList("Press Enter to continue | Type Exit to save and exit ", promptValues);;
			
			if(promptReply.equals("exit")) {
				break;
			}
			
		}
		
	}
	
	/**
	 * Runs the game with the Traditional ruleset
	 */
	public void runTraditional() {
		
		if(startingPlayer == 1) {
			grid.display(player1.getColour());
		}
		else {
			grid.display(player2.getColour());
		}
		while(!gameOver) {
			
			// Increment rounds counter
			rounds++;
			
			// Player order if player1 starts
			if(startingPlayer == 1) {
				
				// Player 1 makes move
				System.out.println();
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player1.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display(player2.getColour());
				System.out.println();
				
				// Player 2 makes move
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player2.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display(player1.getColour());
				
			}
			// Player order if player2 starts
			else {
				
				// Player 2 makes move
				System.out.println();
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player2.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display(player1.getColour());
				System.out.println();
				
				// Player 1 makes move
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player1.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display(player2.getColour());
				
			}
			
			if(gameOver) {
				System.out.println();
				int[] results = grid.getResults();
				if(player1.getColour() == results[0]) {
					System.out.println(player1.getName() + " won the game.");
				}
				else if(player2.getColour() == results[0]) {
					System.out.println(player2.getName() + " won the game.");
				}
				else {
					System.out.println("It's a draw!");
				}
				System.out.println("The final score is " + results[1] + ":" + results[2] +".");
				break;
			}
			
			// Display end of round text
			System.out.println();
			int[] score = grid.getResults();
			System.out.println("Round " + rounds + ": The score is " + score[1] + ":" + score[2] +".");
			
			// Ask user to continue or exit
			System.out.println();
			String[] promptValues = {"","Exit"};
			String promptReply = InputOutput.getInputFromList("Press Enter to continue | Type Exit to save and exit ", promptValues);;
			
			if(promptReply.equals("exit")) {
				break;
			}
		}
	}
	
	/**
	 * Run the game with hints and the Traditional ruleset
	 */
	public void runTraditionalHints() {
		
		grid.display();
		while(!gameOver) {
			
			// Increment rounds counter
			rounds++;
			
			// Player order if player1 starts
			if(startingPlayer == 1) {
				
				// Player 1 makes move
				System.out.println();
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player1.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display();
				System.out.println();
				
				// Player 2 makes move
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player2.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display();
				
			}
			// Player order if player2 starts
			else {
				
				// Player 2 makes move
				System.out.println();
				if(grid.checkValidMoves(player2.getColour())) {
					player2.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player2.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display();
				System.out.println();
				
				// Player 1 makes move
				if(grid.checkValidMoves(player1.getColour())) {
					player1.makeMove(grid);
				}
				else {
					System.out.println("The game is over since " + player1.getName() + " cannot make a valid move.");
					gameOver = true;
					break;
				}
				grid.display();
				
			}
			
			if(gameOver) {
				System.out.println();
				int[] results = grid.getResults();
				if(player1.getColour() == results[0]) {
					System.out.println(player1.getName() + " won the game.");
				}
				else if(player2.getColour() == results[0]) {
					System.out.println(player2.getName() + " won the game.");
				}
				else {
					System.out.println("It's a draw!");
				}
				System.out.println("The final score is " + results[1] + ":" + results[2] +".");
				break;
			}
			
			// Display end of round text
			System.out.println();
			int[] score = grid.getResults();
			System.out.println("Round " + rounds + ": The score is " + score[1] + ":" + score[2] +".");
			
			// Ask user to continue or exit
			System.out.println();
			String[] promptValues = {"","Exit"};
			String promptReply = InputOutput.getInputFromList("Press Enter to continue | Type Exit to save and exit ", promptValues);;
			
			if(promptReply.equals("exit")) {
				break;
			}
		}
	}
	
	// Useful getter methods
	
	/**
	 * Get current winner of game
	 * @return The results from grid
	 */
	public int getWinner() {
		int[] results = grid.getResults();
		if(player1.getColour() == results[0]) {
			return 0;
		}
		else if(player2.getColour() == results[0]) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	/**
	 * @return players
	 */
	public int getPlayers() {
		return players;
	}

	/**
	 * @return gamemode
	 */
	public int getGamemode() {
		return gamemode;
	}

	/**
	 * @return rounds
	 */
	public int getRounds() {
		return rounds;
	}

	/**
	 * @return startingPlayer
	 */
	public int getStartingPlayer() {
		return startingPlayer;
	}

	/**
	 * @return gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

}
