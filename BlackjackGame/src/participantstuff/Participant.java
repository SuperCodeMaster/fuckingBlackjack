package participantstuff;

import gamestuff.Commons;
import gui.Livefeed;
import cardstuff.Card;

/**
 * @author Joachim Lundberg
 * 
 * An abstract class that got the methods and variables that act the same
 * for every sub class.
 * The abstract methods act different in different sub classes
 */
public abstract class Participant implements Commons {
	
	
	private String name;
	protected boolean isStanding = false;
	protected boolean isBusted = false;
	protected Hand hand;
	protected int bet, deposition;
	protected Livefeed livefeed;
	
	/**
	 * @param name sets the name of the Participant
	 */
	public Participant(String name, Livefeed livefeed){
		this.name = name;
		this.livefeed = livefeed;
		initialize();
	}
	
	/**
	 * Initialize the participant by creating a new Hand,
	 * setting the bust and stand boolean to false,
	 * sets the bet to zero
	 */
	private void initialize(){
		hand = new Hand();
		standing(false);
		busting(false);
		setBet(0);
	}
	
	public String getName(){
		return name;
	}
	
	public int getScore(){
		return this.hand.getScore();
	}
	
	public int getBet(){
		return bet;
	}
	
	/**
	 * Sets the bet for the participant ( @param bet )
	 */
	public void setBet(int bet){
		setDeposition(getDeposition() - bet);
		this.bet = bet;
	}
	
	public int getDeposition(){
		// Returns the deposition the participant has
		return deposition;
	}
	
	/**
	 * Sets the participants deposition based on @param deposit
	 */
	public void setDeposition(int deposit){
		this.deposition = deposit;
	}
	
	public Hand getHand(){
		// Returns the participants hand
		return hand;
	}

	/**
	 * @returns standing boolean
	 * true if participant is standing and false if participant is not standing
	 */
	public boolean isStanding(){
		return isStanding;
	}
	
	/**
	 * @param bool sets the value true or false for the standing boolean
	 */
	public void standing(boolean bool){
		isStanding = bool;
		if(isStanding){
			livefeed.addToFeed(this.getName() + " stands.");
		}
	}
	
	/**
	 * @returns isBusted boolean
	 * true if participant is busted (score is over 21) or else false
	 */
	public boolean isBusted(){
		if(getScore() > BLACKJACK){
			busting(true);
			livefeed.addToFeed(this.getName() + " is busted!");
		}
		return isBusted;
	}

	/**
	 * @param bool sets the value true or false for the isBusted boolean
	 */
	public void busting(boolean bool) {
		isBusted = bool;
	}
	
	public boolean hasBlackjack(){
		if(getHand().isBlackjack()){
			livefeed.addToFeed(this.getName() + " got a BLACKJACK!");
			return true;
		}
		else {
			return false;
		}
	}
	
	public abstract ParticipantType getType();
	
	public abstract void makeDeposition();
	
	public abstract void placeBet();
	
	public abstract void hitOrStand();

	/**
	 * Gets a card ( @param card ) and adds it to the hand
	 */
	public void dealToPlayer(Card card){
		hand.addToHand(card);
		livefeed.addToFeed(this.getName()+ " got " + card.toString());
	}
	
	/**
	 * Clear the participants hand,
	 * set the score to zero, 
	 * make sure the participant is not busted or is standing,
	 * set bet to zero
	 */
	public void newGame(){
		hand.throwCards();
		hand.setScore(0);
		busting(false);
		standing(false);
		setBet(0);
	}
}
