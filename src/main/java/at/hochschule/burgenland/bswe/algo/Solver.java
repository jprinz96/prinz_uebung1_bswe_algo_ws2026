package at.hochschule.burgenland.bswe.algo;

/**
 * Solves 9x9 Sudoku boards using a recursive backtracking algorithm.
 */
public class Solver {

    /**
     * Attempts to solve the given Sudoku board.
     * The provided board is modified during the solving process.
     *
     * @param sudokuBoard the 9x9 Sudoku board where 0 represents an empty field
     * @return the solved board, or {@code null} if no solution exists
     */
    public static int[][] solve(int[][] sudokuBoard) {
        if (!checkCompletedWrongSudoku(sudokuBoard)) {
            return null;
        } else if (isSudokuSolvable(sudokuBoard)) {
            return sudokuBoard;
        }
        return null;
    }

    /**
     * Recursively fills empty fields using backtracking.
     *
     * @param sudokuBoard the Sudoku board being solved
     * @return {@code true} if a valid solution was found
     */
    private static boolean isSudokuSolvable(int[][] sudokuBoard) {
        for (int row = 0; row < sudokuBoard.length; row++) {
            for (int col = 0; col < sudokuBoard.length; col++) {
                if (sudokuBoard[row][col] == 0) {
                    for (int tryNumber = 1; tryNumber <= sudokuBoard.length; tryNumber++) {
                        if (isValid(sudokuBoard, row, col, tryNumber)) {
                            sudokuBoard[row][col] = tryNumber;
                            if (isSudokuSolvable(sudokuBoard)) {
                                return true;
                            } else {
                                sudokuBoard[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] sudokuBoard, int row, int col, int number) {
        return !isNumberInRow(sudokuBoard, row, number)
                && !isNumberInColumn(sudokuBoard, col, number)
                && !isNumberInBox(sudokuBoard, row, col, number);
    }

    private static boolean isNumberInRow(int[][] sudokuBoard, int row, int numberToCheck) {
        for (int i = 0; i <= sudokuBoard.length - 1; i++) {
            if (sudokuBoard[row][i] == numberToCheck) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] sudokuBoard, int column, int numberToCheck) {
        for (int i = 0; i <= sudokuBoard.length - 1; i++) {
            if (sudokuBoard[i][column] == numberToCheck) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] sudokuBoard, int row, int col, int numberToCheck) {
        int startRow = row - (row % 3);
        int startCol = col - (col % 3);

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudokuBoard[i][j] == numberToCheck) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCompletedWrongSudoku(int[][] sudokuBoard) {
        for (int row = 0; row < sudokuBoard.length; row++) {
            for (int col = 0; col < sudokuBoard.length; col++) {
                int checkNumber = sudokuBoard[row][col];

                if (checkNumber == 0) {
                    continue;
                }
                if (checkNumber < 1 || checkNumber > 9) {
                    return false;
                }
                sudokuBoard[row][col] = 0; //so that it doesn’t find itself
                boolean valid = isValid(sudokuBoard, row, col, checkNumber);
                sudokuBoard[row][col] = checkNumber; //reallocate after the examination

                if (!valid) {
                    return false;
                }


            }
        }
        return true;
    }

}
