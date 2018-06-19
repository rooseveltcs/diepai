package main;

import screenIO.ScreenData;
import screenIO.ScreenParser;
import screenIO.ScreenReader;
import screenIO.Screenshot;
import screenIO.Timer;

public class ScreenTimer 
{
	//THIS CLASS IS FOR DEBUGGING
	//This class is used to test how fast the screen reader is running.
	
	//Start this class with a thread. 
	public static void start()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				printData();
			}
		}).start();
	}
	
	//Print data about the current screenshot rate and screenshot parse rate.
	//Print this data every 10 seconds.
	private static void printData()
	{
		while (true)
		{
			try 
			{
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			System.out.println("Current Data:");
			System.out.println("Average Screenshot Rate: " + ScreenReader.timer.getAverageTime());
			System.out.println("Screenshot Range: " + ScreenReader.timer.getFastestTime() + " - " + ScreenReader.timer.getSlowestTime());
			System.out.println();
			System.out.println("Average Parse Rate: " + ScreenParser.timer.getAverageTime());
			System.out.println("Parse Range: " + ScreenParser.timer.getFastestTime() + " - " + ScreenParser.timer.getSlowestTime());
			System.out.println("Parse Delay Count: " + ScreenParser.iNumDelays);
			System.out.println();
			System.out.println();
			ScreenReader.resetData();
			ScreenParser.resetData();
		}
	}

}
