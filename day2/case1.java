import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class case1{

    public static boolean isAscending(int[] data){
        if(data.length == 1) return true;

        for(int i=1; i<data.length; i++){
            if(data[i-1] >= data[i]){
                return false;
            }
            if(Math.abs(data[i-1] - data[i]) < 1 || Math.abs(data[i-1] - data[i])>3){
                return false;
            }
        }
        return true;
    }

    public static boolean isDescending(int[] data){
        if(data.length == 1) return true;

        for(int i=1; i<data.length; i++){
            if(data[i-1] <= data[i]){ 
                return false;
            }
            if(Math.abs(data[i-1] - data[i]) < 1 || Math.abs(data[i-1] - data[i])>3){
                return false;
            }

        }
        return true;
    }

    public static int countSafeData(String filePath) throws IOException{

        int count = 0;

        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        String line;
        
        while((line = buffer.readLine()) != null){

            String[] temp = line.split("\\s+");

            int[] data = new int[temp.length];
            for(int i=0; i<temp.length; i++){
                data[i] = Integer.parseInt(temp[i]);
            }
            
            if(isAscending(data) || isDescending(data)){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args){
        try{
            int res = countSafeData("input.txt");
            System.out.println("No of Safe Data : "+res);
        }catch(IOException e){
            System.out.println("File not found : "+e.getMessage());
        }
    }
}




