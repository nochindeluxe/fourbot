
package com.nochindeluxe.fourbot;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FieldAnalyzerTest {
    
    public FieldAnalyzerTest() {
    }

    @Test
    public void testUpdateField() {
        System.out.println("Testing updateField()...");
        int[][] fieldUpdate = new int[7][6];
        FieldAnalyzer instance = new FieldAnalyzer();
        instance.updateField(fieldUpdate);
        int colNum = instance.getColNum();
        int rowNum = instance.getRowNum();
        assertEquals(colNum, 7);
        assertEquals(rowNum, 6);
    }

    @Test
    public void testGetMoves() {
        System.out.println("Testing getMoves()...");
        FieldAnalyzer instance = new FieldAnalyzer();
        Field mockField = new Field(); 
        mockField.setCell(0, 5, 1);
        mockField.setCell(0, 4, 1);
        mockField.setCell(0, 3, 2);
        mockField.setCell(6, 5, 1);
        instance.setField(mockField);
        ArrayList<Cell> moves = instance.getMoves();
        Cell move0 = moves.get(0);
        assertEquals(move0.getCol(), 0);
        assertEquals(move0.getRow(), 2);
        Cell move5 = moves.get(6);
        assertEquals(move5.getCol(), 6);
        assertEquals(move5.getRow(), 4);
    }
    
    @Test
    public void testPlayerHasVerticalWinCondition() {
        System.out.println("Testing vertical win condition...");
        FieldAnalyzer instance = new FieldAnalyzer();
        Field mockField = new Field();
        mockField.setCell(3, 5, 1);
        mockField.setCell(3, 4, 1);
        mockField.setCell(3, 3, 1);
        instance.setField(mockField);
        Cell mockCell = new Cell(3, 2, 1);
        assertTrue(instance.playerHasVerticalWinCondition(1, mockCell));
    }
    
    @Test
    public void testPlayerHasHorizontalWinCondition() {
        System.out.println("Testing horizontal win condition...");
        FieldAnalyzer instance = new FieldAnalyzer();
        Field mockField = new Field();
        mockField.setCell(0, 5, 2);
        mockField.setCell(1, 5, 2);
        mockField.setCell(2, 5, 2);
        instance.setField(mockField);
        Cell mockCell = new Cell(3, 5, 0);
        assertTrue(instance.playerHasHorizontalWinCondition(2, mockCell));
        
        mockField = new Field();
        mockField.setCell(0, 5, 1);
        mockField.setCell(2, 5, 1);
        mockField.setCell(3, 5, 1);
        instance.setField(mockField);
        mockCell = new Cell(1, 5, 0);
        assertTrue(instance.playerHasHorizontalWinCondition(1, mockCell));
        
        mockField = new Field();
        mockField.setCell(0, 5, 1);
        mockField.setCell(1, 5, 1);
        mockField.setCell(3, 5, 1);
        instance.setField(mockField);
        mockCell = new Cell(2, 5, 0);
        assertTrue(instance.playerHasHorizontalWinCondition(1, mockCell));
        
        mockField = new Field();
        mockField.setCell(1, 5, 2);
        mockField.setCell(2, 5, 2);
        mockField.setCell(3, 5, 2);
        instance.setField(mockField);
        mockCell = new Cell(0, 5, 0);
        assertTrue(instance.playerHasHorizontalWinCondition(2, mockCell));
    }
    
    @Test public void testPlayerHasEastDiagWinCondition() {
        System.out.println("Testing east diag win condition...");
        FieldAnalyzer instance = new FieldAnalyzer();
        Field mockField = new Field();
        for(int i=0; i<4; i++) {
            mockField.setCell(i, 5, 1);
        }
        
        for(int i=1; i<4; i++) {
            mockField.setCell(i, 4, 1);
        }
        
        for(int i=2; i<4; i++) {
            mockField.setCell(i, 3, 1);
        }
        
        instance.setField(mockField);
        Cell mockCell = new Cell(3, 2, 1);
        assertTrue(instance.playerHasEastDiagWinCondition(1, mockCell));
    }
    
    @Test public void testPlayerHasWestDiagCondition() {
        System.out.println("Testing west diag win condition...");
        FieldAnalyzer instance = new FieldAnalyzer();
        Field mockField = new Field();
        for(int i=6; i>2; i--) {
            mockField.setCell(i, 5, 1);
        }
        
        for(int i=5; i>2; i--) {
            mockField.setCell(i, 4, 1);
        }
        
        for(int i=4; i>2; i--) {
            mockField.setCell(i, 3, 1);
        }
        
        instance.setField(mockField);
        Cell mockCell = new Cell(3, 2, 1);
        assertTrue(instance.playerHasWestDiagWinCondition(1, mockCell));
    }

}
