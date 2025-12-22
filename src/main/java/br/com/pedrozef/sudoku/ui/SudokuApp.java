package br.com.pedrozef.sudoku.ui;

import br.com.pedrozef.sudoku.controller.SudokuGame;
import br.com.pedrozef.sudoku.model.Difficulty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SudokuApp extends Application {
    private final SudokuGame game = new SudokuGame();

    @Override
    public void start(Stage stage) {
        game.start(Difficulty.EASY);

        SudokuGrid grid = new SudokuGrid(game);
        BorderPane root = new BorderPane(grid);

        stage.setTitle("Jogo do Sudoku - POO JavaFX");
        stage.setScene(new Scene(root, 520, 560));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}