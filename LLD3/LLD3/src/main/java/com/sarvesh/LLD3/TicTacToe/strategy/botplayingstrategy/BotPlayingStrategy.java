package com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy;

import com.sarvesh.LLD3.TicTacToe.model.Board;
import com.sarvesh.LLD3.TicTacToe.model.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
