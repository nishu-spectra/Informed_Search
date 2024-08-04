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

    int graph[][];
    int numNodes;
    GBFS(int graph[][])
    {
        this.graph = graph;
        this.numNodes = graph.length;
    }

    int heuristicCalculator(int x1, int y1, int x2, int y2) //using manhattan distance method for heuristic calculation
    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    void Gbfs(Node start, Node goal)
    {
        PriorityQueue<Node> open = new PriorityQueue<>();
        HashSet<Node> close = new HashSet<>();

        open.add(start);

        while (!open.isEmpty())
        {
            Node current = open.poll();
            if (current.x == goal.x && current.y == goal.y)
            {
                path(current);
                return;
            }
            close.add(current);
            for (Node neighbour : getNeighbours(current, goal))
            {
                if (!open.contains(neighbour) && !close.contains(neighbour))
                {
                    neighbour.parent = current;
                    open.add(neighbour);
                }
            }
        }
        System.out.println("No path found");
    }

    List<Node> getNeighbours(Node node, Node goal)
    {
        List<Node> neighbours = new LinkedList<>();

        for (int i = 0; i < numNodes; i++) {
            if (graph[node.x][i] != 0) {  // Check if there's an edge
                int heuristic = heuristicCalculator(i, node.y, goal.x, goal.y);
                neighbours.add(new Node(i, node.y, node, heuristic));
            }
            if (graph[node.y][i] != 0) {  // Check if there's an edge
                int heuristic = heuristicCalculator(node.x, i, goal.x, goal.y);
                neighbours.add(new Node(node.x, i, node, heuristic));
            }
        }

        return neighbours;
    }

    void path(Node current)
    {
        List<Node> path = new LinkedList<>();
        while (current != null)
        {
            path.add(0, current);
            current = current.parent;
        }

        System.out.println(path);
    }
}

public class GreedyBestFirstSearch {
    public static void main(String[] args) {
        System.out.println("Hello World");
        int graph[][] = {
            {0, 11, 14, 7, 0, 0, 0, 0},
            {11, 0, 0, 0, 15, 0, 0, 0},
            {14, 0, 0, 0, 8, 10, 0, 0},
            {7, 0, 0, 0, 0, 25, 0, 0},
            {0, 15, 8, 0, 0, 0, 9, 0},
            {0, 0, 10, 25, 0, 0, 0, 20},
            {0, 0, 0, 0, 9, 0, 0, 10},
            {0, 0, 0, 0, 0, 20, 10, 0}
        };

        GBFS gbfs = new GBFS(graph);

        // Start node is at (2, 7) with heuristic value to goal (7, 2)
        GBFS.Node start = gbfs.new Node(2, 7, null, gbfs.heuristicCalculator(2, 7, 7, 2));

        // Goal node is at (7, 2) with heuristic value (0 since it's the goal)
        GBFS.Node goal = gbfs.new Node(7, 2, null, 0);

        gbfs.Gbfs(start, goal);
    }
}
