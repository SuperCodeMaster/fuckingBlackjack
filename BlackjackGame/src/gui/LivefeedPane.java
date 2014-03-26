package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LivefeedPane extends JPanel implements Livefeed {

	private static final long serialVersionUID = 1L;
	private JPanel feed = new JPanel();
	private ArrayList<JLabel> tempFeeds = new ArrayList<JLabel>();
	private Image backgroundImage;
	
	public LivefeedPane() {
		this.setSize(300, 400);
		this.setLayout(null);
		this.setOpaque(false);
		feed.setOpaque(false);
		ImageIcon icon = new ImageIcon("Images/feedBack.png");
		backgroundImage = icon.getImage();
		feed.setBounds(5, 5, 280, 320);
		feed.setLayout(new BoxLayout(feed, BoxLayout.Y_AXIS));
		this.add(feed);
	}

	@Override
	public void addToFeed(String string) {
		JLabel label = new JLabel();
		label.setText(string);
		label.setForeground(Color.LIGHT_GRAY);
		feed.add(label, 0);
		tempFeeds.add(label);
		if(tempFeeds.size() > 21){
			for(int i = 21; i < tempFeeds.size(); ++i){
				feed.remove(i);
				tempFeeds.remove(i);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		int width = getWidth();
		int height = getHeight();
		g.drawImage(backgroundImage, 0, 0, width, height, null);
	}
}
