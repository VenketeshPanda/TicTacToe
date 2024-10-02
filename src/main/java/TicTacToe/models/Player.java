package TicTacToe.models;

import java.util.Scanner;

public abstract class Player {
    private int id;
    private String name;
    private PlayerType playerType;
    private Symbol symbol;
    private Scanner sc = new Scanner(System.in);

    public Player(int id, String name, PlayerType playerType, Symbol symbol) {
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Move makeMove(Board board){
        System.out.println("Enter the row where you want to make the move");
        int row = sc.nextInt();
        System.out.println("Enter the column where you want to make the move");
        int col = sc.nextInt();
        //Should we validate here? we will do it in game class
        return new Move(new Cell(row,col),this);
    }
}
