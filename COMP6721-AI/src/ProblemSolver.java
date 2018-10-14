public class ProblemSolver
{
	public static void main(String[] args)
	{
		// Numbers to be adjusted if the debug toggle is present, as components
		// of args will be in different locations if it is.
		int searchTypeDebug = 0;
		int eightPuzzleDebug = 1;
		boolean debug = false;

		// Print out correct usage and end the program if there aren't any
		// parameters
		if (args.length < 1)
		{
			printUsage();
		}

		// Check for debug toggle
		if (args[0].equals("-d"))
		{
			searchTypeDebug = 1;
			eightPuzzleDebug = 2;
			debug = true;
			System.out.println("Search Type passed in: "
					+ args[searchTypeDebug].toLowerCase());
		}

		String searchType = args[searchTypeDebug].toLowerCase();

		if (args.length > 2) // We will run with 8puzzle
		{
			int[] startingStateBoard = dispatchEightPuzzle(args,
					eightPuzzleDebug);

			if (searchType.equals("dfs")) // Use DFSearch.java
			{
				DFSearch.search(startingStateBoard, debug);
			}
			// else if (searchType.equals("bfs")) // Use BFSearch.java
			// {
			// 	BFSearch.search(startingStateBoard, debug);
			// }
			// Use BFSearch.java with number out of place
			else if (searchType.equals("bfso"))
			{
				BFSearch.search(startingStateBoard, debug, 'o');
			}
			// Use BFSearch.java with Eculidean Distance
			else if (searchType.equals("bfse"))
			{
				BFSearch.search(startingStateBoard, debug, 'e');
			}
			// Use AStarSearch.java with number out of place
			else if (searchType.equals("aso"))
			{
				AStarSearch.search(startingStateBoard, debug, 'o');
			}
			// Use AStarSearch.java with Eculidean Distance
			else if (searchType.equals("ase"))
			{
				AStarSearch.search(startingStateBoard, debug, 'e');
			}
			// An invalid searchType has been passed in. Print correct usage and
			// end the program.
			else
			{
				printUsage();
			}
		}
		else
		{
				printUsage();
		}
	}

	// Helper method to print the correct usage and end the program
	private static void printUsage()
	{
		System.out.println("Usage: ./Main <searchType> [Initial Puzzle State]");
		System.exit(-1);
	}

	// Helper method to build our initial 8puzzle state passed in through args
	private static int[] dispatchEightPuzzle(String[] a, int d)
	{
		int[] initState = new int[12];
		// i -> loop counter
		for (int i = d; i < a.length; i++)
		{
			initState[i - d] = Integer.parseInt(a[i]);
			//System.out.println(i-d);
		}
		//System.exit(0);
		return initState;
	}
}
