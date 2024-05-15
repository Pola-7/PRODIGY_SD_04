import java.util.Scanner;

public class SudokuSolver {
    private static final int SIZE = 9;
    private int[][] board;

    public SudokuSolver(int[][] board) {
        this.board = board;
    }

    public void solve() {
        if (solveHelper(0, 0)) {
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }

    private boolean solveHelper(int row, int col) {
        if (row == SIZE) {
            row = 0;
            if (++col == SIZE) {
                return true;
            }
        }
        if (board[row][col] != 0) {
            return solveHelper(row + 1, col);
        }
        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(row, col, num)) {
                board[row][col] = num;
                if (solveHelper(row + 1, col)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isValidMove(int row, int col, int num) {
        return !isInRow(row, num) && !isInColumn(col, num) && !isInBox(row - row % 3, col - col % 3, num);
    }

    private boolean isInRow(int row, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private boolean isInBox(int startRow, int startCol, int num) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row + startRow][col + startCol] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printBoard() {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] unsolvedBoard = getUserInput();
        SudokuSolver solver = new SudokuSolver(unsolvedBoard);
        System.out.println("Solving Sudoku puzzle...");
        solver.solve();
    }

    private static int[][] getUserInput() {
        Scanner scanner = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];
        System.out.println("Enter the Sudoku puzzle (use 0 to represent empty cells):");
        for (int i = 0; i < SIZE; i++) {
            System.out.printf("Enter numbers for row %d (separated by spaces): ", i + 1);
            String[] input = scanner.nextLine().split(" ");
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Integer.parseInt(input[j]);
            }
        }
        return board;
    }
}

