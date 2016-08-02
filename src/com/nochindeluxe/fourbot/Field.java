package com.nochindeluxe.fourbot;

import java.util.ArrayList;

public class Field {
    
    private ArrayList<Cell> cells;
    private int colNum;
    private int rowNum;
    
    public Field() {
        //init field
        cells = new ArrayList<>();
        colNum = 7;
        rowNum = 6;
        for(int i=0; i<colNum; i++) {
            for(int j=0; j<rowNum; j++) {
                Cell cell = new Cell(i, j, 0);
                cells.add(cell);
            }
        }
    }
    
    //parses into (col, row)
    public void update(int[][] field) {
        colNum = field.length;
        rowNum = field[0].length;
        
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
    
    public Cell getCell(int col, int row) {
        for(Cell cell : cells) {
            if(cell.getCol() == col && cell.getRow() == row) {
                return cell;
            }
        } 
        
        return null;
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
    
    public int getColNum() {
        return colNum;
    }
    
    public int getRowNum() {
        return rowNum;
    }
    
    //Test
    public void setCell(int col, int row, int checker) {
        for(Cell cell : cells) {
            if(cell.getCol() == col && cell.getRow() == row) {
                cell.setChecker(checker);
                break;
            }
        }
    }
}
