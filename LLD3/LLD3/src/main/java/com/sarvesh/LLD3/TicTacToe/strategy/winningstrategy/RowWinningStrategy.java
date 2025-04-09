package com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy;

import com.sarvesh.LLD3.TicTacToe.model.Board;
import com.sarvesh.LLD3.TicTacToe.model.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private final Map<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Character aChar = move.getPlayer().getSymbol().getaChar();

        if (!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        Map<Character, Integer> currRowMap = rowMaps.get(row);

        if (!currRowMap.containsKey(aChar)) {
            currRowMap.put(aChar, 0);
        }
        currRowMap.put(aChar, currRowMap.get(aChar) + 1);

        return currRowMap.get(aChar).equals(board.getDimension());
    }
}
