package levelPieces;

import java.util.ArrayList;

import gameEngine.Drawable;
import gameEngine.Moveable;
import gameEngine.Player;

public class LevelSetup {
	private Drawable[] board = new Drawable[20];

	//Generate level
	public void createLevel(int levelNum) {
		if(levelNum == 1) {
			//Setup level 1
			level1();
		} else {
			//Setup level 2
			level2();
		}
		
	}
	
	public void level1() {
		//create level1
		Castle castle = new Castle();
		Princess princess = new Princess("Princess", 10);
		Archer archer = new Archer("Archer", 9);
		Treasure treasure = new Treasure("Treasure", 2);
		Goblin goblin = new Goblin("Goblin", 8);
		Player player = new Player(0);
		
		board[princess.getLocation()] = princess;
		board[princess.getLocation()-1] = castle;
		board[archer.getLocation()] = archer;
		board[treasure.getLocation()] = treasure;
		board[goblin.getLocation()] = goblin;
		board[player.getLocation()] = player;
		
	}
	
	public void level2() {
		//create level2
		System.out.print("Level 2");
	}
	
	//Return the board
	public Drawable[] getBoard() {
		return board;
		
	}

	//Return an arraylist of the moving pieces
	public ArrayList<Moveable> getMovingPieces() {
		ArrayList<Moveable> moveable = new ArrayList<Moveable>();
		for(int i = 0; i < board.length-1; i++) {
			//instanceof found on stackoverflow
			if(board[i] instanceof Moveable) {
				moveable.add((Moveable) board[i]);
			}
		}
		return moveable;
	}

	//Return an arraylist of interacting pieces
	public ArrayList<GamePiece> getInteractingPieces() {
		ArrayList<GamePiece> interactingPieces = new ArrayList();
		for(int i = 0; i < board.length-1; i++) {
			//instanceof found on stackoverflow
			if(board[i] instanceof GamePiece) {
				interactingPieces.add((GamePiece) board[i]);
			}
		}
		return interactingPieces;
	}

	//return player starting location
	public int getPlayerStartLoc() {
		
		return 0;
	}

	
	
}
