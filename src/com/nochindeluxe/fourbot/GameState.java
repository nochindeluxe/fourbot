package com.nochindeluxe.fourbot;

public class GameState {
   
    private int round;
    private int[][] field;
    
    public GameState() {
        field = null;
    }
    
    public void parseUpdate(String[] update) {
        String parent = update[0];
        String type = update[1];
        String value = update[2];
        
        OUTER:
        switch (parent) {
            case "game":
                switch (type) {
                    case "round":
                        setRound(value);
                        break OUTER;
                    case "field":
                        parseField(value);
                        break OUTER;
                    default:
                        System.err.println("***NCD ERROR: Unrecognized game update received from game server***");
                        break OUTER;
                }
            default:
                System.err.println("***NCD ERROR: Unrecognized update received from game server. type="+type+"***");
                break;
        }
    }
    
    private void setRound(String roundNum) {
        round = Integer.parseInt(roundNum);
    }
    
    public int getRound() {
        return round;
    }
    
    private void parseField(String fieldString) {
        System.err.println("parsing field string: "+fieldString);
        String[] rows = fieldString.split(";");
        field = new int[rows[0].split(",").length][rows.length];
        for(int i=0; i<rows.length; i++) {
            String[] rowCells = rows[i].split(",");
            for(int j=0; j<rowCells.length; j++) {
                System.err.println("field size - col: "+i+"  row: "+j);
                field[j][i] = Integer.parseInt(rowCells[j]);   
            }
        }        
    }
    
    public int[][] getField() {
        return field;
    }
    
    public boolean hasField() {
        return field != null;
    }
    
}
