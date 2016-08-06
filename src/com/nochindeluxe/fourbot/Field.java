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
    
    public void setCell(int col, int row, int checker) {
        for(Cell cell : cells) {
            if(cell.getCol() == col && cell.getRow() == row) {
                cell.setChecker(checker);
                break;
            }
        }
    }
    
    public void setField(Field newField) {
        ArrayList<Cell> newCells = new ArrayList<>();
        for(Cell cell : newField.getCells()) {
            Cell newCell = new Cell(cell.getCol(), cell.getRow(), cell.getChecker());
            newCells.add(newCell);
        }
        cells = newCells;
    }
    
    public void setField(String fieldString) {
        String[] rows = fieldString.split(";");
        int[][] fieldArray = new int[rows[0].split(",").length][rows.length];
        for(int i=0; i<rows.length; i++) {
            String[] rowCells = rows[i].split(",");
            for(int j=0; j<rowCells.length; j++) {
                fieldArray[j][i] = Integer.parseInt(rowCells[j]);   
            }
        }
        
        colNum = fieldArray.length;
        rowNum = fieldArray[0].length;
        
        ArrayList<Cell> newCells = new ArrayList<>();
        for(int i=0; i<fieldArray.length; i++) {
            for(int j=0; j<fieldArray[0].length; j++) {
                Cell cell = new Cell(i, j, fieldArray[i][j]);
                newCells.add(cell);
            }
        }
        
        cells = newCells;
    }
}
