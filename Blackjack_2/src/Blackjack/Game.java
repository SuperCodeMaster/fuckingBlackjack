package Blackjack;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

public class Game implements Constants{

	private Table table;
	private Dealer dealer;
	private Player player;
	private AI ai1;
	private Participant ai2;
	private Vector<Participant> participants = new Vector<Participant>();
	
	public Game(){
		initialize();
	}
	
	public void run(){
		do{
			playGame();
		}while (keepPlaying());
	}
	
	private void playGame(){
		for(Participant p : participants){
			p.newGame();
			p.makeBet();
		}
		dealer.initialDeal(participants);
		for(Participant p : participants){
			if(p == dealer){
				dealer.revealHiddenCard();
			}
			while((!p.busting()) && (!p.standing())){
				dealer.deal(p);
			}
		}
	}
	
	private void totalOpponents(){
		boolean checkInput = false;
		int opponentCount = -1;		
		do{
			System.out.print("How many opponents do you want?(0/1/2): ");
			try{
				opponentCount = new Scanner(System.in).nextInt();
			} catch (InputMismatchException e) {
		    	System.out.println("Entered value is not an integer!!");
			}
			if(opponentCount >= 0 && opponentCount <= 2){
				checkInput = true;
				if(opponentCount == 1){
					ai1 = new AI("Computer Player");
					participants.add(ai1);
				}
				if(opponentCount == 2){
					ai1 = new AI("Computer Player 1");
					participants.add(ai1);
					ai2 = new AI("Computer Player 2");
					participants.add(ai2);
				}
			}
			else {
				System.err.println("Enter a valid choice...");
			}
		}while(!checkInput);
	}
	
	private boolean keepPlaying(){
		boolean isPlaying = true;
		boolean checkInput = false;
		String YorN;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in); // Scanner is never closed because then the 'system.in' disappears...
		System.out.print("Do you want to play again, " + player.getName() + "? (Yes/No): ");
		do{
			YorN = input.nextLine();
			if(YorN.equalsIgnoreCase("yes")) {
				isPlaying = true;
				checkInput = true;
			}
			else if(YorN.equalsIgnoreCase("no")) {
				isPlaying = false;
				checkInput = true;
			}
			else {
				System.err.println("Enter 'yes' or 'no'!");
			}
		} while (!checkInput);
		return isPlaying ;
	}
	
	private void initialize(){
		dealer = new Dealer();
		player = new Player();
		participants.add(player);
//		totalOpponents();
		participants.add(dealer);

		table = new Table(participants);
		
		for(int i = 0; i < participants.size(); i++){
			participants.get(i).makeDeposition();
		}
	}
	
	private void winner(){
//		participants.remove(dealer);
		if(dealer.getScore() == BLACKJACK){
			System.out.println(dealer.getName() + " won!");
		}
		for(Participant p : participants){
			if((p.getScore() > dealer.getScore() && !p.isBusted()) || (!p.isBusted() && dealer.isBusted())){
				System.out.println(p.getName() + " won!");
			}
			else if(p.getScore() <= dealer.getScore() || p.isBusted()){
				System.out.println(p.getName() + " lost!");
			}
		}
//		participants.add(dealer);
	}
}
