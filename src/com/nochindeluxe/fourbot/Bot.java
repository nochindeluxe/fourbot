package com.nochindeluxe.fourbot;

import java.util.ArrayList;
import java.util.Random;

public class Bot {
    
    private final GameSettings gameSettings;
    private final GameState gameState;
    private final FieldAnalyzer fieldAnalyzer;
    
    public Bot(GameSettings gameSettings, GameState gameState) {
        this.gameSettings = gameSettings;
        this.gameState = gameState;
        fieldAnalyzer = new FieldAnalyzer();
    }
    
    public String makeMove() {
        //fieldAnalyzer.updateField(gameState.getField());
        Field field = new Field();
        field.update(gameState.getField());
        ArrayList<Cell> moves = fieldAnalyzer.getMoves(field);
        Cell defaultMove = moves.get(0); //This is only used if the opponent has win conditions no matter what we do
        
        //If we have a win condition somewhere, play that
        for(Cell cell : moves) {
            if(fieldAnalyzer.cellHasWinCondition(field, gameSettings.getBotId(), cell)) {
                return "place_disc "+String.valueOf(cell.getCol());
            }
        }
        
        //If our opponent has a win condition somwhere, play that
        for(Cell cell : moves) {
            if(fieldAnalyzer.cellHasWinCondition(field, gameSettings.getOppBotId(), cell)) {
                return "place_disc "+String.valueOf(cell.getCol());
            }
        }
        
        //Otherwise, choose a random move from our moves list
        while(moves.size() > 0) {
            Random random = new Random();
            int randomMove = random.nextInt(moves.size());
            
            if(moveGivesOpponentWinCondition(field, moves.get(randomMove))) {
                moves.remove(randomMove);
            } else {
                return "place_disc "+String.valueOf(moves.get(randomMove).getCol());
            }
        }
        
        //If we get here it means that every possible move we have
        //gives our opponent a win, so just play and get it over with :(
        return "place_disc "+String.valueOf(defaultMove.getCol());
    }
    
    public boolean moveGivesOpponentWinCondition(Field currentField, Cell selectedMove) {
        Field simField = new Field();
        simField.setField(currentField);
        
        simField.setCell(selectedMove.getCol(), selectedMove.getRow(), gameSettings.getBotId());
        
        if(selectedMove.getRow()-1 >= 0) {
            Cell oppNextMove = new Cell(selectedMove.getCol(), selectedMove.getRow()-1, 0);
            return fieldAnalyzer.cellHasWinCondition(simField, gameSettings.getOppBotId(), oppNextMove);
        } else {
            return false;
        }
    }
}
