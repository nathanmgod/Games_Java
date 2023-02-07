import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = {{' ',' ',' '}
                         ,{' ',' ',' '}
                         ,{' ',' ',' '}};
        Scanner scanner = new Scanner(System.in);

        printBoard(board);

        while (true) {
            playerMove(board, scanner);
            if (isGameFinished(board)){
                break;
            }
            printBoard(board);

            computerMove(board);
            if (isGameFinished(board)){
                break;
            }
            printBoard(board);
        }
        scanner.close();
    }

    private static boolean hasContestantWon(char[][] board, char symbol) {
        return (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
               (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
               (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||

               (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
               (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
               (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||

               (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }
    private static boolean isGameFinished(char[][] board) {

        if (hasContestantWon(board, 'X')) {
            System.out.println("Player wins!");
            printBoard(board);
            return true;
        }
        if (hasContestantWon(board, 'O')) {
            System.out.println("Computer wins!");
            printBoard(board);
            return true;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        System.out.println("The game ended in a tie!");
        printBoard(board);
        
        return true;
    }

    private static void printBoard(char[][] board){
        System.out.println(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("--+---+--");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("--+---+--");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
    }

    private static void computerMove(char[][] board) {
        int computerMove;
        Random rand = new Random();

        do {
            computerMove = rand.nextInt(9) + 1;

        } while (isValidMove(board, computerMove));

        System.out.println("Computer chose: " + computerMove);
        placeMove(board,computerMove,'O');
    }

    private static void playerMove(char[][] board,Scanner scanner){
        int userInput = 0;

        do {
            boolean validUserInput;
            do {
                validUserInput = true;

                System.out.println("Where would you like to Play? (1-9)");
                String input = scanner.nextLine();
                try {
                    userInput = Integer.parseInt(input);
                }
                catch (NumberFormatException nfe){
                    System.out.println("Please enter a valid integer");
                    validUserInput = false;
                }
            }while (!validUserInput);

            if(isValidMove(board, userInput)){
                System.out.println(userInput + " is not a valid move.");
            }
        } while (isValidMove(board, userInput));

        placeMove(board,userInput,'X');
    }

    private static boolean isValidMove(char[][] board, int position){
        switch (position) {
            case 1 -> {
                return board[0][0] != ' ';
            }
            case 2 -> {
                return board[0][1] != ' ';
            }
            case 3 -> {
                return board[0][2] != ' ';
            }
            case 4 -> {
                return board[1][0] != ' ';
            }
            case 5 -> {
                return board[1][1] != ' ';
            }
            case 6 -> {
                return board[1][2] != ' ';
            }
            case 7 -> {
                return board[2][0] != ' ';
            }
            case 8 -> {
                return board[2][1] != ' ';
            }
            case 9 -> {
                return board[2][2] != ' ';
            }
            default -> {
                return true;
            }
        }
    }

    private static void placeMove(char[][] board, int position, char symbol){
        switch (position) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][1] = symbol;
            case 3 -> board[0][2] = symbol;
            case 4 -> board[1][0] = symbol;
            case 5 -> board[1][1] = symbol;
            case 6 -> board[1][2] = symbol;
            case 7 -> board[2][0] = symbol;
            case 8 -> board[2][1] = symbol;
            case 9 -> board[2][2] = symbol;
        }
    }
}