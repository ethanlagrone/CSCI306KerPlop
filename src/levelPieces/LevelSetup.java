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
		Queen queen = new Queen(19);
		Archer archer = new Archer(17);
		Treasure treasure = new Treasure(2);
		Goblin goblin = new Goblin(8);
		//Treasure treasure2 = new Treasure(6);
		
		board[queen.getLocation()] = queen;
		board[queen.getLocation()-1] = castle;
		board[archer.getLocation()] = archer;
		board[treasure.getLocation()] = treasure;
		board[goblin.getLocation()] = goblin;	
		//board[treasure2.getLocation()] = treasure2;
	}
	
	//create level2
	public void level2() {
		System.out.println("Level 2");
		clearBoard();
	}
	
	//Clear the board
	public void clearBoard() {
		for(int i = 0; i<board.length; i++) {
			board[i] = null;
		}
	}
	
	//Return the board
	public Drawable[] getBoard() {
		return board;
		
	}

	//Return an arraylist of the moving pieces
	public ArrayList<Moveable> getMovingPieces() {
		ArrayList<Moveable> moveable = new ArrayList<Moveable>();
		for(int i = 0; i < board.length; i++) {
			//instanceof found on stackoverflow
			if(board[i] instanceof Moveable) {
				moveable.add((Moveable) board[i]);
			}
		}
		for (Moveable m : moveable) {
	        System.out.println(m);
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
