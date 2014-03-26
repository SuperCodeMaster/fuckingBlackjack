package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import cardstuff.Card;
import participantstuff.Participant;
import participantstuff.ParticipantType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

public class Seat extends JPanel implements GUIListener{
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	private ArrayList<Card> cardList = new ArrayList<Card>();
	
	private Participant participant;
	private JPanel statusPanel;
	
	private JLayeredPane cardPanel;

	
	
	
	private JLabel name;
	private JLabel deposition = new JLabel("Deposition: ");
	private JLabel bet = new JLabel("Bet: ");
	private JLabel score = new JLabel("Score: ");
	
	public Seat(Participant participant) {
		this.participant = participant;
		initialize();
	}
	
	public boolean isTakenBy(Participant p){
		if(p.equals(participant)){
			return true;
		}
		else {
			return false;
		}
	}

	private void initialize() {
		setLayout(null);
		setOpaque(false);
		initializeStatusPanel();
		this.add(statusPanel);
		initializeCardPanel();
		this.add(cardPanel);
		setLabelsProperties(Color.YELLOW, new Font("Arial", Font.BOLD, 17));
	}
	
	public void addCard(Card c){
		cardList.add(c);
	}
	
	private void initializeStatusPanel(){
		statusPanel = new JPanel();
		statusPanel.setSize(200, 70);
		statusPanel.setOpaque(false);
		statusPanel.setLayout(new GridLayout(4,1));
		
		name  = new JLabel(participant.getName());
		labelList.add(name);
		statusPanel.add(name);
		
		if(participant.getType().equals(ParticipantType.DEALER)){
			statusPanel.setLocation(5, 5);
		}
		else {
			labelList.add(deposition);
			labelList.add(bet);
			statusPanel.add(deposition);
			statusPanel.add(bet);
			statusPanel.setLocation(5, 308);
		}

		labelList.add(score);
		statusPanel.add(score);
	}
	
	private void initializeCardPanel(){
		cardPanel = new JLayeredPane();
		cardPanel.setSize(new Dimension(285, 300));
		cardPanel.setOpaque(false);
		
		if(participant.getType().equals(ParticipantType.DEALER)){
			cardPanel.setLocation(5, 80);
		}
		else {
			cardPanel.setLocation(5, 5);
		}
	}
	
	private void setLabelsProperties(Color color, Font font){
		for(JLabel jl : labelList){
			jl.setForeground(color);
			jl.setFont(font);
		}
	}
	
	private JLabel createCardLabel(Card card, Point origin) {
		JLabel cardLabel = new JLabel();
		cardLabel.setIcon(card.getCardIcon());
		cardLabel.setBounds(origin.x, origin.y, card.getCardIcon().getIconWidth(), card.getCardIcon().getIconHeight());
		return cardLabel;
	}
	
	private void updateHand(){
		cardPanel.removeAll();
		
		//This is the origin of the first label added.
	    Point origin = new Point(10, 20);
	    //This is the offset for computing the origin for the next label.
	    int offset = 20;
		
	    
	    if(participant.getHand().getNumCards() != 0){
			for(int i = 0; i < participant.getHand().getNumCards(); i++){
				Card c = participant.getHand().getCard(i);
				cardPanel.add(createCardLabel(c, origin), new Integer(i));
				origin.x += offset;
		        origin.y += offset;
			}
		}
	    if(participant.getType().equals(ParticipantType.DEALER) && participant.getHand().getNumCards() < 2){
			JLabel hiddenCardLabel = new JLabel();
			hiddenCardLabel.setIcon(new ImageIcon("images/cards/back.png"));
			hiddenCardLabel.setBounds(origin.x, origin.y, hiddenCardLabel.getIcon().getIconWidth(), hiddenCardLabel.getIcon().getIconHeight());
			cardPanel.add(hiddenCardLabel, new Integer(2));
        }
//		if(participant.isBusted()){
//			JLabel statusLabel = new JLabel("BUSTED!");
//			statusLabel.setFont(new Font("Arial", Font.BOLD, 50));
//			statusLabel.setForeground(Color.ORANGE);
//			cardPanel.add(statusLabel);
//		}
//		else if(participant.hasBlackjack()){
//			JLabel statusLabel = new JLabel("BLACKJACK!");
//			statusLabel.setFont(new Font("Arial", Font.BOLD, 40));
//			statusLabel.setForeground(Color.GREEN);
//			cardPanel.add(statusLabel);
//		}
//		else if(participant.isStanding()){
//			JLabel statusLabel = new JLabel("STANDING");
//			statusLabel.setFont(new Font("Arial", Font.BOLD, 40));
//			statusLabel.setForeground(Color.CYAN);
//			cardPanel.add(statusLabel);
//		}
	}
	
	private void updateStatus(){
		score.setText("Score: " + participant.getScore());
		deposition.setText("Deposition: " + participant.getDeposition());
		bet.setText("Bet: " + participant.getBet());
	}

	@Override
	public void refresh() {
		updateStatus();
		updateHand();
		updateUI();
	}

	@Override
	public void newGame() {
		cardPanel.removeAll();
		updateStatus();
		updateUI();
	}
}
