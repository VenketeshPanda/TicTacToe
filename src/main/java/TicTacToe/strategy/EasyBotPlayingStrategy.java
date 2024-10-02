package TicTacToe.strategy;

import TicTacToe.models.Board;
import TicTacToe.models.Cell;
import TicTacToe.models.CellState;
import TicTacToe.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        //Move in the first cell which is empty
        for(List<Cell> row: board.getCells()){
            for(Cell cell: row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(new Cell(cell.getRow(), cell.getColumn()),null);
                }
            }
        }
        return null;
    }
}
