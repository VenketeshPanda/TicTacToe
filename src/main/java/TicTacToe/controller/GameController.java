package TicTacToe.controller;

import TicTacToe.models.Cell;
import TicTacToe.models.Game;
import TicTacToe.models.GameState;
import TicTacToe.models.Player;
import TicTacToe.strategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int size, List<Player> players, List<WinningStrategy> winningStrategies){
        //Validate:
        //1. Check the players count == size-1
        //2. You can only have one Bot in the game
        //3. Every player should have a separate symbol

        return  Game.getBuilder()
                .setSize(size)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void display(Game game){
        game.displayBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();
    }
}

/*
1. start the game : creation, set the attributes, taking the input
2. display the empty board
3. make the move: input, make the move, check for the winner, update the state
4. check the game stage, if SUCCESS-> get the winner and display
                         if DRAW-> declare the draw
*/
