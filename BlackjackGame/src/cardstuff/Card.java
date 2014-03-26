package cardstuff;

import gamestuff.Commons;

import javax.swing.ImageIcon;

/**
 * @author Joachim Lundberg
 *
 * This is the Card class.
 * there are 52 different cards
 * every card got a suit and a rank.
 */
public class Card implements Commons {

	private int suit, rank, score, cardIndex;
	private String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
	private String[] ranks  = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
	private ImageIcon ii;
	
	/**
	 * @param int suit is a number from 0 - 3 to determine the suit of the card
	 * @param int rank is a number from 1 - 13 to determine the rank of the card
	 * @param int cardIndex is a unique ID for the card
	 */
	public Card(int suit, int rank, int cardIndex){
		this.suit = suit;
		this.rank = rank;
		this.cardIndex = cardIndex;
		initialize();
	}
	
	@Override
	public String toString(){
		return ranks[(rank - 1)] + " of " + suits[suit];
	}
	
	public ImageIcon getCardIcon(){
		return ii;
	}
	
	public String getSuit(){
		return suits[suit];
	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	/**
	 * If card rank is one then it's an ace
	 * @return true if Ace or false if not
	 */
	public boolean isAce(){
		if(rank == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * FaceCards are Jack, Queen and King
	 * @return true if card is a faceCard or false if it's not
	 */
	public boolean isFaceCard(){
		if(rank > 10){
			return true;
		}
		else {
			return false;
		}
	}
	
	private void initialize(){
		ii = new ImageIcon("Images/cards/" + cardIndex + ".png");
		if(isAce())
			setScore(HIGH_ACE);
		else if(isFaceCard()) 
			setScore(FACE_CARD);
		else
			setScore(rank);
	}
}
