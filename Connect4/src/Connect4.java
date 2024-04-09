import java.util.Scanner;

public class Connect4 {
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLUMNS = 7;
    private static final char EMPTY_SLOT = '-';
    private static final char PLAYER_ONE_SYMBOL = 'X';
    private static final char PLAYER_TWO_SYMBOL = 'O';

    private char[][] gameBoard;
    private int playerOneWins;
    private int playerTwoWins;
    private int draws;

    public Connect4() {
        gameBoard = new char[NUM_ROWS][NUM_COLUMNS];
        resetGame();
    }

    public void resetGame() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                gameBoard[row][col] = EMPTY_SLOT;
            }
        }
    }

    public void displayBoard() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                System.out.print(gameBoard[row][col] + " ");
            }
            System.out.println();
        }
    }

    public boolean dropDisc(int column, char playerSymbol) {
        if (column < 0 || column >= NUM_COLUMNS) {
            System.out.println("Invalid column. Please choose a column between 1 and " + NUM_COLUMNS);
            return false;
        }

        for (int row = NUM_ROWS - 1; row >= 0; row--) {
            if (gameBoard[row][column] == EMPTY_SLOT) {
                gameBoard[row][column] = playerSymbol;
                return true;
            }
        }

        System.out.println("Column is full. Please choose another column.");
        return false;
    }

    public boolean checkWin(char playerSymbol) {
        // Check horizontally
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col <= NUM_COLUMNS - 4; col++) {
                if (gameBoard[row][col] == playerSymbol &&
                        gameBoard[row][col + 1] == playerSymbol &&
                        gameBoard[row][col + 2] == playerSymbol &&
                        gameBoard[row][col + 3] == playerSymbol) {
                    return true;
                }
            }
        }

        // Check vertically
        for (int row = 0; row <= NUM_ROWS - 4; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (gameBoard[row][col] == playerSymbol &&
                        gameBoard[row + 1][col] == playerSymbol &&
                        gameBoard[row + 2][col] == playerSymbol &&
                        gameBoard[row + 3][col] == playerSymbol) {
                    return true;
                }
            }
        }

        // Check diagonally (positive slope)
        for (int row = 0; row <= NUM_ROWS - 4; row++) {
            for (int col = 0; col <= NUM_COLUMNS - 4; col++) {
                if (gameBoard[row][col] == playerSymbol &&
                        gameBoard[row + 1][col + 1] == playerSymbol &&
                        gameBoard[row + 2][col + 2] == playerSymbol &&
                        gameBoard[row + 3][col + 3] == playerSymbol) {
                    return true;
                }
            }
        }

        // Check diagonally (negative slope)
        for (int row = 0; row <= NUM_ROWS - 4; row++) {
            for (int col = 3; col < NUM_COLUMNS; col++) {
                if (gameBoard[row][col] == playerSymbol &&
                        gameBoard[row + 1][col - 1] == playerSymbol &&
                        gameBoard[row + 2][col - 2] == playerSymbol &&
                        gameBoard[row + 3][col - 3] == playerSymbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public void updateStatistics(char winner) {
        if (winner == PLAYER_ONE_SYMBOL) {
            playerOneWins++;
        } else if (winner == PLAYER_TWO_SYMBOL) {
            playerTwoWins++;
        } else {
            draws++;
        }
    }

    public void displayStatistics() {
        System.out.println("Overall Score:");
        System.out.println("Player One Wins: " + playerOneWins);
        System.out.println("Player Two Wins: " + playerTwoWins);
        System.out.println("Draws: " + draws);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connect4 game = new Connect4();
        boolean playAgain = true;

        while (playAgain) {
            boolean isPlayerOneTurn = true;
            boolean isGameOver = false;

            while (!isGameOver) {
                game.displayBoard();
                char currentPlayerSymbol = isPlayerOneTurn ? PLAYER_ONE_SYMBOL : PLAYER_TWO_SYMBOL;
                System.out.println("Player " + currentPlayerSymbol + "'s turn");

                System.out.print("Enter column (1-" + NUM_COLUMNS + "): ");
                int column = scanner.nextInt() - 1;

                if (game.dropDisc(column, currentPlayerSymbol)) {
                    if (game.checkWin(currentPlayerSymbol)) {
                        game.displayBoard();
                        System.out.println("Player " + currentPlayerSymbol + " wins!");
                        game.updateStatistics(currentPlayerSymbol);
                        isGameOver = true;
                    } else if (game.isBoardFull()) {
                        game.displayBoard();
                        System.out.println("It's a draw!");
                        game.updateStatistics(EMPTY_SLOT);
                        isGameOver = true;
                    } else {
                        isPlayerOneTurn = !isPlayerOneTurn;
                    }
                }
            }

            game.displayStatistics();

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();

            if (response.equals("no") || response.equals("n")) {
                playAgain = false;
            } else {
                game.resetGame();
            }
        }

        scanner.close();
    }

    private boolean isBoardFull() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (gameBoard[row][col] == EMPTY_SLOT) {
                    return false;
                }
            }
        }
        return true;
    }
}
