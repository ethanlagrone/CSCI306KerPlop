package tests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import levelPieces.Canon;
import levelPieces.Goblin;
import levelPieces.Ninja;
import levelPieces.Queen;
import levelPieces.Treasure;

public class TestInteractions {
	
	//test archer interactions
	@Test
	public void archerInteraction() {
		//stub function
	}
	
	//test canon interactions
	@Test
	public void canonInteraction() {
		//Not sure how to test random hits
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
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Ninja ninja = new Ninja(5);
		gameBoard[5] = ninja;
		
		// Checks for interactions which should be none except for the three spaces around the ninja
		for (int i=0; i<4; i++)
			assertEquals(InteractionResult.NONE, ninja.interact(gameBoard, i));
		for (int i=4; i<=6; i++)	
			assertEquals(InteractionResult.HIT, ninja.interact(gameBoard, i));
		for (int i=7; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, ninja.interact(gameBoard, i));
	}
	
	//test queen interaction 
	@Test
	public void queenInteraction() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Queen queen = new Queen(5);
		gameBoard[5] = queen;
		
		assertEquals(InteractionResult.ADVANCE, queen.interact(gameBoard, 5));

		// Checks for interactions which should be none except for the space on the queen
		for (int i=0; i<5; i++)
			assertEquals(InteractionResult.NONE, queen.interact(gameBoard, i));
		for (int i=6; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, queen.interact(gameBoard, i));
	}
	
	//test treasure interaction
	@Test
	public void treasureInteraction() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Treasure treasure = new Treasure(5);
		gameBoard[5] = treasure;
		
		assertEquals(InteractionResult.GET_POINT, treasure.interact(gameBoard, 5));

		// Checks for interactions which should be none except for the space on the treasure
		for (int i=0; i<5; i++)
			assertEquals(InteractionResult.NONE, treasure.interact(gameBoard, i));
		for (int i=6; i<GameEngine.BOARD_SIZE; i++)	
			assertEquals(InteractionResult.NONE, treasure.interact(gameBoard, i));
	}
	
}
