package br.com.pedrozef.sudoku.ui;

import br.com.pedrozef.sudoku.controller.SudokuGame;
import br.com.pedrozef.sudoku.model.Difficulty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SudokuApp extends Application {
    private final SudokuGame game = new SudokuGame();
    private BorderPane root;
    private ChoiceBox<Difficulty> difficultyChoice;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        setupToolbar();

        // Inicia o primeiro jogo
        startNewGame(Difficulty.EASY);

        stage.setTitle("Jogo do Sudoku - POO JavaFX");
        stage.setScene(new Scene(root, 520, 560));
        stage.show();
    }

    private void setupToolbar() {
        difficultyChoice = new ChoiceBox<>();
        difficultyChoice.getItems().addAll(Difficulty.values());
        difficultyChoice.setValue(Difficulty.EASY);

        Button newGameButton = new Button("Novo Jogo");
        newGameButton.setOnAction(e -> startNewGame(difficultyChoice.getValue()));

        Button solveButton = new Button("Resolver");
        solveButton.setOnAction(e -> solveGame());

        ToolBar toolBar = new ToolBar(
            new Label("Dificuldade:"),
            difficultyChoice,
            newGameButton,
            solveButton
        );
        root.setTop(toolBar);
    }

    private void startNewGame(Difficulty difficulty) {
        game.start(difficulty);
        SudokuGrid grid = new SudokuGrid(game);
        root.setCenter(grid);
    }

    private void solveGame() {
        game.solve();
        // Redesenha a grade com a solução
        SudokuGrid grid = new SudokuGrid(game);
        root.setCenter(grid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
