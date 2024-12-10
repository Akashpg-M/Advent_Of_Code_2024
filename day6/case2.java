// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;

// public class case2{
//     public static void main(String[] args){

//         try{
//             int res = getPos("input.txt");
//             System.out.println("Result : "+ res);
//         }catch(IOException e){
//             System.out.println("Error : "+e.getMessage());
//         }

//     }

//     public static int getPos(String filePath) throws IOException{
//         BufferedReader br = new BufferedReader(new FileReader(filePath));
//         String line;
//         int row = -1, col = -1;
//         ArrayList<String> grid = new ArrayList<>();

//         while((line = br.readLine()) != null){
//             if(line.contains("^") || line.contains(">")|| line.contains("v")|| line.contains("<")){
//                 row = grid.size();
//                 col = line.indexOf('^') >= 0 ? line.indexOf('^') :
//                       line.indexOf('>') >= 0 ? line.indexOf('>') :
//                       line.indexOf('v') >= 0 ? line.indexOf('v') :
//                       line.indexOf('<');
//             }
//             grid.add(line);
//         }

//         br.close();


//         int[][] direction = {
//             {-1, 0}, // Up
//             {0, 1},  // Right
//             {1, 0},  // Down
//             {0, -1}  // Left
//         };

//         int dId;
//         char startPt = grid.get(row).charAt(col);
//         if(startPt == '^') dId = 0;
//         else if(startPt == '>') dId = 1;
//         else if(startPt == 'v') dId = 2;
//         else dId = 3;

//         int count = 1;

//         boolean[][][] visited = new boolean[grid.size()][grid.get(0).length()][4];
//         visited[row][col][dId] = true;

//         while(true){

//             int nr = row + direction[dId][0];
//             int nc = col+ direction[dId][1];

//             if(!(nr >= 0 && nr < grid.size() && nc >= 0 && nc <grid.get(0).length() && grid.get(nr).charAt(nc) != '#')){
//                 dId = (dId+1)%4;
//                 continue;
//             }

//             if(isCycle(row, col, nr, nc, dId, visited, grid)) count++;

//             row = nr;
//             col = nc;

//             visited[row][col][dId] = true;
            

//             if(row == 0 || row == grid.size() - 1 || col == 0 || col == grid.get(0).length()-1) break;
//         }

//         return count;
//     }

//     public static boolean isCycle(int startRow, int startCol, int blockRow, int blockCol, int dId, boolean[][][] visited, ArrayList<String> grid){
//         int[][] direction = {
//             {-1, 0}, // Up
//             {0, 1},  // Right
//             {1, 0},  // Down
//             {0, -1}  // Left
//         };

//         int row = startRow, col = startCol, curDir = dId;
//         boolean[][][] tempVisited = new boolean[visited.length][visited[0].length][4];

//         // Temporary copy of the visited array
//         for (int r = 0; r < visited.length; r++) {
//             for (int c = 0; c < visited[0].length; c++) {
//                 System.arraycopy(visited[r][c], 0, tempVisited[r][c], 0, 4);
//             }
//         }

//         // Start the simulation
//         while (true) {
//             int nr = row + direction[curDir][0];
//             int nc = col + direction[curDir][1];

//             // Skip the blocked position
//             if (nr == blockRow && nc == blockCol) {
//                 curDir = (curDir + 1) % 4;
//                 continue;
//             }

//             // Change direction if the next cell is invalid
//             if (!(nr >= 0 && nr < grid.size() && nc >= 0 && nc < grid.get(0).length() && grid.get(nr).charAt(nc) != '#')) {
//                 curDir = (curDir + 1) % 4;
//                 continue;
//             }

//             // Check for a cycle
//             if (tempVisited[nr][nc][curDir]) return true;

//             tempVisited[nr][nc][curDir] = true;
//             row = nr;
//             col = nc;

//             // Break on reaching the boundary
//             if (row == 0 || row == grid.size() - 1 || col == 0 || col == grid.get(0).length() - 1) break;
//         }

//         return false;
//     }

//     public static int getNextDir(int r, int c, int dId, ArrayList<String> grid){
//         int[][] direction = {
//             {-1, 0}, // Up
//             {0, 1},  // Right
//             {1, 0},  // Down
//             {0, -1}  // Left
//         };
//         while(true){
//             int nr = r + direction[dId][0];
//             int nc = c+ direction[dId][1];
//             if(!(nr >= 0 && nr < grid.size() && nc >= 0 && nc <grid.get(0).length() && grid.get(nr).charAt(nc) != '#')){
//                 dId = (dId+1)%4;
//                 continue;
//             }
//             return dId;
//         }   
//     }
// }   

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class case2 {
    public static void main(String[] args) {
        try {
            int res = getPos("input.txt");
            System.out.println("Result: " + res);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static int getPos(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int startRow = -1, startCol = -1;
        ArrayList<String> grid = new ArrayList<>();

        // Read the grid and locate the guard's starting position
        while ((line = br.readLine()) != null) {
            if (line.contains("^") || line.contains(">") || line.contains("v") || line.contains("<")) {
                startRow = grid.size();
                startCol = line.indexOf('^') >= 0 ? line.indexOf('^') :
                           line.indexOf('>') >= 0 ? line.indexOf('>') :
                           line.indexOf('v') >= 0 ? line.indexOf('v') :
                           line.indexOf('<');
            }
            grid.add(line);
        }
        br.close();

        int[][] directions = {
            {-1, 0}, // Up
            {0, 1},  // Right
            {1, 0},  // Down
            {0, -1}  // Left
        };

        int startDirection;
        char guardStart = grid.get(startRow).charAt(startCol);
        if (guardStart == '^') startDirection = 0;
        else if (guardStart == '>') startDirection = 1;
        else if (guardStart == 'v') startDirection = 2;
        else startDirection = 3;

        boolean[][] visited = new boolean[grid.size()][grid.get(0).length()];
        int validObstructions = 0;

        // Traverse all positions and simulate the cycle check
        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).length(); c++) {
                if (grid.get(r).charAt(c) == '#' || (r == startRow && c == startCol)) continue;

                // Simulate only if this position is a potential obstruction
                if (!visited[r][c] && isCycle(startRow, startCol, startDirection, r, c, grid, directions, visited)) {
                    validObstructions++;
                }
            }
        }

        return validObstructions;
    }

    public static boolean isCycle(int startRow, int startCol, int startDirection, int blockRow, int blockCol, 
                                  ArrayList<String> grid, int[][] directions, boolean[][] visited) {
        int row = startRow, col = startCol, dir = startDirection;
        boolean[][][] localVisited = new boolean[grid.size()][grid.get(0).length()][4];

        while (true) {
            int nextRow = row + directions[dir][0];
            int nextCol = col + directions[dir][1];

            // Skip the blocked position
            if (nextRow == blockRow && nextCol == blockCol) {
                dir = (dir + 1) % 4; // Turn right
                continue;
            }

            // Change direction if the next cell is invalid
            if (!(nextRow >= 0 && nextRow < grid.size() && nextCol >= 0 && nextCol < grid.get(0).length() 
                  && grid.get(nextRow).charAt(nextCol) != '#')) {
                dir = (dir + 1) % 4;
                continue;
            }

            // Detect cycle
            if (localVisited[nextRow][nextCol][dir]) {
                return true;
            }

            // Mark as visited
            localVisited[nextRow][nextCol][dir] = true;

            // Move forward
            row = nextRow;
            col = nextCol;

            // Stop at boundaries
            if (row == 0 || row == grid.size() - 1 || col == 0 || col == grid.get(0).length() - 1) break;
        }

        // Mark the obstruction as processed
        visited[blockRow][blockCol] = true;
        return false;
    }
}
