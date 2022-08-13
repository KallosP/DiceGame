
public class Util {
	//first option, second op..., etc.
	public static void menuList(String title, String fOp, String sOp, String tOp) {
		System.out.println("\n" + title.toUpperCase() + "\n 1. " + fOp + "\n 2. " + sOp + "\n 3. " + tOp);
	}
	
	/**@author gefei -> https://stackoverflow.com/questions/14196987/java-round-to-nearest-multiple-of-5-either-up-or-down 
	 * 
	 * @param num
	 * @return rounds any number to multiple of 5
	 */
	public int roundToMultOfFive(int num) {
		return 5*(Math.round(num/5));
	}
	
	public static void printTitleAndWelcome() {
		System.out.println("+~~~~~~~~~~~~~~~~~~~~~~~~+");
		System.out.println("| Welcome to Dice 10,000 |");
		System.out.println("+~~~~~~~~~~~~~~~~~~~~~~~~+");
		System.out.println();
		System.out.println("+-------------------------------------------------------+");
		System.out.println("|For all questions enter 'Y' or 'N' to answer Yes or No.|");
		System.out.println("+-------------------------------------------------------+");
	}
	
	public void gameOverMsg() {
		System.out.println("\n\n+~~~~~~~~~~~+");
		System.out.println("| GAME OVER |");
		System.out.println("+~~~~~~~~~~~+\n");
	}
	
	String border;
	
	public void dynamicFrame(String title) {
		border = "";
		
		for(int i = 0; i < title.length(); ++i) {
			border += "-";
		}
		
		System.out.println("+------------" + border + "-+");
		System.out.println("| Your Roll: " + title  + " |");
		System.out.println("+------------" + border + "-+");
	}
	
	public void allDiceKeptMsg() {
		System.out.println("+-----------------------------------------------+");
		System.out.println("|All dice and points have been kept! Roll again.|");
		System.out.println("+-----------------------------------------------+");
	}
	
	public void winMsg(String title) {
		border = "";
		
		for(int i = 0; i < title.length(); ++i) {
			border += "-";
		}
		
		System.out.println("+-" + border + "-+");
		System.out.println("| " + title  + " |");
		System.out.println("+-" + border + "-+\n");
	}
	
	public void pLostMsg(String msg) {
		String blankBorder = "";
		border = "";
		
		for(int i = 0; i < msg.length(); ++i) {
			border += ":";
			blankBorder += " ";
		}
		
		System.out.println(":::" + border + ":::");
		System.out.println(":: " + blankBorder + " ::");
		System.out.println(":: " +  msg   + " ::");
		System.out.println(":: " + blankBorder + " ::");
		System.out.println(":::" + border + ":::");
	}
}
