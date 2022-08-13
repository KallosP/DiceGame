import java.util.Scanner;

public class Rules{
	Util util = new Util();
	Scanner scnr = new Scanner(System.in);
	
	public void printDiceRules() {
		System.out.println("===============|"
				+ "\nEach player takes it in turn at rolling the dice and must set aside at least one scoring die (1s, 5s, triples, 3 pairs, or a run of 6. See score values below).  Their turn continues, rolling the remaining dice, as long as they have thrown and set aside a scoring number or combination.  Players announce their progressive score for their turn after each roll. \r\n"
				+ "\r\n"
				+ "A player's turn ends when they either decide to stop and score their accumulated points or until they have a scoreless throw and score nothing for that turn.  Should all six dice be set aside as scoring then the player may roll them all again and continue their tally. \r\n"
				+ "\r\n"
				+ "Scoring combinations only count when made with a single throw.  For example, a player who rolls and puts aside a 1 and then throws two 1s with the next throw may only score 300 not a 1,000. \r\n"
				+ "\r\n"
				+ "The first player to score a total of 10,000 or above wins."
				+ "\n\nScore Values: \n1 = 100 points\r\n"
				+ "5 = 50 points\r\n"
				+ "1, 1, 1 = 1,000 points\r\n"
				+ "#, #, # = # x 100   e.g. 2, 2, 2 = 200 points:  6, 6, 6 = 600 points\r\n"
				+ "#, #, #, #	= # x 200\r\n"
				+ "#, #, #, #, # = # x 200\r\n"
				+ "1, 2, 3, 4, 5, 6	= 3,000 points\r\n"
				+ "3 pairs = 1,500 points (including four-of-a-kind and a pair)\r\n"
				+ "\r\n"
				+ "If five dice are counted as scoring, the remaining die may be thrown in an attempt to roll a 1 or 5.  If successful, the player adds their points and is allowed to continue rolling.\r\n"
				+ "\r\n"
				+ "Six-of-a-kind made with a single throw wins the game outright.\r\n"
				+ "===============|");

		UserChoices userMC = new UserChoices();
		util.menuList("Main Menu", "Play", "Rules", "Quit");
		char userI = scnr.next().charAt(0);
		
		userMC.ensureValidInputNums(userI);
		userMC.userInput();
		
	}
}
