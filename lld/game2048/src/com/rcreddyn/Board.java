package com.rcreddyn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {
    private final static int DEFAULT_SIZE = 4;
    private final int[][] board;
    private final int score;

    public Board() {
        this.board = new int[Board.DEFAULT_SIZE][Board.DEFAULT_SIZE];
        this.score = 0;
    }

    public Board(int size) {
        this.board = new int[size][size];
        this.score = 0;
    }

    private Board(int[][] board, int score) {
        this.score = score;
        this.board = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                this.board[row][col] = board[row][col];
            }
        }
    }

    private static int[][] transpose(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                newBoard[row][col] = board[col][row];
            }
        }
        return newBoard;
    }

    private static int[][] reverseRows(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                newBoard[row][col] = board[row][board[0].length - col - 1];
            }
        }
        return newBoard;
    }

    public int getSize() {
        return board.length;
    }

    public int getScore() {
        return this.score;
    }

    public int getCell(Cell cell) {
        return board[cell.getX()][cell.getY()];
    }

    public boolean isEmpty(Cell cell) {
        return getCell(cell) == 0;
    }

    public List<Cell> getEmptyCells() {
        List<Cell> emptyCells = new ArrayList<>();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                Cell cell = new Cell(row, col);
                if (isEmpty(cell)) {
                    emptyCells.add(cell);
                }
            }
        }
        return emptyCells;
    }

    public Board placeTile(Cell cell, int number) {
        if (!isEmpty(cell)) {
            return this;
        }
        Board newBoard = new Board(this.board, this.score);
        newBoard.board[cell.getX()][cell.getY()] = number;
        return newBoard;
    }

    public Board move(Move move) {
        int newScore = 0;
        int[][] tempBoard = new int[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                tempBoard[row][col] = board[row][col];
            }
        }
        if (move == Move.LEFT || move == Move.RIGHT) {
            tempBoard = transpose(tempBoard);
        }
        if (move == Move.DOWN || move == Move.RIGHT) {
            tempBoard = reverseRows(tempBoard);
        }

        int[][] newBoard = new int[board.length][board[0].length];
        for (int row = 0; row < tempBoard.length; row++) {
            LinkedList<Integer> currRow = new LinkedList<>();
            for (int col = 0; col < tempBoard.length; col++) {
                if (tempBoard[row][col] > 0) {
                    currRow.add(tempBoard[row][col]);
                }
            }

            LinkedList<Integer> newRow = new LinkedList<>();
            while (currRow.size() >= 2) {
                int first = currRow.pop();
                int second = currRow.peek();
                if (second == first) {
                    int newNumber = first * 2;
                    newRow.add(newNumber);
                    newScore += newNumber;
                    currRow.pop();
                } else {
                    newRow.add(first);
                }
            }
            newRow.addAll(currRow);

            for (int col = 0; col < board[0].length; col++) {
                if (!newRow.isEmpty()) {
                    newBoard[row][col] = newRow.pop();
                }
            }
        }
        if (move == Move.DOWN || move == Move.RIGHT) {
            newBoard = reverseRows(newBoard);
        }
        if (move == Move.LEFT || move == Move.RIGHT) {
            newBoard = transpose(newBoard);
        }
        return new Board(newBoard, this.score + newScore);
    }
}
