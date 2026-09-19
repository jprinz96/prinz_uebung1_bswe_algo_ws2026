package at.hochschule.burgenland.bswe.algo.input;

import java.util.Scanner;

public class ManuelSudokuReader {
    public static int[][] readSudokuByUserInput(Scanner scan) {
        int[][] sudoku = new int[9][9];
        for (int row = 0; row < sudoku.length; row++) {
            for (int col = 0; col < sudoku.length ; col++) {
                sudoku[row][col] = validateUserInput(scan, row, col);
            }
        }
        return sudoku;
    }
    private static int validateUserInput(Scanner scan, int row, int col) {
        while (true){
            System.out.printf("Please enter a number for field [%d][%d] - 0 indicates an empty field:", row+1, col+1);
            System.out.println();
            String input = scan.nextLine().trim();

            try {
                int number = Integer.parseInt(input);
                if(number >= 0  && number <= 9) {
                    return number;
                }
                else {
                    System.out.println("Invalid input. Please enter a number between 0 and 9.");
                }
            }catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a number ");
            }
        }
    }
}
