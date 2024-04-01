import java.util.Scanner;

public class Main {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static int playerXWins = 0;
    private static int playerOWins = 0;
    private static int draws = 0;

    public static void main(String[] args) {
        while (true) {
            initializeBoard();
            printBoard();
            playGame();
            printScoreboard();
            if (!playAgain()) {
                break;
            }
        }
        System.out.println("Thanks for playing!");
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void playGame() {
        boolean gameEnded = false;
        while (!gameEnded) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            if (currentPlayer == 'X') {
                int[] move = getPlayerMove();
                int row = move[0];
                int col = move[1];
                board[row][col] = currentPlayer;
            } else {
                makeComputerMove();
            }
            printBoard();
            if (checkWin()) {
                System.out.println("Player " + currentPlayer + " wins!");
                updateScoreboard(currentPlayer);
                gameEnded = true;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                draws++;
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        System.out.print("Enter row (0, 1, or 2): ");
        move[0] = scanner.nextInt();
        System.out.print("Enter column (0, 1, or 2): ");
        move[1] = scanner.nextInt();
        return move;
    }

    private static void makeComputerMove() {
        // Implement AI for computer move (for simplicity, we make a random move)
        int row, col;
        do {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (board[row][col] != '-');
        board[row][col] = currentPlayer;
    }

    private static boolean checkWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) || checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private static boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printScoreboard() {
        System.out.println("Scoreboard:");
        System.out.println("Player X Wins: " + playerXWins);
        System.out.println("Player O Wins: " + playerOWins);
        System.out.println("Draws: " + draws);
    }

    private static void updateScoreboard(char winner) {
        if (winner == 'X') {
            playerXWins++;
        } else if (winner == 'O') {
            playerOWins++;
        }
    }

    private static boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to play again? (yes/no): ");
        String choice = scanner.next().toLowerCase();
        return choice.equals("yes");
    }
}
