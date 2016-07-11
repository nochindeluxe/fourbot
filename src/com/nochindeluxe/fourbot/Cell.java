package com.nochindeluxe.fourbot;

public class Cell {
    public final int col;
    public final int row;
    public int checker;
    
    public Cell(int col, int row, int checker) {
        this.col = col;
        this.row = row;
        this.checker = checker;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getRow() {
        return row;
    }
    
    public void setChecker(int val) {
        checker = val;
    }
    
    public int getChecker() {
        return checker;
    }
}
