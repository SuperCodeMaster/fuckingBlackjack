package Blackjack;
import java.util.ArrayList;
import java.util.Collections;


public class CardDeck {

	private ArrayList<Card> deck;
	private ArrayList<Card> usedCards = new ArrayList<Card>();
	
	public CardDeck(){
		deck = new ArrayList<Card>();
		loadDeck();
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
		System.out.println("Dealer is shuffling the deck...");
	}
	
	public Card draw(){
		if(deck.isEmpty()){
			restackDeck();
		}
//		System.out.println(deck.size());
		if(deck.size() > 0){
			usedCards.add(deck.get(deck.size() - 1));
//			System.out.println(usedCards.get(usedCards.size() - 1).toString());
			return deck.remove(deck.size() - 1);
		}
		else return null;
	}
	
	public void listDeck(){
	for(int i = 0; i < deck.size(); i++){
			System.out.println(deck.get(i).toString() + ", Score: " + deck.get(i).getScore());
		}
	}
	
	public int getTotalCards()
	{
		return deck.size();
	}
	
	private void loadDeck() {
		for(int i = 0; i < 2; i++){
			for(int s = 0; s < 4; s++){
				for(int r = 1; r < 14; r++){
					deck.add(new Card(s,r));
				}
			}
		}
	}
	
	private void restackDeck(){
		System.out.println("No more cards\nRestacking old Cards");
		deck.addAll(usedCards);
		usedCards.clear();
		shuffle();
	}
}
