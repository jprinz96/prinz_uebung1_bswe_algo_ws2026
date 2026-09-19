package at.hochschule.burgenland.bswe.algo.input;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CsvFileReaderTest {

    @Test
    void testShouldReadSudokuFromCsvFile() {
        CsvFileReader csvFileReader = new CsvFileReader();
        int[][] sudoku = csvFileReader.readUnsolvedSudoku("solvablesudoku.csv");

        assertEquals(9, sudoku.length);
        for (int[] row : sudoku) {
            assertEquals(9, row.length);
        }

    }

    @Test
    void testCsvReaderShouldReadNumbersCorrectly() {
        CsvFileReader csvFileReader = new CsvFileReader();
        int[][] sudoku = csvFileReader.readUnsolvedSudoku();
        assertEquals(8, sudoku[0][0]);
        assertEquals(7, sudoku[2][1]);
    }

    @Test
    void testCsvReaderShouldRejectNonNumericValues() {
        CsvFileReader csvFileReader = new CsvFileReader();

        assertThrows(IllegalArgumentException.class, () ->
                csvFileReader.readUnsolvedSudoku("sudokuWithNonNumeric.csv"));
    }

    @Test
    void testCsvReaderShouldOnlyAllow0to9Values() {
        CsvFileReader csvFileReader = new CsvFileReader();
        assertThrows(IllegalArgumentException.class, () ->
                csvFileReader.readUnsolvedSudoku("sudokuWithInvalidNumbers.csv"));

    }

    @Test
    void testCsvReaderShouldRejectSudokuWithWrongSize() {
        CsvFileReader csvFileReader = new CsvFileReader();
        assertThrows(IllegalArgumentException.class, () ->
                csvFileReader.readUnsolvedSudoku("sudokuWithWrongLenght.csv"));
    }

}
