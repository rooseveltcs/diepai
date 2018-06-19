package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import screenIO.RobotManager;
import screenIO.ScreenData;
import screenIO.Screenshot;

public class PixelTest 
{
	//THIS CLASS IS FOR DEBUGGING
	//This class is used to test various properties of images.
	private static RobotManager robotManager = new RobotManager();
	private static boolean bIsRecording = false;
	private static String startKey = "1";
	private static String stopKey = "2";
	
	//Display an image at a larger scale.
	public static void displayPixels(BufferedImage image, int iScale)
	{
		class myJPanel extends JPanel
		{
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
		}
		JFrame frame = new JFrame();
		frame.setSize(ScreenData.SCREEN_WIDTH, ScreenData.SCREEN_HEIGHT);
		frame.add(new myJPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//Write an image to a file.
	public static void writeImage(BufferedImage image, String sDirectory)
	{
		try 
		{
			ImageIO.write(image, "jpg", new File(sDirectory));
		} 
		catch (IOException e) 
		{
			System.out.println("Problem Writing Image");
		}
	}
	
	//Read an image from a file.
	public static BufferedImage readImage(String sDirectory)
	{
		try {
			return ImageIO.read(new File(sDirectory));
		} catch (IOException e) 
		{
			System.out.println("Problem Reading Image");
			return null;
		}
	}
	
	//If bIsRecording is true, display the RGB value at the position of the mouse 
	//whenever the mouse is clicked.
	public static void mouseClicked()
	{
		if (bIsRecording)
		{
			Screenshot screenshot = robotManager.takeScreenshot();
			System.out.println(screenshot.getPixel(getMouseX(), getMouseY()));
		}
	}
	
	//When the startKey is pressed, set bIsRecording to true.
	//When the stopKey is pressed, set bIsRecording to false.
	public static void keyPressed(String sKeycode)
	{
		if (sKeycode.equals(startKey) && !bIsRecording)
		{
			bIsRecording = true;
		}
		else if (sKeycode.equals(stopKey) && bIsRecording)
		{
			bIsRecording = false;
		}
	}
	
	//Get the x position of the mouse.
	private static int getMouseX()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	//Get the y position of the mouse.
	private static int getMouseY()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}

}
