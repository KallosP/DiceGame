import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainGame{
	
	ArrayList<Integer> listOfdieVals = new ArrayList<Integer>();
	Scanner scnr = new Scanner(System.in);
	Dice die = new Dice(); //single dice object which will act as all dice, each roll stored/added to arrayList
	Dice diceOp = new Dice(); //dice operation object
	Util util = new Util();
	
	char userOp; //user input
	
	char roll;
	final int MIN = 1;
	final int MAX = 6;
	public int playerScore = 0;
	
	public int tempPlayerScore = 0; //is reverted to 0 if player has kept dice, rolls, and then scratches, if dice are passed with dice kept, add this amount to the actual playerScore
	boolean playerWon = false, bot1Won = false, bot2Won = false, bot3Won = false, bot4Won = false; //only set to true if score is >10000
	
	//counters keep track of how many of each type of dice exist with each roll
	int oneCtr = 0, twoCtr = 0, threeCtr = 0, fourCtr = 0, fiveCtr = 0, sixCtr = 0;
	int[] ctrs = {oneCtr, twoCtr, threeCtr, fourCtr, fiveCtr, sixCtr};
	
	boolean diceWasKept = false;
	
	//scores initialized to 0
	int b1Score = 0, b2Score = 0, b3Score = 0, b4Score = 0;
	int otherTempB4Score = 0;
	
	//secondary user input
	char newUserOp;
	
	//scratch boolean, used for bots and player
	boolean scratchedTurn;
	
	//prints message and updates score of player
	public void msgAndScoreIncr(String msg, int scoreAmt) {
		System.out.println(msg);
		tempPlayerScore += scoreAmt;
	}
	
	//sets dice kept to true
	public void setDiceWasKept() {
		diceWasKept = true;
	}
	
	//Determines type of kind and displays according message, point, and counter operations
	public void ofKindPath(ArrayList<Integer> dieVals, int[] ctrs, int ofKindType) {
		
		if(ofKindType == 3) {
			
			if(ctrs[0] == 3) {
				msgAndScoreIncr("\nCombo of 1's Found! +1000 points.", 1000);
				diceOp.removeNumOfKind(dieVals, 1);
				setDiceWasKept();
				diceOp.clearCtrs(ctrs);
			} else if(ctrs[1] == 3 || ctrs[2] == 3 || ctrs[3] == 3 || ctrs[4] == 3 || ctrs[5] == 3) {
				for(int i = 1; i < ctrs.length; ++i) {
					if(ctrs[i] == 3) {
						msgAndScoreIncr("\nCombo of " + (i+1) + "'s Found! " + "+" + ((i+1)*100) + " points.", ((i+1)*100));
						diceOp.removeNumOfKind(dieVals, (i+1));
						setDiceWasKept();
						diceOp.clearCtrs(ctrs);
						break;
					} 
				}
			} else {
				System.out.println("\nNo combo granted, look closer...\n");
			}
			
		} 
		else if(ofKindType == 4) {
			
			if(ctrs[0] == 4) {
				msgAndScoreIncr("\nCombo of 1's Found! +2000 points.", 2000);
				diceOp.removeNumOfKind(dieVals, 1);
				setDiceWasKept();
				diceOp.clearCtrs(ctrs);
			}
			 else if(ctrs[1] == 4 || ctrs[2] == 4 || ctrs[3] == 4 || ctrs[4] == 4 || ctrs[5] == 4) {
				for(int i = 1; i < ctrs.length; ++i) {
					if(ctrs[i] == 4) {
						msgAndScoreIncr("\nCombo of " + (i+1) + "'s Found! +" + (((i+1)*2)*100) + " points.", (((i+1)*2)*100));
						diceOp.removeNumOfKind(dieVals, (i+1));
						setDiceWasKept();
						diceOp.clearCtrs(ctrs);
						break;
					} 
				}
			} else {
				System.out.println("\nNo combo granted, look closer...\n");
			}
			
		}
		else if(ofKindType == 5) {
			
			if(ctrs[0] == 5) {
				msgAndScoreIncr("\nCombo of 1's Found! +4000 points.", 4000);
				diceOp.removeNumOfKind(dieVals, 1);
				setDiceWasKept();
				diceOp.clearCtrs(ctrs);
			} else if(ctrs[1] == 5 || ctrs[2] == 5 || ctrs[3] == 5 || ctrs[4] == 5 || ctrs[5] == 5){
				for(int i = 1; i < ctrs.length; ++i) {
					if(ctrs[i] == 5) {
						msgAndScoreIncr("\nCombo of " + (i+1) + "'s Found! +" + (((i+1)*4)*100) + " points.", (((i+1)*4)*100));
						diceOp.removeNumOfKind(dieVals, (i+1));
						setDiceWasKept();
						diceOp.clearCtrs(ctrs);
						break;
					} 
				}
			} else {
				System.out.println("\nNo combo granted, look closer...\n");
			}
			
		}
		else {
			System.out.println("\nNo combo granted, look closer...\n");
		}
		
	}
	
	//determines which kind path to take based on user input via uniqueUserOp variable
	public void numOfKind(ArrayList<Integer> dieVals, int[] ctrs, int playerScore) {
		char uniqueUserOp;
		System.out.println("\nEnter the type of kind (i.e. 3, 4, 5, 6)");
		uniqueUserOp = scnr.next().charAt(0);
		
		switch(uniqueUserOp) {
			case '3':
				ofKindPath(dieVals, ctrs, 3);
				break;
			case '4':
				ofKindPath(dieVals, ctrs, 4);
				break;
			case '5':
				ofKindPath(dieVals, ctrs, 5);
				break;
			case '6':
				ofKindPath(dieVals, ctrs, 6);
				break;
			default:
				System.out.println("\nInvalid type.");
				break;
		}
		
	}
	
	//checks for straight
	public boolean isStraight(ArrayList<Integer> dieVals, int[] ctrs) {
		
		int straightTracker = 0;
		for(int i = 0; i < ctrs.length; ++i) {
			if(ctrs[i] == 1 && dieVals.size() == 6) {
				++straightTracker;
			}
		}
		
		if(straightTracker == 6) {
			msgAndScoreIncr("Straight!!! +3000", 3000);
			dieVals.clear();
			return true;
		} else {
			return false;
		}
		
	}
	
	//checks for 3 pairs
	public boolean hasThreePairs(ArrayList<Integer> dieVals, int[] ctrs) {
		
		int threePairsTracker = 0;
		for(int i = 0; i < ctrs.length; ++i) {
			if(ctrs[i] == 2 && dieVals.size() == 6) {
				++threePairsTracker;
			}
		}
		
		if(threePairsTracker == 3) {
			msgAndScoreIncr("Three pairs found! +1500", 1500);
			dieVals.clear();
			return true;
		} else {
			return false;
		}
		
	}
	
	//searches for any other combo type (currently only checks for straight and three pairs)
	public void otherCombo(ArrayList<Integer> dieVals, int[] ctrs, int playerScore) {
		System.out.println("\nSearching...\n");
		
		boolean loopOn = true;
		
		while(loopOn) {
						
			if(isStraight(dieVals, ctrs)) {
				scratchedTurn = false;
				break;
			} 
			else if(hasThreePairs(dieVals, ctrs)) {
				scratchedTurn = false;
				break;
			}
			else {
				System.out.println("No other combo found.");
				break;
			}

		}
		
	}
	
	//iterates through list of dice and updates counters
	public void assignCtrs(ArrayList<Integer> dieVals) {
		int currVal;
		
		for(int i = 0; i < dieVals.size(); ++i) {
			
			currVal = dieVals.get(i);
			
			if(currVal == 1) {
				ctrs[0] += 1;
			} else if(currVal == 2) {
				ctrs[1] += 1;
			} else if(currVal == 3) {
				ctrs[2] += 1;
			} else if(currVal == 4) {
				ctrs[3] += 1;
			} else if(currVal == 5) {
				ctrs[4] += 1;
			} else if(currVal == 6) {
				ctrs[5] += 1;
			}
		}
	}
	
	//prompts user to roll dice, contains input validation
	public void pDiceRollPrompts() {
		System.out.println("Enter 'R' to roll the dice.");
		roll = scnr.next().charAt(0);
		

		while(roll != 'r' && roll != 'R') {
			System.out.println("Invalid entry. Press 'R' on the keyboard to roll the dice.");
			roll = scnr.next().charAt(0);
		}
	}
	
	//rolls 6 random dice values, calls the assignCtrs() method, and checks for a scratched turn
	public void rollDice() {
		
		//COMMENT OUT IF BUG TESTING
		for(int i = 0; i < 6; ++i) {
			listOfdieVals.add(die.roll(MIN, MAX));
		}
		
		//BUG TESTING
		/*listOfdieVals.add(1);
		listOfdieVals.add(1);
		listOfdieVals.add(1);
		listOfdieVals.add(1);
		listOfdieVals.add(1);
		listOfdieVals.add(2);*/
		
		assignCtrs(listOfdieVals);
		
		if(ctrs[0] > 0 || ctrs[4] > 0) {
			scratchedTurn = false;
		}
		else if(ctrs[1] < 3 && ctrs[2] < 3 && ctrs[3] < 3 && ctrs[5] < 3) {
			scratchedTurn = true;
		} else {
			scratchedTurn = false;
		}
		
	}
	
	//rolls remaining dice and returns whether or not is a scratched turn
	public boolean rollRemainingDice(int[] ctrs, int numDiceLeft) {
		
		listOfdieVals.clear();
		diceOp.clearCtrs(ctrs);
		for(int i = 0; i < numDiceLeft; ++i) {
			listOfdieVals.add(die.roll(MIN, MAX));
		}
		
		diceWasKept = false;
		assignCtrs(listOfdieVals);
		
		if(ctrs[0] > 0 || ctrs[4] > 0) {
			return false;
		}
		else if(ctrs[1] < 3 && ctrs[2] < 3 && ctrs[3] < 3 && ctrs[5] < 3) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//method for increasing the score of the player when keeping one die at a time
	public void incrScoreForOne(int dieVal) {
		if(dieVal == 5) {
			tempPlayerScore += 50;
		} else if(dieVal == 1) {
			tempPlayerScore += 100;
		}
	}
	
	//checks for six of a kind
	public boolean isSixOfKind(int[] ctrs) {
		
		for(int i = 0; i < ctrs.length; ++i) {
			if(ctrs[i] == 6) {
				msgAndScoreIncr("\n6 of a Kind!!! You Win!!!", 0);
				return true;
			}
		}
		return false;
		
	}
	
	//tempB4Score getters and setters
	public int getTempB4Score() {
		return otherTempB4Score;
	}
	public void setTempB4Score(int score) {
		otherTempB4Score = score;
	}
	
	//returns random number from 1-100 which will be used to determine the 
	//amount of points that will be added to a bot's score 
	public int randBotPointIncr() {
		return die.roll(1, 100);
	}
	
	//pseudo turns for all bots; either modifies scores or scratches
	public ArrayList<Integer> botPlayersTurn(ArrayList<Integer> dieVals, int[] ctrs, boolean pScratched, int pScore) {
		
		int tempB1Score = 0, tempB2Score = 0, tempB3Score = 0, tempB4Score = 0;
		
		int randVar;
		
		System.out.println("\nRESULTS:");
		
		//BOT 1 point addition
		randVar = randBotPointIncr();
		
		if(randVar <= 60) { //most common point range, 60% chance of being accessed
			tempB1Score += die.roll(50, 1250);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar <= 79) { //second most common, 19% chance
			tempB1Score += die.roll(50, 2500);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar <= 85) { // 5%
			tempB1Score += die.roll(50, 3750);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar <= 90) { //4%
			tempB1Score += die.roll(50, 5000);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar <= 94) { // 3%
			tempB1Score += die.roll(50, 6250);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar <= 96) { // 2%
			tempB1Score += die.roll(50, 7500);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		} 
		else if(randVar <= 99) { //2%
			tempB1Score += die.roll(50, 8750);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		else if(randVar == 100) { // 1%
			tempB1Score += die.roll(50, 10000);
			tempB1Score = util.roundToMultOfFive(tempB1Score);
		}
		
		//BOT 2 point addition
		randVar = randBotPointIncr();
		
		if(randVar <= 60) {
			tempB2Score += die.roll(50, 1250);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 60 && randVar <= 79) {
			tempB2Score += die.roll(50, 2500);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 79 && randVar <= 85) { 
			tempB2Score += die.roll(50, 3750);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 85 && randVar <= 90) {
			tempB2Score += die.roll(50, 5000);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 91 && randVar <= 94) {
			tempB2Score += die.roll(50, 6250);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 94 && randVar <= 96) {
			tempB2Score += die.roll(50, 7500);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar > 97 && randVar <= 99) {
			tempB2Score += die.roll(50, 8750);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		} else if(randVar == 100) { 
			tempB2Score += die.roll(50, 10000);
			tempB2Score = util.roundToMultOfFive(tempB2Score);
		}
		
		//BOT 3 point addition
		randVar = randBotPointIncr();
		
		if(randVar <= 60) {
			tempB3Score += die.roll(50, 1250);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 60 && randVar <= 79) {
			tempB3Score += die.roll(50, 2500);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 79 && randVar <= 85) { 
			tempB3Score += die.roll(50, 3750);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 85 && randVar <= 90) {
			tempB3Score += die.roll(50, 5000);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 91 && randVar <= 94) {
			tempB3Score += die.roll(50, 6250);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 94 && randVar <= 96) {
			tempB3Score += die.roll(50, 7500);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar > 97 && randVar <= 99) {
			tempB3Score += die.roll(50, 8750);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		} else if(randVar == 100) { 
			tempB3Score += die.roll(50, 10000);
			tempB3Score = util.roundToMultOfFive(tempB3Score);
		}

		//BOT 4 point addition
		randVar = randBotPointIncr();
		
		if(randVar <= 60) {
			tempB4Score += die.roll(50, 1250);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 60 && randVar <= 79) {
			tempB4Score += die.roll(50, 2500);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 79 && randVar <= 85) { 
			tempB4Score += die.roll(50, 3750);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 85 && randVar <= 90) {
			tempB4Score += die.roll(50, 5000);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 91 && randVar <= 94) {
			tempB4Score += die.roll(50, 6250);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 94 && randVar <= 96) {
			tempB4Score += die.roll(50, 7500);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar > 97 && randVar <= 99) {
			tempB4Score += die.roll(50, 8750);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		} else if(randVar == 100) { 
			tempB4Score += die.roll(50, 10000);
			tempB4Score = util.roundToMultOfFive(tempB4Score);
		}
		
		int rNOFPD1, rNOFPD2, rNOFPD3, rNOFPD4; //rNOFPD = random number of passed dice
		
		do {
			
			rNOFPD1 = die.roll(1, 6);
			rNOFPD2 = die.roll(1, 6);
			rNOFPD3 = die.roll(1, 6);
			rNOFPD4 = die.roll(1, 6);
			rollRemainingDice(ctrs, rNOFPD4);
			
		}while(listOfdieVals.contains(1) || listOfdieVals.contains(5));
		
		if(rNOFPD1 > 3 || tempB1Score == 0) {
			tempB1Score = 0;
			System.out.println("\n|!Bot 1 has scratched!|");
		} else {
			b1Score += tempB1Score;
			System.out.println("\n[Bot 1's current score is now " + b1Score + "]");
		}
		
		if(rNOFPD2 > 3 || tempB2Score == 0) {
			tempB2Score = 0;
			System.out.println("\n|!Bot 2 has scratched!|");
		} else {
			b2Score += tempB2Score;
			System.out.println("\n[Bot 2's current score is now " + b2Score + "]");
		}
		
		if(rNOFPD3 > 3 || tempB3Score == 0) {
			tempB3Score = 0;
			System.out.println("\n|!Bot 3 has scratched!|");
		} else {
			b3Score += tempB3Score;
			System.out.println("\n[Bot 3's current score is now " + b3Score + "]");
		}
		
		if(rNOFPD4 > 3 || tempB4Score == 0) {
			tempB4Score = 0;
			System.out.println("\n|!Bot 4 has scratched!|\n");
			listOfdieVals.clear();
			scratchedTurn = false;
			rollDice();
			pDiceRollPrompts();
			return listOfdieVals;
		} 
		else {
			b4Score += tempB4Score;
			
			System.out.println("\n[Bot 4's current score is now " + b4Score + "]\n");
			System.out.println("\nBot 4 passed you " + rNOFPD4 + " dice and made +" + tempB4Score + " points.");
			System.out.println("\nWould you like to roll off of Bot 4's previous roll? [Potential points: +" + tempB4Score + "]");
			userOp = scnr.next().charAt(0);
			while(userOp != 'Y' && userOp != 'y' && userOp != 'N' && userOp != 'n') {
				System.out.println("Invalid entry. Press 'Y' or 'N'.");
				userOp = scnr.next().charAt(0);
			}
			
			if(userOp == 'Y' || userOp == 'y') {
				scratchedTurn = rollRemainingDice(ctrs, rNOFPD4);
				setTempB4Score(tempB4Score);
				System.out.println("\nRolling dice...\n");
				return listOfdieVals;
			} else {
				listOfdieVals.clear();
				System.out.println();
				rollDice();
				pDiceRollPrompts();
				return listOfdieVals;
			}
		}
		
	}
	
	//main game method
	public void Game() {
		
		rollDice();
		System.out.println();
		pDiceRollPrompts();
				
		do {
			
			UserChoices userMC = new UserChoices();
			String frameTitle;
			
			System.out.println("\n==========================");
			
			frameTitle = diceOp.printDice(listOfdieVals);
			util.dynamicFrame(frameTitle);
			
			util.menuList("Options", "Keep die", "Keep Combos", "Pass");
			System.out.println(" 4. Roll Remaining Dice");
			System.out.println(" 5. Check Scores");
			System.out.println(" 6. Quit");

			
			if(isSixOfKind(ctrs)) {
				System.out.println("Six of a kind!");
				playerWon = true;
			}
			
			if(playerScore >= 10000) {
				playerWon = true;
			}
			
			if(playerWon) {
				frameTitle = diceOp.printDice(listOfdieVals);
				util.dynamicFrame(frameTitle);
				break;
			}
									
			if(b1Score >= 10000) {
				bot1Won = true;
				break;
			} else if(b2Score >= 10000) {
				bot2Won = true;
				break;
			} else if(b3Score >= 10000) {
				bot3Won = true;
				break;
			} else if(b4Score >= 10000) {
				bot4Won = true;
				break;
			}
						
			userOp = scnr.next().charAt(0);
			userMC.ensureValidInputNums(userOp);
			
			diceOp.clearCtrs(ctrs);
			assignCtrs(listOfdieVals);
									
			switch(userOp) {
			
				case '1':
					
					boolean errPrompt = false;
					do {
						
						System.out.println("\nEnter dice value to keep:");
						try {
							
							int userdieVal = scnr.nextInt();
							scnr.nextLine();
							for(int i = 0; i < listOfdieVals.size(); ++i) {
								if(userdieVal == listOfdieVals.get(i)) {
									if(userdieVal == 1 || userdieVal == 5) {
										incrScoreForOne(listOfdieVals.get(i));
										listOfdieVals.remove(i);
										setDiceWasKept();
										System.out.println("\nDice Kept.");
										errPrompt = false;
									} else {
										errPrompt = true;
									}
									break;
								} else {
									errPrompt = true;
								}
							}
							
						} catch(Exception e){
							
							errPrompt = true;
							scnr.nextLine();
							
						}
						
						if(errPrompt) {
							System.out.println("\nInvalid die, cannot be kept.");
						} else {
							System.out.println("[Potential Points: +" + tempPlayerScore + "]\n");
						}
						
						if(listOfdieVals.size() == 0) {
							util.allDiceKeptMsg();
							diceOp.clearCtrs(ctrs);
							rollDice();
							pDiceRollPrompts();
							
							if(isSixOfKind(ctrs) || playerScore >= 10000) {
								playerWon = true;
							}
							
							break;
						}
						
						System.out.println("\nDo you wish to keep another die? (Type 'Y' or 'N')");
						System.out.println("\nRemaining dice: ");
						frameTitle = diceOp.printDice(listOfdieVals);
						util.dynamicFrame(frameTitle);
						
						System.out.println();
						userOp = scnr.next().charAt(0);
						userMC.ensureValidInputLets(userOp);
					
					} while(userOp == 'Y' || userOp == 'y');
					
					break;
					
				case '2':
					
					do {
						frameTitle = diceOp.printDice(listOfdieVals);
						util.dynamicFrame(frameTitle);
						
						util.menuList("Choose combo type:", "# of a Kind", "Other", "Back");
						newUserOp = scnr.next().charAt(0);
						
						diceOp.clearCtrs(ctrs);
						assignCtrs(listOfdieVals);
						
						if(newUserOp == '1') {
							numOfKind(listOfdieVals, ctrs, tempPlayerScore);
						} else if(newUserOp == '2') {
							otherCombo(listOfdieVals, ctrs, tempPlayerScore);
						} else if(newUserOp == '3'){
							System.out.println();
							break;
						}else {
							System.out.println("Invalid entry.");
						}
						
						System.out.println("\nPotential Points: +" + tempPlayerScore);
						
						if(listOfdieVals.size() == 0) {
							System.out.println();
							util.allDiceKeptMsg();
							diceOp.clearCtrs(ctrs);
							rollDice();
							pDiceRollPrompts();
							
							if(isSixOfKind(ctrs) || playerScore >= 10000) {
								playerWon = true;
							}
							
							break;
						}
						
					}while(newUserOp != '3');
				
					break;
					
				case '3':
					
					if(scratchedTurn) {
						System.out.println("\nScratched!\n");
						tempPlayerScore = 0;
					} else {
						playerScore += tempPlayerScore;
						tempPlayerScore = 0;
						System.out.println("\nScore adjusted. [Current Score: " + playerScore + "]\n");
					}		
					
					System.out.println("\nDice passed.\nWaiting turn...\n");
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					listOfdieVals = botPlayersTurn(listOfdieVals, ctrs, scratchedTurn, playerScore);
					
					if(listOfdieVals.size() < 6) {
						if(scratchedTurn) {
							System.out.println("\nScratched!\n");
							tempPlayerScore = 0;
							break;
						} else {
							tempPlayerScore = getTempB4Score();
							System.out.println("\nNo scratch! Points successfully rolled over! [Current Score: " + playerScore + "] " + "[Potential Points: +" + tempPlayerScore + "]\n");
							break;
						}
					} else {
						break;
					}
					
				case '4':
					
					if(diceWasKept) { 
						scratchedTurn = rollRemainingDice(ctrs, listOfdieVals.size());
					} else {
						System.out.println("\nMust keep at least one die to re-roll.\n");
					}

					break;
					
				case '5':
					
					System.out.println("\n\n[Your Current Score: " + playerScore + "]");
					
					if(scratchedTurn) {
						System.out.println("[Potential Points: 0]\n");
					} else {
						System.out.println("[Potential Points: +" + tempPlayerScore + "]\n");
					}
					
					System.out.println("[Bot 1's Current Score: " + b1Score + "]");
					System.out.println("[Bot 2's Current Score: " + b2Score + "]");
					System.out.println("[Bot 3's Current Score: " + b3Score + "]");
					System.out.println("[Bot 4's Current Score: " + b4Score + "]\n");
					
					break;
					
				case '6':
					
					break;
					
			}
			
		} while(userOp != '6');
				
		String winMsg;
		String pLostMsg = "[Your Final Score: " + playerScore + "]";
		
		util.gameOverMsg();
		
		if(playerWon) {
			winMsg = "You win! Final Score: " + playerScore; 
			util.winMsg(winMsg);
		} else if(bot1Won) {
			winMsg = "Bot 1 Wins! Final Score: " + b1Score;
			util.winMsg(winMsg);
			util.pLostMsg(pLostMsg);
		} else if(bot2Won) {
			winMsg = "Bot 2 Wins! Final Score: " + b2Score;
			util.winMsg(winMsg);
			util.pLostMsg(pLostMsg);
		} else if(bot3Won) {
			winMsg = "Bot 3 Wins! Final Score: " + b3Score;
			util.winMsg(winMsg);
			util.pLostMsg(pLostMsg);
		} else if(bot4Won) {
			winMsg = "Bot 4 Wins! Final Score: " + b4Score;
			util.winMsg(winMsg);
			util.pLostMsg(pLostMsg);
		} 
		else {
			System.out.println("\n\nGame Exited");
		}
		
	}
	
}