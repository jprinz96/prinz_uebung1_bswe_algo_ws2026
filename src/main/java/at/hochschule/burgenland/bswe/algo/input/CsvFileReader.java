package at.hochschule.burgenland.bswe.algo.input;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class CsvFileReader {
    /**
     * Reads an unsolved Sudoku from the input.csv file.
     *
     * @return a 9x9 Sudoku array where 0 represents an empty cell
     */
    public int[][] readUnsolvedSudoku() {
        Path path = Paths.get("src", "main", "resources", "input.csv");
        int[][] sudokuBoard = new int[9][9];
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != 9) {
                    throw new IllegalArgumentException("Row: " + (row + 1) + " must be of length 9");
                }
                for (int col = 0; col < values.length; col++) {
                    int value;
                    try {
                        value = Integer.parseInt(values[col].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid value at row " + (row + 1)
                                + ", column " + (col + 1)
                                + ": must be a number",
                                e);
                    }

                    if (value < 0 || value > 9) {
                        log.error("Invalid number {} at row {}, column {}", value, row + 1, col + 1);
                        throw new IllegalArgumentException("Value: " + value + " in row: "
                                + row + ", column: " + col + " invalid. Must be between 0 and 9");
                    }

                    sudokuBoard[row][col] = value;
                }
                row++;

            }
            if (row < sudokuBoard.length) {
                throw new IllegalArgumentException("Sudoku must contain exactly 9 rows");
            }
        } catch (IOException e) {
            log.error("Error while reading Sudoku from {}", path, e);
            throw new RuntimeException("Could not load Sudoku file", e);
        }
        log.info("Sudoku successfully read");
        return sudokuBoard;
    }

    //Method for testing
    int[][] readUnsolvedSudoku(String filename){
        Path path = Paths.get("src", "test", "resources", filename);
        int[][] sudokuBoard = new int[9][9];

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length != 9) {
                    throw new IllegalArgumentException("Row: " + (row + 1) + " must be of length 9");
                }
                for (int col = 0; col < values.length; col++) {
                    int value;
                    try {
                        value = Integer.parseInt(values[col].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid value at row " + (row + 1)
                                + ", column " + (col + 1)
                                + ": must be a number",
                                e);
                    }

                    if (value < 0 || value > 9) {
                        log.error("Invalid number {} at row {}, column {}", value, row + 1, col + 1);
                        throw new IllegalArgumentException("Value: " + value + " in row: "
                                + row + ", column: " + col + " invalid. Must be between 0 and 9");
                    }

                    sudokuBoard[row][col] = value;
                }
                row++;

            }
            if (row < sudokuBoard.length) {
                throw new IllegalArgumentException("Sudoku must contain exactly 9 rows");
            }
        } catch (IOException e) {
            log.error("Error while reading Sudoku from {}", path, e);
            throw new RuntimeException("Could not load Sudoku file", e);
        }
        log.info("Sudoku successfully read");
        return sudokuBoard;
    }
}
