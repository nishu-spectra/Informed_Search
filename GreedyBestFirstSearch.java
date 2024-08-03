package Informed_Search;
import java.util.*;

class GBFS {
    class Node implements Comparable<Node> {
        int x;
        int y;
        Node parent;
        int heuristic;
        Node(int x, int y, Node parent, int heuristic)
        {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.heuristic = heuristic;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.heuristic, other.heuristic);
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    int graph [][];
    int numNodes;
    GBFS(int graph[][])
    {
        this.graph = graph;
        this.numNodes = graph.length;
    }

    int heuristicCalculator(int x1, int y1, int x2, int y2) //using manhattan distance method for heuristic calculation
    {
        return Math.abs(x1-x2)+ Math.abs(y1-y2);
    }

     void Gbfs(Node start, Node goal)
     {
         PriorityQueue<Node> open = new PriorityQueue<>();
         PriorityQueue<Node> close = new PriorityQueue<>();

         open.add(start);
         
         while(!open.isEmpty())
         {
            Node current = open.poll();
            if(close.equals(current))
            {
                path(current);
                return;
            }
            close.add(current);
            for(Node neighbour: getNeighbours(current,goal))
            {
                if(!open.contains(neighbour)  &&  !close.contains(neighbour))
                {
                    neighbour.parent= current;
                    open.add(neighbour);

                }
            }  
            
         }
         System.out.println("No path found");
     }

     List<Node> getNeighbours(Node node, Node goal)
     {
         List<Node> neighbours = new LinkedList<>();
         int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            
            if (nx >= 0 && nx < numNodes && ny >= 0 && ny <numNodes && graph[nx][ny] != 0) {
                neighbours.add(new Node(nx, ny, node, heuristicCalculator(nx, ny, goal.x, goal.y)));
            }
        }
            
        return neighbours;
     }

     void path(Node current)
     {
         List<Node> path = new LinkedList<>();
         while(current!=null)
         {
            path.add(0,current);
            current=current.parent;
         }
         System.out.print("GBFS path is: ");
         for (Node n : path) {
            System.out.println("Node: (" + n.x + ", " + n.y + ")");
        }
         //System.out.println("Path:" +path);
     }

}
public class GreedyBestFirstSearch {
    public static void main(String[] args) {
      System.out.println("Hello World");
        int graph[][] = {{0,11,14,7,0,0,0,0},
    {11,0,0,0,15,0,0,0},
    {14,0,0,0,8,10,0,0},
    {7,0,0,0,0,25,0,0},
    {0,15,8,0,0,0,9,0},
    {0,0,10,25,0,0,0,20},
    {0,0,0,0,9,0,0,10},
    {0,0,0,0,0,20,10,0}};
    GBFS gbfs = new GBFS(graph);
    GBFS.Node start = gbfs.new Node(0, 0,null, gbfs.heuristicCalculator(2, 7, 7, 2));
    GBFS.Node goal = gbfs.new Node(7,2,null,0);    
    gbfs.Gbfs(start, goal);
    }
    
}
