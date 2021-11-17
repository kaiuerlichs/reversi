/**
 * Class representing a Human Player of the Reversi Game
 * @author Kai Uerlichs
 * @version 1.0
 */
public class HumanPlayer extends Player implements java.io.Serializable {

	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = 6122427112279983158L;

	/**
	 * Constructor for a HumanPlayer instance, setting the player colour and name
	 * @param pColour The colour of this player's pieces
	 * @param pName The name of this player
	 */
	public HumanPlayer(int pColour, String pName) {
		setColour(pColour);
		setName(pName);
	}
	
	/**
	 * Overrides the makeMove() method of Player
	 */
	@Override
	public void makeMove(Grid grid) {
		
		// Initialise local variables
		boolean validMove = false;
		int[] moveCoords = null;
		
		// Get a valid move from the user
		while(!validMove) {
			int[] input = InputOutput.getValidAlphanumeric(grid.getSizeX(), grid.getSizeY(), getName() + " (" + getColourSymbol() + ")" + " - Enter your move: ");
			if(grid.checkMoveValid(input[0], input[1], getColour())){
				moveCoords = input;
				validMove = true;
			}
			else {
				System.out.println("This move is not valid, please try again.");
			}
		}
		
		// Attempt to make a move on the Reversi board
		try {
			grid.makeMove(moveCoords[0], moveCoords[1], getColour());
		} catch (Exception e) {
			System.out.println("Something went wrong, please try again.");
			makeMove(grid);
		}
	}

	/**
	 * Place a piece in the centre 4 fields as part of the setup in traditional Reversi
	 */
	@Override
	public void placeCentrePiece(Grid grid) {
		int move[] = InputOutput.getValidAlphanumericCentre(getName() + ": Please enter a field to place your piece on: ");
		if(grid.isEmpty(move[0], move[1])) {
			grid.placePiece(move[0], move[1], getColour());
		}
		else {
			System.out.println("This field is invalid, please try again.");
			placeCentrePiece(grid);
		}
	}
	
}
