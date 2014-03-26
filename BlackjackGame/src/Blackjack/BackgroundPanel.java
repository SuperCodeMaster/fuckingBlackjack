package Blackjack;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class BackgroundPanel extends JPanel{
	private static final long serialVersionUID = 1L;

	private Image backgroundImage;

	public BackgroundPanel()
	{
		this.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("Images/table.jpg");
		backgroundImage = icon.getImage();
	}

	@Override
	public void paintComponent(Graphics g){
		int width = getWidth();
		int height = getHeight();
		g.drawImage(backgroundImage, 0, 0, width, height, null);
	}
}
