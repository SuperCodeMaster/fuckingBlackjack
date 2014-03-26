package Blackjack;

import java.util.Vector;

public class ScoreComparator implements Constants{

	private Dealer dealer;
	private Vector<Participant> participants = new Vector<Participant>();
	
	public ScoreComparator(Vector<Participant> vector, Dealer dealer){
		participants = vector;
		this.dealer = dealer;
		compare();
	}
	
	private void compare(){
		participants.remove(dealer);
		if(dealer.getScore() == BLACKJACK){
			System.out.println(dealer.getName() + " won!");
			for(Participant p : participants){
				System.out.println(p.getName() + " lost!");
			}
		}
		else if(dealer.isBusted()){
			System.out.println(dealer.getName() + " lost!");
			for(Participant p : participants){
				if(!p.isBusted()){
					System.out.println(p.getName() + " won!");
				}
			}
		}
		else {
			for(Participant p : participants){
				if(!p.isBusted() && p.getScore() < dealer.getScore()){
					System.out.println(p.getName() + " won!");
				}
			}
		
		}
		participants.add(dealer);
	}
}
