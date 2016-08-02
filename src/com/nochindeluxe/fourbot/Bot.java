package com.nochindeluxe.fourbot;

import java.util.ArrayList;
import java.util.Random;

public class Bot {
    
    private GameSettings gameSettings;
    private GameState gameState;
    private FieldAnalyzer fieldAnalyzer;
    
    public Bot(GameSettings gameSettings, GameState gameState) {
        this.gameSettings = gameSettings;
        this.gameState = gameState;
        fieldAnalyzer = new FieldAnalyzer();
    }
    
    public String makeMove() {
        fieldAnalyzer.updateField(gameState.getField());
        ArrayList<Cell> moves = fieldAnalyzer.getMoves();
        
        //If a win condition exists, play that move
        for(Cell cell : moves) {
            if(fieldAnalyzer.cellHasWinCondition(cell)) {
                return "place_disc "+String.valueOf(cell.getCol());
            }
        }
        
        //Otherwise, choose a random move
        Random random = new Random();
        int randomMove = random.nextInt(moves.size());
        return "place_disc "+String.valueOf(moves.get(randomMove).getCol());
    }
}
