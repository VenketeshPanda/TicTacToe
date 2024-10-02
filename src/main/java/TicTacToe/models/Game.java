package TicTacToe.models;

import TicTacToe.strategy.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> playerList;
    private Player winner;
    private int nextPlayer;
    private List<Move> moves;
    private GameState gameState;
    private List<WinningStrategy> winningStrategies;
    Scanner sc = new Scanner(System.in);

    private Game(Builder builder){
        board = new Board(builder.size);
        playerList=builder.players;
        winner=null;
        winningStrategies=builder.winningStrategies;
        moves=new ArrayList<>();
        nextPlayer=0;
        gameState=GameState.IN_PROGRESS;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(int nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public boolean checkWinner(Move move){
        //We might have multiple ways of winning, we need to check each way
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public boolean validateMove(Move m){
        int row=m.getCell().getRow();
        int col=m.getCell().getColumn();

        //Make sure the move is a valid one(Inside the boundary)
        if(row<0 || row>board.getSize()-1 || col<0 || col>board.getSize()-1){
            return false;
        }

        return board.getCells()
                .get(row)
                .get(col)
                .getCellState()
                .equals(CellState.EMPTY);
    }

    public void makeMove(){
        //Current player
        Player currentPlayer = playerList.get(nextPlayer);
        System.out.println("Its "+currentPlayer.getName()+"'s turn, please make the move!");
        Move m = currentPlayer.makeMove(board);
        if(!validateMove(m)){
            System.out.println("Invalid move! Please try again later! :)");
        }
        int row=m.getCell().getRow();
        int col=m.getCell().getColumn();
        Cell cellToChange = board.getCells().get(row).get(col);
        cellToChange.setCellState(CellState.FILLED);
        cellToChange.setSymbol(currentPlayer.getSymbol());

        m.setCell(cellToChange);
        m.setPlayer(currentPlayer);
        moves.add(m);

        nextPlayer++;
        nextPlayer %= playerList.size();

        //Confirm if there is a winner!!
        if(checkWinner(m)){
            setWinner(currentPlayer);
            setGameState(GameState.SUCCESS);
        } else if(moves.size() == board.getSize() * board.getSize()) {
            setWinner(null);
            setGameState(GameState.DRAW);
        }
    }

    public void displayBoard(){
        board.display();
    }

    public void undo() {
        //Whatever we did to make the change, we need to reverse it
        if(moves.isEmpty()){
            System.out.println("Nothing to undo bro");
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(moves.get(moves.size()-1));
//        lastMove.setPlayer(null);
        lastMove.getCell().setCellState(CellState.EMPTY);
        lastMove.getCell().setSymbol(null);
//        lastMove.setCell(null);

        nextPlayer--;
        //(a-b)%n = (a-b+n)%n

        nextPlayer = (nextPlayer+playerList.size())%playerList.size();

        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(board,lastMove);
        }

        setGameState(GameState.IN_PROGRESS);
        setWinner(null);
    }

    public static class Builder{
        private int size;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder(){

        }

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public void playerValidate(){
            if(players.size()!=size-1){
                throw new RuntimeException("No of Players and board size doesn't match");
            }
        }

        public void validateSymbol(){
            Set<Character> symbolSet = new HashSet<>();
            for(Player player: players){
                if(symbolSet.contains(player.getSymbol().getSym())){
                    throw new RuntimeException("Multiple players have same symbol");
                }
                symbolSet.add(player.getSymbol().getSym());
            }
        }

        public void validate(){
            playerValidate();
            validateBot();
            validateSymbol();
        }

        public void validateBot(){
            int botCount=0;

            for(Player player: players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
                if(botCount>1) throw new RuntimeException("More than 1 bot is there");
            }
        }

        public Game build(){
            //validations while taking input from user
            validate();
            return new Game(this);
        }
    }
}
