// Sean Szumlanski
// COP 3503, Fall 2017

// ==============================
// SneakyKnights: TestCase06.java
// ==============================
// A large test case in which none of the knights can attack one another.


import java.io.*;
import java.util.*;

public class TimedTestCase06
{
	private static void checkTest(boolean success)
	{
		System.out.println(success ? "Hooray!" : "fail whale :(");
	}

	public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(new File("input_files/TestCase06-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		for(int i = 0; i < 50; i++)
		{
			long totalTime = 0;
			final long start = System.nanoTime();
			checkTest(SneakyKnights.allTheKnightsAreSafe(list, 30000) == true);
			final long end = System.nanoTime();
			totalTime = (end - start);
			System.out.println("Time: " + (totalTime / 1000000.0) + "ms");
		}
		
	}
}
