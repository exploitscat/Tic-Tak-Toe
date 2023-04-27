import java.util.Scanner;

public class TicTacToe {
    private static char[][] board = new char[3][3];
    private static final char EMPTY = '-';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        initializeBoard();
        boolean isPlayerXTurn = true;
        boolean gameIsOn = true;

        while (gameIsOn) {
            printBoard();
            char currentPlayer = isPlayerXTurn ? PLAYER_X : PLAYER_O;
            makeMove(currentPlayer);
            gameIsOn = !checkWin(currentPlayer) && !isBoardFull();
            isPlayerXTurn = !isPlayerXTurn;
        }
    }

    private static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static void makeMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        boolean isValidMove;

        do {
            System.out.println("Player " + player + ", enter your move (row[1-3] col[1-3]):");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            isValidMove = (row >= 0 && row < 3) && (col >= 0 && col < 3) && (board[row][col] == EMPTY);
            if (!isValidMove) {
                System.out.println("Invalid move. Try again.");
            }
        } while (!isValidMove);

        board[row][col] = player;
    }

    private static boolean checkWin(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                System.out.println("Player " + player + " wins!");
                return true;
            }
        }

        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            System.out.println("Player " + player + " wins!");
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
