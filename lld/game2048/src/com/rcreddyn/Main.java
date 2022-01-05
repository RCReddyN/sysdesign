package com.rcreddyn;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Board board = new Board(4);
        Computer computer = new Computer();
        User user = new User();
        Scanner input = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            board = computer.makeMove(board);
        }
        printBoard(board);
        while (!board.getEmptyCells().isEmpty()) {
            int dir = input.nextInt();
            board = user.makeMove(board, dir);
            printBoard(board);
            board = computer.makeMove(board);
            printBoard(board);
        }

        if (board.getScore() == 2048) {
            System.out.println("Congratulations! Your Score: " + board.getScore());
        }
        else {
            System.out.println("Game Over! Your Score: " + board.getScore());
        }
    }

    public static void printBoard(Board board){
        for(int row =0; row<board.getSize(); row++){
            for(int col=0; col< board.getSize(); col++){
                System.out.print(board.getCell(new Cell(col, row))+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
