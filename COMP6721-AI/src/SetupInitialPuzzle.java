import java.util.List;
import java.util.Scanner;

import javax.jws.soap.SOAPBinding;

public class SetupInitialPuzzle {

	
	public void initialPuzzle(List<String> So) {
		
		System.out.println("Please enter the So state of the Puzzle");
		Scanner sc = new Scanner(System.in);
		
		
		
		for(int i = 0; i<=11;i++) {
		
			So.add(i,sc.next());
		}
		
		System.out.println("Initital state So is :" + So);
		
	}
	
}
