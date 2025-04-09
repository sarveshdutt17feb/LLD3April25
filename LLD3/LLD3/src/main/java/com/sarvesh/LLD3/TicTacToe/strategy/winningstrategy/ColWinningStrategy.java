package com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy;

import com.sarvesh.LLD3.TicTacToe.model.Board;
import com.sarvesh.LLD3.TicTacToe.model.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy{
    Map<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if (!colMaps.containsKey(col)) {
            colMaps.put(col, new HashMap<>());
        }

        Map<Character, Integer> colMap = colMaps.get(col);

        if (!colMap.containsKey(aChar)) {
            colMap.put(aChar, 0);
        }

        colMap.put(aChar, colMap.get(aChar) + 1);

        if (colMap.get(aChar).equals(board.getDimension())) {
            return true;
        }

        return false;
    }
}
