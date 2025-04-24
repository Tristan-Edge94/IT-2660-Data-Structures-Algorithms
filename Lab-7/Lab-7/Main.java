class Main {
  public static void main(String[] args) {
    String[] vertices = {
      "Liberal Arts",           // 0
      "Student Services",       // 1
      "Health Careers & Sciences", // 2
      "Health Technologies Center", // 3
      "Recreation Center",      // 4
      "Technology Learning Center", // 5
      "Business & Technology",  // 6
      "Theatre"                 // 7
    };

    // Define edges based on direct walkability on the map
    int[][] edges = {
      {0, 1}, {1, 0}, // Liberal Arts <-> Student Services
      {1, 2}, {2, 1}, // Student Services <-> Health Careers & Sciences
      {2, 3}, {3, 2}, // Health Careers & Sciences <-> Health Tech Center
      {2, 4}, {4, 2}, // Health Careers & Sciences <-> Recreation Center
      {1, 5}, {5, 1}, // Student Services <-> Technology Learning Center
      {5, 6}, {6, 5}, // Technology Learning Center <-> Business & Technology
      {6, 7}, {7, 6}  // Business & Technology <-> Theatre
    };

    Graph<String> graph = new UnweightedGraph<>(vertices, edges);
    int startIndex = graph.getIndex("Business & Technology");
    UnweightedGraph<String>.SearchTree dfs = graph.dfs(startIndex);

    // Print paths from Business & Technology to each target
    int target1 = graph.getIndex("Health Technologies Center");
    int target2 = graph.getIndex("Student Services");
    int target3 = graph.getIndex("Recreation Center");

    System.out.println();
    dfs.printPath(target1);
    System.out.println();

    dfs.printPath(target2);
    System.out.println();

    dfs.printPath(target3);
    System.out.println();

    // Print the entire tree
    System.out.println();
    dfs.printTree();
  }
}
