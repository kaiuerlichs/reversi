/**
 * Class representing a player in Reversi
 * @author Kai Uerlichs
 * @version 1.0
 */

public abstract class Player implements java.io.Serializable{
	
	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = -6108835260522424478L;

	/**
	 * The colour of the player's pieces
	 */
	private int colour;
	
	/**
	 * The name of the player
	 */
	private String name;
	
	/**
	 * Player makes a move on the Reversi board
	 * @param grid The grid to make the move on
	 */
	public abstract void makeMove(Grid grid);
	
	/**
	 * Place a piece in the centre 4 fields as part of the setup in traditional Reversi
	 */
	public abstract void placeCentrePiece(Grid grid);
	
	/**
	 * The player skips a move
	 */
	public void skipMove() {
		System.out.println(getName() + " skips because there are no valid moves.");
	}
	
	/**
	 * Return the colour of this player's pieces
	 * @return The colour
	 */
	public int getColour() {
		return colour;
	}
	
	/**
	 * Return the symbol for the colour of this player's pieces
	 * @return The colour symbol
	 */
	public String getColourSymbol() {
		if(getColour() == -1) {
			return "O";
		}
		else {
			return "X";
		}
	}
	
	/**
	 * Set the colour of this player's pieces
	 * @param pColour The colour to set
	 */
	public void setColour(int pColour) {
		colour = pColour;
	}

	/**
	 * Return the name of this player
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of this player
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
