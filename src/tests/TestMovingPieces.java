package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import gameEngine.Drawable;
import gameEngine.GameEngine;
import levelPieces.Goblin;
import levelPieces.Ninja;

public class TestMovingPieces {

	//Testing ninja movement
	@Test
	public void testMovingNinja() {
		//it is ok if the ninja lands on the same spot as another object but it can not land on the player
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Ninja ninja = new Ninja(5);
		gameBoard[5] = ninja;
		int oldLoc=5;
		for(int i = 0; i < 100; i++) {
			//Ninja should never land on the player 
			ninja.move(gameBoard, 6);
			assertTrue(ninja.getLocation() != 6);
			//Ninja should never move more than 5 spaces at a time
			assertTrue(Math.abs(ninja.getLocation()-oldLoc)<=5);
			oldLoc=ninja.getLocation();
		}
	}
	
	//Testing Goblin movement
	@Test
	public void testMovingGoblin() {
		Drawable [] gameBoard = new Drawable[GameEngine.BOARD_SIZE];
		Goblin goblin = new Goblin(5);
		gameBoard[5] = goblin;
		int locationBefore = goblin.getLocation();
		goblin.move(gameBoard, 0);
		Goblin goblin2 = new Goblin(0);
		gameBoard[0] = goblin2;
		goblin2.move(gameBoard, 0);
		
		//make sure goblin moves one space to the left
		assertEquals(goblin.getLocation(), locationBefore-1);
		
		//test to make sure that the goblin wont go past the index of 0
		assertEquals(goblin2.getLocation(), 0);
		
	}

}
