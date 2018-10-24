import java.util.ArrayList;
/**
 * 
 * State interface from which problem states inherit. Defines a method to check
 * if the current state is a goal, generate successors, and find the cost to
 * come to the current state.
 * 
 */
public interface State
{
	// determine if current state is goal
	boolean isGoal();

	// generate successors to the current state
	ArrayList<State> genSuccessors();

	// determine cost from initial state to THIS state
	double findCost();


	// print the current state into array format

	public void printStatePuzzleFormat();

	// Retrieve the current sate format
	String getStatePuzzleFormat();

	// compare the actual state data
	public boolean equals(State s);

	//report the solution path access recursively
	public void reportSolutionPath(SearchNode tempNode, int searchCount, String fileName, long stime);

	int getPirority();

	int getOutOfPlace();

	int getEculideanist();

	int getDepthVal();
}
