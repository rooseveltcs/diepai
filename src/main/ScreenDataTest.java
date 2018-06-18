package main;

import screenIO.RobotManager;
import screenIO.ScreenData;
import screenIO.Shape;
import screenIO.ShapeData;
import screenIO.ShapeList;

public class ScreenDataTest 
{
	public static RobotManager rm = new RobotManager();
	public static void startWithThread()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				startTest();
			}
		}).start();
	}
	
	public static void startTest()
	{
		while (true)
		{
			sleep(4000);
			while (ScreenData.shapes.size() == 0)
			{
				System.out.println(ScreenData.shapes.size());
				sleep(200);
			}
			ShapeList shapes = ScreenData.shapes.getShapesWithColor(ShapeData.SQUARE_COLOR);
			shapes.printShapes();
			for (int i = 0; i < shapes.size(); i++)
			{
				Shape shape = shapes.get(i);
				rm.moveMouseTo(shape.getX(), shape.getY());
				sleep(2000);
			}
		}
	}
	
	public static void sleep(int ms)
	{
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
