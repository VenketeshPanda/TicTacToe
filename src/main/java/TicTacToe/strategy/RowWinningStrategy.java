package TicTacToe.strategy;

import TicTacToe.models.Board;
import TicTacToe.models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{

    HashMap<Integer,HashMap<Character,Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        //Do the algo in O(1) //Therefore HashMap
        //R0 -> {'X' : count}
        //R0 -> {'O' : count}
        //R1 -> {'X' : count}

        int row = move.getCell().getRow();
        Character sym = move.getCell().getSymbol().getSym();

        //Check if there is a hashmap for the corresponding row
        if(!counts.containsKey(row)){
            counts.put(row,new HashMap<>());
        }
        HashMap<Character,Integer> countRow = counts.get(row);

        //Check if a particular sym is present in the hashmap to update its frequency
        if(!countRow.containsKey(sym)){
            countRow.put(sym,0);
        }

        //Updating the frequency of the symbol in the corresponding row
        countRow.put(sym,countRow.get(sym)+1);

        if(countRow.get(sym) == board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Character sym = move.getPlayer().getSymbol().getSym();

        counts.get(row).put(sym,counts.get(row).get(sym-1));
    }
}
