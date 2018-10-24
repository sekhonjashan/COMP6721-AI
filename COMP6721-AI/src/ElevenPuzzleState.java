import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
/**
 * 
 * ElevenPuzzleState defines a state for the 11-d puzzle problem. In
 * terms of the actual tiles, '0' represents the hole in the board, and 0 is
 * treated special when generating successors. We do not treat '0' as a tile
 * itself, it is the "hole" in the board
 * 
 */
public class ElevenPuzzleState implements State
{
	private final int PUZZLE_SIZE = 12;

	private char[] ALPHABETIC = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l'};

	private int outOfPlace = 0;

	private int ecdDist = 0;

	private final int[] GOAL = new int[]
	{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0};
	private int[] curBoard;
	private String config;
	private int priority;
	private int depthNode;

	/**
	 * Constructor for ElevenPuzzleState
	 * 
	 * @param board - the board representation for the new state to be constructed
	 */
	public ElevenPuzzleState(int[] board)
	{
		curBoard = board;
		config = "0 ";
		priority = 0;
		depthNode = 0;
		setOutOfPlace();
		setEculideanDist();
	}

	public ElevenPuzzleState(int[] board, String _config, int _priority, int _deep)
	{
		curBoard = board;
		config = _config;
		priority = priority;
		depthNode = _deep;
		setOutOfPlace();
		setEculideanDist();
	}

	/**
	 * How much it costs to come to this state
	 */
	@Override
	public double findCost()
	{
		return 1;
	}

	@Override
	public int getDepthVal()
	{
		return depthNode;
	}

	@Override
	public int getPirority(){
		return priority;
	}
	/*
	 * Set the 'tiles out of place' distance for the current board
	 */
	private void setOutOfPlace()
	{
		for (int i = 0; i < curBoard.length; i++)
		{
			if (curBoard[i] != GOAL[i])
			{
				outOfPlace++;
			}
		}
	}

	/*
	 * Set the eculideanDist Distance for the current board
	 */
	private void setEculideanDist()
	{
		// linearly search the array independent of the nested for's below
		int index = -1;

		// just keeps track of where we are on the board (relatively, can't use
		// 0 so these
		// values need to be shifted to the right one place)
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				index++;

				// sub 1 from the val to get the index of where that value
				// should be
				int val = (curBoard[index] - 1);

				/*
				 * If we're not looking at the hole. The hole will be at
				 * location -1 since we subtracted 1 before to turn val into the
				 * index
				 */
				if (val != -1)
				{
					// Horizontal offset, mod the tile value by the horizontal
					// dimension
					int horiz = val % 4;
					// Vertical offset, divide the tile value by the vertical
					// dimension
					int vert = val / 4;

					ecdDist += (int)Math.sqrt(Math.pow(vert - (y),2) + Math.pow(horiz - (x),2));
				}
				// If we are looking at the hole, skip it
			}
		}
	}

	/*
	 * Attempt to locate the "0" spot on the current board
	 * 
	 * @return the index of the "hole" (or 0 spot)
	 */
	private int getHole()
	{
		// If returning -1, an error has occured. The "hole" should always exist
		// on the board and should always be found by the below loop.
		int holeIndex = -1;

		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			if (curBoard[i] == 0)
				holeIndex = i;
		}
		return holeIndex;
	}

	/**
	 * Getter for the outOfPlace value
	 * 
	 * @return the outOfPlace h(n) value
	 */
	@Override
	public int getOutOfPlace()
	{
		return outOfPlace;
	}

	/**
	 * Getter for the eculideanDist Distance value
	 * 
	 * @return the eculideanDist Distance h(n) value
	 */
	@Override
	public int getEculideanist()
	{
		return ecdDist;
	}

	/*
	 * Makes a copy of the array passed to it
	 */
	private int[] copyBoard(int[] state)
	{
		int[] ret = new int[PUZZLE_SIZE];
		for (int i = 0; i < PUZZLE_SIZE; i++)
		{
			ret[i] = state[i];
		}
		return ret;
	}

	/**
	 * Is thought about in terms of NO MORE THAN 12 operations. Can slide tiles
	 * from 12 directions
	 * 
	 * @return an ArrayList containing all of the successors for that state
	 */
	@Override
	public ArrayList<State> genSuccessors()
	{
		ArrayList<State> successors = new ArrayList<State>();
		int hole = getHole();
		int _deepNode = depthNode;
		// if we CAN slide into the hole
		if (hole-4 > 0)
		{
			/*
			 * we can slide "upwise" into the hole, so generate a new state for
			 * this condition and throw it into successors
			 */
			switchAndStore(hole - 4, hole, successors, 1, (_deepNode + 1));
		}
		// try to generate a state by sliding a tile "up-right" into the hole
		if (hole != 7 && hole != 11 && (hole - 3) > 0)
		{
			switchAndStore(hole - 3, hole, successors, 2, (_deepNode + 1));
		}
		// try to generate a state by sliding a tile "right" into the hole
		if (hole != 3 && hole != 7 && hole != 11)
		{
			switchAndStore(hole + 1, hole, successors, 3, (_deepNode + 1));
		}
		// try to generate a state by sliding a tile "down-right" into the hole
		if (hole != 3 && hole != 7 && hole + 5  < 12)
		{
			switchAndStore(hole + 5, hole, successors, 4, (_deepNode + 1));
		}
		// try to generate a state by sliding a tile "down" into the hole
		if (hole + 4 < 12)
		{
			switchAndStore(hole + 4, hole, successors, 5, (_deepNode + 1));
		}

		// try to generate a state by sliding a tile "down-left" into the hole
		if (hole != 0 && hole != 4 && hole != 8 && hole +3 < 12)
		{
			switchAndStore(hole + 3, hole, successors, 6, (_deepNode + 1));
		}

		// try to generate a state by sliding a tile "left" into the hole
		if (hole != 0 && hole != 4 && hole != 8)
		{
			switchAndStore(hole - 1, hole, successors, 7, (_deepNode + 1));
		}
		// try to generate a state by sliding a tile "up-left" into the hole
		if (hole != 4 && hole != 8 && hole-5  > 0)
		{
			switchAndStore(hole - 5, hole, successors, 8, (_deepNode + 1));
		}
		//System.out.println("ini Puzzle _deepNode"  + _deepNode);
		return successors;
	}

	/*
	 * Switches the data at indices d1 and d2, in a copy of the current board
	 * creates a new state based on this new board and pushes into s.
	 */
	private void switchAndStore(int d1, int d2, ArrayList<State> s, int priority, int _deepNode)
	{
		int[] cpy = copyBoard(curBoard);
		int temp = cpy[d1];
		cpy[d1] = curBoard[d2];
		cpy[d2] = temp;
		s.add((new ElevenPuzzleState(cpy, ALPHABETIC[d1]+" ", priority, _deepNode)));
	}

	/**
	 * Check to see if the current state is the goal state.
	 * 
	 * @return - true or false, depending on whether the current state matches
	 *         the goal
	 */
	@Override
	public boolean isGoal()
	{
		if (Arrays.equals(curBoard, GOAL))
		{
			return true;
		}
		return false;
	}

	
	/**
	 * Method to print out the current state. Prints the puzzle into array format.
	 */
	@Override
	public void printStatePuzzleFormat()
	{
		StringBuilder _format =new StringBuilder();
		_format.append(config);
		_format.append("[");
		for(int i = 0; i<curBoard.length ;i++)
		{
			if(i+1 == curBoard.length){
				_format.append(curBoard[i]);
			}else{
				_format.append(curBoard[i] + ", ");
			}
		}
		String result=_format.toString();
		result += "]";
		System.out.println(result);
	}

	/**
	 * Overloaded equals method to compare two states.
	 * 
	 * @return true or false, depending on whether the states are equal
	 */
	@Override
	public boolean equals(State s)
	{
		if (Arrays.equals(curBoard, ((ElevenPuzzleState) s).getCurBoard()))
		{
			return true;
		}
		else
			return false;

	}

	/**
	 * Getter to return the current board array
	 * 
	 * @return the curState
	 */
	public int[] getCurBoard()
	{
		return curBoard;
	}

	/**
	 * Getter to return the current board array
	 * 
	 * @return the curState
	 */
	public String getStatePuzzleFormat()
	{
		StringBuilder _format =new StringBuilder();
		_format.append(config);
		_format.append("[");
		for(int i = 0; i<curBoard.length ;i++)
		{
			if(i+1 == curBoard.length){
				_format.append(curBoard[i]);
			}else{
				_format.append(curBoard[i] + ", ");
			}
		}
		String _dataFormat = _format.toString();
		_dataFormat += "]";
		System.out.println(_dataFormat);
		return _dataFormat;
	}
/**
	 * Method to print out the current state. Prints the puzzle into array format.
	 */
	public void loadDataintoFile(String fname , Stack<SearchNode> goalPath) {
				FileWriter _fileReader = null;
				BufferedWriter _bufferWriter = null;
				try{
					 _fileReader = new FileWriter(new File(fname));
					_bufferWriter = new BufferedWriter(_fileReader);
					for (int i =  goalPath.size()-1; i >=0; i--)
					{
							SearchNode tempNode = (SearchNode) goalPath.get(i);
							_bufferWriter.write(tempNode.getCurState().getStatePuzzleFormat() + "\n");
					}

					System.out.println("Go to File "+ fname);
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						_bufferWriter.close();
						_fileReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}

	/**
	 * Method to print out the reportSolutionPath . Writing data into the puzzle into array format.
	 */
	 @Override
		public void reportSolutionPath(SearchNode tempNode, int searchCount, String fileName, long  stime){
			Stack<SearchNode> goalPath = new Stack<SearchNode>();
				goalPath.push(tempNode);
				tempNode = tempNode.getParent();
				if(tempNode == null) {
					System.out.println("Hurray Goal state !!");
					System.exit(-1);
				}
				while (tempNode.getParent() != null)
				{
					goalPath.push(tempNode);
					tempNode = tempNode.getParent();
				}
				goalPath.push(tempNode);
				loadDataintoFile(fileName, goalPath);
	
				System.out.println("Solution cost: =" + goalPath.size());
				System.out.println("Search path cost: ="
							+ searchCount);
				long etime   = System.currentTimeMillis();
				long totalTime = etime - stime;
					System.out.println("Performance (in ms) :" + totalTime);

				System.exit(0);
		}
	}