import java.util.Arrays;

public class Sorting {
	
	public static void main (String[] args) {
		System.out.println("Hello World!");
		int[] nums = {3, -5, 20, 50, 21, 80, 40, 30, 32};
		/*
		boolean sorted = false;
		while (!sorted) {
			int num1 = (int)(Math.random()*nums.length);
			int num2 = (int)(Math.random()*nums.length);
			
			int temp = nums[num1];
			nums[num1] = nums[num2];
			nums[num2] = temp;
			
			boolean finish = true;
			for (int i = 0; i < nums.length-1; i++) {
				if (nums[i] >nums[i+1]) {
				finish = false;
				break;
				}
			}
			if (finish) {break;}
			//swap the two number and check if its sorted ^
		}
		*/
		
		for (int i = 0; i < nums.length; i++) {
			for (int b = 0; b<nums.length-i-1; b++) {
				if (nums[b] > nums[b+1]) {
					int temp = nums[b+1];
					nums[b+1] = nums[b];
					nums[b] = temp;
					//this for loop moves biggest number to end
				}
			}
		}
		System.out.println(Arrays.toString(nums));
	}
}