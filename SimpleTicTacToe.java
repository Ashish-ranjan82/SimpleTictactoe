import java.util.Scanner;

public class SimpleTicTacToe {
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};  // A 1D array for the board
    static char currentPlayer = 'X';  // Start with player X

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (!gameEnded) {
            printBoard();
            playerMove(scanner);
            gameEnded = checkForWinner();

            // Switch player if the game is not over
            if (!gameEnded) {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        printBoard();
        System.out.println("Game Over! Player " + currentPlayer + " wins!");
        scanner.close();
    }

    // Print the current board
    private static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
            System.out.println("---------");
        }
    }

    // Handle player's move
    private static void playerMove(Scanner scanner) {
        int move = -1;
        boolean validMove = false;

        while (!validMove) {
            System.out.println("Player " + currentPlayer + ", enter your move (1-9): ");
            move = scanner.nextInt() - 1;

            if (move >= 0 && move < 9 && board[move] == ' ') {
                board[move] = currentPlayer;
                validMove = true;
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }

    // Check if there's a winner or if the game has ended in a draw
    private static boolean checkForWinner() {
        // Winning combinations
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Columns
                {0, 4, 8}, {2, 4, 6}              // Diagonals
        };

        // Check for a winner
        for (int[] combo : winCombinations) {
            if (board[combo[0]] == currentPlayer &&
                board[combo[1]] == currentPlayer &&
                board[combo[2]] == currentPlayer) {
                return true;
            }
        }

        // Check for a draw
        for (char c : board) {
            if (c == ' ') {
                return false;  // Continue playing if there's an empty space
            }
        }

        System.out.println("It's a draw!");
        return true;  // It's a draw if the board is full and no winner
    }
}

