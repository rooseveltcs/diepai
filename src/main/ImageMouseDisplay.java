package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import screenIO.RobotManager;

public class ImageMouseDisplay extends JPanel
{
	private BufferedImage image;
	private int iScale;
	private RobotManager rm = new RobotManager();
	
	public ImageMouseDisplay(BufferedImage image, int iScale)
	{
		this.image = image;
		this.iScale = iScale;
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(image.getWidth() * iScale, image.getHeight() * iScale));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		for (int x = 0; x < image.getWidth(); x++)
		{
			for (int y = 0; y < image.getHeight(); y++)
			{
				g.setColor(new Color(image.getRGB(x, y)));
				g.fillRect(x * iScale, y * iScale, iScale, iScale);
			}
		}
	}
	
	public void moveMouseTo(int x, int y)
	{
		Point topLeft = this.getLocationOnScreen();
		rm.moveMouseTo((int) (topLeft.x + ((x + .5) * iScale)), (int) (topLeft.y + ((y + .5) * iScale)));
	}
	
	public void delay(int ms)
	{
		rm.delay(ms);
	}
}
