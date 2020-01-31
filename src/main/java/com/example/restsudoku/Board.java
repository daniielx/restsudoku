package com.example.restsudoku;

public class Board {
    private int arr[][];

    public int[][] getArr() {
        return arr;
    }

    public void setArr(int[][] arr) {
        this.arr = arr;
    }

    public Board(int[][] arr) {
        this.arr = arr;
    }
    public Board() {

    }
}
