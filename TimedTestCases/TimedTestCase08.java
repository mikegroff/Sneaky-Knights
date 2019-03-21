// Sean Szumlanski
// COP 3503, Fall 2017

// ==============================
// SneakyKnights: TestCase08.java
// ==============================
// A very large test case in which none of the knights can attack one another.


import java.io.*;
import java.util.*;

public class TimedTestCase08
{
	private static void checkTest(boolean success)
	{
		System.out.println(success ? "Hooray!" : "fail whale :(");
	}

	public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(new File("input_files/TestCase08-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		System.out.println("Number of knights: " + list.size());
		
		for(int i = 0; i < 50; i++)
		{
			long totalTime = 0;
			final long start = System.nanoTime();
			checkTest(SneakyKnights.allTheKnightsAreSafe(list, Integer.MAX_VALUE) == true);
			final long end = System.nanoTime();
			totalTime = (end - start);
			System.out.println("Time: " + (totalTime / 1000000.0) + "ms");
		}
	}
}