package Blackjack;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Table extends JFrame {

	private static final long serialVersionUID = 1L;

//	private JPanel dealerPanel;
//	private JPanel playerPanel;
	
	public Table(Vector<Participant> pVector) {
		this.setBounds(100, 100, 800, 800);
		this.setResizable(false);
		this.setContentPane(new BackgroundPanel());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		for(Participant p : pVector){
			add(new PlayerSlot(p));
		}
	}
	
//	private void setPanelBounds(){
//		dealerPanel.setBounds(100,100,100,100);
//		playerPanel.setBounds(500, 500, 100, 100);
//	}
}
