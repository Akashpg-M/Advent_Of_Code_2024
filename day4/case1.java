import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class case1{
    public static void main(String[] args){
        try{
            List<String> grid = getGrid("input.txt");
            int wordCount = countWordOccurance(grid, "XMAS");
            System.out.println("word count : "+ wordCount);
        }catch(IOException e){
            System.out.println("Error : "+ e.getMessage());
        }
    }

    public static List<String> getGrid(String filePath) throws IOException{
        List<String> grid = new ArrayList<>();

        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = buffer.readLine()) != null){
            grid.add(line);
        }

        buffer.close();

        return grid;
    }

    public static int countWordOccurance(List<String> grid, String Word){

        int count = 0;
        int[][] dir = {
                        {1,0},
                        {-1,0},
                        {0,-1},
                        {0,1},
                        {1,-1},
                        {-1,1},
                        {1, 1},
                        {-1,-1}
                    };
        for(int i = 0; i< grid.size(); i++){
            for(int j=0; j<grid.get(0).length(); j++){
                count += countWordMatch(i, j, 0, Word, dir, grid);
            }
        }
        return count;
    }


    public static int countWordMatch(int r, int c, int wi, String word, int[][] directions, List<String> grid) {
        int count = 0;
        for(int[] dir: directions){
            int index = 0, row = r, col = c;
            while(row >= 0 && col >= 0 && row < grid.size() && index<word.length() && col < grid.get(0).length() && grid.get(row).charAt(col) == word.charAt(index)){
                row += dir[0];
                col += dir[1];
                index ++;
            }
            if(index == word.length()) count++;
        }
        return count;
    }  
}


// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.List;
// import java.util.ArrayList;

// public class case1 {
//     public static void main(String[] args) {
//         try {
//             List<String> grid = getGrid("input.txt");
//             int wordCount = countWordOccurrences(grid, "XMAS");
//             System.out.println("Total occurrences of 'XMAS': " + wordCount);
//         } catch (IOException e) {
//             System.out.println("Error: " + e.getMessage());
//         }
//     }

//     // Reads the grid from a file
//     public static List<String> getGrid(String filePath) throws IOException {
//         List<String> grid = new ArrayList<>();
//         BufferedReader buffer = new BufferedReader(new FileReader(filePath));
//         String line;
//         while ((line = buffer.readLine()) != null) {
//             grid.add(line);
//         }
//         buffer.close();
//         return grid;
//     }

//     // Count occurrences of the word in all directions
//     public static int countWordOccurrences(List<String> grid, String word) {
//         int count = 0;
//         int[][] directions = {
//             {1, 0},    // Right
//             {-1, 0},   // Left
//             {0, 1},    // Down
//             {0, -1},   // Up
//             {1, 1},    // Down-Right
//             {-1, -1},  // Up-Left
//             {1, -1},   // Down-Left
//             {-1, 1}    // Up-Right
//         };

//         int rows = grid.size();
//         int cols = grid.get(0).length();

//         // Traverse each cell in the grid
//         for (int i = 0; i < rows; i++) {
//             for (int j = 0; j < cols; j++) {
//                 count += searchWord(grid, word, i, j, directions);
//             }
//         }
//         return count;
//     }

//     // Searches for the word starting from grid[r][c]
//     public static int searchWord(List<String> grid, String word, int r, int c, int[][] directions) {
//         int count = 0;

//         // Try all directions
//         for (int[] dir : directions) {
//             int row = r, col = c, index = 0;

//             // Check if the word can be formed in this direction
//             while (index < word.length() &&
//                     row >= 0 && col >= 0 &&
//                     row < grid.size() && col < grid.get(0).length() &&
//                     grid.get(row).charAt(col) == word.charAt(index)) {

//                 row += dir[0]; // Move in the current direction
//                 col += dir[1];
//                 index++;
//             }

//             // If the word is completely matched, increment count
//             if (index == word.length()) {
//                 count++;
//             }
//         }
//         return count;
//     }
// }
