package main;

import static methods.Console.ptn;

import screenIO.ScreenData;
import screenIO.ScreenParser;
import screenIO.ScreenReader;
import screenIO.Screenshot;
import screenIO.Timer;

public class ScreenTimer 
{
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
			ptn("Current Data:");
			ptn("Average Screenshot Rate: " + ScreenReader.timer.getAverageTime());
			ptn("Screenshot Range: " + ScreenReader.timer.getFastestTime() + " - " + ScreenReader.timer.getSlowestTime());
			ptn();
			ptn("Average Parse Rate: " + ScreenParser.timer.getAverageTime());
			ptn("Parse Range: " + ScreenParser.timer.getFastestTime() + " - " + ScreenParser.timer.getSlowestTime());
			ptn("Parse Delay Count: " + ScreenParser.iNumDelays);
			ptn();
			ptn();
			ScreenReader.resetData();
			ScreenParser.resetData();
		}
	}

}
