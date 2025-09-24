package gameEngine;

import java.util.ArrayList;

import levelPieces.GamePiece;
import levelPieces.LevelSetup;

/**
 * Framework for LevelGame.
 * 
 * @author Mark Baldwin
 * @author Cyndi Rader
 * 
 * 
 *
 */

public class GameEngine {
	/**
	 * Determines the size of the game board.
	 * 
	 * Board is a 1D structure. It is an odd number so player can start exactly in
	 * the middle if desired.
	 */
	public final static int BOARD_SIZE = 21;
	/**
	 * Number of levels defined for this game. GameEngine will automatically play
	 * each level, starting with 1 through the number specified here.
	 */
	public final static int NUM_LEVELS = 2;
	// Keep track of the current level, starting with level 1
	private int currentLevel;
	// LevelSetup levelData will create all the data structures for a level
	private LevelSetup levelData;
	// Each level has a 1D array for the board containing pieces that can be drawn
	// or null for empty
	private Drawable[] gameBoard;
	// Only some pieces can move.
	private ArrayList<Moveable> movingPieces;
	// Only game pieces interact
	private ArrayList<GamePiece> interactingPieces;
	// Player keeps track of player status and controls player movement/location
	private Player player;

	/**
	 * Driver for the game. Creates game and plays it.
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.playGame();
	}

	/**
	 * Constructor for GameEngine. It creates a new LevelSetup called levelData
	 */
	public GameEngine() {
		levelData = new LevelSetup();
	}

	/**
	 * Plays the game. Continues until either all levels have been completed or the
	 * player has been killed. Program exits when game is over.
	 */
	public void playGame() {
		// Give player a default location of 0
		player = new Player(0);

		displayInstructions() ;
		
		// play through the levels until all done or player dies
		while (currentLevel < NUM_LEVELS && !player.isDead()) {
			currentLevel++;
			setupLevel(currentLevel); // setup the next level

			playLevel(); // and play that level
		}

		// If reach this point, either all levels were completed OR
		// player is dead
		if (player.isDead())
			System.out.println("Too bad, you lose...");
		else
			System.out.println("Congratulations, you won!");
		System.exit(0);
	}

	/**
	 * Display the instructions
	 * 
	 * @param gameBoard - board so we know what pieces to display info on
	 */
	private void displayInstructions() {
		System.out.println("* The Game KerPlop *");
		System.out.println("The object of the game is to capture prizes without being killed.");
		System.out.println("You need " + Player.POINTS_TO_ADVANCE + 
				" points to advance, you die after " + Player.POINTS_TO_DIE + " hits.") ;
		System.out.println("Archer - A: The archer shoots at the player if it is within 10 spots, random chance if the player gets killed.");
		System.out.println("Canon - >: The canon shoots if the player is within 3 spots, and there's a 1/10 chance it kills.");
		System.out.println("Castle - C: The castle stands in front of the princess and protects her by...doing nothing?");
		System.out.println("Goblin - G: The goblin moves twoards the player and attacks it if it is within one.");
		System.out.println("Ninja - N: The ninja jumps around in scilence, if it lands next to the user, you take a hit!");
		System.out.println("Queen - Q: The queen was kidnapped and is kept behind the castle, if the player saves her, you move on!");
		System.out.println("Treasure - T: If the player lands on the treasure, you get a point!");
	}
	

	/**
	 * Plays the game for one level.
	 * 
	 * During each round, the board is displayed, the player enters a movement
	 * option, the player's location is updated, then the player interacts with any
	 * pieces in the interactingPieces list. Moveable piece locations are then
	 * updated for the next round. This continues until the level is finished.
	 */
	public void playLevel() {
		while (!levelFinished()) {
			displayBoard(); // update the display

			// prompt and update the player's location
			player.doMove(gameBoard);

			interaction(); // process interactions after player moves

			movePieces(); // then move the pieces
		}
	}

	/**
	 * Should be called at the beginning of every level (including beginning of
	 * program) to set up the data for that level.
	 * 
	 * @param levelNum The number of the level to be loaded. Level number should be
	 *                 from 1 to GameEngine.NUM_LEVELS
	 */
	public void setupLevel(int levelNum) {
		// LevelSetup levelData needs to create the specified level
		levelData.createLevel(levelNum);

		// get the data for the level
		gameBoard = levelData.getBoard();
		movingPieces = levelData.getMovingPieces();
		interactingPieces = levelData.getInteractingPieces();

		// reset player statistics, starting location determined
		// by level engine
		player.resetLevel(levelData.getPlayerStartLoc());
	}

	/**
	 * Prints a representation of the board. A null represents an empty space (drawn
	 * as just a space). All other objects must be Drawable, so that the draw method
	 * can be used.
	 */
	public void displayBoard() {
		for (int i = 0; i < gameBoard.length; i++) {
			// Ensure player is always drawn, even if on top of another piece
			// Note, only 1 piece per location except player can be on top of another
			if (i == player.getLocation())
				player.draw();
			else if (gameBoard[i] == null)
				System.out.print(' ');
			else
				gameBoard[i].draw();
			System.out.print('|');
		}
		System.out.println();
	}

	/**
	 * Calls the move method for each Moveable piece. All Moveable pieces should be
	 * placed in the movingPieces list.
	 */
	public void movePieces() {
		for (Moveable piece : movingPieces) {
			piece.move(gameBoard, player.getLocation());
		}
	}

	/**
	 * Calls the interact method for each interactingPiece (i.e., GamePiece) All
	 * interacting pieces should be placed in the interactingPieces list. GameEngine
	 * will display a message and take the appropriate action based on the
	 * InteractionResult (e.g., sets player status to DEAD if the result is
	 * InteractionResult.KILL).
	 */
	public void interaction() {
		for (GamePiece piece : interactingPieces) {
			InteractionResult result = piece.interact(gameBoard, player.getLocation());
			if (result == InteractionResult.GET_POINT) {
				player.addPoint();
				System.out.println("\nYou just won a prize!\n");
			}
			if (result == InteractionResult.HIT) {
				player.takeDamage();
				System.out.println("\nYou just took a hit!\n");
				if (player.isDead()) {
					System.out.println("Too many hits, you are dead");
					// can only be killed once
					break;
				}
			}
			if (result == InteractionResult.KILL) {
				player.killed();
				System.out.println("\nKerPolp! Something just killed you!\n");
				// can only be killed once
				break;
			}
			if (result == InteractionResult.ADVANCE) {
				player.wonAdvance();
				System.out.println("\nGood news, you have won an advance!\n");
				// can only advance once
				break;
			}
		}
	}

	/**
	 * Determines if this level is complete. Will be complete if player is advancing
	 * (either by interaction with a game piece or accumulating points) or dead
	 * (either by interaction with a game piece or accumulating too many hits).
	 * 
	 * @return true if player advances or was killed, false otherwise
	 */
	public boolean levelFinished() {
		if (player.canAdvance()) {
			if (currentLevel < NUM_LEVELS)
				System.out.println("Advancing to next level...\n");
			return true;
		}
		if (player.isDead()) {
			return true;
		}
		return false;
	}

}
