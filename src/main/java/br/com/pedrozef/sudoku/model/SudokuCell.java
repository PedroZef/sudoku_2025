package br.com.pedrozef.sudoku.model;

public class SudokuCell {
    private int value; // 0 = vazio
    private boolean fixed; // true se é dica inicial

    public SudokuCell() {
        this(0, false);
    }

    public SudokuCell(int value, boolean fixed) {
        this.value = value;
        this.fixed = fixed;
    }

    public int getValue() {
        return value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setValue(int value) {
        if (value < 0 || value > 9)
            throw new IllegalArgumentException("Valor deve ser 0..9");
        this.value = value;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
}