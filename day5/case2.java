// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.*;

// public class case2 {
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new FileReader("testinput.txt"));
//         List<String> rules = new ArrayList<>();
//         List<List<Integer>> updates = new ArrayList<>();
//         String line;

//         // Read the rules from the input until an empty line is encountered
//         Map<Integer, List<Integer>> beforeMap = new HashMap<>();
//         while ((line = br.readLine()) != null && !line.isEmpty()) {
//             String[] parts = line.split("\\|");
//             int before = Integer.parseInt(parts[0].trim());
//             int after = Integer.parseInt(parts[1].trim());

//             beforeMap.putIfAbsent(after, new ArrayList<>());
//             beforeMap.get(after).add(before);
//             rules.add(line);
//         }

//         // Read the updates after the empty line
//         while ((line = br.readLine()) != null) {
//             List<Integer> update = new ArrayList<>();
//             String[] pages = line.split(",");
//             for (String page : pages) {
//                 update.add(Integer.parseInt(page));
//             }
//             updates.add(update);
//         }

//         // Close the file
//         br.close();

//         int sumMiddlePages = 0;

//         // Process each update
//         for (List<Integer> update : updates) {
//             if (!isValidOrder(update, beforeMap)) {
//                 makeValid(update, beforeMap);
//                 int middleIndex = update.size() / 2;
//                 sumMiddlePages += update.get(middleIndex);
//             }
            
//         }

//         System.out.println("The sum of the middle page numbers is: " + sumMiddlePages);
//     }

//     public static boolean isValidOrder(List<Integer> update, Map<Integer, List<Integer>> beforeMap) {
//         // Keep track of the index positions of each page in the update
//         Map<Integer, Integer> pageIndex = new HashMap<>();
//         for (int i = 0; i < update.size(); i++) {
//             pageIndex.put(update.get(i), i);
//         } 

//         // Check if the update order satisfies all the beforeMap rules
//         for (Integer page : pageIndex.keySet()) {
//             if (beforeMap.containsKey(page)) {
//                 List<Integer> beforePages = beforeMap.get(page);
    
//                 // Ensure all prerequisite pages appear before the current page in the update
//                 for (int before : beforePages) {
//                     if (pageIndex.containsKey(before) && pageIndex.get(before) > pageIndex.get(page)) {
//                         // The prerequisite appears after the current page, invalid order
//                         return false;
//                     }
//                 }
//             }
//         }
//         return true;
//     }

//     public static void makeValid(List<Integer> update, Map<Integer, List<Integer>> beforeMap) {
//         List<Integer> sortedOrder = new ArrayList<>();
//         Set<Integer> visited = new HashSet<>();
//         Set<Integer> cycle = new HashSet<>();

//         // Perform topological sort for all pages in the update list
//         for (int page : update) {
//             if (!visited.contains(page)) {
//                 if (!dfs(page, beforeMap, visited, cycle, sortedOrder)) {
//                     throw new RuntimeException("Cycle detected in the prerequisites. Topological sort not possible.");
//                 }
//             }
//         }

//         // Reorder the update list based on the sorted order
//         update.clear();
//         update.addAll(sortedOrder);
//     }

//     // Depth-First Search (DFS) for topological sorting
//     private static boolean dfs(int page, Map<Integer, List<Integer>> beforeMap, Set<Integer> visited, Set<Integer> cycle, List<Integer> sortedOrder) {
//         // If the page is already in the cycle, a cycle is detected
//         if (cycle.contains(page)) {
//             return false;
//         }

//         // If the page is already visited, return true (it has been processed)
//         if (visited.contains(page)) {
//             return true;
//         }

//         // Add the page to the cycle set to detect any circular dependency
//         cycle.add(page);

//         // Traverse the dependencies for the current page
//         if (beforeMap.containsKey(page)) {
//             for (int dependency : beforeMap.get(page)) {
//                 if (!dfs(dependency, beforeMap, visited, cycle, sortedOrder)) {
//                     return false; // Cycle detected
//                 }
//             }
//         }

//         // Remove the page from the cycle set and mark it as visited
//         cycle.remove(page);
//         visited.add(page);

//         // Add the page to the sorted order
//         sortedOrder.add(page);

//         return true;
//     }
// }
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class case2 {
    private static List<int[]> rules = new ArrayList<>(); // Define rules as a static member

    public static void main(String[] args) {
        List<List<Integer>> updates = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            // Read rules section
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                String[] parts = line.split("\\|");
                int a = Integer.parseInt(parts[0].trim());
                int b = Integer.parseInt(parts[1].trim());
                rules.add(new int[]{a, b});
            }
            
            // Read updates section
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                List<Integer> update = new ArrayList<>();
                for (String part : parts) {
                    update.add(Integer.parseInt(part.trim()));
                }
                updates.add(update);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int ans = 0;

        for (List<Integer> update : updates) {
            if (followsRules(update)) {
                continue; // No need to sort if it follows the rules
            }

            List<Integer> sortedUpdate = sortCorrectly(update);
            if (sortedUpdate != null && !sortedUpdate.isEmpty()) {
                ans += sortedUpdate.get(sortedUpdate.size() / 2);
            }
        }

        System.out.println(ans);
    }

    // Check if an update follows the rules
    private static boolean followsRules(List<Integer> update) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            indexMap.put(update.get(i), i);
        }

        for (int[] rule : rules) {
            int a = rule[0];
            int b = rule[1];
            if (indexMap.containsKey(a) && indexMap.containsKey(b) && indexMap.get(a) >= indexMap.get(b)) {
                return false; // The order is not correct
            }
        }

        // Return true and the middle element if it follows the rules
        return true;
    }

    // Sort the update according to the rules and return the sorted list
    private static List<Integer> sortCorrectly(List<Integer> update) {
        List<int[]> myRules = new ArrayList<>();
        for (int[] rule : rules) {
            if (update.contains(rule[0]) && update.contains(rule[1])) {
                myRules.add(rule);
            }
        }

        Map<Integer, Integer> indeg = new HashMap<>();
        for (int[] rule : myRules) {
            indeg.put(rule[1], indeg.getOrDefault(rule[1], 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        while (ans.size() < update.size()) {
            for (int x : update) {
                if (ans.contains(x)) {
                    continue;
                }
                if (indeg.getOrDefault(x, 0) <= 0) {
                    ans.add(x);
                    for (int[] rule : myRules) {
                        if (rule[0] == x) {
                            indeg.put(rule[1], indeg.get(rule[1]) - 1);
                        }
                    }
                }
            }
        }

        return ans;
    }
}
