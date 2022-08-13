import java.util.Scanner;

public class UserChoices{
	MainGame startGame = new MainGame();
	Rules diceRules = new Rules();
	Scanner scnr = new Scanner(System.in);
	
	private char userMC;
	
	public void ensureValidInputNums(char userInput) {
		this.userMC = userInput;
		
		while(userMC != '1' && userMC != '2' && userMC != '3' && userMC != '4' && userMC != '5' && userMC != '6') {
			
			System.out.println("Invalid entry. Press 1, 2, 3, 4, 5, or 6 to proceed.");
			userMC = scnr.next().charAt(0); //TODO: maybe delete this line, causes confusion with wrong input and rolling remaining dice
		}
	}
	
	public void ensureValidInputLets(char userInput) {
		this.userMC = userInput;
		
		while(userMC != 'Y' && userMC != 'y' && userMC != 'N' && userMC != 'n') {
			System.out.println("Invalid entry. Press 'Y' or 'N'.");
			userMC = scnr.next().charAt(0);
		}
	}
	
	public void userInput() {
		
		if(userMC == '1') {
			startGame.Game();
		}
		else if(userMC == '2') {
			diceRules.printDiceRules();
		}
		else if(userMC == '3') {
			System.out.println("\nGame Exited");
			System.exit(0);
		}
	}
}
