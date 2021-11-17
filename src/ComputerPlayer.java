/**
 * Class representing a Computer Player of the Reversi Game
 * @author Kai Uerlichs
 * @version 1.0
 */
public class ComputerPlayer extends Player implements java.io.Serializable {

	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = -5317467115053395728L;

	/**
	 * Constructor for a ComputerPlayer instance, setting the player colour and name
	 * @param pColour The colour of this player's pieces
	 */
	public ComputerPlayer(int pColour) {
		setColour(pColour);
		setName("Computer");
	}
	
	/**
	 * Overrides the makeMove() method of Player
	 */
	@Override
	public void makeMove(Grid grid) {
		// Determine best move
		int move[] = determineBestMove(grid);
		
		//Make move on grid
		grid.makeMove(move[0], move[1], getColour());
		
		// Output move made to the user
		System.out.println(getName() + " (" + getColourSymbol() + ")" + " plays "+generateHumanCoords(move)+".");
	}
	
	/**
	 * Determines the best possible move to make (based on number of pieces flipped)
	 * @param grid The grid to evaluate
	 * @return The move to make
	 */
	public int[] determineBestMove(Grid grid) {
		// Initialise local variables
		int move[] = {-1,-1};
		int maxScore = 0;
		
		// Score all moves and keep the best one
		for(int i = 0; i < grid.getSizeX(); i++) {
			for(int j = 0; j < grid.getSizeY(); j++) {
				int score = grid.getMoveScore(i, j, getColour());
				if(score > maxScore) {
					move[0] = i;
					move[1] = j;
					maxScore = score;
				}
			}
		}
		
		// Return the best move
		return move;
	}
	
	/**
	 * Converts grid coordinates into alphanumeric code that is identifiable by the user
	 * @param coords The coordinates of the move
	 * @return The alphanumeric code
	 */
	public String generateHumanCoords(int[] coords) {
		return Grid.LETTER_REFERENCE[coords[1]]+(coords[0]+1);
		
	}

	/**
	 * Randomly place a piece in the centre 4 fields as part of the setup in traditional Reversi 
	 */
	@Override
	public void placeCentrePiece(Grid grid) {
		boolean valid = false;
		int min = 3;
		int max = 4;
		
		int x = -1;
		int y = -1;
		
		while(!valid) {
			x = (int) (Math.random() * (max - min + 1) + min);
			y = (int) (Math.random() * (max - min + 1) + min);
			valid = grid.isEmpty(x, y);
		}
		
		grid.placePiece(x, y, getColour());
		
		int[] move = {x,y};
		
		System.out.println(getName() + " (" + getColourSymbol() + ")" + " plays "+generateHumanCoords(move)+".");
	}

}
