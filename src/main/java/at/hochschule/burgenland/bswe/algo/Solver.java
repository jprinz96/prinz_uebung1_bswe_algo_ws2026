package at.hochschule.burgenland.bswe.algo;

public class Solver {

    public static int[][] solve(int[][] sudokuBoard) {
        if (isSudokuSolvable(sudokuBoard)) {
            return sudokuBoard;
        }
        return null;
    }

    private static boolean isValid(int[][] sudokuBoard, int row, int col, int number) {
        if (!isNumberInRow(sudokuBoard, row, number)
                && !isNumberInColumn(sudokuBoard, col, number)
                && !isNumberInBox(sudokuBoard, row, col, number)) {
            return true;
        }

        return false;
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
}
