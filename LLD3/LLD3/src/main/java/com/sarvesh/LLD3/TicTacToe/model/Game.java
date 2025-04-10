package com.sarvesh.LLD3.TicTacToe.model;

import com.sarvesh.LLD3.TicTacToe.exception.InvalidMoveException;
import com.sarvesh.LLD3.TicTacToe.exception.InvalidUndoException;
import com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy.ColWinningStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy.DiagonalWinningStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy.RowWinningStrategy;
import com.sarvesh.LLD3.TicTacToe.strategy.winningstrategy.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextMovePlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMovePlayerIndex() {
        return nextMovePlayerIndex;
    }

    public void setNextMovePlayerIndex(int nextMovePlayerIndex) {
        this.nextMovePlayerIndex = nextMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void printBoard() {
        board.printBoard();
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            return false;
        }

        //Whether the cell at which player is trying to make a move is empty or not.
        if (!board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)) {
            return false;
        }

        return true;
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextMovePlayerIndex);

        System.out.println("This is " + currentPlayer.getPlayerName() + "'s move.");

        //Player will choose the move that they want to make.
        Move move = currentPlayer.makeMove(board);

        //Game will validate if the move that player has chosen is valid or not.
        if (!validateMove(move)) {
            //throw some exception to the player.
            throw new InvalidMoveException("Invalid move, please retry");
        }

        //Move is valid, so apply this move to the board.
        int row = move.getCell().getRow(); // 2
        int col = move.getCell().getCol(); // 1

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextMovePlayerIndex = (nextMovePlayerIndex + 1) % players.size();

        if (checkWinner(finalMove)) {
            System.out.println("I am comming inside checkwinner in Game Class");
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }
    public void undo() throws InvalidUndoException {
        Move move=null;
        if (!moves.isEmpty()) {
            move = moves.get(moves.size() - 1);
        }else {
            throw new InvalidUndoException("Board is Empty you can not undo!!");
        }
        if(!moves.isEmpty())
            moves.remove(moves.size()-1);

        //check Move is valid, so apply this move to the board.
        int row = move.getCell().getRow(); // 2
        int col = move.getCell().getCol(); // 1

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);
        Character aChar = move.getPlayer().getSymbol().getaChar();
        Map<Integer,HashMap<Character,Integer>> rowMaps = RowWinningStrategy.getRowMaps();
        Map<Integer,HashMap<Character,Integer>> colMaps = ColWinningStrategy.getColMaps();
        Map<Character, Integer> leftDiagonalMap = DiagonalWinningStrategy.getLeftDiagonalMap();
        Map<Character, Integer> rightDiagonalMap = DiagonalWinningStrategy.getRightDiagonalMap();
        if(rowMaps.containsKey(row)){
            Map<Character,Integer> currentRowMap = rowMaps.get(row);
            if(!currentRowMap.isEmpty()){
                currentRowMap.put(aChar,currentRowMap.get(aChar)-1);
            }
        }

        if(colMaps.containsKey(col)){
            Map<Character,Integer> currentColMap = colMaps.get(col);
            if(!currentColMap.isEmpty()){
                currentColMap.put(aChar,currentColMap.get(aChar)-1);
            }
        }
        if(row==col){
           if(!leftDiagonalMap.isEmpty()){
               leftDiagonalMap.put(aChar,leftDiagonalMap.get(aChar)-1);
           }
        }
        if (row+col==board.getDimension()-1){
            if(!rightDiagonalMap.isEmpty()){
                rightDiagonalMap.put(aChar,rightDiagonalMap.get(aChar)-1);
            }
        }




    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        //private List<Move> moves;
        //private Player winner;
        //private GameState gameState;
        //private int nextMovePlayerIndex;

        private Builder() {
            this.players = new ArrayList<>();
            this.dimension = 0;
            this.winningStrategies = new ArrayList<>();
        }

        private void validateBotCount() {
            int count = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    count += 1;
                    if (count > 1) {
                        throw new RuntimeException("Only one BOT is allowed per game");
                    }
                }
            }
        }

        private void validateUniqueSymbols() {
            Set<Character> symbolSet = new HashSet<>();
            for (Player player : players) {
                symbolSet.add(player.getSymbol().getaChar());
            }

            if (symbolSet.size() != dimension - 1) {
                throw new RuntimeException("Every player should have a unique symbol");
            }
        }

        private void validations() {
            validateBotCount();
            validateUniqueSymbols();
        }

        public Game build() {
            //validations
            validations();

            return new Game(dimension, players, winningStrategies);
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }

}
