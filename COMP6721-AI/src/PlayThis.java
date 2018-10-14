import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayThis {

	
	public static void main(String[] args) {
	
		// TODO Auto-generated method stub
		   List<String> GoalState = new ArrayList<String>();
		   List<String> So = new ArrayList<String>();
		
		GoalState.add(0, "1, 2, 3, 4, 5, 6, 7, 8 , 9, 10, 11, 0");
		
		System.out.println("Welcome to 11d Puzzle");

		SetupInitialPuzzle pz = new SetupInitialPuzzle();
		
		pz.initialPuzzle(So);	
		
		selectingAlgo(So,GoalState);
		
	
	}

	
	private static void selectingAlgo(List<String> so, List<String> goalState) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Select Algorithm to Play puzzle");
		String selection = sc.next();
		
		switch (selection){
			case "a":
				DFS dfs = new DFS(so, goalState);
		
				break;
			case "b":
				break;
			case "c":
				break;
			case "d":
				break;
			case "e":
				break;
				default:
					System.out.println("Not a valid case, Please retry");
					return;
		}
	}


	
}
