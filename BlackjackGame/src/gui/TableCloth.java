package gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class TableCloth extends JPanel{
	private static final long serialVersionUID = 1L;

	private Image backgroundImage;

	public TableCloth()
	{
		this.setLayout(new GridLayout(2,3));
		ImageIcon icon = new ImageIcon("Images/table.png");
		backgroundImage = icon.getImage();
	}

	@Override
	public void paintComponent(Graphics g){
		int width = getWidth();
		int height = getHeight();
		g.drawImage(backgroundImage, 0, 0, width, height, null);
	}
}
