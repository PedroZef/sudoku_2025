package br.com.pedrozef.sudoku.controller;


import br.com.pedrozef.sudoku.exception.InvalidMoveException;
import br.com.pedrozef.sudoku.model.Difficulty;
import br.com.pedrozef.sudoku.model.SudokuBoard;
import br.com.pedrozef.sudoku.service.SudokuGenerator;
import br.com.pedrozef.sudoku.service.SudokuValidator;

public class SudokuGame {
    private SudokuBoard board;
    private final SudokuValidator validator = new SudokuValidator();
    private final SudokuGenerator generator = new SudokuGenerator();

    public void start(Difficulty difficulty) {
        this.board = generator.generate(difficulty);
    }

    public SudokuBoard getBoard() {
        return board;
    }

    public void play(int row, int col, int value) {
        if (board.getCell(row, col).isFixed())
            throw new InvalidMoveException("Não é permitido alterar uma dica fixa.");
        if (!validator.isValidMove(board, row, col, value))
            throw new InvalidMoveException("Jogada inválida para a posição.");
        board.setValue(row, col, value);
    }

    public boolean isCompleted() {
        return board != null && board.isFull() && validator.isSolved(board);
    }
}