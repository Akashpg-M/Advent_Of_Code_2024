import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.FileReader;

public class case1 {
    public static int calculateProductSum(String filePath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int res = 0;

        while((line = br.readLine()) != null){
            String regex = "mul\\((\\d+),(\\d+)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while(matcher.find()){
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                res += x*y;
            }
        }
        return res;
    }
    public static void main(String[] args){
        try{
            int res = calculateProductSum("input.txt");
            System.out.println("Result : "+ res);
        }catch(IOException e){
            System.out.println("Error : " + e.getMessage());
        } 
    }
}
