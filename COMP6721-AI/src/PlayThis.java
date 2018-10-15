import java.util.Scanner;
public class PlayThis
{
	
	public static int[] initState = new int[12];
	public static void main(String[] args)
	{

		
		boolean debug = false;
		char Val;

		System.out.println("Welcome to 11d Puzzle");
		
		System.out.println("Please enter the So state of the Puzzle");
		initialPuzzle();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select Algorithm to Play puzzle");
		System.out.println("\t1. DFS \n\t"
				+ "3. BFS \n\t"
				+ "4. A* \n\t");
		
			String selector = scanner.nextLine();
			switch(selector){
				case "1" : 
					DFSearch.search(initState, debug);
				break;
				
				case "2":
					// dfsIterateSearch(scanner.nextLine(), startTime);
				break;
				
				case "3" : 
					Val = SelectHeuristic();
					BFSearch.search(initState, debug, Val);
				break;

				case "4" : 
					Val = SelectHeuristic();
					AStarSearch.search(initState, debug, Val);
				break;
				
				default:
				 System.out.println("Not a valid case, Please retry");
				 System.exit(0);
			}
	
	}

	public static int[] initialPuzzle() {
		
		Scanner sc = new Scanner(System.in);
		
		for (int i = 0; i <= 11; i++)
		{
			initState[i] = sc.nextInt();
			//System.out.println(i-d);
		}
		
		return initState;
		
	}
	
	public static char SelectHeuristic() {
		System.out.println("Choose the Heuristic function.\n a.Misplaced Tiles(h1) \n b.Euclidian Distance(h2)\n");
		
		Scanner scanner = new Scanner(System.in);
		String _heuristicVal = scanner.nextLine();
		
		
		
		char value;
		if(_heuristicVal.equals("h1")){
			value = 'o';
		}else{
			value = 'e';
		}
		scanner.close();
		return value;
	}

}
