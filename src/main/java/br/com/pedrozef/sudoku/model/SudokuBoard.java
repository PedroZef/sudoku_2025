package br.com.pedrozef.sudoku.model;

public class SudokuBoard {
    public static final int SIZE = 9;
    private final SudokuCell[][] cells = new SudokuCell[SIZE][SIZE];

    public SudokuBoard() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                cells[r][c] = new SudokuCell();
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getValue(int row, int col) {
        return cells[row][col].getValue();
    }

    public void setValue(int row, int col, int value) {
        if (cells[row][col].isFixed())
            throw new IllegalStateException("Célula fixa");
        cells[row][col].setValue(value);
    }

    public void setFixedValue(int row, int col, int value) {
        cells[row][col].setValue(value);
        cells[row][col].setFixed(true);
    }

    public boolean isFull() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (cells[r][c].getValue() == 0)
                    return false;
        return true;
    }
}