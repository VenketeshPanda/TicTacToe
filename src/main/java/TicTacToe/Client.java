package TicTacToe;

import TicTacToe.controller.GameController;
import TicTacToe.models.*;
import TicTacToe.strategy.BotPlayingStrategy;
import TicTacToe.strategy.BotPlayingStrategyFactory;
import TicTacToe.strategy.ColumnWinningStrategy;
import TicTacToe.strategy.RowWinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        GameController gameController = new GameController();
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(1,"Venky", PlayerType.HUMAN,new Symbol('X')));
        players.add(new BotPlayer(2,"Bottiiee", PlayerType.BOT,new Symbol('O'),BotDifficultyLevel.EASY));

        Game game = gameController.startGame(
                3,
                players,
                List.of(new RowWinningStrategy(), new ColumnWinningStrategy())
        );

        gameController.display(game);

        while(gameController.checkState(game).equals(GameState.IN_PROGRESS)){
            gameController.makeMove(game);
            gameController.display(game);

            System.out.println("Do you want to undo? [Y/N]");
            String inputUndo = sc.nextLine();
            if(inputUndo.equals("Y")){
                gameController.undo(game);
                System.out.println("Undo successful!");
                gameController.display(game);
            }
        }
        if(gameController.checkState(game).equals(GameState.SUCCESS)){
            System.out.println(gameController.getWinner(game).getName()+" won the game! Hurrah!");
        } else if(gameController.checkState(game).equals(GameState.DRAW)){
            System.out.println("Game ended in a draw ~*-*~");
        }
    }
}

//Create the models
//Get ready with the controller and basic interaction with the clients
//Make sure every model has a constructor
//Implement the functionalities one by one