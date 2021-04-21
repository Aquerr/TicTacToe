package pl.bartlomiejstepien.tictactoeconsole;

import java.util.Scanner;

public class TicTacToeConsole
{

    private static final int[][] possibleWins = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9},
            {1, 5, 9},
            {3, 5, 7}
    };

    public static void main(String[] args) {
        var ticTacToe = new TicTacToeConsole();
        ticTacToe.start();
    }

    private void start()
    {
        Scanner scanner = new Scanner(System.in);
        char currentMove = 'X';
        char[] board = new char[9];

        while (true)
        {
            System.out.println(stringifyBoard(board));
            System.out.println(currentMove + " turn: ");
            System.out.println("Type position 1-9: ");

            int position = scanner.nextInt();
            if (position < 1 || position > 10 || board[position - 1] != Character.MIN_CODE_POINT)
            {
                System.out.println("Given position is invalid!");
                continue;
            }

            board[position - 1] = currentMove;

            if(hasWon(currentMove, board))
            {
                System.out.println(stringifyBoard(board));
                System.out.println("Congratulations! You won!");
                break;
            }

            if (currentMove == 'X')
                currentMove = 'O';
            else currentMove = 'X';
        }
    }

    private boolean hasWon(char currentSide, char[] board)
    {
        for (final int[] win : possibleWins)
        {
            int firstPos = win[0] - 1;
            int secondPos = win[1] - 1;
            int thirdPos = win[2] - 1;
            if (board[firstPos] == currentSide && board[secondPos] == currentSide && board[thirdPos] == currentSide)
                return true;
        }

        return false;
    }

    private String stringifyBoard(char[] board)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < board.length; i++)
        {
            stringBuilder.append("|").append(board[i]);

            if (i == 2 || i == 5 || i == 8)
                stringBuilder.append("|\n");
        }

        return stringBuilder.toString();
    }
}
