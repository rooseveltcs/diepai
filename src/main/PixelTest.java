package main;

import static methods.PrimativeMethods.isCloseTo;

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
	private static RobotManager robotManager = new RobotManager();
	private static boolean bIsRecording = false;
	private static String startKey = "1";
	private static String stopKey = "2";
	
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
	
	public static ArrayList<Color> anylizeColors(BufferedImage image, int iColorMargin, int iDisplayScale)
	{
		ArrayList<Color> colors = new ArrayList<>();
		
		for (int x = 0; x < image.getWidth(); x++)
		{
			INNER_FOR:
			for (int y = 0; y < image.getHeight(); y++)
			{
				Color pixel = new Color(image.getRGB(x, y));
				for (Color color : colors)
				{
					if (isColor(pixel, color, iColorMargin))
					{
						continue INNER_FOR;
					}
				}
				colors.add(pixel);
			}
		}
		
		class myJPanel extends JPanel
		{
			public void paintComponent(Graphics g)
			{
				int iXCoord = 0;
				int iYCoord = 0;
				for (Color color : colors)
				{
					if (iXCoord + iDisplayScale > ScreenData.SCREEN_WIDTH)
					{
						iXCoord = 0;
						iYCoord += iDisplayScale;
					}
					g.setColor(color);
					g.fillRect(iXCoord, iYCoord, iDisplayScale, iDisplayScale);
					iXCoord += iDisplayScale;
				}
			}
		}
		JFrame frame = new JFrame();
		frame.setSize(ScreenData.SCREEN_WIDTH, ScreenData.SCREEN_HEIGHT);
		frame.add(new myJPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return colors;
	}
	
	private static boolean isColor(Color color, Color targetColor, int iMargin)
	{
		if (!isCloseTo(color.getRed(), targetColor.getRed(), iMargin)) return false;
		if (!isCloseTo(color.getGreen(), targetColor.getGreen(), iMargin)) return false;
		if (!isCloseTo(color.getBlue(), targetColor.getBlue(), iMargin)) return false;
		return true;
	}
	
	public static void mouseClicked()
	{
		if (bIsRecording)
		{
			Screenshot screenshot = robotManager.takeScreenshot();
			System.out.println(screenshot.getPixel(getMouseX(), getMouseY()));
		}
	}
	
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
	
	private static int getMouseX()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getX();
	}
	
	private static int getMouseY()
	{
		return (int) MouseInfo.getPointerInfo().getLocation().getY();
	}

}
