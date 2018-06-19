package aiming;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import screenIO.RobotManager;
import screenIO.ScreenData;
import screenIO.Shape;
import screenIO.ShapeData;
import screenIO.ShapeList;
import tankInfo.TankTest;

public class AimControl
{
	//This class is used to control aiming while playing the game
	//by moving the mouse to various positions.
	private static RobotManager robotManager = new RobotManager();
	private static String startKey = "openbracket";
	private static String stopKey = "closebracket";
	private static boolean bIsRunning = false;
	private static Thread runningThread;
	
	//Start with a thread. 
	private static void startWithThread()
	{
		runningThread = new Thread(new Runnable()
		{
			public void run()
			{
				start();
			}
		});
		runningThread.start();
	}
	
	//This method controls where the program aims. 
	//The program finds the shape on the screen with the highest value
	//that is closest to the player. 
	//It then moves the mouse to the center of that shape.
	//It continues to execute continuously until the stop key is pressed. 
	private static void start()
	{
		while (true)
		{
			ShapeList shapes = ScreenData.shapes;
			
			if (shapes.hasShapeWithColor(ShapeData.PENTAGON_COLOR))
			{
				ShapeList pentagons = shapes.getShapesWithColor(ShapeData.PENTAGON_COLOR);
				Shape target = pentagons.getClosestShapeTo(ScreenData.CENTER);
				robotManager.moveMouseTo(target.getX(), target.getY());
			}
			else if (shapes.hasShapeWithColor(ShapeData.TRIANGLE_COLOR))
			{
				ShapeList triangles = shapes.getShapesWithColor(ShapeData.TRIANGLE_COLOR);
				Shape target = triangles.getClosestShapeTo(ScreenData.CENTER);
				robotManager.moveMouseTo(target.getX(), target.getY());
			}
			else if (shapes.hasShapeWithColor(ShapeData.SQUARE_COLOR))
			{
				ShapeList squares = shapes.getShapesWithColor(ShapeData.SQUARE_COLOR);
				Shape target = squares.getClosestShapeTo(ScreenData.CENTER);
				robotManager.moveMouseTo(target.getX(), target.getY());
			}
			ScreenData.waitForUpdate();
		}
	}
	
	//When the startKey is pressed, set bIsRunning to true and start with thread.
	//When the stopKey is pressed, set bIsRunning to false and stop the running thread.
	public static void keyPressed(String sKeycode)
	{
		if (sKeycode.equals(startKey) && !bIsRunning)
		{
			startWithThread();
			bIsRunning = true;
		}
		else if (sKeycode.equals(stopKey) && bIsRunning)
		{
			//TODO figure out how to do this without a deprecated method.
			runningThread.stop();
			bIsRunning = false;
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
