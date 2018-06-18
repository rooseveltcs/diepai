package screenIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

public class ScreenData 
{
	public static final int SCREEN_WIDTH;
	public static final int SCREEN_HEIGHT;
	
	public static final int X_CENTER;
	public static final int Y_CENTER;
	public static final Point CENTER;
	
	public static boolean bDisplayShapes = false;
	public static ShapeDisplay shapeDisplay = new ShapeDisplay();
	static
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		SCREEN_WIDTH = screenSize.width;
		SCREEN_HEIGHT = screenSize.height;
		
		X_CENTER = ScreenData.SCREEN_WIDTH / 2;
		Y_CENTER = ScreenData.SCREEN_HEIGHT / 2;
		CENTER = new Point(X_CENTER, Y_CENTER);
		if (bDisplayShapes) shapeDisplay.setupDisplay();
	}
	
	public static ShapeList shapes = new ShapeList();
	
	public static void updateShapes(ShapeList newShapes)
	{
		//System.out.println("UPDATING");
		ScreenData.shapes = newShapes;
		if (bDisplayShapes) 
		{	
			shapeDisplay.repaint();
			//sleep(500);
		}
	}
	
	public static void waitForUpdate(int ms)
	{
		long lCurrentScreenshotTime = shapes.getOriginTime();
		while (shapes.getOriginTime() == lCurrentScreenshotTime)
		{
			sleep(ms);
		}
	}
	
	public static void waitForUpdate()
	{
		long lCurrentScreenshotTime = shapes.getOriginTime();
		while (shapes.getOriginTime() == lCurrentScreenshotTime)
		{
			sleep(10);
		}
	}
	
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
