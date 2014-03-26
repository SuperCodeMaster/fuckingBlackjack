package Blackjack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerSlot extends JPanel {

	private static final long serialVersionUID = 1L;

	private Participant participant;
	
	private JPanel labels = new JPanel(new FlowLayout());
	private JPanel hand = new JPanel();
	
	private JLabel name = new JLabel();
	private JLabel deposition = new JLabel();
	private JLabel bet = new JLabel();
	private JLabel score = new JLabel();
	
	public PlayerSlot(Participant p) {
		participant = p;
		initialize();
		setOpaque(false);
	}
	
	private void initialize(){
		setBounds();
		
		name.setText(participant.getName());
		score.setText("Score: " + participant.getScore());
		deposition.setText("Deposition: " + participant.getDeposition());
		bet.setText("Bet: " + participant.getBet());
		
		labels.add(name);
		labels.add(score);
		labels.add(deposition);
		labels.add(bet);
		
		add(labels);
		add(hand);
	}
	private void setBounds(){
		if(participant.getType() == PlayerType.HUMAN){
			this.setLocation(100, 500);
		}
		else if(participant.getType() == PlayerType.DEALER){
			this.setLocation(100, 100);
		}
	}

}
