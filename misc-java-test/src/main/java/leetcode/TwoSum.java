package leetcode;

import java.util.HashMap;
import java.util.Map;


public class TwoSum {
	
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
        	return null;
        }
        
        if (nums.length == 2) {
        	if (nums[0] + nums[1] == target) {
        		return new int[] { 1, 2 };
        	}
        }
        
        // val : indice1,2
        final int NOT_SET = -1;
        int n;
        Map<Integer, int[]> unique = new HashMap<Integer, int[]>(nums.length);
        for (int i = 0; i < nums.length; i++) {
        	n = nums[i];
        	if (!unique.containsKey(n)) {
        		int[] idx = new int[] { i + 1, NOT_SET };
        		unique.put(n, idx);
        	} else {
        		int[] idx = unique.get(n);
        		if (idx[1] == -1) {
        			idx[1] = i + 1;
        		}
        	}
        }
        
        for (Integer part1 : unique.keySet()) {
        	
        	int part2 = target - part1;
        	if (part1 == part2) {
        		int[] idx = unique.get(part2);
        		if (idx[1] != NOT_SET) {
        			return idx;
        		}
        	} else {
            	if (unique.containsKey(part2)) {
            	    int x = unique.get(part1)[0];
            	    int y = unique.get(part2)[0];
            	    if (x <= y) {
                 		return new int[] { x, y };
            	    } else {
            	    	return new int[] { y, x };
            	    }
            	}
        	}
        }
        
    	
    	return null;
    	
    }    
    
    public static void main(String[] args) {
		int[] numbers = {2, 11, 5, 5, 0};
		int target = 10;
		
		TwoSum ts = new TwoSum();
    	int[] idx = ts.twoSum(numbers, target);
    	if (idx != null) {
    		System.out.println("" + idx[0] + ", " + idx[1]);
    	} else {
    		System.out.println("Not found");
    	}
	}
}
