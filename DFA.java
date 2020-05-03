import java.util.ArrayList;
import java.util.Scanner;


public class DFA {
	
	public ArrayList<String> transitions; 		// This ArrayList contains the states transitions
	public ArrayList<String> acceptStates;      // This String comtains the accept states
	public int currentState;         			// Pointer to the current state
	public boolean result;
	
	/*
	 *   The constructor parses the representation string into the data structures defined above
	 */
	
	public DFA(String representation) {
		
		transitions = new ArrayList<String>();
		acceptStates = new ArrayList<String>();
		result = false;
		currentState = 0; // State 0 the is start state
		
		String prefix = representation.split("#")[0];
		String suffix = representation.split("#")[1];
		
		/*
		 * Skipping the first two indices, which are the state number and ','
		 * 
		 * Given a triple i,j,k where i is the state number, j is the transition of input 0, and k is the transition of input 1,
		 * "j,k" is added to the index i of the ArrayList transitions
		 * 
		 */

		for(int index = 0 ; index < prefix.split(";").length ; index++) {
			transitions.add(prefix.split(";")[index].substring(2,prefix.split(";")[index].length()));
		}
		
		for(int index = 0 ; index < suffix.split(",").length ; index++) {
			acceptStates.add(suffix.split(",")[index]);
		}
		
		PrintTransitions();
		System.out.println("Accept States: " + acceptStates);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nEnter input string: ");
		System.out.println("\n" + Run(scanner.nextLine()));
		
		scanner.close();
		
	}
	
	/*
	 *  Runs the input string against the generated DFA to check if it belongs to the language of the DFA
	 */
	
	public boolean Run(String input) {
		
		System.out.println("\nStart State: " + currentState);
		
		int previousState = 0;
		
		for(int index = 0 ; index < input.length() ; index++) {
			
			// If next letter in input is 0
			
			if(input.substring(index, index+1).equals("0")) {
				previousState = currentState;
				currentState = Integer.parseInt(((transitions.get(currentState).charAt(0))+""));
			}
			
			// Else if next letter in input is 1
			
			else if(input.substring(index, index+1).equals("1")) {
				currentState = Integer.parseInt(((transitions.get(currentState).charAt(2))+""));
			}
			
			System.out.println("δ(" + previousState+","+input.substring(index, index+1) + ") = " + currentState);
			
			if(acceptStates.contains(currentState+"")) 
				result = true;
			else
				result = false;
			
		}
		
		System.out.println("End state: " + currentState);
		
		return result;
	}
	
	/*
	 *   Displays the transition functions to verify the representation string
	 */

	public void PrintTransitions() {
		for(int index = 0 ; index < transitions.size() ; index++) {
			System.out.println("δ("+index+","+0+") = "+transitions.get(index).substring(0,1));
			System.out.println("δ("+index+","+1+") = "+transitions.get(index).substring(2,3));
		}
	}
	
	public static void main(String [] args) {
		new DFA("0,0,1;1,1,2;2,2,2#2");
	}

}
