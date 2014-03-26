package Blackjack;

import java.util.Random;

public class AI extends Participant{
	public AI(String name){
		super(name);
	}

	@Override
	protected void hitOrStand() {
		printHand(this);
		if(this.getScore() >= AISTAND && this.getScore() <= BLACKJACK){
			stand(true);
		}
		else if(this.getScore() < AISTAND){
			standing = false;
		}
	}

	@Override
	public void makeDeposition() {
		int d = new Random().nextInt((10 - 5) + 1) + 5;
		setDeposition(d * 100);
		System.out.println("A deposition of " + getDeposition() + " was added to " + this.getName());
	}

	@Override
	public void makeBet() {
		int b = new Random().nextInt(getDeposition()) + 1;
		setBet(b);
		System.out.println(getName() + " placed a bet: " + getBet());
	}
	
	@Override
	protected PlayerType getType() {
		return PlayerType.AI;
	}
}
