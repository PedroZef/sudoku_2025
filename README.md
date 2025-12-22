# Sudoku

Um simples jogo de Sudoku desenvolvido em Java com interface gráfica utlizando JavaFX.

## Tecnologias Utilizadas

* **Java 21**: Plataforma de desenvolvimento.
* **JavaFX 21**: Framework para a interface gráfica.
* **Maven**: Ferramenta para gerenciamento e build do projeto.

## Como Executar

Para compilar e executar o projeto, siga os passos abaixo:

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/PedroZef/sudoku_2025.git
    cd seu-repositorio-sudoku
    ```

2. **Compile o projeto:**

    Utilize o Maven para compilar o projeto. Isso irá baixar todas as dependências necessárias.

    ```bash
    mvnw.cmd clean install
    ```

3. **Execute o jogo:**

    Após a compilação, execute o aplicativo com o seguinte comando:

    ```bash
    mvnw.cmd javafx:run
    ```

    Isso iniciará a interface gráfica do jogo de Sudoku.

## Como Jogar

* A interface do jogo exibe um tabuleiro de Sudoku.
* Células com números pré-preenchidos são fixas e não podem ser alteradas.
* Clique em uma célula vazia e digite um número de 1 a 9 para fazer sua jogada.
* Se a jogada for inválida (número repetido na linha, coluna ou bloco 3x3), um aviso será exibido e a jogada será desfeita.
* O jogo termina quando você preencher todas as células corretamente. Uma mensagem de parabéns será exibida.
* Para limpar uma célula, basta apagar o número digitado.
