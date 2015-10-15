import java.util.ArrayList;

/**
 * Created by Kristoffer on 12/10/2015.
 */
public class Node {
    private final int UP = 1;
    private final int DOWN = 0;
    private boolean isEgg;
    Board board;

    public Node(Board board) {
        this.board = board;
    }

    public ArrayList<Node> generateNeighbours(){
        ArrayList<Node> neighbours = new ArrayList<Node>();

        for (int j = 0; j < board.getHeight(); j++) {
            for (int i = 0; i < board.getWidth(); i++) {
                //Check if the current position is an egg
                if(board.getPositionValue(i,j) == 1){
                    //Try to move down. If there is no egg there, copy the current board and create a new node where the egg is moved down
                    if(isLegalMove(i,j,DOWN)){
                        Board newBoard = new Board(board.getHeight(), board.getWidth(), board.getK());
                        newBoard.setMap(board.copy());
                        newBoard.addEgg(i, j + 1);
                        newBoard.removeEgg(i, j);
                        neighbours.add(new Node(newBoard));
                    }
                    if(isLegalMove(i,j,UP)){
                        //Try to move up. If there is no egg there, copy the current board and create a new node where the egg is moved down
                        Board newBoard = new Board(board.getHeight(), board.getWidth(), board.getK());
                        newBoard.setMap(board.copy());
                        newBoard.addEgg(i, j - 1);
                        newBoard.removeEgg(i, j);
                        neighbours.add(new Node(newBoard));
                    }
                }
            }
        }
        return neighbours;
    }

    private boolean isLegalMove(int x, int y, int direction){
        //Check if there is no egg on the next position and we are still inside the board
        if(direction == UP && (y - 1 > 0) && board.getPositionValue(x,y-1) != 1) return true;
        if(direction == DOWN && y + 1 < board.getHeight() && board.getPositionValue(x,y+1) != 1) return true;
        return false;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isEgg() {
        return isEgg;
    }

    public void setIsEgg(boolean isEgg) {
        this.isEgg = isEgg;
    }
}
