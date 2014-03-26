package participantstuff;

import gui.Livefeed;

import java.util.Vector;

import javax.swing.JOptionPane;

import cardstuff.Card;
import cardstuff.Deck;

/**
 * This is the Dealer class. The dealer handles the card deck and deals cards to players as to it self.
 * @author Joachim Lundberg
 *
 */
public class Dealer extends Participant {

	private Deck deck;
	private Card hiddenCard;
	
	public Dealer(Livefeed livefeed){
		// Calls for the super constructor (Participant)
		super("Dealer", livefeed);
		initialize();
	}
	
	public void shuffleDeck(){
		// Runs the shuffle-method from deck
		deck.shuffle();	
	}
	
	/**
	 * Draws a card from the deck and gives to a Participant ( @param p )
	 */
	public void deal(Participant p) {
		Card c = deck.draw();
		p.dealToPlayer(c);
	}

	/**
	 * The first deal of the round, deals 2 cards to every participant except for the dealer.
	 * The dealer gets one card to the hand and one card face-down in table(the first card).
	 * @param vector holds the participants that will get cards.
	 */
	public void initialDeal(Vector<Participant> vector){
		for(int i = 0; i < 2; i++){
			for (Participant p : vector){
				if(p == this && i == 0){
					dealHiddenCard(p);
				}
				else {
					deal(p);
				}
			}
		}
	}
	
	public void revealHiddenCard(){
		// Adds the hidden card to dealers hand
		this.hand.addToHand(hiddenCard);
	}

	/**
	 * Pays a participant 2 to 1 if player had blackjack or 3 to 2 of his bet if not,
	 * uses (int) to round off from double to int
	 * @param p is the participant that should get paid
	 */
	public void payTo(Participant p){
		int payout;
		if(p.hasBlackjack()){
			payout = p.getBet() * 2;
		}
		else {
			payout = (int) (p.getBet() * 1.5);
		}
		p.setDeposition(p.getDeposition() + payout);
		livefeed.addToFeed(p.getName() + " won " + payout + "\n Deposition is now " + p.getDeposition());
		if(p.getType().equals(ParticipantType.HUMAN)){
			JOptionPane.showMessageDialog(null, p.getName() + " won " + payout);
		}
	}

	@Override
	public void makeDeposition() {
		// Adds a hundred times the Max deposit limit to the bank (This is more for future work)
		setDeposition(MAX_DEPOSIT * 100);
	}

	@Override
	public void placeBet() {
		// The dealer does not bet
	}
	
	@Override
	public ParticipantType getType() {
		// Return what type of Participant this is.
		return ParticipantType.DEALER;
	}

	@Override
	public void hitOrStand(){
		if(this.getScore() >= DEALERSTAND){
			// Dealer stands
			standing(true);
		} 
		else {
			// Dealer hits
			standing(false);
		}
	}
	
	/**
	 * A method for dealing a face down card to participant( @param p )
	 */
	private void dealHiddenCard(Participant p) {
		hiddenCard = deck.draw();
		livefeed.addToFeed(this.getName() + " got a hidden card.");
	}

	private void initialize(){
		// Creates a game deck and shuffles it
		deck = new Deck(livefeed);
		shuffleDeck();
	}
}
