/**
 * Class representing statistical data for the Reversi game
 * @author Kai Uerlichs
 * @version 1.0
 */

public class Statistic implements java.io.Serializable {

	/**
	 * Objects of this class may be stored using Serialisation
	 */
	private static final long serialVersionUID = -2675298682148652405L;
	
	private int spAnalysedGames;
	private int mpAnalysedGames;
	
	// Singleplayer statistics
	private int spGamesWon;
	private int spGamesLost;
	private int spDraws;
	private float spWinRate;
	
	// Multiplayer statistics
	private int mpPlayer1Wins;
	private int mpPlayer2Wins;
	private int mpDraws;
	private float mpPlayer1WinRate;
	private float mpPlayer2WinRate;
	
	/**
	 * Default constructor for an empty Statistic
	 */
	public Statistic() {
		spAnalysedGames = 0;
		mpAnalysedGames = 0;
		
		// Singleplayer statistics
		spGamesWon = 0;
		spGamesLost = 0;
		spDraws = 0;
		spWinRate = 0;
		
		// Multiplayer statistics
		mpPlayer1Wins = 0;
		mpPlayer2Wins = 0;
		mpDraws = 0;
		mpPlayer1WinRate = 0;
		mpPlayer2WinRate = 0;
	}
	
	/**
	 * Add a new singleplayer stat entry
	 * @param winner The winner of the game
	 */
	public void addSingleplayer(int winner) {
		spAnalysedGames++;
		if(winner == 0) {
			spGamesWon++;
		}
		else if(winner == 1) {
			spGamesLost++;
		}
		else {
			spDraws++;
		}
		spWinRate = spGamesWon / (spGamesWon + spGamesLost + spDraws);
		
		ReversiApp.saveStats();
	}

	/**
	 * Add a new multiplayer stat entry
	 * @param winner The winner of the game
	 */
	public void addMultiplayer(int winner) {
		mpAnalysedGames++;
		if(winner == 0) {
			mpPlayer1Wins++;
		}
		else if(winner == 1) {
			mpPlayer2Wins++;
		}
		else {
			mpDraws++;
		}
		mpPlayer1WinRate = mpPlayer1Wins / (mpPlayer1Wins + mpPlayer2Wins + mpDraws);
		mpPlayer2WinRate = mpPlayer2Wins / (mpPlayer1Wins + mpPlayer2Wins + mpDraws);
		
		ReversiApp.saveStats();
	}
	
	/**
	 * Set all statistics to 0
	 */
	public void reset() {
		spAnalysedGames = 0;
		mpAnalysedGames = 0;
		
		// Singleplayer statistics
		spGamesWon = 0;
		spGamesLost = 0;
		spDraws = 0;
		spWinRate = 0;
		
		// Multiplayer statistics
		mpPlayer1Wins = 0;
		mpPlayer2Wins = 0;
		mpDraws = 0;
		mpPlayer1WinRate = 0;
		mpPlayer2WinRate = 0;
	}
	
	/**
	 * @return the spAnalysedGames
	 */
	public int getSpAnalysedGames() {
		return spAnalysedGames;
	}

	/**
	 * @return the mpAnalysedGames
	 */
	public int getMpAnalysedGames() {
		return mpAnalysedGames;
	}

	/**
	 * @return the spGamesWon
	 */
	public int getSpGamesWon() {
		return spGamesWon;
	}

	/**
	 * @return the spGamesLost
	 */
	public int getSpGamesLost() {
		return spGamesLost;
	}

	/**
	 * @return the spDraws
	 */
	public int getSpDraws() {
		return spDraws;
	}

	/**
	 * @return the spWinRate
	 */
	public float getSpWinRate() {
		return spWinRate;
	}

	/**
	 * @return the mpPlayer1Wins
	 */
	public int getMpPlayer1Wins() {
		return mpPlayer1Wins;
	}

	/**
	 * @return the mpPlayer2Wins
	 */
	public int getMpPlayer2Wins() {
		return mpPlayer2Wins;
	}

	/**
	 * @return the mpDraws
	 */
	public int getMpDraws() {
		return mpDraws;
	}

	/**
	 * @return the mpPlayer1WinRate
	 */
	public float getMpPlayer1WinRate() {
		return mpPlayer1WinRate;
	}

	/**
	 * @return the mpPlayer2WinRate
	 */
	public float getMpPlayer2WinRate() {
		return mpPlayer2WinRate;
	}
	
}
