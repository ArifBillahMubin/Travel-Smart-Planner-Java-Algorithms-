import java.util.*;

public class TravelSmartPlanner2 {

    static final String[] cities = {"Rajshahi", "Sylhet", "Chittagong", "Barishal", "Khulna", "Dhaka"};
    static final int INF = Integer.MAX_VALUE;

    static class Graph {
        int vertices;
        int[][] adjacencyMatrix;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyMatrix = new int[vertices][vertices];
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(adjacencyMatrix[i], INF);
                adjacencyMatrix[i][i] = 0; 
            }
        }

        public void addEdge(int source, int destination, int weight) {
            adjacencyMatrix[source][destination] = weight;
            adjacencyMatrix[destination][source] = weight;
        }

        // Shortest Route using Dijkstra's Algorithm
        public void dijkstra(int startVertex, int endVertex) {
            boolean[] visited = new boolean[vertices];
            int[] distance = new int[vertices];
            int[] parent = new int[vertices];
            Arrays.fill(distance, INF);
            Arrays.fill(parent, -1);
            distance[startVertex] = 0;

            for (int i = 0; i < vertices; i++) {
                int u = findMinVertex(distance, visited);
                if (u == -1) break;
                visited[u] = true;

                for (int v = 0; v < vertices; v++) {
                    if (!visited[v] && adjacencyMatrix[u][v] != INF &&
                            distance[u] + adjacencyMatrix[u][v] < distance[v]) {
                        distance[v] = distance[u] + adjacencyMatrix[u][v];
                        parent[v] = u;
                    }
                }
            }

            printPath(startVertex, endVertex, distance, parent);
        }

        private int findMinVertex(int[] distance, boolean[] visited) {
            int minVertex = -1;
            for (int i = 0; i < vertices; i++) {
                if (!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
                    minVertex = i;
                }
            }
            return minVertex;
        }

        private void printPath(int startVertex, int endVertex, int[] distance, int[] parent) {
            if (distance[endVertex] == INF) {
                System.out.println("No path exists between " + cities[startVertex] + " and " + cities[endVertex]);
                return;
            }
            System.out.println("Shortest distance: " + distance[endVertex]);
            System.out.print("Path: ");
            Stack<Integer> path = new Stack<>();
            int current = endVertex;
            while (current != -1) {
                path.push(current);
                current = parent[current];
            }
            while (!path.isEmpty()) {
                System.out.print(cities[path.pop()] + (path.isEmpty() ? "\n" : " -> "));
            }
        }

        // Plan Trip Within Budget using Greedy Algorithm
        public void planTripWithinBudget(int startVertex, int budget) {
            boolean[] visited = new boolean[vertices];
            int[] cost = new int[vertices];
            Arrays.fill(cost, INF);
            cost[startVertex] = 0;

            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[]{startVertex, 0});

            System.out.println("Cities you can travel to within a budget of " + budget + ":");
            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int currentVertex = current[0];
                int currentCost = current[1];

                if (visited[currentVertex]) continue;
                visited[currentVertex] = true;

                if (currentVertex != startVertex) {
                    System.out.println("- " + cities[currentVertex] + " (Cost: " + currentCost + ")");
                }

                for (int neighbor = 0; neighbor < vertices; neighbor++) {
                    if (!visited[neighbor] && adjacencyMatrix[currentVertex][neighbor] != INF) {
                        int newCost = currentCost + adjacencyMatrix[currentVertex][neighbor];
                        if (newCost <= budget && newCost < cost[neighbor]) {
                            cost[neighbor] = newCost;
                            pq.offer(new int[]{neighbor, newCost});
                        }
                    }
                }
            }
        }

        // Multi-City Travel Plan using Divide-and-Conquer
        public void multiCityTravelPlan(int[] citiesToVisit) {
            System.out.println("Multi-City Travel Plan:");
            System.out.print("Order of travel: ");
            List<Integer> travelOrder = divideAndConquerRoute(citiesToVisit, 0, citiesToVisit.length - 1);
            int totalCost = 0;
            for (int i = 0; i < travelOrder.size() - 1; i++) {
                int from = travelOrder.get(i);
                int to = travelOrder.get(i + 1);
                totalCost += adjacencyMatrix[from][to];
                System.out.print(cities[from] + " -> ");
            }
            System.out.println(cities[travelOrder.get(travelOrder.size() - 1)]);
            System.out.println("Total travel cost: " + totalCost);
        }

        private List<Integer> divideAndConquerRoute(int[] citiesToVisit, int start, int end) {
            if (start == end) {
                return new ArrayList<>(List.of(citiesToVisit[start]));
            }

            int mid = (start + end) / 2;
            List<Integer> left = divideAndConquerRoute(citiesToVisit, start, mid);
            List<Integer> right = divideAndConquerRoute(citiesToVisit, mid + 1, end);

            List<Integer> combined = new ArrayList<>(left);
            combined.addAll(right);
            return combined;
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1, 9);  // R -> S
        graph.addEdge(1, 2, 6);  // S -> C
        graph.addEdge(2, 5, 15); // C -> D
        graph.addEdge(5, 4, 7);  // D -> K
        graph.addEdge(5, 3, 10); // D -> B
        graph.addEdge(4, 3, 9);  // K -> B
        graph.addEdge(3, 2, 11); // B -> C
        graph.addEdge(0, 5, 2);  // R -> D

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Travel Smart Planner ===");
            System.out.println("1. Find Shortest Route");
            System.out.println("2. Plan Trip Within Budget");
            System.out.println("3. Multi-City Travel Plan");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting... Thank you for using Travel Smart Planner!");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("\nCities:");
                    for (int i = 0; i < cities.length; i++) {
                        System.out.println(i + ": " + cities[i]);
                    }
                    System.out.print("Enter source city number: ");
                    int source = scanner.nextInt();
                    System.out.print("Enter destination city number: ");
                    int destination = scanner.nextInt();
                    graph.dijkstra(source, destination);
                    break;
                case 2:
                    System.out.println("\nCities:");
                    for (int i = 0; i < cities.length; i++) {
                        System.out.println(i + ": " + cities[i]);
                    }
                    System.out.print("Enter starting city number: ");
                    int startCity = scanner.nextInt();
                    System.out.print("Enter your budget: ");
                    int budget = scanner.nextInt();
                    graph.planTripWithinBudget(startCity, budget);
                    break;
                case 3:
                    System.out.println("\nCities:");
                    for (int i = 0; i < cities.length; i++) {
                        System.out.println(i + ": " + cities[i]);
                    }
                    System.out.print("Enter number of cities to visit: ");
                    int numCities = scanner.nextInt();
                    int[] citiesToVisit = new int[numCities];
                    for (int i = 0; i < numCities; i++) {
                        System.out.print("Enter city number " + (i + 1) + ": ");
                        citiesToVisit[i] = scanner.nextInt();
                    }
                    graph.multiCityTravelPlan(citiesToVisit);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }

        scanner.close();
    }
}
