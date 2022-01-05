package com.rcreddyn;

import java.util.List;
import java.util.Random;

public class BoardService {
    private final Random randomGenerator = new Random();

    public Board makeMove(Board board){
        List<Cell> emptyCells = board.getEmptyCells();
        double numberToPlace = randomGenerator.nextDouble();
        int indexToPlaceAt = randomGenerator.nextInt(emptyCells.size());
        Cell cellToPlaceAt = emptyCells.get(indexToPlaceAt);
        return board.placeTile(cellToPlaceAt, numberToPlace >= 0.9?4:2);
    }
}
