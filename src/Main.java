public class Main {

    public static void main(String[] args) {
        //Create board
        Board b = new Board(5,5,2);

        //Search for optimal solution
        Node startNode = new Node(b);
        SAsearch sa = new SAsearch(startNode,1,0.001);
        Board result = sa.search();

        //Print solution
        System.out.println(result);
        System.out.println("Score: " + result.evaluate());
    }
}
