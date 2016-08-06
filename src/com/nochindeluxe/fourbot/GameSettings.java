package com.nochindeluxe.fourbot;

public class GameSettings {
    
    private int timeBank;
    private int timePerMove;
    private String p1, p2;
    private String botName;
    private int botId, oppBotId;
    private int colNum, rowNum;
    
    public GameSettings() {
        timeBank = 0;
        timePerMove = 0;
        p1 = "p1";
        p2 = "p2";
        botName = "myBot";
        botId = -1;
        colNum = 0;
        rowNum = 0;
    }
    
    public void parseSetting(String[] setting) {
        String type = setting[0];
        String value = setting[1];
        
        switch(type) {
            case "timebank":
                timeBank = Integer.parseInt(value);
                break;
            case "time_per_move":
                timePerMove = Integer.parseInt(value);
                break;
            case "player_names":
                String[] names = value.split(",");
                p1 = names[0];
                p2 = names[1];
                break;
            case "your_bot":
                botName = value;
                break;
            case "your_botid":
                botId = Integer.parseInt(value);
                oppBotId = (botId == 1) ? 2 : 1;
                break;
            case "field_columns":
                colNum = Integer.parseInt(value);
                break;
            case "field_rows":
                rowNum = Integer.parseInt(value);
                break;
            default:
                System.err.println("***NCD ERROR: Unrecognized setting received from game server***");
                break;
        }
    }
    
    public int getTimeBank() {
        return timeBank;
    }
    
    public int getTimePerMove() {
        return timePerMove;
    }
    
    public String getP1Name() {
        return p1;
    }
    
    public String getP2Name() {
        return p2;
    }
    
    public String getMyBotName() {
        return botName;
    }
    
    public int getBotId() {
        return botId;
    }
    
    public int getOppBotId() {
        return oppBotId;
    }
    
    public int getColNum() {
        return colNum;
    }
    
    public int getRowNum() {
        return rowNum;
    }
}
