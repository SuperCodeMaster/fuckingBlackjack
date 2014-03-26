package Blackjack;
import java.util.ArrayList;


public class Hand implements Constants{

	private ArrayList<Card> hand = new ArrayList<Card>();
	private int score;
	
	public void addToHand(Card card){
		hand.add(card);
		score += card.getScore();
		checkAce();
	}
	
	public void printHand(){
		for(Card card : hand){
			System.out.println(card.toString());
		}
		System.out.println("Total Score: " + getScore());
		System.out.println();
	}
	
	public void throwCards() {
		hand.clear();
	}
	
	public int getNumCards(){
		return hand.size();
	}

	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}

	private void checkAce(){
		for (Card c : hand){
			if(c.isAce() && c.getScore() != 1 && this.getScore() > BLACKJACK){
				c.setScore(LOW_ACE);
				this.setScore(this.score - 10);
			}
		}
	}
}
