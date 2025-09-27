package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import levelPieces.Goblin;

public class TestInteractions {
	
	//test archer interactions
	@Test
	public void archerInteraction() {
		//stub function
	}
	
	//test canon interactions
	@Test
	public void canonInteraction() {
		//stub function
	}
	
	//test goblin interactions
	@Test
	public void goblinInteraction() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Goblin goblin = new Goblin(5);
		gameBoard[5] = goblin;
		
		// Hit player if on same space
		assertEquals(InteractionResult.HIT, goblin.interact(gameBoard, 5));
		
		// These loops ensure no interaction if not on same space
		for (int i=0; i<5; i++)
			assertEquals(InteractionResult.NONE, goblin.interact(gameBoard, i));
		for (int i=6; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, goblin.interact(gameBoard, i));
	}
	
	//test ninja interaction
	@Test
	public void ninjaInteraction() {
		//stub function
	}
	
	//test queen interaction 
	@Test
	public void queenInteraction() {
		//stub function
	}
	
	//test treasure interaction
	@Test
	public void treasureInteraction() {
		//stub function
	}
	
}
