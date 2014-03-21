package Blackjack;

public class Card implements Constants{

	private boolean isAce = false;
	private int suit, rank, score;
	private String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
	private String[] ranks  = { "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };
	
	public Card(int suit, int rank){
		this.suit = suit;
		this.rank = rank;
		initialize();
	}
	
	public String toString(){
		return ranks[(rank - 1)] + " of " + suits[suit];
	}
	
	public int getSuit(){
		return suit;
	}
	
	public String getSuits(){
		return suits[suit];
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public boolean isAce(){
		if(rank == 1)
			isAce = true;
		else
			isAce = false;
		return isAce;
	}
	
	private void initialize(){
		if(isAce())
			setScore(HIGH_ACE);
		else if(rank < 10) 
			setScore(rank);
		else
			setScore(FACE_CARD);
	}
}
