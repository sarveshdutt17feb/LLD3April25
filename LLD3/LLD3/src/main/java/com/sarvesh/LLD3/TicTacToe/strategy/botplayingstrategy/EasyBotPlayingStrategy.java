package com.sarvesh.LLD3.TicTacToe.strategy.botplayingstrategy;

import com.sarvesh.LLD3.TicTacToe.model.Board;
import com.sarvesh.LLD3.TicTacToe.model.Cell;
import com.sarvesh.LLD3.TicTacToe.model.CellState;
import com.sarvesh.LLD3.TicTacToe.model.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
    //Iterate over the board and make a move at the first empty cell.

    for (List<Cell> row : board.getBoard()) {
        for (Cell cell : row) {
            if (cell.getCellState().equals(CellState.EMPTY)) {
                return new Move(
                        null,
                        cell
                );
            }
        }
    }
    return null;
}
}
