import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS {

	List<String> So,GoalState = new ArrayList<>();
	String[] TilePosition = {"a","b","c","d","e","f","g","h","j","k","l"};

	public DFS(List<String> so, List<String> goalState) {
		super();
		this.So = so;
		this.GoalState = goalState;
	
		System.out.println("HERE" + this.So);
		
		if (So.contains(GoalState)){
			System.out.println("Goal State is found");
			System.exit(0);
		}
		GenerateChilds GC = new GenerateChilds(So);
	}
	

	

	
	
}
