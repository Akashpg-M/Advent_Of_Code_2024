import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

public class problem2 {

    public static int calSimilarityScore(String filePath) throws IOException{

        HashMap<Integer,Integer> hash = new HashMap<>();
        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        String line;
        ArrayList<Integer> list = new ArrayList<>();

        while((line = buffer.readLine()) != null){

            String[] columns = line.split("\\s+");
            int k1 = Integer.parseInt(columns[0].trim());
            int k2 = Integer.parseInt(columns[1].trim());

            list.add(k1);

            hash.compute(k2,(key, value) -> (value == null) ? 1 : value + 1);   
        }

        buffer.close();

        int val = 0;

        for(int k : list){
            int v = hash.getOrDefault(k,0);
            val += k*v;
        }

        return val;
    }
    public static void main(String[] args){
        try{
            int res = calSimilarityScore("prb1_input.txt");
            System.out.print("Similarity Score : "+res);
        }catch(IOException e){
            System.out.print("Error Occured "+e.getMessage());
        }
    }
}


