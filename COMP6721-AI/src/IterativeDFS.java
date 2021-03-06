
import java.util.ArrayList;
import java.util.Stack;
//import java.util.Collections;
/**
 * Defines a Depth-First search to be performed on a qualifying puzzle.
 */
public class IterativeDFS
{
	/**
	 * Initialization function for 11-d puzzle DFSearch
	 * 
	 * @param board
	 */
	public static void search(int[] board, long d)
	{
		Stack<SearchNode> stack = new Stack<SearchNode>();

		performSearch(stack, d, board);
	}

	/*
	 * Helper method to check to see if a SearchNode has already been evaluated.
	 * Returns true if it has, false if it hasn't.
	 */
	private static boolean checkRepeats(SearchNode n)
	{
		boolean retValue = false;
		SearchNode checkNode = n;

		// While n's parent isn't null, check to see if it's equal to the node
		// we're looking for.
		while (n.getParent() != null && !retValue)
		{
			if (n.getParent().getCurState().equals(checkNode.getCurState()))
			{
				retValue = true;
			}
			n = n.getParent();
		}

		return retValue;
	}

	/**
	 * Performs a DFSearch using q as the search space
	 * 
	 * @param s - A SearchNode queue to be populated and searched
	 */
	public static void performSearch(Stack<SearchNode> s, long d, int[] board)
	{
	int searchCount = 0; // counter for number of iterations
	int traversinNodeCollection = 0;
	while(true)
	{
		SearchNode root = new SearchNode(new ElevenPuzzleState(board));
		s.add(root);
		while (!s.isEmpty()) // while the queue is not empty
		{
			traversinNodeCollection ++;
			SearchNode tempNode = (SearchNode) s.pop();

			// if tempNode is not the goal state
			if (!tempNode.getCurState().isGoal())
			{
				// generate tempNode's immediate successors
				ArrayList<State> tempSuccessors = tempNode.getCurState()
						.genSuccessors();
				if(tempNode.getCurState().getDepthVal() < searchCount)
				{
					for (int i = 0; i < tempSuccessors.size(); i++)
					{
						SearchNode newNode = new SearchNode(tempNode,
								tempSuccessors.get(i), tempNode.getCost()
										+ tempSuccessors.get(i).findCost(), 0);
						if (!checkRepeats(newNode))
						{
							s.add(newNode);
						}
					}
				}
			}
			else
			// The goal state has been found. Print the path it took to get to
			// it.
			{
				// Use a stack to track the path from the starting state to the
				// goal state
				tempNode.getCurState().reportSolutionPath(tempNode, traversinNodeCollection, "puzzleIDFS", d);
				// Stack<SearchNode> goalPath = new Stack<SearchNode>();
				// goalPath.push(tempNode);
				// tempNode = tempNode.getParent();

				// while (tempNode.getParent() != null)
				// {
				// 	goalPath.push(tempNode);
				// 	tempNode = tempNode.getParent();
				// }
				// goalPath.push(tempNode);
				// tempNode.getCurState().loadDataintoFile("puzzleDFS" , goalPath);
				// // // The size of the stack before looping through and emptying it.
				// // int loopSize = goalPath.size();

				// // for (int i = 0; i < loopSize; i++)
				// // {
				// // 	tempNode = goalPath.pop();
				// // 	//tempNode.getCurState().printState();
				// // 	tempNode.getCurState().printStatePuzzleFormat();
				// // 	// System.out.println();
				// // 	// System.out.println();
				// // }
				// System.out.println("The cost was: " + tempNode.getCost());
				// if (d)
				// {
				// 	System.out.println("The number of nodes examined: "
				// 			+ searchCount);
				// }

				// System.exit(0);
			}
		}
		searchCount++;
		//System.out.println("Error! No solution found!");
	}
		// This should never happen with our current puzzles.
		
	}
}

