package TicTacToe.models;

import TicTacToe.strategy.BotPlayingStrategy;
import TicTacToe.strategy.BotPlayingStrategyFactory;

public class BotPlayer extends Player{

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(int id, String name, PlayerType playerType, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(id, name, playerType, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
    }

    public BotPlayer(int id, String name, PlayerType playerType, Symbol symbol) {
        super(id, name, playerType, symbol);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public Move makeMove(Board board){
        return botPlayingStrategy.makeMove(board);
    }

}
