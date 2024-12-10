import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class case2 {

    public static int calProdSum(String filePath) throws IOException{
        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        String line;

        String regEx = "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regEx);

        boolean enableMul = true;
        int res = 0;

        while((line = buffer.readLine()) != null){

            Matcher match = pattern.matcher(line);

            while(match.find()){
                String matchset = match.group();
                if(matchset.equals("do()")){
                    enableMul = true;
                }else if(matchset.equals("don't()")){
                    enableMul = false;
                }else if(matchset.startsWith("mul(") && enableMul){
                    int x = Integer.parseInt(match.group(1));
                    int y = Integer.parseInt(match.group(2));
                    res += x*y;
                }
            }
        }
        return res;
    }
    public static void main(String[] args){
        try{
            int res = calProdSum("input.txt");
            System.out.println("Result : "+ res); 
        }catch(IOException e){
            System.out.println("Error : "+e.getMessage());
        }
        
    }
}
