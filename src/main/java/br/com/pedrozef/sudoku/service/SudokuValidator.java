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
        // todas células preenchidas e válidas
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                int v = board.getValue(r, c);
                if (v == 0 || !isValidSpot(board, r, c))
                    return false;
            }
        }
        return true;
    }

    // Verifica consistência local sem remover o próprio valor
    private boolean isValidSpot(SudokuBoard board, int row, int col) {
        int num = board.getValue(row, col);
        if (num == 0)
            return false;

        // checa duplicidade na linha (ignorando a própria célula)
        for (int c = 0; c < SudokuBoard.SIZE; c++)
            if (c != col && board.getValue(row, c) == num)
                return false;

        // checa duplicidade na coluna
        for (int r = 0; r < SudokuBoard.SIZE; r++)
            if (r != row && board.getValue(r, col) == num)
                return false;

        // checa duplicidade no bloco
        int sr = row - row % 3, sc = col - col % 3;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) {
                int rr = sr + r, cc = sc + c;
                if ((rr != row || cc != col) && board.getValue(rr, cc) == num)
                    return false;
            }

        return true;
    }
}
