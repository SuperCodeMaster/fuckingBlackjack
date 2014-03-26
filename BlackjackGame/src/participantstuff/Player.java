package participantstuff;

import gui.Livefeed;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * @author Joachim Lundberg
 * 
 * This is the Human player that is controlled by the user.
 * The class extends the abstract class Participant to get
 * the methods and variables from it. 
 * Prompts the user for different information.
 */
public class Player extends Participant{

	/**
	 * Calls for the super constructor with the String returned from "whatsYourName()"
	 */
	public Player(Livefeed livefeed) {
		super(whatsYourName(), livefeed);
	}
	
	/**
	 * Asks the user to "Hit"(take another card) or "Stand"(Stop taking cards)
	 * if user already has 21 the user will automatically stand.
	 */
	@Override
	public void hitOrStand(){
		if(this.getScore() == BLACKJACK){
			standing(true);
		}
		else if(this.getScore() < BLACKJACK){
			Object[] options = { "HIT", "STAND" };
			if(JOptionPane.showOptionDialog(null, "Hit Or Stand?", "", JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0){
				standing(false);
			}
			else {
				standing(true);
			}
		}
	}
	
	/**
	 * A JOptionPane with choices pops up
	 * Asks the user how big deposit he/she wants to make
	 * sets deposition to the choice.
	 */
	@Override
	public void makeDeposition() {
		boolean validChoice = false;
		do{
			try{
				String[] choices = { String.valueOf(((MAX_DEPOSIT / 10) * 1)), String.valueOf(((MAX_DEPOSIT / 10) * 2)),
						String.valueOf(((MAX_DEPOSIT / 10) * 3)), String.valueOf(((MAX_DEPOSIT / 10) * 4)), 
						String.valueOf(((MAX_DEPOSIT / 10) * 5)), String.valueOf(((MAX_DEPOSIT / 10) * 6)),
						String.valueOf(((MAX_DEPOSIT / 10) * 7)), String.valueOf(((MAX_DEPOSIT / 10) * 8)), 
						String.valueOf(((MAX_DEPOSIT / 10) * 9)), String.valueOf(MAX_DEPOSIT)};
			    
				int deposit = Integer.parseInt((String) JOptionPane.showInputDialog(null, "How much money do want to deposit?", 
			    		"Deposit", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]));
				
				setDeposition(deposit);
				livefeed.addToFeed(this.getName() + " made a deposit of " + deposit + ".");
				validChoice = true;
			} catch(NumberFormatException e) {
				// if the optionPane gets closed
				if(JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Warning", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
					System.exit(0);
					validChoice = true;
				}
			}
		} while(!validChoice);

	}
	
	/**
	 * Prompts the user to insert his/her name.
	 * The name can be no longer than 16 characters
	 * and can only contain alphabetic characters (A - ö) and whitespace.
	 * @return the inserted name in a String
	 */
	private static String whatsYourName(){
		boolean validName = false;
		String name;
		do{
			name = JOptionPane.showInputDialog(null, "Enter your name:");
			if(name == null){
				// if the optionPane gets closed
				if(JOptionPane.showOptionDialog(null, "Do you really want to quit?", "Warning", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
					System.exit(0);
				}
			}
			if(name.trim().isEmpty()){
				// Checks if name is only whitespace
				JOptionPane.showMessageDialog(null, "Your can't only contain whitespace");
			}
			else if(name.length() < 3){
				// Checks if name is shorter than 4 characters
				JOptionPane.showMessageDialog(null, "Your name must contain at least 3 characters");
			}
			else if(Pattern.matches("[a-öA-Ö ]+", name) && name.length() <= 16){
				// Checks if the name contains only A-ö and/or whitespace and is shorter than 16
				name = name.trim();
				validName = true;
			}
			else if(name.length() > 16){
				// Checks if the name is longer than 16 characters
				JOptionPane.showMessageDialog(null, "Your name can not be longer than 16 characters");
			}
			else if(name.length() < 1){
				// Checks if name is shorter than 1 character
				JOptionPane.showMessageDialog(null, "You must enter a name..." + "\nIf you don't have one, make one up!");
			}
			else {
				// If name contains anything except from A-ö and whitespace
				JOptionPane.showMessageDialog(null, "You can only have alphabetic characters in your name");
			}
		}while(!validName);
		
		/* Splits the name on whitespace to substrings,
		 * creates a new StringBuffer.
    	 * Puts together the substrings with spaces in the StringBuffer 
    	 * and sets every first character to upper case.
    	 * Put the buffer in a string and trims the whitespace */
    	 
		String[] stringArr = name.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringArr.length; i++) {
        	sb.append(Character.toUpperCase(stringArr[i].charAt(0))).append(stringArr[i].substring(1)).append(" ");
        }
        return sb.toString().trim();
	}

	/**
	 * Prompts the user with various bet options
	 * the bet must be lower than the deposition
	 */
	@Override
	public void placeBet() {
		boolean validBet = false;
		do{
			int placedBet = 0;
			Object[] betOptions = { "20", "40", "100", "200", "400", "800", "1000" };
			int choice = JOptionPane.showOptionDialog(null, "Your deposition is " + getDeposition() + "\nPlace your bet:", "Bet", JOptionPane.YES_NO_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, betOptions, betOptions[0]);
			
			switch (choice){
			case 0:
				placedBet = 20;
				break;
			case 1:
				placedBet = 40;
				break;
			case 2:
				placedBet = 100;
				break;
			case 3:
				placedBet = 200;
				break;
			case 4:
				placedBet = 400;
				break;
			case 5:
				placedBet = 800;
				break;
			case 6:
				placedBet = 1000;
				break;
			}
			
			if(placedBet == 0){
				// if the optionPane gets closed
				JOptionPane.showMessageDialog(null, "This is not a game for children, you can't just cancel the betting...\n" + "THIS IS MADNESS!");
			}
			else if(placedBet > getDeposition()){
				// if bet is higher than the deposition
				JOptionPane.showMessageDialog(null, "Your bet can not be higher than your deposition");
			}
			else {
				setBet(placedBet);
				livefeed.addToFeed(this.getName()+ " placed a bet: " + this.getBet());
				validBet = true;
			}
		} while (!validBet);
	}

	@Override
	public ParticipantType getType() {
		return ParticipantType.HUMAN;
	}
}
