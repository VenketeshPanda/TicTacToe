package TicTacToe.models;

public class Cell {
    private int row;
    private int column;
    private Symbol symbol;
    private CellState cellState;

    public Cell(int row,int column){
        this.row=row;
        this.column=column;
        this.symbol=null;
        this.cellState=CellState.EMPTY;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public void display(){
        if(symbol!=null){
            System.out.print("|- "+symbol.getSym()+" -|");
        } else{
            System.out.print("| - |");
        }
    }
}
