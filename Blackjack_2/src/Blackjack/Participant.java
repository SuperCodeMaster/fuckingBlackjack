package Blackjack;


public abstract class Participant implements Constants{

	private String name;
	protected boolean standing = false;
	protected boolean isBusted = false;
	protected Hand hand;
	protected int bet, deposition;
	
	protected abstract PlayerType getType();
	
	public Participant(String name){
		this.name = name;
		initialize();
	}
	
	public void dealToPlayer(Card card){
		hand.addToHand(card);
		System.out.println(this.getName() + " got " + card.toString() + "\n");
		printHand(this);
	}
	
	public void newGame(){
		hand.throwCards();
		hand.setScore(0);
		isBusted = false;
		standing = false;
		setBet(0);
	}
	
	public void printHand(Participant p){
		System.out.println("**** " + p.getName() + "'s hand ****");
		this.hand.printHand();
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
	
	public void setBet(int bet){
		this.bet = bet;
	}
	
	public int getDeposition(){
		return deposition;
	}
	
	public void setDeposition(int cash){
		this.deposition = cash;
	}

	public boolean standing(){
		hitOrStand();
		if(standing){
			System.out.println(this.getName() + " stands...");
		}
		else {
			System.out.println(this.getName() + " takes a new card...");
		}
		return standing;
	}
	
	public void stand(boolean bool){
		standing = bool;
	}
	
	public boolean isBusted(){
		return isBusted;
	}

	public boolean busting() {
		if(getScore() > BLACKJACK){
			isBusted = true;
			System.out.println(this.getName() + " got busted!");
		}
		return isBusted;
	}
	
	public abstract void makeDeposition();
	
	public abstract void makeBet();
	
	private void initialize(){
		hand = new Hand();
		standing = false;
		isBusted = false;
		setBet(0);
	}

	protected abstract void hitOrStand();
}
