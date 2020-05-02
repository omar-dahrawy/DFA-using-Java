import java.util.ArrayList;
import java.util.Scanner;


public class DFA {
	
	public ArrayList<String> states;
	public String acceptStates;
	public boolean result;
	public int currentState;
	
	public DFA(String representation) {
		
		states = new ArrayList<String>();
		acceptStates = "";
		result = false;
		currentState = 0;
		
		int index = 0;
		states.add(representation.substring(index+2,index+5));
		
		for(index+=6 ; index < representation.length()-4 ; index+=6) {
			states.add(representation.substring(index+2,index+5));
		}
		
		acceptStates = (representation.substring(representation.indexOf('#')+1,representation.length()));
		
		PrintArray(states);
		System.out.println("Accept States: " + acceptStates);
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nEnter input string: ");
		System.out.println("\n" + Run(scanner.nextLine()));
		
		scanner.close();
		
	}
	
	public boolean Run(String input) {
		
		System.out.println("\nStart State: " + currentState);
		
		for(int i = 0 ; i < input.length() ; i++) {
			if(input.substring(i, i+1).equals("0")) {
				currentState = Integer.parseInt(((states.get(currentState).charAt(0))+""));
			}
			else if(input.substring(i, i+1).equals("1")) {
				currentState = Integer.parseInt(((states.get(currentState).charAt(2))+""));
			}
			
			System.out.println("Current State: " + currentState);
			
			if(acceptStates.contains(currentState+"")) 
				result = true;
			else
				result = false;
			
		}
		return result;
	}
	

	
	public void PrintArray(ArrayList<String> list) {
		for(int i = 0 ; i < list.size() ; i++) {
			System.out.println(list.get(i));
		}
		System.out.println("\n--------------\n");
	}
	
	public static void main(String [] args) {
		new DFA("0,0,1;1,1,2;2,2,2#2");
	}

}
