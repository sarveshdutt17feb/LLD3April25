package com.sarvesh.LLD3.TicTacToe.controller;

import com.sarvesh.LLD3.TicTacToe.exception.InvalidMoveException;
import com.sarvesh.LLD3.TicTacToe.exception.InvalidUndoException;
import com.sarvesh.LLD3.TicTacToe.model.Game;
import com.sarvesh.LLD3.TicTacToe.model.GameState;
import com.sarvesh.LLD3.TicTacToe.model.Player;
import com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {
    //makeMove
    //undo
    //checkWinner
    //gameState

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return Game.getBuilder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState gameState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public void undo(Game game) {
        try {
            game.undo();
        }catch (InvalidUndoException e){
            System.out.println(e.getMessage());
        }

    }
}
