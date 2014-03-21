package Blackjack;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Player extends Participant{

	public Player() {
		super(whatsYourName());
	}
	
	@Override
	protected void hitOrStand(){
		printHand(this);
		if(this.getScore() == BLACKJACK){
			this.standing = true;
		}
		else if(this.getScore() < BLACKJACK){
			boolean checkInput = false;
			String YorN;
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in); // Scanner is never closed because then the 'system.in' disappears...
//			System.out.println("Your score is " + this.getScore());
			System.out.print("Do you want another card " + this.getName() + "? (Yes/No): ");
			do{
				YorN = input.nextLine();
				if(YorN.equalsIgnoreCase("yes")) {
					checkInput = true;
				}
				else if(YorN.equalsIgnoreCase("no")) {
					stand(true);
					checkInput = true;
				}
				else {
					System.err.println("Enter 'yes' or 'no'!");
				}
			} while (!checkInput);
		}
	}
	
	@Override
	public void makeDeposition() {
		boolean validDeposit = false;
		do{
			try{
				System.out.print("How much money do want to deposit?(max " +  + MAX_DEPOSIT +"): ");
				@SuppressWarnings("resource")
				Scanner moneyInsert = new Scanner(System.in);
				int d = moneyInsert.nextInt();
				if(d <= MAX_DEPOSIT){
					setDeposition(d);
					validDeposit = true;
				}
				else {
			    	System.out.println("Deposition must be between " + MIN_DEPOSIT + " - " + MAX_DEPOSIT);
				}
			} catch (InputMismatchException e) {
		    	System.out.println("Deposition must be between " + MIN_DEPOSIT + " - " + MAX_DEPOSIT);
		    }
		} while (!validDeposit);
		System.out.println("A deposition of " + getDeposition() + " was added to " + this.getName());
	}
	
	@SuppressWarnings("resource")
	private static String whatsYourName(){
		boolean validName = false;
		String name = "Player";
		do{
			System.out.print("What's your name?: ");
			name = new Scanner(System.in).nextLine();
			if(Pattern.matches("[a-zA-Z ]+", name)){
				validName = true;
			}
			else {
				System.out.println("You can only have alphabetic characters in your name...");
			}
		}while(!validName);
		return name;
	}

	@Override
	public void makeBet() {
		int placedBet = -1;
		boolean validBet = false;
		do{
			try{
				System.out.println("Your deposition is: " + getDeposition());
				System.out.print("Place your bet: ");
				@SuppressWarnings("resource")
				Scanner betInput = new Scanner(System.in);
				placedBet = betInput.nextInt();
				if(placedBet > getDeposition()){
					System.out.println("Your bet can not be higher than your deposition");
				}
				else if(placedBet > 0){
					setBet(placedBet);
					validBet = true;
				}
				else{
					System.out.println("This is not a game for children, you can't bet 0\n" +
							"THIS IS MADNESS!");
				}
			} catch (InputMismatchException e) {
		    	System.out.println("Entered value is not an integer!!\n");
		    }
		} while (!validBet);
//		return placedBet;
	}

	@Override
	protected PlayerType getType() {
		return PlayerType.HUMAN;
	}
}
