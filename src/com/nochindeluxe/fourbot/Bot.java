package com.nochindeluxe.fourbot;

import java.util.ArrayList;

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
        String move = "";
        
        fieldAnalyzer.updateField(gameState.getField());
        //int openCell = fieldAnalyzer.getFirstOpenCell();
        ArrayList<Cell> moves = fieldAnalyzer.getMoves();
        for(Cell cell : moves) {
            System.err.println("Available moves: "+cell.getCol()+", "+cell.getRow());
        }
        move = "place_disc "+String.valueOf(moves.get(0).getCol());
        
        return move;
    }
}
