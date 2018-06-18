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
import screenIO.ShapeTracker;
import tankInfo.TankTest;

import static methods.Console.*;

public class AimControl
{
	private static RobotManager robotManager = new RobotManager();
	private static String startKey = "openbracket";
	private static String stopKey = "closebracket";
	private static boolean bIsRunning = false;
	private static Thread runningThread;
	
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
	
	/*private static void start()
	{
		TankTest.setEnemyColors();
		while (true)
		{
			ShapeList shapes = ScreenData.shapes;
			ShapeList enemyTanks = shapes.getShapesWithColors(TankTest.enemyColors);
			enemyTanks = enemyTanks.getShapesOutsideRadius(ShapeData.MIN_TANK_RADIUS);
			if (enemyTanks.size() > 0)
			{
				Shape target = enemyTanks.getClosestShapeTo(ScreenData.CENTER);
				robotManager.moveMouseTo(target.getX(), target.getY());
			}
			else
			{
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
			}
			ScreenData.waitForUpdate();
		}
	}*/
	
	public static void keyPressed(String sKeycode)
	{
		if (sKeycode.equals(startKey) && !bIsRunning)
		{
			startWithThread();
			bIsRunning = true;
		}
		else if (sKeycode.equals(stopKey) && bIsRunning)
		{
			runningThread.stop();
			bIsRunning = false;
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
