package methods;

public class PrimativeMethods 
{
	public static boolean isCloseTo(int i1, int i2, int iMargin)
	{
		if (Math.abs(i1 - i2) <= iMargin) return true;
		return false;
	}
}
