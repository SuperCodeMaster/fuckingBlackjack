package cardstuff;
import gui.Livefeed;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {

	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> usedCards = new ArrayList<Card>();
	private Livefeed livefeed;
	
	public Deck(Livefeed livefeed){
		this.livefeed = livefeed;
		loadDeck();
	}
	
	public void shuffle(){
		Collections.shuffle(deck);
	}
	
	public Card draw(){
		Card tempCard = null;
		if(deck.isEmpty()){
			restackDeck();
		}
		if(deck.size() > 0){
			tempCard = deck.remove(deck.size() - 1);
			usedCards.add(tempCard);
		}
		return tempCard;
	}
	
	private void loadDeck() {
		int cardIndex;
		for(int i = 0; i < 2; i++){
			cardIndex = 1;
			for(int s = 0; s < 4; s++){
				for(int r = 1; r < 14; r++){
					deck.add(new Card(s, r, cardIndex));
					cardIndex++;
				}
			}
		}
	}
	
	private void restackDeck(){
		livefeed.addToFeed("No more cards\nRestacking old Cards...");
		for(Card c : usedCards){
			deck.add(c);
		}
		usedCards.clear();
		shuffle();
	}
}
