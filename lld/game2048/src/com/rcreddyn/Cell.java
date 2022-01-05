package com.rcreddyn;

public class Cell {
    private final int x;
    private final int y;

    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
