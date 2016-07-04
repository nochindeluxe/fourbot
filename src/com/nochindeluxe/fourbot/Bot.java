package com.nochindeluxe.fourbot;

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
        int openCell = fieldAnalyzer.getFirstOpenCell();
        move = "place_disc "+String.valueOf(openCell);
        
        return move;
    }
}
