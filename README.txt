CS 5V81 SP 10: Divide and conquer algorithms

Leejia James

Optional tasks (individual): 
2. Compare the performance of the two versions of partition discussed in class
on the running time of Quick sort, on arrays with distinct elements.
Try arrays that are randomly ordered (by shuffle) and arrays in
descending order.

How to Compile:
1. Unzip contents of lxj171130.zip into a folder named "lxj171130".
2. Run the command `javac lxj171130/*.java` to compile.
3. Run the command `java lxj171130.QuickSort <n> <choice>` to execute the program.

where,
n - Number of elements in the array
choice - 1, 2, 3 or 4
	1 for quickSort (partition 1), array in random order
	2 for quickSort (partition (Hoare)), array in random order
	3 for quickSort (partition 1),array in descending order
	4 for quickSort (partition (Hoare)), array in descending order
