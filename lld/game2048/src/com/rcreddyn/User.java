package com.rcreddyn;

import java.util.Random;

public class User {
    private final Random randomGenerator = new Random();

    public Board makeMove(Board board, int dir){
        Move move = Move.RIGHT;
        switch (dir){
            case 0:
                move = Move.LEFT;
                break;
            case 1:
                move = Move.RIGHT;
                break;
            case 2:
                move = Move.UP;
                break;
            case 3:
                move = Move.DOWN;
                break;
        }
        return board.move(move);
    }
}

