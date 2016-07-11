package com.nochindeluxe.fourbot;

import java.util.Scanner;

public class GameParser {
   
    private final Scanner scanner; 
    private final GameSettings gameSettings;
    private final GameState gameState;
    private final Bot bot;
    
    public GameParser() {
        scanner = new Scanner(System.in);
        gameSettings = new GameSettings();
        gameState = new GameState();
        bot = new Bot(gameSettings, gameState);
    }
    
    public void run() {
        
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.length() == 0) { 
                continue;
            } 
     
            String[] parts = line.split(" ");
            switch(parts[0]) {
                case "settings":
                    String[] setting = new String[2];
                    setting[0] = parts[1];
                    setting[1] = parts[2];
                    gameSettings.parseSetting(setting);
                    break;
                case "update":
                    String[] update = new String[3];
                    update[0] = parts[1];
                    update[1] = parts[2];
                    update[2] = parts[3];
                    gameState.parseUpdate(update);
                    break;
                case "action":
                    if(parts[1].equals("move")) {
                        System.out.println(bot.makeMove());
                    } else {
                        System.err.println("***NCD ERROR: Unrecognized action received from game server***");
                    }
                    break;
                default:
                    // error
                    System.err.println("***NCD ERROR: Unrecognized input received from game server***");
            }
        }
    }
}
