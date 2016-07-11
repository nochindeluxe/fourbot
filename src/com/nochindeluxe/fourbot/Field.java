package com.nochindeluxe.fourbot;

import java.util.ArrayList;

public class Field {
    
    private ArrayList<Cell> cells;
    
    public Field() {
        
    }
    
    public void update(int[][] field) {
        cells = new ArrayList<>();
        for(int i=0; i<field.length; i++) {
            for(int j=0; j<field[0].length; j++) {
                Cell cell = new Cell(i, j, field[i][j]);
                cells.add(cell);
            }
        }
    }
    
    public ArrayList<Cell> getCells() {
        return cells;
    }
    
    public ArrayList<Cell> getCol(int col) {
        ArrayList<Cell> colCells = new ArrayList<>();
        for(Cell cell : cells) {
            if(cell.getCol() == col) {
                colCells.add(cell);
            }
        }
        return colCells;
    }
}
