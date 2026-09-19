package at.hochschule.burgenland.bswe.algo;

import at.hochschule.burgenland.bswe.algo.input.CsvFileReader;
import at.hochschule.burgenland.bswe.algo.input.ManualSudokuReader;

import java.util.Scanner;

public class SudokuMenu {
    private static final String CYAN = "\u001B[36m";
    private static final String RESET = "\u001B[0m";

    private static final Scanner scan = new Scanner(System.in);

    public static void run() {
        printGreeting();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scan.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    CsvFileReader csvFileReader = new CsvFileReader();
                    int[][] unsolvedSudoku = csvFileReader.readUnsolvedSudoku();
                    solveSudoku(unsolvedSudoku);
                }
                case "2" -> {
                    int[][] unsolvedSudoku = ManualSudokuReader.readSudokuByUserInput(scan);
                    solveSudoku(unsolvedSudoku);
                }
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void solveSudoku(int[][] unsolvedSudoku) {
        int[][] originalSudoku = copySudoku(unsolvedSudoku);

        int[][] solvedSudoku = Solver.solve(unsolvedSudoku);
        solution(solvedSudoku, originalSudoku);
    }

    private static void solution(int[][] solvedSudoku, int[][] originalSudoku) {
        if (solvedSudoku == null) {
            System.out.println("Unsolvable Sudoku 😞");
        } else {
            System.out.println("~~~~~~ Solved Sudoku ~~~~~~");
            printSudoku(solvedSudoku, originalSudoku);
        }
    }

    private static void printGreeting() {
        System.out.println("~~~~~~~~~~~ Welcome to the Sudoku solver ~~~~~~~~~~~");
    }

    private static void printMenu() {
        System.out.println("""
                1 - Load Sudoku from CSV file
                2 - Enter Sudoku manually
                0 - Exit""");
    }

    /**
     * Creates a deep copy of the given Sudoku board.
     * Changes to the original board therefore do not affect the copy.
     *
     * @param sudokuBoard the Sudoku board to copy
     * @return an independent copy of the board
     */
    private static int[][] copySudoku(int[][] sudokuBoard) {
        int[][] copy = new int[sudokuBoard.length][];

        for (int row = 0; row < sudokuBoard.length; row++) {
            copy[row] = sudokuBoard[row].clone();
        }

        return copy;
    }
    /**
     * Prints the solved Sudoku in a grid.
     * Numbers inserted by the solver are displayed in cyan.
     *
     * @param solvedBoard the solved Sudoku
     * @param originalBoard the original board used to identify inserted numbers
     */
    private static void printSudoku(int[][] solvedBoard, int[][] originalBoard) {
        System.out.println("+-------+-------+-------+");

        for (int row = 0; row < solvedBoard.length; row++) {
            System.out.print("| ");

            for (int col = 0; col < solvedBoard[row].length; col++) {
                int number = solvedBoard[row][col];

                if (originalBoard[row][col] == 0) {
                    System.out.print(CYAN + number + RESET + " ");
                } else {
                    System.out.print(number + " ");
                }

                if ((col + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }

            System.out.println();

            if ((row + 1) % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
        }
    }

}
