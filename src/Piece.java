/**
 * Class representing a Reversi piece
 * 
 * @author Kai Uerlichs
 * @version 1.0
 */

public class Piece implements java.io.Serializable {

	/**
	 * Object will be stored using serialisation, therefore serialVersionUID is required
	 */
	private static final long serialVersionUID = 2987492436935864556L;
	
	/**
	 *  If colour == 0, field is empty; if 1, piece is white/light; if /1, piece is black/dark; 
	 */
	private int colour;
	
	/**
	 * Constructor for Piece objects
	 */
	public Piece() {
		
		colour = 0;
		
	}
	
	/**
	 * Flips the piece, i.e. changes the colour
	 */
	public void flip() {
		
		// If piece is light
		if(colour == 1) {
			colour = -1;
		}
		
		// If piece is dark
		else if(colour == -1) {
			colour = 1;
		}
		
	}

	/**
	 * Get method for colour field
	 * @return colour
	 */
	public int getColour() {
		return colour;
	}

	/**
	 * Set method for colour field
	 * @param colour The colour to set
	 */
	public void setColour(int colour) {
		this.colour = colour;
	}
	
}
