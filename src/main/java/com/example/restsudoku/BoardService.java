package com.example.restsudoku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {


    private GamerRepository gamerRepository;

    @Autowired
    public void setGamerRepository(GamerRepository gamerRepository) {
        this.gamerRepository = gamerRepository;
    }

    static int arr[][] = new int[4][4];
    private static final int size = 4;


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
            response += "NoSolution      ";
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
        int r = row - row % 2;
        int c = col - col % 2;

        for (int i = r; i < r + 2; i++)
            for (int j = c; j < c + 2; j++)
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
                    for (int number = 1; number <= 4; number++) {
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


    public String saveUserGame(String userName, int userId, String game) {
        Gamer gamer = new Gamer(userName, userId, game);
        gamerRepository.save(gamer);
        return gamer.getGamerName();
    }

    public String getSavedGame(String userName, int userId) {
        return gamerRepository.findById(userId).get().getGame();
    }

    public String getClue(String str) {
        int x = 0;
        char charArr[] = str.toCharArray();
        for (int i=0; i< arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(charArr[x++]));
            }
        }
        x=0;
        String response = "";
        if (solveForClue()) {
            for (int i=0; i< arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    response += arr[i][j];
                }
            }
        }
        else {
            response += str;
        }
        return response;
    }

    private boolean solveForClue() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (arr[row][col] == 0 ) {
                    for (int number = 1; number <= 4; number++) {
                        if (isOk(row, col, number)) {
                            arr[row][col] = number;
                            return true;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public String checkBoard(String str) {

        int x = 0;
        char charArr[] = str.toCharArray();
        for (int i=0; i< arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(charArr[x++]));
            }
        }
        x=0;
        for(int row = 0; row < size; row ++) {
            for(int col = 0; col < size; col++) {
                if(arr[row][col] != 0) {
                    if(isOkCheckBoard(row, col, arr[row][col])) {
                        return Integer.toString(arr[row][col]);
                    }
                }
            }
        }
        return "*";
    }

    private boolean isOkCheckBoard(int row, int col, int number) {
        int flag = -1;
        for (int i = 0; i < size; i++) {
            if (arr[row][i] == number) {
                flag++;
            }
            if (flag == 1) {
                return true;
            }

        }
        flag = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i][col] == number) {
                flag++;
            }
            if (flag == 1) {
                return true;
            }
        }

        int r = row - row % 2;
        int c = col - col % 2;
        flag = -1;
        for (int i = r; i < r + 2; i++) {
            for (int j = c; j < c + 2; j++) {
                if (arr[i][j] == number) {
                    flag++;
                }
                if (flag == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
