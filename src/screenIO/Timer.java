package screenIO;

public class Timer 
{
	public static final long START_TIME = System.currentTimeMillis();
	
	private int iNumTimes = 0;
	private int iTotalTime = 0;
	private int iSlowestTime;
	private int iFastestTime;
	
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
	
	public double getAverageTime()
	{
		if (iNumTimes == 0) return -1;
		return iTotalTime / (double) iNumTimes;
	}
	
	public int getFastestTime()
	{
		return iFastestTime;
	}
	
	public int getSlowestTime()
	{
		return iSlowestTime;
	}
}
