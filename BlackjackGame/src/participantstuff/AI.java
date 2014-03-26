package participantstuff;

import gui.Livefeed;

import java.util.Random;

/**
 * 
 * @author Joachim Lundberg
 *
 * The AI class is a computer player that act with 
 * the rules and hits until the score is either over 
 * AISTAND from the Commons interface or is busted.
 */
public class AI extends Participant{
	
	public AI(String name, Livefeed livefeed){
		super(name, livefeed);
	}

	@Override
	public void hitOrStand() {
		if(this.getScore() >= AISTAND && this.getScore() <= BLACKJACK){
			standing(true);
		}
		else if(this.getScore() < AISTAND){
			standing(false);
		}
	}

	/**
	 * Randomizes the deposit with even 
	 * hundreds from 500 to 1000
	 */
	@Override
	public void makeDeposition() {
		int d = new Random().nextInt((10 - 5) + 1) + 5;
		setDeposition(d * 100);
		livefeed.addToFeed(this.getName() + " made a deposit of " + (d * 100) + ".");
	}

	/**
	 * Randomizes the bet from 7 different options
	 * if bet is higher than deposition 
	 * the bet is randomized again.
	 */
	@Override
	public void placeBet() {
		boolean validBet = false;
		do{
			int placedBet = 0;
			int b = new Random().nextInt(7);
			switch (b){
			case 0:
				placedBet = 20;
				break;
			case 1:
				placedBet = 40;
				break;
			case 2:
				placedBet = 100;
				break;
			case 3:
				placedBet = 200;
				break;
			case 4:
				placedBet = 400;
				break;
			case 5:
				placedBet = 800;
				break;
			case 6:
				placedBet = 1000;
				break;
			}
			if(placedBet <= getDeposition() && placedBet > 0){
				setBet(placedBet);
				livefeed.addToFeed(this.getName()+ " placed a bet: " + this.getBet());
				validBet = true;
			}
		}while(!validBet);
	}
	
	@Override
	public ParticipantType getType() {
		return ParticipantType.AI;
	}
}
