package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import participantstuff.Participant;
import participantstuff.ParticipantType;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

public class Table extends JFrame implements GUIListener{

	private static final long serialVersionUID = 1L;
	
	private Vector<Participant> participants;
	private ArrayList<GUIListener> guiListeners = new ArrayList<GUIListener>();
	
	private Seat dealerSeat;
	private Seat playerSeat;
	private Seat cpuSeat1;
	private Seat cpuSeat2;
	private LivefeedPane livefeed;
	
	
	public Table(Vector<Participant> pVector, LivefeedPane livefeed) {
		participants = pVector;
		this.livefeed = livefeed;
		initialize();
		placeSeats();
	}


	private void initialize() {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(Table.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		this.setBounds(100, 200, 900, 800);
		this.setTitle("Blackjack by b13joalu");
		this.setResizable(false);
		this.setContentPane(new TableCloth());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void placeSeats(){
		for(Participant p : participants){
			if(p.getType().equals(ParticipantType.HUMAN)){
				playerSeat = new Seat(p);
				guiListeners.add(playerSeat);
			}
			else if(p.getType().equals(ParticipantType.DEALER)){
				dealerSeat = new Seat(p);
				guiListeners.add(dealerSeat);
			}
			else if(p.getType().equals(ParticipantType.AI)){
				if(cpuSeat1 == null){
					cpuSeat1 = new Seat(p);
					guiListeners.add(cpuSeat1);
				}
				else {
					cpuSeat2 = new Seat(p);
					guiListeners.add(cpuSeat2);
				}
			}
		}
//		this.getContentPane().add(new JLabel(""));
		this.getContentPane().add(livefeed);
		this.getContentPane().add(dealerSeat);
		this.getContentPane().add(new JLabel(new ImageIcon("images/deck.png")));
		if(cpuSeat2 != null){
			this.getContentPane().add(cpuSeat2);
		}
		else {
			this.getContentPane().add(new JLabel(""));
		}
		if(cpuSeat1 != null){
			this.getContentPane().add(cpuSeat1);
		}
		else {
			this.getContentPane().add(new JLabel(""));
		}
		this.getContentPane().add(playerSeat);
		
	}


	@Override
	public void refresh() {
		for(GUIListener listener : guiListeners){
			listener.refresh();
		}
	}


	@Override
	public void newGame() {
		for(GUIListener listener : guiListeners){
			listener.newGame();
		}
	}

}
