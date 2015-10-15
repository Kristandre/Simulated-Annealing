import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kristoffer on 12/10/2015.
 */
public class SAsearch {
    private Node startNode;
    private double temp;
    private double dT;

    public SAsearch(Node startNode, double startTemp, double dT) {
        this.startNode = startNode;
        this.temp = startTemp;
        this.dT = dT;
    }

    public Board search(){
        Node currentNode = this.startNode;
        Node bestNode = this.startNode;
        while(this.temp > 0){
            ArrayList<Node> neighbours = currentNode.generateNeighbours();

            double maxEval = 0;
            for(Node n:neighbours){
                double temporaryEval = n.getBoard().evaluate();

                //If the most optimal solution is found, return it
                if(temporaryEval == 1) return n.getBoard();

                //If new max evaluation score is found, update maxEval
                if(temporaryEval > maxEval){
                    maxEval = temporaryEval;
                    if(n.getBoard().evaluate()>bestNode.getBoard().evaluate()){
                        bestNode = n;
                    }
                }
            }

            //Calculate x,p,q to decide if the next step should be exploiting or exploring
            double q = (maxEval-currentNode.getBoard().evaluate())/currentNode.getBoard().evaluate();
            double p = Math.min(1,Math.exp(-q/temp));
            double x = Math.random();
            if(x > p){
                //Exploit
                currentNode=bestNode;
            } else{
                //Explore
                int randomIndex = new Random().nextInt(neighbours.size());
                currentNode = neighbours.get(randomIndex);
            }

            this.temp -= dT;
        }
       return bestNode.getBoard();
    }
}
