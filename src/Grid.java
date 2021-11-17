/**
 * Class representing a Reversi board grid
 * 
 * @author Kai Uerlichs
 * @version 1.0
 */
public class Grid implements java.io.Serializable {
	
	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = 6266464902895793950L;

	/**
	 * This is used across the project to correctly understand alphanumeric codes for fields on the grid
	 */
	public static final String[] LETTER_REFERENCE = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V"+"W"+"X"+"Y"+"Z"};

	// Initialise local fields
	private Piece[][] pieces;
	private int sizeX;
	private int sizeY;
	
	/**
	 * Constructor initialising the standard Reversi grid of (8x8)
	 */
	public Grid() {
		
		sizeX = 8;
		sizeY = 8;
		pieces = new Piece[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				pieces[i][j] = new Piece();
			}
		}
		
	}
	
	/**
	 * This method initialises the grid in Othello mode, meaning the centre four pieces will be filled in accordance to the Othello rule set
	 * @see <a href="https://en.wikipedia.org/wiki/Reversi#Othello">Wikipedia page on Reversi/Othello</a>
	 */
	public void initialiseOthello() {
		pieces[4][3].setColour(-1);
		pieces[3][4].setColour(-1);
		pieces[3][3].setColour(1);
		pieces[4][4].setColour(1);
	}
	
	/**
	 * Checks whether a field on the Reversi grid is currently empty (0)
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return Whether the field is empty (true) or not (false)
	 */
	public boolean isEmpty(int x, int y) {
		return (pieces[x][y].getColour() == 0);
	}
	
	/**
	 * Places a piece on the Reversi board without abiding to the usual move rules
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param colour The colour to set
	 */
	public void placePiece(int x, int y, int colour) {
		if(pieces[x][y].getColour() == 0) {
			pieces[x][y].setColour(colour);
		}
	}
	
	/**
	 * Displays the grid to the console, where (X) represents light pieces and (O) represents dark pieces.
	 */
	public void display() {
		System.out.println();
		
		// Print top row (numbers)
		System.out.print("   ");
		for(int i = 0; i < sizeX; i++) {
			System.out.print("  "+(i+1)+" ");
		}
		
		// Print divider
		System.out.println();
		System.out.print("   +");
		for(int i = 0; i < sizeX; i++) {
			System.out.print("---+");
		}
		System.out.println();
		
		// Print each row
		for(int y = 0; y < sizeY; y++) {
			
			// Print letter column
			System.out.print(" "+LETTER_REFERENCE[y]+" ");
			
			//Print all other columns for this row
			for(int x = 0; x < sizeX; x++) {
			
				System.out.print("| ");
				if(pieces[x][y].getColour()==-1) {
					System.out.print("O");
				}
				else if(pieces[x][y].getColour()==1) {
					System.out.print("X");
				}
				else {
					System.out.print(" ");
				}
				System.out.print(" ");
			}
			
			// Print end of line
			System.out.print("| "+"\n"+"   +");
			
			// Print divider
			for(int j = 0; j < sizeX; j++) {
				System.out.print("---+");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Displays the grid with hints to the console, where (X) represents light pieces and (O) represents dark pieces.
	 * @param colour The colour to evaluate for hints
	 */
	public void display(int colour) {
		System.out.println();
		
		// Print top row (numbers)
		System.out.print("   ");
		for(int i = 0; i < sizeX; i++) {
			System.out.print("  "+(i+1)+" ");
		}
		
		// Print divider
		System.out.println();
		System.out.print("   +");
		for(int i = 0; i < sizeX; i++) {
			System.out.print("---+");
		}
		System.out.println();
		
		// Print each row
		for(int y = 0; y < sizeY; y++) {
			
			// Print letter column
			System.out.print(" "+LETTER_REFERENCE[y]+" ");
			
			//Print all other columns for this row
			for(int x = 0; x < sizeX; x++) {
			
				System.out.print("| ");
				if(pieces[x][y].getColour()==-1) {
					System.out.print("O");
				}
				else if(pieces[x][y].getColour()==1) {
					System.out.print("X");
				}
				
				//Show hints if move is valid for specified colour
				else {
					if(checkMoveValid(x,y,colour)) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
				System.out.print(" ");
			}
			
			// Print end of line
			System.out.print("| "+"\n"+"   +");
			
			// Print divider
			for(int j = 0; j < sizeX; j++) {
				System.out.print("---+");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * Check whether a certain move is valid to play
	 * @see <a href="https://en.wikipedia.org/wiki/Reversi#Rules">Wikipedia page on game rules</a>
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param colour The colour to play
	 * @return Whether the move is valid
	 */
	public boolean checkMoveValid(int x, int y, int colour) {
		
		// Check whether field is empty
		if(pieces[x][y].getColour() != 0) {
			return false;
		}
		
		// Set oppositeColour variable
		int oppositeColour;
		if(colour == 1) {
			oppositeColour = -1;
		}
		else {
			oppositeColour = 1;
		}
		
		// Declare local fields
		int validDirections = 0;
		int counter;
		
		// Check left
		counter = 0;
		for(int i = 1; (x-i) >= 0; i++) {
			if(pieces[x-i][y].getColour() == 0) {
				break;
			}
			if(pieces[x-i][y].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x-i][y].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check left up
		counter = 0;
		for(int i = 1; (x-i) >= 0 && (y-i) >= 0; i++) {
			if(pieces[x-i][y-i].getColour() == 0) {
				break;
			}
			if(pieces[x-i][y-i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x-i][y-i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check up
		counter = 0;
		for(int i = 1; (y-i) >= 0; i++) {
			if(pieces[x][y-i].getColour() == 0) {
				break;
			}
			if(pieces[x][y-i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x][y-i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check right up
		counter = 0;
		for(int i = 1; (y-i) >= 0 && (x+i) < sizeX; i++) {
			if(pieces[x+i][y-i].getColour() == 0) {
				break;
			}
			if(pieces[x+i][y-i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x+i][y-i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check right
		counter = 0;
		for(int i = 1; (x+i) < sizeX; i++) {
			if(pieces[x+i][y].getColour() == 0) {
				break;
			}
			if(pieces[x+i][y].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x+i][y].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check right down
		counter = 0;
		for(int i = 1; (y+i) < sizeY && (x+i) < sizeX; i++) {
			if(pieces[x+i][y+i].getColour() == 0) {
				break;
			}
			if(pieces[x+i][y+i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x+i][y+i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check down
		counter = 0;
		for(int i = 1; (y+i) < sizeY; i++) {
			if(pieces[x][y+i].getColour() == 0) {
				break;
			}
			if(pieces[x][y+i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x][y+i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		// Check left down
		counter = 0;
		for(int i = 1; (x-i) >= 0 && (y+i) < sizeY; i++) {
			if(pieces[x-i][y+i].getColour() == 0) {
				break;
			}
			if(pieces[x-i][y+i].getColour() == oppositeColour) {
				counter++;
			}
			if(pieces[x-i][y+i].getColour() == colour) {
				if(counter > 0) {
					validDirections++;
					break;
				}
				else {
					break;
				}
			}
		}
		
		
		// Return true if the move is valid, false if invalid
		if(validDirections>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Makes a move on the grid, if valid
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param colour The colour of the move
	 */
	public void makeMove(int x, int y, int colour) {
		
		// Check if move is valid
		if(checkMoveValid(x,y,colour) == true) {
			
			// Place new piece
			pieces[x][y].setColour(colour);
			
			// Set opposite colour variable
			int oppositeColour;
			if(colour == 1) {
				oppositeColour = -1;
			}
			else {
				oppositeColour = 1;
			}
			
			// Declare necessary fields
			int counter;
			boolean accepted;
			
			// Check for flips in left direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0; i++) {
				if(pieces[x-i][y].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0; i++) {
					if(pieces[x-i][y].getColour() == oppositeColour) {
						pieces[x-i][y].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in left up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0 && (y-i) >= 0; i++) {
				if(pieces[x-i][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0 && (y-i) >= 0; i++) {
					if(pieces[x-i][y-i].getColour() == oppositeColour) {
						pieces[x-i][y-i].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y-i) >= 0; i++) {
				if(pieces[x][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (y-i) >= 0; i++) {
					if(pieces[x][y-i].getColour() == oppositeColour) {
						pieces[x][y-i].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y-i) >= 0 && (x+i) < sizeX; i++) {
				if(pieces[x+i][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (y-i) >= 0 && (x+i) < sizeX; i++) {
					if(pieces[x+i][y-i].getColour() == oppositeColour) {
						pieces[x+i][y-i].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x+i) < sizeX; i++) {
				if(pieces[x+i][y].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (x+i) < sizeX; i++) {
					if(pieces[x+i][y].getColour() == oppositeColour) {
						pieces[x+i][y].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y+i) < sizeY && (x+i) < sizeX; i++) {
				if(pieces[x+i][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (y+i) < sizeY && (x+i) < sizeX; i++) {
					if(pieces[x+i][y+i].getColour() == oppositeColour) {
						pieces[x+i][y+i].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y+i) < sizeY; i++) {
				if(pieces[x][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (y+i) < sizeY; i++) {
					if(pieces[x][y+i].getColour() == oppositeColour) {
						pieces[x][y+i].flip();
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in left down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0 && (y+i) < sizeY; i++) {
				if(pieces[x-i][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Flip required pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0 && (y+i) < sizeY; i++) {
					if(pieces[x-i][y+i].getColour() == oppositeColour) {
						pieces[x-i][y+i].flip();
					}
					else {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Check if any valid moves can be played by either player
	 * @return Whether any valid moves are possible
	 */
	public boolean checkValidMoves() {
		
		int validMoves = 0;
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(checkMoveValid(i,j,1) == true || checkMoveValid(i,j,-1) == true) {
					validMoves++;
				}
			}
		}
		if(validMoves > 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Check if any valid moves can be played by a player of the specified colour
	 * @param colour The colour to check moves for
	 * @return Whether any valid moves are possible
	 */
	public boolean checkValidMoves(int colour) {
		
		int validMoves = 0;
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(checkMoveValid(i,j,colour) == true) {
					validMoves++;
				}
			}
		}
		if(validMoves > 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * Return a score for each move (how many new pieces in specified colour will be generated?)
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param colour The colour of the move
	 * @return The score
	 */
	public int getMoveScore(int x, int y, int colour) {
		
		if(checkMoveValid(x,y,colour) == true) {
			
			// Set opposite colour variable
			int oppositeColour;
			if(colour == 1) {
				oppositeColour = -1;
			}
			else {
				oppositeColour = 1;
			}
			
			// Declare variables
			int score = 1;
			int counter;
			boolean accepted;
			
			// Check for flips in left direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0; i++) {
				if(pieces[x-i][y].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0; i++) {
					if(pieces[x-i][y].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in left up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0 && (y-i) >= 0; i++) {
				if(pieces[x-i][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0 && (y-i) >= 0; i++) {
					if(pieces[x-i][y-i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y-i) >= 0; i++) {
				if(pieces[x][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (y-i) >= 0; i++) {
					if(pieces[x][y-i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right up direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y-i) >= 0 && (x+i) < sizeX; i++) {
				if(pieces[x+i][y-i].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y-i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y-i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (y-i) >= 0 && (x+i) < sizeX; i++) {
					if(pieces[x+i][y-i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x+i) < sizeX; i++) {
				if(pieces[x+i][y].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (x+i) < sizeX; i++) {
					if(pieces[x+i][y].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in right down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y+i) < sizeY && (x+i) < sizeX; i++) {
				if(pieces[x+i][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x+i][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x+i][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (y+i) < sizeY && (x+i) < sizeX; i++) {
					if(pieces[x+i][y+i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (y+i) < sizeY; i++) {
				if(pieces[x][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (y+i) < sizeY; i++) {
					if(pieces[x][y+i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			
			// Check for flips in left down direction
			accepted = false;
			counter = 0;
			for(int i = 1; (x-i) >= 0 && (y+i) < sizeY; i++) {
				if(pieces[x-i][y+i].getColour() == 0) {
					break;
				}
				if(pieces[x-i][y+i].getColour() == oppositeColour) {
					counter++;
				}
				if(pieces[x-i][y+i].getColour() == colour) {
					if(counter > 0) {
						accepted = true;
						break;
					}
					else {
						break;
					}
				}
			}
			// Count flippable pieces
			if(accepted == true) {
				for(int i = 1; (x-i) >= 0 && (y+i) < sizeY; i++) {
					if(pieces[x-i][y+i].getColour() == oppositeColour) {
						score++;
					}
					else {
						break;
					}
				}
			}
			return score;	
		}
		else {
			return 0;
		}
		
	}
	
	/**
	 * Determine the current results of the game
	 * @return An integer array containing the results (winner/current leader, light pieces, dark pieces)
	 */
	public int[] getResults() {
		
		// Initialise variables
		int[] results = new int[3];
		int light = 0;
		int dark = 0;
		
		// Count points
		for(int i = 0; i < sizeX; i++) {
			for(int j = 0; j < sizeY; j++) {
				if(pieces[i][j].getColour() == -1) {
					dark++;
				}
				else if(pieces[i][j].getColour() == 1) {
					light++;
				}
			}
		}
		
		// Determine winner
		if(light == dark) {
			results[0]= 0;
		}
		else if(light > dark) {
			results[0]= 1;
		}
		else {
			results[0]= -1;
		}
		
		// Add points to results
		results[1] = dark;
		results[2] = light;
		
		return results;
	}

	/**
	 * @return the sizeX
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @param sizeX the sizeX to set
	 */
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	/**
	 * @return the sizeY
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @param sizeY the sizeY to set
	 */
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	
	

}
