package levelPieces;

import gameEngine.Drawable;


//Game Piece that only implements Drawable and doesn't interact
public class Castle implements Drawable {
	@Override
	public void draw() {
		System.out.print("C");
	}
	
}
