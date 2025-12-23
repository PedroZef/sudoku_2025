package br.com.pedrozef.sudoku.service;


import br.com.pedrozef.sudoku.model.SudokuBoard;

public class SudokuValidator {

    public boolean isValidMove(SudokuBoard board, int row, int col, int num) {
        if (num == 0) return true;
        if (num < 1 || num > 9)
            return false;
        // linha
        for (int c = 0; c < SudokuBoard.SIZE; c++)
            if (board.getValue(row, c) == num)
                return false;
        // coluna
        for (int r = 0; r < SudokuBoard.SIZE; r++)
            if (board.getValue(r, col) == num)
                return false;
        // bloco 3x3
        int sr = row - row % 3, sc = col - col % 3;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board.getValue(sr + r, sc + c) == num)
                    return false;
        return true;
    }

    public boolean isSolved(SudokuBoard board) {
        // Verifica se todas as linhas, colunas e blocos 3x3 estão corretos
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            if (!isRowValid(board, i) || !isColumnValid(board, i)) {
                return false;
            }
        }
        for (int r = 0; r < SudokuBoard.SIZE; r += 3) {
            for (int c = 0; c < SudokuBoard.SIZE; c += 3) {
                if (!isBlockValid(board, r, c)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSetValid(int[] set) {
        java.util.Set<Integer> seen = new java.util.HashSet<>();
        for (int num : set) {
            if (num == 0 || !seen.add(num)) {
                return false;
            }
        }
        return seen.size() == 9;
    }

    private boolean isRowValid(SudokuBoard board, int row) {
        int[] set = new int[SudokuBoard.SIZE];
        for (int c = 0; c < SudokuBoard.SIZE; c++) {
            set[c] = board.getValue(row, c);
        }
        return isSetValid(set);
    }

    private boolean isColumnValid(SudokuBoard board, int col) {
        int[] set = new int[SudokuBoard.SIZE];
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            set[r] = board.getValue(r, col);
        }
        return isSetValid(set);
    }

    private boolean isBlockValid(SudokuBoard board, int startRow, int startCol) {
        int[] set = new int[SudokuBoard.SIZE];
        int i = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                set[i++] = board.getValue(startRow + r, startCol + c);
            }
        }
        return isSetValid(set);
    }
}
