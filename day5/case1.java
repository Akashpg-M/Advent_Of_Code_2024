// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.HashSet;

// public class case1 {
//     public static void main(String[] args){
//         try{
//             int res = getVal("input.txt");
//             System.out.println("result : "+res);
//         }catch(IOException e){
//             System.out.println("Error : "+e.getMessage());
//         }
//     }


//     public static int getVal(String filePath) throws IOException{
//         int count = 0;
//         HashMap<String,String> ordering =  new HashMap<>();
        
//         BufferedReader b = new BufferedReader(new FileReader(filePath));
//         String line;
//         while(!(line = b.readLine()).isEmpty()){
//             String[] order = line.split("\\|");
//             ordering.put(order[1], order[0]);
//         }

//         while((line = b.readLine()) !=null){
//             boolean flag = true;
//             HashSet<String> numChk = new HashSet<>();
//             String[] nums = line.split(",");
//             for(String c: nums){
//                 numChk.add(c);
//                 if(ordering.containsKey(c) && !numChk.contains(ordering.get(c))){
//                     flag = false;
//                     break;
//                 }
//             }
//             if(flag){
//                 String midVal = nums[nums.length/2].trim();
//                 count += Integer.parseInt(midVal);
//             };
//         }
//         b.close();
//         return count;
//     }
// }

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class case1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        List<String> rules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();
        String line;

        // Read the rules from the input until an empty line is encountered
        Map<Integer, List<Integer>> beforeMap = new HashMap<>();
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] parts = line.split("\\|");
            int before = Integer.parseInt(parts[0].trim());
            int after = Integer.parseInt(parts[1].trim());

            beforeMap.putIfAbsent(after, new ArrayList<>());
            beforeMap.get(after).add(before);
            rules.add(line);
        }

        // Read the updates after the empty line
        while ((line = br.readLine()) != null) {
            List<Integer> update = new ArrayList<>();
            String[] pages = line.split(",");
            for (String page : pages) {
                update.add(Integer.parseInt(page));
            }
            updates.add(update);
        }

        // Close the file
        br.close();

        int sumMiddlePages = 0;

        // Process each update
        for (List<Integer> update : updates) {
            if (isValidOrder(update, beforeMap)) {
                int middleIndex = update.size() / 2;
                sumMiddlePages += update.get(middleIndex);
            }
        }

        System.out.println("The sum of the middle page numbers is: " + sumMiddlePages);
    }

    public static boolean isValidOrder(List<Integer> update, Map<Integer, List<Integer>> beforeMap) {
        // Keep track of the index positions of each page in the update
        Map<Integer, Integer> pageIndex = new HashMap<>();
        for (int i = 0; i < update.size(); i++) {
            pageIndex.put(update.get(i), i);
        } 

        // Check if the update order satisfies all the beforeMap rules
        for (Integer page : pageIndex.keySet()) {
            if (beforeMap.containsKey(page)) {
                List<Integer> beforePages = beforeMap.get(page);
    
                // Ensure all prerequisite pages appear before the current page in the update
                for (int before : beforePages) {
                    if (pageIndex.containsKey(before) && pageIndex.get(before) > pageIndex.get(page)) {
                        // The prerequisite appears after the current page, invalid order
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

