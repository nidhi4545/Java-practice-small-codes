package com.company;

import static java.lang.Math.sqrt;

public class Main {

    public static void main(String[] args) {
	int [][] board = {{0,0,4,5,2,0,9,0,0},
            {0,9,0,0,3,0,8,0,0},
            {0,3,0,0,1,0,0,0,2},
            {0,5,9,8,0,0,2,0,4},
            {0,7,0,2,0,3,0,9,0},
            {3,0,2,0,0,1,5,8,0},
            {7,0,0,0,4,0,0,5,0},
            {0,0,1,0,8,0,0,4,0},
            {0,0,5,0,7,9,3,0,0}};

        System.out.println("The Sudoku board is:");
        printBoard(board);

        if(solveSudokuBoard(board)){
            System.out.println("Solved Successfully");
            printBoard(board);
        }
        else{
            System.out.println("Not able to solve board :(");
        }



    }

    private static void printBoard(int[][] board){
        for(int row=0; row < 9; row++){
            if(row % 3 == 0 && row != 0){
                System.out.println("------------");
            }
            for(int column = 0; column < board.length; column++){
                if(column % 3 == 0 && column != 0){
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean isNumberInRow(int[][] board, int row, int numberRow){
        for(int i = 0; i < 9; i++){
            if(board[row][i] == numberRow){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int column, int numberCol){
        for(int j = 0; j < 9; j++){
            if(board[j][column] == numberCol){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int row, int column, int number){
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;
        for(int i = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if(board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][]board, int row, int column, int number){
        return !isNumberInRow(board, row,number) && !isNumberInColumn(board,column,number) && !isNumberInBox(board,row,column,number);
    }

    private static boolean solveSudokuBoard(int[][] board){
        for(int row = 0; row < 9; row++){
            for(int column = 0; column < 9; column ++){
                if(board[row][column] == 0){
                    for(int numberToTry = 1; numberToTry <= 9; numberToTry++){
                        if(isValidPlacement(board, row,column,numberToTry)){
                            board[row][column] = numberToTry;

                            if(solveSudokuBoard(board)){
                                return true;
                            }
                            else{
                                board[row][column] = 0;
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
