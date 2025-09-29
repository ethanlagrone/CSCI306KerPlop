package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import gameEngine.InteractionResult;
import levelPieces.Archer;
import levelPieces.Canon;
import levelPieces.Goblin;
import levelPieces.Ninja;
import levelPieces.Queen;
import levelPieces.Treasure;

public class TestInteractions {
	
	//test archer interactions
	@Test
	public void archerInteraction() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Archer archer = new Archer(15);
		gameBoard[15] = archer;
		int hitCount = 0;
		//check if archer kills player over 500 attempts, almost assuredly will be true
		for(int i = 0; i<500; i++) {
			InteractionResult result = archer.interact(gameBoard, 13);
			if(InteractionResult.KILL == result){
				hitCount+=1;
			}
		}
		//Barring an extreme statistical anomaly, this should be true
		assertTrue(hitCount > 0);
		
		
		
		int hitCount2 = 0;
		//test archer's behavior at a distance
			
		for(int i = 0; i<500; i++) {
			InteractionResult result = archer.interact(gameBoard, 1);
			if(InteractionResult.KILL == result){
				hitCount2+=1;
			}
		}
		
//The probability of getting hit assuming the player is at location 1
//and the archer is at location 15 (they are 14 away from each other)
//is (21-14)/42 = 7/42.
//based on some stats stuff (bootstrap distribution) the number of hits
//should fall between 57 and 109 99% of the time
		assertTrue((hitCount2>57)&&(hitCount2<109));
//		assertTrue(hitCount2 == 0);
		
		
		
	}
	
	
	//test canon interactions
	@Test
	public void canonInteraction() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Canon canon = new Canon(5);
		gameBoard[5] = canon;
		int hitCount = 0;
		//check if cannon kills player over 500 attempts, almost assuredly will be true
		for(int i = 0; i<500; i++) {
			InteractionResult result = canon.interact(gameBoard, 3);
			if(InteractionResult.KILL == result){
				hitCount+=1;
			}
		}
		
		//Barring an extreme statistical anomaly, this should be true
		assertTrue(hitCount > 0);
		
		int hitCount2 = 0;
		//check if canon does not hit player if they are far away, it should not
		//this found a bug in the canon hit logic, negative numbers were being factored in, added an absolute value
		for(int i = 0; i<500; i++) {
			InteractionResult result = canon.interact(gameBoard, 0);
			if(InteractionResult.KILL == result){
				hitCount2 +=1;
			}
		}
		
		assertTrue(hitCount2 == 0);
	}
	
	
	//test goblin interactions(similar to bumble bee an example. This code heavily borrows from the bumblebee test.
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
