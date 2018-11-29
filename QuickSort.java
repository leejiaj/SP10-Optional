/**
 * @author Leejia James
 *
 * Divide and conquer algorithms
 * Compared the performance of the two versions of partition discussed in class
 * on the running time of Quick sort, on arrays with distinct elements.
 * Tried arrays that are randomly ordered (by shuffle) and arrays in
 * descending order.
 *
 * Ver 1.0: 2018/11/10
 */

package lxj171130;

import java.util.Random;

public class QuickSort {
	
	public static Random random = new Random();
	
    public static void main(String[] args) {
	int n = 10;  int choice = 1;
	if(args.length > 0) { n = Integer.parseInt(args[0]); }
	if(args.length > 1) { choice = Integer.parseInt(args[1]); }
    int[] arr = new int[n];
    int[] arr1 = new int[n];
    for(int i=0; i<n; i++) {
	    arr[i] = i;
	    arr1[i] = n - i - 1;
	}
    
	Timer timer = new Timer();
	switch(choice) {
	case 1: // quicksort with partition 1 on randomly ordered array 
	    Shuffle.shuffle(arr);
	    quickSort1(arr);
	    break;
	case 2: // quicksort with partition (Hoare) on randomly ordered array
	    Shuffle.shuffle(arr);
	    quickSort2(arr);
	    break;
	case 3: // quicksort with partition 1 on descending order array
	    quickSort1(arr1);
	    break;
	case 4: // quicksort with partition (Hoare) descending order array
	    quickSort2(arr1);
	    break;
	}
	timer.end();

	System.out.println("Choice: " + choice + "\n" + timer);
    }
	
	/**
	 * quicksort with partition 1
	 * @param arr
	 */
	public static void quickSort1(int[] arr) {
		quickSortOne(arr, 0, arr.length-1);
	}

	/**
	 * quicksort with partition 1 - recursive method
	 * @param arr
	 * @param p
	 * @param r
	 */
	private static void quickSortOne(int[] arr, int p, int r) {
		if(p < r) {
			int q = randomizedPartition(arr, p, r, 1);
			quickSortOne(arr, p, q-1);
			quickSortOne(arr, q+1, r);
		}
	}

	/**
	 * Randomized partition 
	 * @param arr
	 * @param p
	 * @param r
	 * @param version
	 * @return
	 */
	private static int randomizedPartition(int[] arr, int p, int r, int version) {
		int i = random.nextInt(r+1);
		int temp = arr[i];
		arr[i] = arr[r];
		arr[r] = temp;
		if(version == 1) {
			return partitionOne(arr, p, r);
		}
		else {
			return partitionTwo(arr, p, r);
		}
	}

	/**
	 * Implementing Partition 1 
	 * @param arr
	 * @param p
	 * @param r
	 * @return
	 */
	private static int partitionOne(int[] arr, int p, int r) {
		int x = arr[r];
		int i = p - 1;
		//LI: arr[p..i] <= x, arr[i+1..j-1] > x, arr[j..r-1] is unprocessed,
		//arr[r] = x
		for(int j=p; j<r-1; j++) {
			if(arr[j] <= x) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;				
			}
		}

		int temp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = temp;
		
		return i+1;
	}
	
	/**
	 * quicksort with partition (Hoare)
	 * @param arr
	 */
	public static void quickSort2(int[] arr) {
		quickSortTwo(arr, 0, arr.length-1);
	}
	
	/**
	 * quicksort with partition (Hoare) - recursive method
	 * @param arr
	 * @param p
	 * @param r
	 */
	private static void quickSortTwo(int[] arr, int p, int r) {
		if(p < r) {
			int q = randomizedPartition(arr, p, r, 2);
			quickSortTwo(arr, p, q);
			quickSortTwo(arr, q+1, r);
		}
	}

	/**
	 * Implementing partition (Hoare)
	 * @param arr
	 * @param p
	 * @param r
	 * @return
	 */
	private static int partitionTwo(int[] arr, int p, int r) {
		int x = arr[r];
		int i = p - 1;
		int j = r + 1;
		// LI: arr[p..i] <= x, arr[j..r] >= x
		while(true) {
			do {
				i++;
			}while(arr[i] < x);
			do {
				j--;
			}while(arr[j] > x);
			if(i >= j) {
				return j;
			}
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}
	

	   /** Timer class for roughly calculating running time of programs
	     *  @author rbk
	     *  Usage:  Timer timer = new Timer();
	     *          timer.start();
	     *          timer.end();
	     *          System.out.println(timer);  // output statistics
	     */

	    public static class Timer {
	        long startTime, endTime, elapsedTime, memAvailable, memUsed;
	        boolean ready;

	        public Timer() {
	            startTime = System.currentTimeMillis();
	            ready = false;
	        }

	        public void start() {
	            startTime = System.currentTimeMillis();
	            ready = false;
	        }

	        public Timer end() {
	            endTime = System.currentTimeMillis();
	            elapsedTime = endTime-startTime;
	            memAvailable = Runtime.getRuntime().totalMemory();
	            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
	            ready = true;
	            return this;
	        }

	        public long duration() { if(!ready) { end(); }  return elapsedTime; }

	        public long memory()   { if(!ready) { end(); }  return memUsed; }
		
	        public String toString() {
	            if(!ready) { end(); }
	            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
	        }
	    }
	    
	    /** @author rbk : based on algorithm described in a book
	     */


	    /* Shuffle the elements of an array arr[from..to] randomly */
	    public static class Shuffle {
		
		public static void shuffle(int[] arr) {
		    shuffle(arr, 0, arr.length-1);
		}

		public static<T> void shuffle(T[] arr) {
		    shuffle(arr, 0, arr.length-1);
		}

		public static void shuffle(int[] arr, int from, int to) {
		    int n = to - from  + 1;
		    for(int i=1; i<n; i++) {
			int j = random.nextInt(i);
			swap(arr, i+from, j+from);
		    }
		}

		public static<T> void shuffle(T[] arr, int from, int to) {
		    int n = to - from  + 1;
		    Random random = new Random();
		    for(int i=1; i<n; i++) {
			int j = random.nextInt(i);
			swap(arr, i+from, j+from);
		    }
		}

		static void swap(int[] arr, int x, int y) {
		    int tmp = arr[x];
		    arr[x] = arr[y];
		    arr[y] = tmp;
		}
		
		static<T> void swap(T[] arr, int x, int y) {
		    T tmp = arr[x];
		    arr[x] = arr[y];
		    arr[y] = tmp;
		}

		public static<T> void printArray(T[] arr, String message) {
		    printArray(arr, 0, arr.length-1, message);
		}

		public static<T> void printArray(T[] arr, int from, int to, String message) {
		    System.out.print(message);
		    for(int i=from; i<=to; i++) {
			System.out.print(" " + arr[i]);
		    }
		    System.out.println();
		}
	    }
	
}
