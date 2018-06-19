package screenIO;

public class Timer 
{
	//THIS CLASS IS FOR DEBUGGING
	//This class is used to time how long code takes to execute.
	//This class is used in main.ScreenTimer to record information about 
	//the screenshot rate and parse rate of the program.
	
	private int iNumTimes = 0;
	private int iTotalTime = 0;
	private int iSlowestTime;
	private int iFastestTime;
	
	//Add a time measurement to the timer.
	//This updates the slowest time, fastest time, number of times, and total time.
	public void addTime(int iTime)
	{
		if (iNumTimes == 0)
		{
			iSlowestTime = iTime;
			iFastestTime = iTime;
		}
		iNumTimes++;
		iTotalTime += iTime;
		if (iTime > iSlowestTime) iSlowestTime = iTime;
		if (iTime < iFastestTime) iFastestTime = iTime;
	}
	
	//Get the average time.
	public double getAverageTime()
	{
		if (iNumTimes == 0) return -1;
		return iTotalTime / (double) iNumTimes;
	}
	
	//Get the fastest time.
	public int getFastestTime()
	{
		return iFastestTime;
	}
	
	//Get the slowest time.
	public int getSlowestTime()
	{
		return iSlowestTime;
	}
}
