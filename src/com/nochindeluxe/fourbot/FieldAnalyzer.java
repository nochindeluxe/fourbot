package com.nochindeluxe.fourbot;

public class FieldAnalyzer {
    
    private int[][] field;
    private int colNum, rowNum;
    
    public FieldAnalyzer() {
        
    }
    
    public void updateField(int[][] fieldUpdate) {
        field = fieldUpdate;
    }
    
    public int getFirstOpenCell() {
        int openCellColumn = -1;
        for(int i=0; i<field.length; i++) {
            for(int j=0; j<field[0].length; j++) {
                int cell = field[i][j];
                if(cell == 0) {
                    openCellColumn = j;
                    break;
                } 
            }
        }
        return openCellColumn;
    }
    
}
