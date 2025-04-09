package com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy;

import com.sarvesh.LLD3.TicTacToe.model.Board;
import com.sarvesh.LLD3.TicTacToe.model.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
