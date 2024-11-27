import org.jetbrains.annotations.NotNull;
import java.io.*;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe");

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };
        int round = 0;
        boolean winner = false;

        while (!winner) {
            Print(gameBoard, round);

            boolean validMove = false;
            int line = 0, column = 0;
            while (!validMove) {
                int[] move = readFromConsole();
                line = move[0];
                column = move[1];
                if (gameBoard[line][column] == ' ') {
                    validMove = true;
                } else {
                    System.out.println("Cell already occupied. Try again.");
                }
            }

            Round(gameBoard, line, column, round);

            if (Check(gameBoard, round)) {
                winner = true;
                if (round == 0) {
                    System.out.println("Winner with X!");
                } else {
                    System.out.println("Winner with O!");
                }
            } else if (isDraw(gameBoard)) {
                System.out.println("It's a draw!");
                break;
            }

            round = (round + 1) % 2;
        }
    }

    public static int @NotNull [] readFromConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter the row number (0, 2, 4) and column number (0, 2, 4): ");
            int line = Integer.parseInt(br.readLine());
            int column = Integer.parseInt(br.readLine());
            if (line > 4 || column > 4 || line % 2 != 0 || column % 2 != 0) {
                System.out.println("Invalid input. Row and column must be 0, 2, or 4.");
                return readFromConsole();
            }
            return new int[]{line, column};
        } catch (IOException e) {
            throw new RuntimeException("Error reading from console.", e);
        }
    }

    public static void Round(char[] @NotNull [] gameBoard, int line, int column, int round) {
        gameBoard[line][column] = (round == 0) ? 'X' : 'O';
    }

    public static boolean Check(char[][] gameBoard, int round) {
        char player = (round == 0) ? 'X' : 'O';

        for (int i = 0; i <= 4; i += 2) {
            if (gameBoard[i][0] == player && gameBoard[i][2] == player && gameBoard[i][4] == player)
                return true;
        }
        for (int j = 0; j <= 4; j += 2) {
            if (gameBoard[0][j] == player && gameBoard[2][j] == player && gameBoard[4][j] == player)
                return true;
        }
        if (gameBoard[0][0] == player && gameBoard[2][2] == player && gameBoard[4][4] == player)
            return true;

        return gameBoard[0][4] == player && gameBoard[2][2] == player && gameBoard[4][0] == player;
    }

    public static boolean isDraw(char[][] gameBoard) {
        for (int i = 0; i <= 4; i += 2) {
            for (int j = 0; j <= 4; j += 2) {
                if (gameBoard[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void Print(char[] @NotNull [] gameBoard, int round) {
        System.out.println(round == 0 ? "X's turn:" : "O's turn:");
        for (char[] row : gameBoard) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
