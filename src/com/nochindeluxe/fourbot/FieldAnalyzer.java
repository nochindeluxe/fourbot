package com.nochindeluxe.fourbot;

import java.util.ArrayList;

public class FieldAnalyzer {
    
    private Field field;
    private int colNum, rowNum;
    
    public FieldAnalyzer() {
        field = new Field();
    }
    
    public void updateField(int[][] fieldUpdate) {
        colNum = fieldUpdate.length;
        rowNum = fieldUpdate[0].length;
        field.update(fieldUpdate);
    }
    
    public int getFirstOpenCell() {
        int openCellCol = -1;
        ArrayList<Cell> cells = field.getCells();
        for(Cell cell : cells) {
            if(cell.getChecker() == 0) {
                openCellCol = cell.getCol();
                break;
            }
        }
        return openCellCol;
    }
    
    public ArrayList<Cell> getMoves() {
        ArrayList<Cell> moves = new ArrayList<>();
        for(int i=0; i<colNum; i++) {
            ArrayList<Cell> colCells = field.getCol(i);
            Cell openCell = getOpenCellForCol(colCells);
            if(openCell != null) {
                moves.add(openCell);
            }
        }
        return moves;
    }
    
    public Cell getOpenCellForCol(ArrayList<Cell> colCells) {
        int openCells = rowNum;
        for(int i=rowNum-1; i>=0; i--) {
            Cell cell = colCells.get(i);
            if(cell.getChecker() > 0) {
                openCells--;
            } else {
                return cell;
            }
        }
        return null;
    }
    
   
    
}
