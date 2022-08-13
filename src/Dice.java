import java.util.ArrayList;

public class Dice {
	
	public int diceNum;
	
	/**@author https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java 
	 * 
	 * @param min
	 * @param max
	 * @return random number in range
	 */
	public int roll(int min, int max) { 
		min = (int) Math.ceil(min);
		max = (int) Math.floor(max);
		diceNum = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return diceNum;
	}
	
	public String printDice(ArrayList<Integer> listOfDice) {
		String diceList = "";
		
		if(listOfDice.size() == 0) {
			System.out.print("NONE\n");
		}
		for(int i = 0; i < listOfDice.size(); ++i) {
			diceList += listOfDice.get(i) + " ";
		}
		
		diceList = diceList.substring(0, diceList.length() - 1);
		
		return diceList;
	}
	
	//removes specific value from list of dice
	public void removeNumOfKind(ArrayList<Integer> dieVals, int valToRemove) {
		
		for(int i = 0; i < dieVals.size(); ++i) {
			if(dieVals.get(i) == valToRemove) {
				dieVals.remove(i);
				i -= 1;
			}
		}		
	}
	
	//checks whether or not a combo should be granted
	public void checkComboGrant(boolean validCombo) {
		if(validCombo = false) {
			System.out.println("\nNo combo granted, look closer...\n");
		}
	}
	
	//clears counters, should be cleared before every new roll
	public void clearCtrs(int[] ctrs) {
		for(int i = 0; i < ctrs.length; ++i) {
			ctrs[i] = 0;
		}
	}
		
}
