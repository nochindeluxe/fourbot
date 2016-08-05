package com.nochindeluxe.fourbot;

import java.util.ArrayList;

public class FieldAnalyzer {
    
    //private Field field;
    //private int colNum, rowNum;
    
    public FieldAnalyzer() {
//        field = new Field();
//        colNum = field.getColNum();
//        rowNum = field.getRowNum();
    }
    
    public void updateField(int[][] fieldUpdate) {
//        colNum = fieldUpdate.length;
//        rowNum = fieldUpdate[0].length;
//        field.update(fieldUpdate);
    }
    
//    public int getColNum() {
//        return colNum;
//    }
    
//    public int getRowNum() {
//        return rowNum;
//    }
    
    public int getFirstOpenCell(Field field) {
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
    
    public ArrayList<Cell> getMoves(Field field) {
        ArrayList<Cell> moves = new ArrayList<>();
        for(int i=0; i<field.getColNum(); i++) {
            ArrayList<Cell> colCells = field.getCol(i);
            Cell openCell = getOpenCellForCol(field, colCells);
            if(openCell != null) {
                moves.add(openCell);
            }
        }
        return moves;
    }
    
    public Cell getOpenCellForCol(Field field, ArrayList<Cell> colCells) {
        int openCells = field.getRowNum();
        for(int i=field.getRowNum()-1; i>=0; i--) {
            Cell cell = colCells.get(i);
            if(cell.getChecker() > 0) {
                openCells--;
            } else {
                return cell;
            }
        }
        return null;
    }
    
    public boolean cellHasWinCondition(Field field, Cell cell) {
	return 
                playerHasVerticalWinCondition(field, 1, cell) ||
                playerHasVerticalWinCondition(field, 2, cell) ||
                
                playerHasHorizontalWinCondition(field, 1, cell) ||
                playerHasHorizontalWinCondition(field, 2, cell) ||
                
                playerHasEastDiagWinCondition(field, 1, cell) ||
                playerHasEastDiagWinCondition(field, 2, cell) ||
                
                playerHasWestDiagWinCondition(field, 1, cell) ||
                playerHasWestDiagWinCondition(field, 2, cell);
    }
    
    public boolean playerHasVerticalWinCondition(Field field, int player, Cell cell) {
        Cell targetCell;
        boolean winAvailable = false;
        
        for(int i=3; i>0; i--) {
            if(cell.getRow()+i <= 5) {
                targetCell = field.getCell(cell.getCol(), cell.getRow()+i);
                if(targetCell.getChecker() != player) {
                    break;
                } else if(i == 1) {
                    winAvailable = true;
                }
            } else {
                break;
            }
        }
        return winAvailable;
    }
    
    public boolean playerHasHorizontalWinCondition(Field field, int player, Cell cell) {
        Cell targetCell;
        boolean winAvailable = false;
        
        //set 1: -3
        for(int i=3; i>0; i--) {
            //make sure cell exists and isn't off the board
            if(cell.getCol()-i >= 0) {
                targetCell = field.getCell(cell.getCol()-i, cell.getRow());
                
                //If we find an empty cell or an opponent's checker, our
                //win condition has failed because we have a 2-gap.
                if(targetCell.getChecker() != player) {
                    break;
                    
                //If we've checked 3 checkers and they all belong to the 
                //player, we have a 3-row, and thus a win condition
                } else if(i == 1) {
                    winAvailable = true;
                }
                
                //If we find a cell off the board, then we just cancel this
                //set, and the rest of the checkers in this set will be checked
                //by later sets.
            } else {
                break;
            }
        }

        //set 2: -2 +1
        for(int i=2; i>-2; i--) {
            if(cell.getCol()-i >= 0 && cell.getCol()-i < field.getColNum()) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()-i, cell.getRow());
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == -1) {
                        winAvailable = true;
                    }
                }
            } else {
                break;
            }
        }
        
        //set 3: -1 +2
        for(int i=-1; i<3; i++) {
            if(cell.getCol()+i >= 0 && cell.getCol()+i < field.getColNum()) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()+i, cell.getRow());
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == 2) {
                        winAvailable = true;
                    }
                }
            } else { 
                break;
            }
        }
        
        //set 4: +3
        for(int i=1; i<4; i++) {
            if(cell.getCol()+i < field.getColNum()) {
                targetCell = field.getCell(cell.getCol()+i, cell.getRow());
                if(targetCell.getChecker() != player) {
                    break;
                } else if(i == 3) {
                    winAvailable = true;
                }
            } else {
                break;
            }
        }
        
        return winAvailable;
    }
    
    public boolean playerHasEastDiagWinCondition(Field field, int player, Cell cell) {
        Cell targetCell;
        boolean winAvailable = false;
        
        //max 3 sets (6 checker max) -- if set doesn't have >4 checkers,
        //it auto fails win condition
        
        //set 1: (-3, +3) 
        for(int i=3; i>0; i--) {
            if(cell.getCol()-i >= 0 && cell.getRow()+i <= 5) {
                targetCell = field.getCell(cell.getCol()-i, cell.getRow()+i);
                if(targetCell.getChecker() != player) {
                    break;
                } else if(i == 1) {
                    winAvailable = true;
                }
            } else {
                break;
            }
        }
        
        //set 2: (-2, +2) (+1, -1)
        for(int i=2; i>-2; i--) {
            if(cell.getCol()-i >= 0 && 
                    cell.getCol()-i <= 6 &&
                    cell.getRow()+i <= 5 && 
                    cell.getRow()+i >=0) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()-i, cell.getRow()+i);
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == -1) {
                        winAvailable = true;
                    }
                }
            } else {
                break;
            }
        }
        
        //set 3: (-1, +1) (+2, -2)
        for(int i=1; i>-3; i--) {
            if(cell.getCol()-i >= 0 && 
                    cell.getCol()-i <= 6 && 
                    cell.getRow()+i <= 5 && 
                    cell.getRow()+i >= 0) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()-i, cell.getRow()+i);
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == -2) {
                        winAvailable = true;
                    }
                }
            } else {
                break;
            }
        }
        
        return winAvailable;
    }
    
    public boolean playerHasWestDiagWinCondition(Field field, int player, Cell cell) {
        Cell targetCell;
        boolean winAvailable = false;
        
        //set 1: (-3, -3) 
        for(int i=3; i>0; i--) {
            if(cell.getCol()-i >= 0 && cell.getRow()-i >= 0) {
                targetCell = field.getCell(cell.getCol()-i, cell.getRow()-i);
                if(targetCell.getChecker() != player) {
                    break;
                } else if(i == 1) {
                    winAvailable = true;
                }
            } else {
                break;
            }
        }
        
        //set 2: (-2, -2) (+1, +1)
        for(int i=2; i>-2; i--) {
            if(cell.getCol()-i >= 0 && 
                    cell.getCol()-i <= 6 &&
                    cell.getRow()-i <= 5 && 
                    cell.getRow()-i >=0) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()-i, cell.getRow()-i);
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == -1) {
                        winAvailable = true;
                    }
                }
            } else {
                break;
            }
        }
        
        //set 3: (-1, -1) (+2, +2)
        for(int i=1; i>-3; i--) {
            if(cell.getCol()-i >= 0 && 
                    cell.getCol()-i <= 6 && 
                    cell.getRow()-i <= 5 && 
                    cell.getRow()-i >= 0) {
                if(i != 0) {
                    targetCell = field.getCell(cell.getCol()-i, cell.getRow()-i);
                    if(targetCell.getChecker() != player) {
                        break;
                    } else if(i == -2) {
                        winAvailable = true;
                    }
                }
            } else {
                break;
            }
        }
        
        //set 4: (+3, +3)
        for(int i=1; i<4; i++) {
            if(cell.getCol()+i <= 6 && cell.getRow()+i <= 5) {
               targetCell = field.getCell(cell.getCol()+i, cell.getRow()+i);
               if(targetCell.getChecker() != player) {
                   break;
               } else if(i == 3) {
                   winAvailable = true;
               }
            } else {
                break;
            }
        }
        
        return winAvailable;
    }
    
    //Test
//    public void setField(Field staticField) {
//        field = staticField;
//    }
    
//    public Field getField() {
//        return field;
//    }
    
}
