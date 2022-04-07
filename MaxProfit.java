import java.io.*;
import java.util.*;

public class MaxProfit {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		String types = in.nextLine();
		String[] typesArray = types.split(" ");
		int n = typesArray.length;
		
		int[] aTimes = new int[n];
		int[] bTimes = new int[n];
		int[] profits = new int[n];
		int[] arrivalTimes = new int[n];
		
		int[][] products = new int[n][3];
		
		for (int i=0; i<n; i++) {
			int aTime = in.nextInt();
			aTimes[i] = aTime;
		}
		
		for (int i=0; i<n; i++) {
			int bTime = in.nextInt();
			bTimes[i] = bTime;
		}
		
		for (int i=0; i<n; i++) {
			int profit = in.nextInt();
			profits[i] = profit;
		}
		
		for (int i=0; i<n; i++) {
			int arrivalTime = in.nextInt();
			arrivalTimes[i] = arrivalTime;
		}
		
		//setting up the 2d array of the products, where each product has (start time, end time, profit)
		for (int i=0; i<n; i++) {
			products[i][0] = arrivalTimes[i];
			products[i][2] = profits[i];
			if (typesArray[i].equals("s"))
				products[i][1] = arrivalTimes[i] + aTimes[i];
			else
				products[i][1] = arrivalTimes[i] + bTimes[i];
		}
		
		//sorting the products array with respect to end times, in ascending order
		Arrays.sort(products,(p,r) -> p[1] - r[1]); 
		
		//The treemap which holds end times and profits, sorted by end times. This helps us find the last job that can be completed before a certain time.
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		//(0,0) has to be in the map as it is the least possible profit we can get. It helps during comparison.
		map.put(0, 0);
		
		//The iteration, adds calculated profits to the treemap if they are greater than the maps last entry.
		for (int i=0; i<n; i++) {
			int[] prod = products[i];
			int prof = prod[2] + map.floorEntry(prod[0]).getValue();
			if (prof>map.lastEntry().getValue()) {
				map.put(prod[1], prof);
			}
		}
		
		out.print(map.lastEntry().getValue());
	}

}
