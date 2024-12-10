import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util. ArrayList;

public class case1 {
    public static void main(String[] args){
        try{
            Map<Long, List<Long>> equation = getInputFromFile("input.txt");
            Long res = sumValidEquation(equation);
            System.out.println("Result : "+res);
        }catch(IOException e){
            System.out.println("Error "+ e.getMessage());
        }
    }

    public static Map<Long, List<Long>> getInputFromFile(String filePath) throws IOException{
        Map<Long, List<Long>> equation = new HashMap<>();

        BufferedReader buf = new BufferedReader(new FileReader(filePath));
        String line;
        
        while((line = buf.readLine()) != null){
            String[] eqnPart = line.split(":");
            
            List<Long> numList = new ArrayList<>();

            Long val = Long.parseLong(eqnPart[0].trim());
            String[] num = eqnPart[1].trim().split("\\s+");

            for(String n : num){
                numList.add(Long.parseLong(n));
            }

            equation.put(val, numList);
        }

        buf.close();
        return equation;
    }

    public static Long sumValidEquation(Map<Long, List<Long>> equation){
        Long sumValidSet = 0L;
        List<Character> operators = List.of('+', '*');

        for(Map.Entry<Long, List<Long>> e : equation.entrySet()){
            Long val = e.getKey();
            List<Long> nums = e.getValue();

            List<List<Character>> operCombo = new ArrayList<>();

            operComboList(operators, nums.size()-1, new ArrayList<>(), operCombo);

            for(List<Character> oper : operCombo){
                if(isValidCombo(val, nums, oper)){
                    sumValidSet += val;
                    break;
                }
            }
        }
        return sumValidSet;
    }

    public static void operComboList(List<Character> oper, int size, List<Character> current, List<List<Character>> combo){
        if(current.size() == size){
            combo.add(new ArrayList<>(current));
            return;
        }
        for(Character op : oper){
            current.add(op);
            operComboList(oper, size, current, combo);
            current.remove(current.size()-1);
        }
    }

    public static boolean isValidCombo(Long val, List<Long> nums, List<Character> operators){
        Long value = nums.get(0);
        for(int i=0; i<operators.size(); i++){
            value = operate(value, nums.get(i+1), operators.get(i));
        }
        return value.equals(val);
    }

    public static Long operate(Long a, Long b, Character op){
        if(op == '+') return a+b;
        else return a*b;
    }
}