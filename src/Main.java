import java.util.Scanner;

public class Main{
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		UserChoices userMC = new UserChoices();		
		Util util = new Util();
		
		util.printTitleAndWelcome();
		
		util.menuList("Main Menu", "Play", "Rules", "Quit");
		char userInput = scnr.next().charAt(0);
		
		userMC.ensureValidInputNums(userInput);
		userMC.userInput(); //userMC = userMenuChoice
		
		scnr.close();
	}

}
