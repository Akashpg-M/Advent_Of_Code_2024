import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class case2 {
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

    public static boolean isRemoveLevelSafe(int[] data){
        for(int i=0; i<data.length; i++){
            int[] temp = new int[data.length -1];
            System.arraycopy(data, 0, temp, 0, i);
            System.arraycopy(data, i+1, temp, i, data.length-i-1);
            if(isAscending(temp) || isDescending(temp)){
                return true;
            }
        }
        return false;
    }

    public static boolean isSafe(int[] data){
        if(isAscending(data) || isDescending(data)){
            return true;
        }
        if(isRemoveLevelSafe(data)){
            return true;
        }
        return false;
    }

    public static int countSafeData(String filePath) throws IOException{
        BufferedReader buffer = new BufferedReader(new FileReader(filePath));

        int count = 0;
        String line;
        while((line = buffer.readLine()) != null){
            String[] temp = line.split("\\s+");
            int[] data = new int[temp.length];

            for(int i = 0; i<temp.length; i++){
                data[i] = Integer.parseInt(temp[i]);
            }

            if(isSafe(data)) count++;
        }

        return count;
    }

    public static void main(String[] args){
        try{
            System.out.println("Safe data : "+countSafeData("input.txt"));
        }catch(IOException e){
            System.out.println("File Error : "+e.getMessage());
        }
        
    }
}
