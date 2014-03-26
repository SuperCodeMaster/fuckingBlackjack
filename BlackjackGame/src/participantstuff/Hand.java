package participantstuff;

import gamestuff.Commons;
import cardstuff.Card;
import java.util.ArrayList;

/**
 * @author Joachim Lundberg
 *
 * The Hand class holds an arrayList with a participant cards.
 */
public class Hand implements Commons{

	private ArrayList<Card> hand = new ArrayList<Card>();
	private int score;
	
	/**
	 * Adds a card( @param card ) to the arrayList
	 * and adds the score of the card.
	 * Checks what score the ace should have (1 or 11)
	 */
	public void addToHand(Card card){
		hand.add(card);
		score += card.getScore();
		checkAce();
	}
	
	/**
	 * @returns the card on place @param i in the arrayList
	 * if the hand is empty @returns null
	 */
	public Card getCard(int i)
	{
		if(i >= 0 && i < hand.size()) {
			return hand.get(i);
		}
		return null;
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
	
	/**
	 * checks if the hand is a blackjack hand
	 * @return true if it is or false if not
	 */
	public boolean isBlackjack(){
		if(this.getScore() == BLACKJACK && getNumCards() == 2 && (getCard(0).isFaceCard() || getCard(1).isFaceCard())){
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Checks if the ace should have score 1 or 11
	 * If score of the hand is over 21 when the ace with score 11
	 * has been added the score of the ace is one
	 */
	private void checkAce(){
		for (Card c : hand){
			if(c.isAce() && c.getScore() != 1 && this.getScore() > BLACKJACK){
				c.setScore(LOW_ACE);
				this.setScore(this.score - 10);
			}
		}
	}
}
