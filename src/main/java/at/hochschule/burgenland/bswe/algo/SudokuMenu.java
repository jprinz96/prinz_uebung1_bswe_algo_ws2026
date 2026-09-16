package at.hochschule.burgenland.bswe.algo;

import at.hochschule.burgenland.bswe.algo.file.CsvFileReader;

import java.util.Arrays;
import java.util.Scanner;

public class SudokuMenu {
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
                    int[][] unsolvedBoard = csvFileReader.readUnsolvedSudoku();
                    int[][] solvedSudoku = Solver.solve(unsolvedBoard);
                    System.out.println("~~~~~~~ Solved Sudoku ~~~~~~~");
                    printSudoku(solvedSudoku);
                }
                case "2" -> {

                }
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printGreeting() {
        System.out.println("\"~~~~~~~~~~~ Welcome to the Sudoku solver ~~~~~~~~~~~\"");
    }

    private static void printMenu() {
        System.out.println("""
                1 - Load Sudoku from CSV file
                2 - Enter Sudoku manually
                0 - Exit""");
    }

    private static void printSudoku(int[][] solvedSudokuBoard) {
        for (int i = 0; i < solvedSudokuBoard.length; i++) {
            System.out.println(Arrays.toString(solvedSudokuBoard[i]));

        }
    }

}
