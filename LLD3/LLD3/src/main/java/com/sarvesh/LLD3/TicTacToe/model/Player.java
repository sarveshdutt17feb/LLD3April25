package com.sarvesh.LLD3.TicTacToe.model;

import java.util.Scanner;

public class Player {
    private Symbol symbol;
    private String playerName;
    private PlayerType playerType;
    Scanner scanner = new Scanner(System.in);
    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.playerName = name;
        this.playerType = playerType;
        //this.scanner = new Scanner(System.in);
    }



    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Move makeMove(Board board){
        //Take row, col in the input from the player.
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row number where you want to make a move");
        int row = scanner.nextInt();

        System.out.println("Please enter the col number where you want to make a move");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }
}
