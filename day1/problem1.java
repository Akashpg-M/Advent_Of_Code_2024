import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class problem1{

    public static int EvaluateOrderedDiff(String filePath) throws IOException {
        ArrayList<Integer> col1 = new ArrayList<>();
        ArrayList<Integer> col2 = new ArrayList<>();

        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = buffer.readLine())!= null){
            String[] column = line.split("\\s+");

            col1.add(Integer.parseInt(column[0].trim()));
            col2.add(Integer.parseInt(column[1].trim()));
        }

        buffer.close();
        Collections.sort(col1);
        Collections.sort(col2);

        int val = 0;
        for(int i = 0; i<col1.size(); i++){
            val+= Math.abs(col1.get(i) - col2.get(i));
        }

        return val;
    }
    public static void main(String[] args){
        
        try{
            int res = EvaluateOrderedDiff("prb1_input.txt");
            System.out.print("result of diff : "+res);
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
}


