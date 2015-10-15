import java.util.Arrays;
import java.util.List;

/**
 * Created by Kristoffer on 12/10/2015.
 */
public class Board {
    private int width, height, k;
    int[][] map;

    public Board(int width, int height, int k) {
        this.width = width;
        this.height = height;
        this.k = k;
        this.map= new int[height][width];
        generateBoard();
    }

    private void generateBoard(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //Fill the k first rows with eggs
                if(i < k){
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                }
            }
        }
    }

    //Create a deep copy of a map
    public int[][] copy() {
        int[][] target = new int[this.map.length][];
        for (int i=0; i <this.map.length; i++) {
            target[i] = Arrays.copyOf(this.map[i], this.map[i].length);
        }
        return target;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public double evaluate(){
        int k = this.getK();
        int l = this.getWidth() -1;
        double points = 0;
        int drEggs = 0;
        int dlEggs = 0;
        for (int i = 0; i < this.getHeight(); i++) {
            //Count eggs on the main diagonals
            drEggs += this.getPositionValue(i, i);
            dlEggs += this.getPositionValue(this.getHeight() - 1 - i, i);
            int rowEggs = 0;
            int colEggs = 0;
            for (int j = 0; j < this.getWidth(); j++) {
                //Count eggs on verticals and horizontals
                rowEggs += this.getPositionValue(i, j);
                colEggs += this.getPositionValue(j, i);
            }
            //Add points
            points += Math.abs(rowEggs-k);
            points += Math.abs(colEggs-k);
        }
        for (int i = 0; i < this.getHeight()-1; i++) {
            int dtlEggs = 0;
            int dtrEggs = 0;
            int dbrEggs = 0;
            int dblEggs = 0;
            for (int j = 0; j < i+1; j++) {
                //Count eggs on other diagonals
                dtlEggs += this.getPositionValue(j, (i - j));
                dtrEggs += this.getPositionValue(j, (l - i + j));
                dbrEggs += this.getPositionValue(l - j, (l - i + j));
                dblEggs += this.getPositionValue(l - j, (i - j));
            }
            points += Math.abs(dtlEggs - k);
            points += Math.abs(dtrEggs - k);
            points += Math.abs(dbrEggs - k);
            points += Math.abs(dblEggs - k);
        }
        points += Math.abs(drEggs - k);
        points += Math.abs(dlEggs - k);
        return 1/(1+points);
    }

    public void addEgg(int x, int y){
        map[y][x] = 1;
    }

    public void removeEgg(int x, int y){
        map[y][x] = 0;
    }

    public int getPositionValue(int x, int y){
        return map[y][x];
    }

    public void setStringPos(int x, int y, char c){
        map[y][x] = c;
    }

    public String toString(){
        String s ="";
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s += map[i][j];
            }
            s += "\n";
        }
        return s;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getK() {
        return k;
    }
}
