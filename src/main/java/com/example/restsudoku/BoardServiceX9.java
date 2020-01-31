package com.example.restsudoku;

import org.springframework.stereotype.Service;

@Service
public class BoardServiceX9 {

    static int arr[][] = new int[9][9];
    private static final int size = 9;


    public String validateBoard(String str) {
        int x = 0;
        char charArr[] = str.toCharArray();
        for (int i=0; i< arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(charArr[x++]));
            }
        }
        x=0;
        String response = "";
        //solve();
        if (solve()) {
            for (int i=0; i< arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    response += arr[i][j];
                }
            }
        }
        else {
            response += "NoSolution                                                            " +
                    "                                                              ";
        }
        return response;
    }

    private static boolean isInRow(int row, int number) {
        for (int i = 0; i < size; i++)
            if (arr[row][i] == number)
                return true;
        return false;
    }

    private static boolean isInCol(int col, int number) {
        for (int i = 0; i < size; i++)
            if (arr[i][col] == number)
                return true;
        return false;
    }

    private static boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (arr[i][j] == number)
                    return true;
        return false;
    }

    private static boolean isOk(int row, int col, int number) {
        return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
    }

    public static boolean solve() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (arr[row][col] == 0 ) {
                    for (int number = 1; number <= size; number++) {
                        if (isOk(row, col, number)) {
                            arr[row][col] = number;
                            if (solve()) {
                                return true;
                            } else {
                                arr[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}
