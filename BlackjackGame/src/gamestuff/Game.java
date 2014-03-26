package gamestuff;

import gui.LivefeedPane;
import gui.Table;

import java.util.Vector;

import javax.swing.JOptionPane;

import participantstuff.AI;
import participantstuff.Dealer;
import participantstuff.Participant;
import participantstuff.Player;
import participantstuff.ParticipantType;

public class Game implements Commons {

	private Table table;
	private Dealer dealer;
	private Player player;
	private AI ai1;
	private Participant ai2;
	private Vector<Participant> participants = new Vector<Participant>();
	private LivefeedPane livefeed;
	
	public Game(){
		initialize();
	}
	
	public void run(){
		do{
			playGame();
		}while (keepPlaying());
		System.exit(0);
	}
	
	private void playGame(){
		newGame();
		refresh();
		dealer.initialDeal(participants);
		refresh();
		for(Participant p : participants){
			if(p.getType().equals(ParticipantType.DEALER)){
				dealer.revealHiddenCard();
				refresh();
			}
			while(!p.isBusted() && !p.isStanding()){
				p.hitOrStand();
					if(!p.isStanding()){
						dealer.deal(p);
					}
				refresh();
			}
		}
		compareScore();
	}
	
	private void totalOpponents(){
		Object[] opponentOptions = { "0", "1", "2" };
		int choice = JOptionPane.showOptionDialog(null, "How many opponents do you want?", "", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, opponentOptions, opponentOptions[0]);
		
		switch (choice){
		case 0:
			break;
		case 1:
			ai1 = new AI("Computer Player", livefeed);
			participants.add(ai1);
			break;
		case 2:
			ai1 = new AI("Computer Player 1", livefeed);
			participants.add(ai1);
			ai2 = new AI("Computer Player 2", livefeed);
			participants.add(ai2);
			break;
		}
	}
	
	private boolean keepPlaying(){
		boolean isPlaying = true;
		boolean checkInput = false;
		do{
			if(JOptionPane.showOptionDialog(null, "Do you want to play again " + player.getName() + "?", "Warning", JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, null, null) == 0){
				checkInput = true;
				
			}
			else {
				isPlaying = false;
				checkInput = true;
			}
		} while (!checkInput);
		return isPlaying ;
	}
	
	private void initialize(){
		livefeed = new LivefeedPane();
		player = new Player(livefeed);
		participants.add(player);
		totalOpponents();
		dealer = new Dealer(livefeed);
		participants.add(dealer);
		
		table = new Table(participants, livefeed);
		
		for(int i = 0; i < participants.size(); i++){
			participants.get(i).makeDeposition();
		}
	}

	private void refresh(){
		table.refresh();
	}
	
	private void newGame(){
		livefeed.addToFeed("*** A new round was started! ***");
		for(Participant p : participants){
			p.newGame();
		}
		table.newGame();
		for(Participant p : participants){
			if(p.getDeposition() < 20){
				JOptionPane.showMessageDialog(null, "Lowest allowd bet is 20, you don't have that much in deposition" + "\nTo continue you have to make a new deposit");
				player.makeDeposition();
			}
			p.placeBet();
		}
	}
	
	private void compareScore(){
		participants.remove(dealer);
		for(Participant p : participants){
			if(p.isStanding() && dealer.isBusted() || p.isStanding() && !dealer.isBusted() && p.getScore() > dealer.getScore()){
				dealer.payTo(p);
			}
		}
		participants.add(dealer);
	}
}
