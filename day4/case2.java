import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class case2{
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
        for(int i = 1; i< grid.size()-1; i++){
            for(int j=1; j<grid.get(0).length()-1; j++){
                if(grid.get(i).charAt(j) == 'A'){
                    if(isXmas(i, j, grid)) count++;
                }
            }
        }
        return count;
    }


    public static boolean isXmas(int r, int c, List<String> grid) {
        int count = 0;
        int[][] dir = {
            {-1,-1},
            {1, 1},
            {-1,1},
            {1,-1}
        };

        for(int d =0; d<2; d++){
            int[] startDir = dir[d*2];
            int[] endDir = dir[d*2+1];

            int startRow = r+startDir[0];
            int startCol = c+startDir[1];
            int endRow = r+endDir[0];
            int endCol = c+endDir[1];

            int startChar = grid.get(startRow).charAt(startCol);
            int endChar = grid.get(endRow).charAt(endCol);

            if(!((startChar == 'M' && endChar == 'S') ||(startChar == 'S' && endChar == 'M'))){
                return false;
            }
        }
        return true;
    }  
}
