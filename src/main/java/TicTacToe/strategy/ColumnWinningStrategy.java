package TicTacToe.strategy;

import TicTacToe.models.Board;
import TicTacToe.models.Move;

import java.util.HashMap;

public class ColumnWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character,Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        //Do the algo in O(1) //Therefore HashMap
        //C0 -> {'X' : count}
        //C0 -> {'O' : count}
        //C1 -> {'X' : count}

        int column = move.getCell().getColumn();
        Character sym = move.getCell().getSymbol().getSym();

        //Check if there is a hashmap for the corresponding column
        if(!counts.containsKey(column)){
            counts.put(column,new HashMap<>());
        }
        HashMap<Character,Integer> countColumn = counts.get(column);

        //Check if a particular sym is present in the hashmap to update its frequency
        if(!countColumn.containsKey(sym)){
            countColumn.put(sym,0);
        }

        //Updating the frequency of the symbol in the corresponding column
        countColumn.put(sym, countColumn.get(sym)+1);

        if(countColumn.get(sym) == board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int column = move.getCell().getColumn();
        Character sym = move.getPlayer().getSymbol().getSym();

        counts.get(column).put(sym,counts.get(column).get(sym-1));
    }
}
