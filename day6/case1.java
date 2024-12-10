import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class case1{
    public static void main(String[] args){

        try{
            int res = getPos("input.txt");
            System.out.println("Result : "+ res);
        }catch(IOException e){
            System.out.println("Error : "+e.getMessage());
        }

    }

    public static int getPos(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int row = -1, col = -1;
        ArrayList<String> grid = new ArrayList<>();

        while((line = br.readLine()) != null){
            if(line.contains("^") || line.contains(">")|| line.contains("v")|| line.contains("<")){
                row = grid.size();
                col = line.indexOf('^') >= 0 ? line.indexOf('^') :
                      line.indexOf('>') >= 0 ? line.indexOf('>') :
                      line.indexOf('v') >= 0 ? line.indexOf('v') :
                      line.indexOf('<');
            }
            grid.add(line);
        }

        br.close();


        int[][] direction = {
            {-1, 0}, // Up
            {0, 1},  // Right
            {1, 0},  // Down
            {0, -1}  // Left
        };

        int dId;
        char startPt = grid.get(row).charAt(col);
        if(startPt == '^') dId = 0;
        else if(startPt == '>') dId = 1;
        else if(startPt == 'v') dId = 2;
        else dId = 3;

        int count = 1;

        boolean[][] visited = new boolean[grid.size()][grid.get(0).length()];
        visited[row][col] = true;

        while(true){

            int nr = row + direction[dId][0];
            int nc = col+ direction[dId][1];

            if(!(nr >= 0 && nr < grid.size() && nc >= 0 && nc <grid.get(0).length() && grid.get(nr).charAt(nc) != '#')){
                dId = (dId+1)%4;
                continue;
            }

            row = nr;
            col = nc;

            if(!visited[row][col]){
                count++;
                visited[row][col] = true;
            }

            if(row == 0 || row == grid.size() - 1 || col == 0 || col == grid.get(0).length()-1) break;
        }
        return count;
    }
}   



