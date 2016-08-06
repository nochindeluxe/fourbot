package com.nochindeluxe.fourbot;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BotTest {
    
    public BotTest() {
    }

//    @Test
//    public void testMakeMove() {
//        System.out.println("Testing makeMove()...");
//        
//        Field mockField = new Field();
//        mockField.setCell(0, 5, 2);
//        mockField.setCell(1, 5, 2);
//        mockField.setCell(2, 5, 1);
//        
//        Bot instance = null;
//        String expResult = "";
//        String result = instance.makeMove();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    @Test 
    public void moveGivesOpponentWinCondition() {
        System.out.println("Testing moveGivesOpponentWinCondition()...");
        
        GameSettings mockGameSettings = mock(GameSettings.class);
        when(mockGameSettings.getBotId()).thenReturn(1);
        when(mockGameSettings.getOppBotId()).thenReturn(2);
        
        GameState mockGameState = mock(GameState.class);
        
        Field mockField = new Field();
        mockField.setCell(0, 5, 2);
        mockField.setCell(1, 5, 2);
        mockField.setCell(2, 5, 1);
        mockField.setCell(0, 4, 2);
        mockField.setCell(1, 4, 2);
        mockField.setCell(2, 4, 2);
        
        Bot instance = new Bot(mockGameSettings, mockGameState);
        Cell targetCell = new Cell(3, 5, 0);
        boolean shouldBeTrue = instance.moveGivesOpponentWinCondition(mockField, targetCell);
        assertTrue(shouldBeTrue);
    }
    
}
