package screenIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import main.Main;

public class ScreenData 
{
	//This class stores data about the screen, including the final shape data for each screenshot.
	
	public static final int SCREEN_WIDTH;
	public static final int SCREEN_HEIGHT;
	
	public static final int X_CENTER;
	public static final int Y_CENTER;
	public static final Point CENTER;
	
	public static ShapeDisplay shapeDisplay = new ShapeDisplay();
	//This static block sets the screen height width, and center.
	//It also starts the shapeDisplay if Main.bDisplayShapes is set to true.
	static
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = screenSize.width;
		SCREEN_HEIGHT = screenSize.height;
		
		X_CENTER = ScreenData.SCREEN_WIDTH / 2;
		Y_CENTER = ScreenData.SCREEN_HEIGHT / 2;
		CENTER = new Point(X_CENTER, Y_CENTER);
		if (Main.bDisplayShapes) shapeDisplay.setupDisplay();
	}
	
	//The current list of shapes.
	public static ShapeList shapes = new ShapeList();
	
	//Update the shapes. This removes the previous ShapeList object.
	//This object can still be used if other classes have a reference to it. 
	//If Main.bDisplayShapes is set to true, update the shape display. 
	public static void updateShapes(ShapeList newShapes)
	{
		ScreenData.shapes = newShapes;
		if (Main.bDisplayShapes) 
		{	
			shapeDisplay.repaint();
		}
	}
	
	//Pause the current thread until the next shape update. Pause interval is specified.
	public static void waitForUpdate(int ms)
	{
		long lCurrentScreenshotTime = shapes.getOriginTime();
		while (shapes.getOriginTime() == lCurrentScreenshotTime)
		{
			sleep(ms);
		}
	}
	
	//Pause the current thread until the next shape update. Use default pause interval.
	public static void waitForUpdate()
	{
		long lCurrentScreenshotTime = shapes.getOriginTime();
		while (shapes.getOriginTime() == lCurrentScreenshotTime)
		{
			sleep(10);
		}
	}
	
	//Pause the current thread.
	public static void sleep(int ms)
	{
		try 
		{
			Thread.sleep(ms);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}
